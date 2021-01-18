package com.anttij.biblereader20;

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

    private Context           mContext;
    private ArrayList<String> mStringList;

    // ---------------------------------------------------------
    // - Constructor
    // ---------------------------------------------------------

    public VP2Adapter(Context context, ArrayList<String> stringList)
    {
        mContext    = context;
        mStringList = stringList;
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
        holder.tvTitle.setText( mStringList.get( position ) );
    }

    @Override
    public int getItemViewType(int position)
    {
        mStringList.get( position );
    }

    /**
     * Returns the size of the mViewArrayList
     *
     * @return
     */
    @Override
    public int getItemCount()
    {
        return mStringList.size();
    }

    // ---------------------------------------------------------------------------------------
    // - Create an inner class in the Adapter class - the inner class is a child class of the
    // - Not Yet subclassed Adapter
    // ---------------------------------------------------------------------------------------

    public class ViewHolderAdapter extends RecyclerView.ViewHolder
    {
        // -------------------------------------
        // - The View Title (TextView) +
        // - Inner class constructor
        // -------------------------------------

        TextView tvTitle;

        public ViewHolderAdapter(@NonNull View itemView)
        {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.viewpager_title);
        }
    }
}
