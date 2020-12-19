package com.biblereader;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ChapterListAdapter implements ListAdapter {

    private ArrayList<Verse> mVerseList;
    private final Activity   mContext;

    /**
     * BookmarkListAdapter constructor
     *
     * @param context
     * @param chapter
     */
    public ChapterListAdapter(Activity context, Chapter chapter)
    {
        mContext   = context;
        mVerseList = chapter.getVerseList();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) { }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) { }

    @Override
    public int getCount()
    {
        return mVerseList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mVerseList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    /**
     * Creates a custom ListView item from layout 'bookmark_row'
     *
     * @param  position
     * @param  rowView
     * @param  parent
     * @return View
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View rowView, @NonNull ViewGroup parent)
    {
        Verse verse = mVerseList.get(position);

        if (rowView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            rowView = inflater.inflate(R.layout.verse_row, null);

            TextView number  = rowView.findViewById(R.id.verse_row_number);
            TextView content = rowView.findViewById(R.id.verse_row_content);

            number.setText(verse.getNumberAsString());
            content.setText(verse.content);
        }

        return rowView;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        return mVerseList.size();
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return false;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}
