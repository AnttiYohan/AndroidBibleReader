package com.biblereader;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FPAdapter extends FragmentStateAdapter
{
    // ---------------------------------------------
    // -
    // ---------------------------------------------

    private ArrayList<Fragment> mFragmentList;

    // ---------------------------------------------
    // -
    // ---------------------------------------------

    public FPAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

    }

    public FPAdapter(@NonNull @NotNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentList) {
        super(fragmentActivity);
        mFragmentList = fragmentList;
    }

    public void setFragmentList(ArrayList<Fragment> fragmentList) {
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount()
    {
        return mFragmentList.size();
    }
}
