package com.biblereader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * PagerFragment, holds a ListView
 */
public class PagerFragment extends Fragment
{
    private Chapter mChapter;

    public PagerFragment(Chapter chapter)
    {
        mChapter = chapter;
    }

    @Override
    public View onCreateView
    (
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    )
    {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }
}