package com.biblereader;

import android.content.Intent;
import android.os.Bundle;

import com.biblereader.db.DatabaseReader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.biblereader.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int mPage;
    public FloatingActionButton  mFab;
    public DatabaseReader        mDbReader;
    private static ArrayList<String> mBooknames;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mDbReader = new DatabaseReader(this);
        mPage = 1;

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        // -------------------------------------------
        // - Create custom view pager transition
        // -------------------------------------------

        viewPager.setPageTransformer
        (
            false,
            (page, position) ->
            {
                mPage = page.getId();
                final float normalized = Math.abs(Math.abs(position) - 1);

                page.setRotationY((float) (position * -Math.sin(normalized)*15));


            }
        );


        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        mFab = findViewById(R.id.main_fab);

        mFab.setOnClickListener(v -> {

            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra("viewPage", mPage);
            startActivityForResult(intent, mPage);

        });
    }

    public DatabaseReader getDbReader()
    {
        return mDbReader;
    }
    public void loadBookNames(ArrayList<String> list)
    {
        mBooknames = list;
    }

    public ArrayList<String> getBooknames()
    {
        return mBooknames;
    }

    public String getBook(int id)
    {
        return mBooknames.get(id);
    }
}