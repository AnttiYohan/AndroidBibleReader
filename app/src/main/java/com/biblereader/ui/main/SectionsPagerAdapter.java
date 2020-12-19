package com.biblereader.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.biblereader.PageFragment;
import com.biblereader.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PageFragment.newInstance(position + 1);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        StringBuilder builder = new StringBuilder("Ch").append(position);
        return builder.toString();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}