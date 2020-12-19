package com.biblereader.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.biblereader.Chapter;
import com.biblereader.Verse;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.util.ArrayList;

public class DatabaseReader extends SQLiteAssetHelper
{
    protected static final String LOGTAG = "DatabaseReader";
    protected static final String DB_NAME    = "bible.db";
    protected static final int    DB_VERSION = 1;
    protected final        Context         mContext;
    protected              SQLiteDatabase  mDatabase;

    @SuppressLint("SdCardPath")
    public DatabaseReader(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);

        mContext  = context;
        mDatabase = getReadableDatabase();

        Log.d(LOGTAG, "is db open?: " + mDatabase.isOpen());
        Log.d(LOGTAG, "Is integrity ok: " + mDatabase.isDatabaseIntegrityOk());
        Log.d(LOGTAG, "Is readonly: " + mDatabase.isReadOnly());
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }

    @Override
    public synchronized void close()
    {
        if ( mDatabase != null ) mDatabase.close();
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    /**
     * Queries the book names from the database and returns
     * Them in an ArrayList
     *
     * @return
     */
    public ArrayList<String> getBooks()
    {
        ArrayList<String> result = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery
        (
            "SELECT name FROM book",
            null
        );

        cursor.moveToFirst();

        while( ! cursor.isAfterLast() )
        {
            result.add(cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();

        return result;
    }

    /**
     * Returns the book id referenced by its name
     * Or '0' if not found
     *
     * @param book
     * @return
     */
    public int getBookId(String book)
    {
        int result = 0;

        Cursor cursor = mDatabase.rawQuery
        (
            "SELECT id FROM book WHERE name = ?", new String[]{book}
        );

        cursor.moveToFirst();
        result = cursor.getInt(0);
        cursor.close();

        return result;
    }

    /**
     * Get a book by id as an ArrayList of chapters
     *
     * @param  bookId
     * @return ArrayList of Chapter objects
     */
    public ArrayList<Chapter> getBookById(int bookId)
    {
        ArrayList<Chapter> result = new ArrayList<>();

        int chapterCount  = 0;
        int chapterAmount = getChapterAmount( bookId );

        while ( chapterCount++ < chapterAmount )
        {
            result.add(getChapter(bookId, chapterCount));
        }

        return result;
    }


    /**
     * Return a chapter as a Chapter object
     *
     * @param bookId
     * @param chapter
     * @return Chapter
     */
    public Chapter getChapter(int bookId, int chapter)
    {
        Chapter result = new Chapter();

        if (bookId > 0)
        {

            StringBuilder stmt = new StringBuilder("SELECT verse, data FROM _");
            stmt.append(bookId)
                .append(" WHERE chapter = ?");

            Cursor cursor = mDatabase.rawQuery
            (
                stmt.toString(),
                new String[]{ Integer.toString( chapter ) }
            );

            cursor.moveToFirst();

            ArrayList<Verse> verseList = new ArrayList<>();
            int verseIndex = cursor.getColumnIndex( "verse" );
            int dataIndex  = cursor.getColumnIndex( "data" );

            while( ! cursor.isAfterLast() )
            {
                verseList.add
                (
                    new Verse
                    (
                        cursor.getString( dataIndex ),
                        cursor.getInt( verseIndex )
                    )
                );
                cursor.moveToNext();
            }

            cursor.close();

            result.setVerseList( verseList );
            result.setNumber( chapter );
        }

        return result;
    }

    /**
     * Return the verse as a String
     *
     * @param book
     * @param chapter
     * @param verse
     * @return
     */
    public String getVerse(String book, int chapter, int verse)
    {
        String result = "";

        int bookId = getBookId(book);

        if (bookId > 0)
        {
            StringBuilder stmt = new StringBuilder("SELECT data FROM _");
            stmt.append(bookId)
                .append(" WHERE chapter = ? AND verse = ?");

            Cursor cursor = mDatabase.rawQuery
            (
                stmt.toString(),
                new String[] { Integer.toString(chapter), Integer.toString(verse) }
            );

            cursor.moveToFirst();

            result = cursor.getString(0);

            cursor.close();
        }

        return result;
    }

    /**
     * Returns the amount of chapters in a book
     *
     * @param bookId
     * @return
     */
    protected int getChapterAmount(int bookId)
    {
        int result = 0;

        StringBuilder stmt = new
        StringBuilder("SELECT count(DISTINCT chapter) FROM _");
        stmt.append(bookId);

        Cursor cursor = mDatabase.rawQuery(stmt.toString(), null);
        cursor.moveToFirst();

        result = cursor.getInt(0);

        cursor.close();

        return result;
    }

}
