package com.biblereader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.biblereader.db.DatabaseReader;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity
{
    protected GridView              bookGridView;
    protected Button                backToMainButton;
    protected ArrayList<BookModel>  mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // ----------------------------------------
        // - Populate the gridview with books from
        // - Bible database
        // ----------------------------------------

        DatabaseReader dbReader = new DatabaseReader(this);

        try {

            // ---------------------------
            // - Attempt to read the books
            // ---------------------------

            mBookList = dbReader.getBooks();

        } catch ( Exception exception ) {

            // --------------------------
            // - Notify the user that no
            // - Books are available
            // --------------------------

        }

        bookGridView     = findViewById(R.id.book_gridview);

        // ------------------------------
        // - If the booklist is populated
        // - Create an adapter and
        // - Display the books in a
        // - GridView
        // ------------------------------

        if ( mBookList.size() > 0 )
        {
            // -----------------------------------
            // - Pick and exit
            // -----------------------------------

            bookGridView.setAdapter(new BookGridAdapter(this, mBookList));
            bookGridView.setOnItemClickListener
            (
                    (parent, view, position, id) -> {

                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.BOOK_NAME_EXTRA, mBookList.get(position).getName());
                        intent.putExtra(MainActivity.BOOK_ID_EXTRA,   mBookList.get(position).getId());
                        setResult(MainActivity.BOOK_PICK, intent);
                        finish();
                    }
            );
        }

        backToMainButton = findViewById(R.id.book_button_back);

    }

    /**
     * Return the the MainActivity
     *
     * @param view
     */
    public void nothing(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}