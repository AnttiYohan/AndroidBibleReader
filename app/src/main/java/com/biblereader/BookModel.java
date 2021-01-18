package com.biblereader;

public class BookModel
{
    public int    mId;
    public String mName;

    public BookModel(int id, String name)
    {
        mId     = id;
        mName   = name;
    }

    public int getId()
    {
        return mId;
    }

    public String getName()
    {
        return mName;
    }
}
