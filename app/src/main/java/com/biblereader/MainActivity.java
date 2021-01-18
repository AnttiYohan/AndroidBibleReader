package com.biblereader;
import android.content.Intent;
import android.os.Bundle;
import com.biblereader.db.DatabaseReader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    // -----------------------------------------
    // - Code for book pick result
    // -----------------------------------------

    public static final String   LOGTAG = "MainActivity";
    public static final int      BOOK_PICK = 7;
    public static final String   BOOK_ID_EXTRA    = "BOOK_ID";
    public static final String   BOOK_NAME_EXTRA  = "BOOK_NAME";

    public int                   mChapter;
    public int                   mBookIndex = 0;
    public String                mBookTitle;
    public Book                  mBook;
    public DatabaseReader        mDbReader;

    public ViewPager2            mViewPager;
    public PagerBookAdapter      mPagerAdapter;
    public TabLayout             mTabLayout;
    public TextView              mBookTitleView;
    public FloatingActionButton  mFab;


    /**
     * Check if a book was picked from the BookActivity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(LOGTAG, "onActivityResult called");

        if ( requestCode == MainActivity.BOOK_PICK && data != null )
        {

            int id  = data.getIntExtra( MainActivity.BOOK_ID_EXTRA, 1 );
            loadBook( id );
        }
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOGTAG, "onCreate called");

        mBookTitleView = findViewById(R.id.main_book_title);
        mDbReader      = new DatabaseReader(this);

        // ------------------------------------------
        // - Set the book index
        // ------------------------------------------

        mBookIndex = 1;

        mViewPager = findViewById(R.id.main_pager);

        mBook         = mDbReader.getBook(mBookIndex);
        mPagerAdapter = new PagerBookAdapter(this, mBook);

        // -------------------------------------------
        // - Create custom view pager transition
        // -------------------------------------------

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer((page, position) -> {
            final float normalized = Math.abs(Math.abs(position) - 1);
            page.setAlpha(1 - position);
            //page.setCameraDistance(.5f + position);
            page.setRotationY(position * -25);
        });

        mTabLayout = findViewById(R.id.main_tablayout);

        TabLayoutMediator mediator = new TabLayoutMediator
        (
            mTabLayout,
            mViewPager,
                true,
                true,
                (tab, position) -> {
                    tab.setText( String.valueOf(position + 1));
                }
        );
        mediator.attach();

        mFab = findViewById(R.id.main_fab);

        mFab.setOnClickListener(v -> {

            Intent intent = new Intent(this, BookActivity.class);
            startActivityForResult(intent, MainActivity.BOOK_PICK);

        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mChapter = position + 1;
                mBookTitleView.setText(mBook.getTitle() + " " + mChapter);
            }
        });

    }

    /**
     * Setup book index
     * @param id
     */
    protected void loadBook(int id)
    {
        mBookIndex = id;
        mBook = mDbReader.getBook(id);
        mPagerAdapter.setBook(mBook);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.scrollTo(0, 0);
    }

    // --------------------------------------------------------
    // - Use this to communicate the chapter into the fragments
    // --------------------------------------------------------

    public interface OnChapterListener {
        void onChapterReceiver(Chapter chapter);
    }
}