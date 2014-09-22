
package com.tony.mushrommstreet.waterfall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.example.mushroomstreet.BuildConfig;


public class DiskLruCache {
	private static final String TAG = "DiskLruCache";
	private static final String CACHE_FILENAME_PREFIX = "cache_";
	private static final int MAX_REMOVALS = 4;
	private static final int INITIAL_CAPACITY = 32;
	private static final float LOAD_FACTOR = 0.75f;

	private final File mCacheDir;
	private int cacheSize = 0;
	private int cacheByteSize = 0;
	private final int maxCacheItemSize = 64; // 64 item default
	private long maxCacheByteSize = 1024 * 1024 * 5; // 5MB default
	private CompressFormat mCompressFormat = CompressFormat.JPEG;
	private int mCompressQuality = 70;

	private final Map<String, String> mLinkedHashMap = Collections.synchronizedMap(new LinkedHashMap<String, String>(INITIAL_CAPACITY,
			LOAD_FACTOR, true));

	private static final FilenameFilter cacheFileFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String filename) {
			return filename.startsWith(CACHE_FILENAME_PREFIX);
		}
	};

	public static DiskLruCache openCache(Context context, File cacheDir, long maxByteSize) {
		if (!cacheDir.exists()) {
			cacheDir.mkdir();
		}

		if (cacheDir.isDirectory() && cacheDir.canWrite() && Utils.getUsableSpace(cacheDir) > maxByteSize) {
			return new DiskLruCache(cacheDir, maxByteSize);
		}

		return null;
	}

	private DiskLruCache(File cacheDir, long maxByteSize) {
		mCacheDir = cacheDir;
		maxCacheByteSize = maxByteSize;
	}

	public void put(String key, Bitmap data) {
		synchronized (mLinkedHashMap) {
			if (mLinkedHashMap.get(key) == null) {
				try {
					final String file = createFilePath(mCacheDir, key);
					if (writeBitmapToFile(data, file)) {
						put(key, file);
						flushCache();
					}
				} catch (final FileNotFoundException e) {
					Log.e(TAG, "Error in put: " + e.getMessage());
				} catch (final IOException e) {
					Log.e(TAG, "Error in put: " + e.getMessage());
				}
			}
		}
	}

	private void put(String key, String file) {
		mLinkedHashMap.put(key, file);
		cacheSize = mLinkedHashMap.size();
		cacheByteSize += new File(file).length();
	}

	private void flushCache() {
		Entry<String, String> eldestEntry;
		File eldestFile;
		long eldestFileSize;
		int count = 0;

		while (count < MAX_REMOVALS && (cacheSize > maxCacheItemSize || cacheByteSize > maxCacheByteSize)) {
			eldestEntry = mLinkedHashMap.entrySet().iterator().next();
			eldestFile = new File(eldestEntry.getValue());
			eldestFileSize = eldestFile.length();
			mLinkedHashMap.remove(eldestEntry.getKey());
			eldestFile.delete();
			cacheSize = mLinkedHashMap.size();
			cacheByteSize -= eldestFileSize;
			count++;
			if (BuildConfig.DEBUG) {
				Log.d(TAG, "flushCache - Removed cache file, " + eldestFile + ", " + eldestFileSize);
			}
		}
	}

	public Bitmap get(String key) {
		synchronized (mLinkedHashMap) {
			final String file = mLinkedHashMap.get(key);
			if (file != null) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "Disk cache hit");
				}
				return BitmapFactory.decodeFile(file);
			} else {
				final String existingFile = createFilePath(mCacheDir, key);
				if (new File(existingFile).exists()) {
					put(key, existingFile);
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "Disk cache hit (existing file)");
					}
					return BitmapFactory.decodeFile(existingFile);
				}
			}
			return null;
		}
	}

	public boolean containsKey(String key) {
		// See if the key is in our HashMap
		if (mLinkedHashMap.containsKey(key)) {
			return true;
		}

		// Now check if there's an actual file that exists based on the key
		final String existingFile = createFilePath(mCacheDir, key);
		if (new File(existingFile).exists()) {
			// File found, add it to the HashMap for future use
			put(key, existingFile);
			return true;
		}
		return false;
	}

	public void clearCache() {
		DiskLruCache.clearCache(mCacheDir);
	}

	public static void clearCache(Context context, String uniqueName) {
		File cacheDir = getDiskCacheDir(context, uniqueName);
		clearCache(cacheDir);
	}

	private static void clearCache(File cacheDir) {
		final File[] files = cacheDir.listFiles(cacheFileFilter);
		for (int i = 0; i < files.length; i++) {
			files[i].delete();
		}
	}

	public static File getDiskCacheDir(Context context, String uniqueName) {

		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !Utils.isExternalStorageRemovable() ? Utils
				.getExternalCacheDir(context).getPath() : context.getCacheDir().getPath();

		return new File(cachePath + File.separator + uniqueName);
	}
	
	public static String createFilePath(File cacheDir, String key) {
		try {
			// Use URLEncoder to ensure we have a valid filename, a tad hacky
			// but it will do for
			// this example
			return cacheDir.getAbsolutePath() + File.separator + CACHE_FILENAME_PREFIX + URLEncoder.encode(key.replace("*", ""), "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			Log.e(TAG, "createFilePath - " + e);
		}

		return null;
	}

	public String createFilePath(String key) {
		return createFilePath(mCacheDir, key);
	}

	public void setCompressParams(CompressFormat compressFormat, int quality) {
		mCompressFormat = compressFormat;
		mCompressQuality = quality;
	}

	private boolean writeBitmapToFile(Bitmap bitmap, String file) throws IOException, FileNotFoundException {

		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(file), Utils.IO_BUFFER_SIZE);
			return bitmap.compress(mCompressFormat, mCompressQuality, out);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
