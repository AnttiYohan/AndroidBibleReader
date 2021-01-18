package com.biblereader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BookGridAdapter extends BaseAdapter
{
    protected Context               mContext;
    protected ArrayList<BookModel>  mList;

    /**
     * Initialize the books in the constructor
     *
     */
    public BookGridAdapter(Context context, ArrayList<BookModel> list)
    {
        mContext = context;
        mList    = list;
    }


    /**
     * Returns the amount of list elements
     *
     * @return int
     */
    @Override
    public int getCount()
    {
        return mList.size();
    }

    /**
     * Return the BookModel of the index form the mList
     *
     * @param  position
     * @return BookModel
     */
    @Override
    public Object getItem(int position)
    {
        return mList.get(position);
    }

    /**
     * Returns the 'id' property of the BookModel at this index
     *
     * @param  position int
     * @return int
     */
    @Override
    public long getItemId(int position)
    {
        return mList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if ( view == null)
        {
            LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.book_cell, null);
        }

        TextView bookNameView = view.findViewById(R.id.book_name_textview);
        bookNameView.setText(mList.get(position).getName());
        return view;
    }
}
