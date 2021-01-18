package com.biblereader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VP2Adapter extends RecyclerView.Adapter<VP2Adapter.ViewHolderAdapter>
{
    // --------------------------------------------------------
    // - My ViewPager2 Adapter class' properties
    // --------------------------------------------------------

    private Context mContext;
    //private ArrayList<String> mStringList;
    private Book    mBook;
    private int     mChapterAmount;

    // ---------------------------------------------------------
    // - Constructor
    // ---------------------------------------------------------

    public VP2Adapter()
    {
        mContext = null;
        mBook    = null;
        mChapterAmount = -1;
    }

    public VP2Adapter(Context context, Book book)
    {
        mContext = context;
        mBook    = book;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(viewType, parent, false);

        return new ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position)
    {
        holder.setChapter( mBook.getChapter( position ) );
    }


    /**
     * Returns the size of the mViewArrayList
     *
     * @return
     */
    @Override
    public int getItemCount()
    {
        return mBook.getChapterCount();
    }

    // ---------------------------------------------------------------------------------------
    // - Create an inner class in the Adapter class - the inner class is a child class of the
    // - Not Yet subclassed Adapter
    // ---------------------------------------------------------------------------------------

    public class ViewHolderAdapter extends RecyclerView.ViewHolder
    {

        private ListView mListView;

        public ViewHolderAdapter(@NonNull View itemView)
        {
            super(itemView);
            mListView = itemView.findViewById(R.id.chapter_in_pager);
        }

        public void setChapter(Chapter chapter)
        {
            ChapterListAdapter listViewAdapter = new ChapterListAdapter((Activity) mContext, chapter);
            mListView.setAdapter(listViewAdapter);
        }

        public ListView getListView()
        {
            return mListView;
        }
    }
}
