package com.biblereader;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * PagerFragment, holds a ListView
 */
public class ChapterFragment extends Fragment
{
    private Chapter            mChapter;

    public ChapterFragment(Chapter chapter)
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

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        ListView verseListView = view.findViewById(R.id.chapter_fragment_listview);
        verseListView.setAdapter(new ChapterListAdapter(getActivity(), mChapter));
    }
}