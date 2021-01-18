package com.biblereader;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PagerBookAdapter extends FragmentStateAdapter
{
    // ---------------------------------------------
    // -
    // ---------------------------------------------

    private Book                       mBook;
    // ---------------------------------------------
    // -
    // ---------------------------------------------

    public PagerBookAdapter(@NonNull @NotNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
        mBook = null;
    }

    public PagerBookAdapter(@NonNull @NotNull FragmentActivity fragmentActivity, Book book)
    {
        super(fragmentActivity);
        mBook = book;
    }

    public void setBook(Book book)
    {
        mBook = book;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position)
    {
        return new ChapterFragment( mBook.getChapter( position ) );
    }

    @Override
    public int getItemCount()
    {
        return mBook.getChapterCount();
    }


}
