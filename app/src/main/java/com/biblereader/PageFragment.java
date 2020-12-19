package com.biblereader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.biblereader.db.DatabaseReader;
import com.biblereader.ui.main.PageViewModel;
import com.biblereader.ui.main.PlaceholderFragment;

import java.util.ArrayList;

public class PageFragment extends Fragment {

    protected static final String LOGTAG = "PageFragment";

    private static final String ARG_SECTION_NUMBER = "Chapter";

    private PageViewModel pageViewModel;
    protected int mChapter;

    public static PageFragment newInstance(int index) {


        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
            mChapter = index;
        }

        pageViewModel.setIndex(index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chapter_layout, container, false);

        DatabaseReader dbReader = new DatabaseReader(getContext());

        ArrayList<String> bookList = dbReader.getBooks();

        for (String book : bookList)
            Log.d(LOGTAG, "Book: " + book);
        int bookId = 1;
        int chapterId = mChapter;
        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append(bookList.get(bookId))
                    .append(" ")
                    .append(chapterId);

        Chapter chapter = dbReader.getChapter(bookId, chapterId);
        TextView titleView = root.findViewById(R.id.chapter_layout_title);
        titleView.setText(titleBuilder.toString());
        ListView listView = root.findViewById(R.id.chapter_layout_listview);

        ChapterListAdapter adapter = new ChapterListAdapter(getActivity(), chapter);

        listView.setAdapter(adapter);


        return root;
    }

}