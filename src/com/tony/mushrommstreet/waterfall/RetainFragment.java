package com.tony.mushrommstreet.waterfall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * A simple non-UI Fragment that stores a single Object and is retained over configuration changes.
 * In this sample it will be used to retain the ImageCache object.
 */
public class RetainFragment extends Fragment {
    private static final String TAG = "RetainFragment";
    private Object mObject;

    /**
     * Empty constructor as per the Fragment documentation
     */
    public RetainFragment() {}

    /**
     * Locate an existing instance of this Fragment or if not found, create and
     * add it using FragmentManager.
     *
     * @param fm The FragmentManager manager to use.
     * @return The existing instance of the Fragment or the new instance if just
     *         created.
     */
    public static RetainFragment findOrCreateRetainFragment(FragmentManager fm) {
        // Check to see if we have retained the worker fragment.
        RetainFragment mRetainFragment = (RetainFragment) fm.findFragmentByTag(TAG);

        // If not retained (or first time running), we need to create and add it.
        if (mRetainFragment == null) {
            mRetainFragment = new RetainFragment();
            fm.beginTransaction().add(mRetainFragment, TAG).commit();
        }

        return mRetainFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make sure this Fragment is retained over a configuration change
        setRetainInstance(true);
    }

    /**
     * Store a single object in this Fragment.
     *
     * @param object The object to store
     */
    public void setObject(Object object) {
        mObject = object;
    }

    /**
     * Get the stored object.
     *
     * @return The stored object
     */
    public Object getObject() {
        return mObject;
    }

}
