package com.biblereader;

import java.util.ArrayList;

public class Book
{
    private String             title;
    private ArrayList<Chapter> chapterList;

    public Book()
    {
        this.title = "";
        this.chapterList = new ArrayList<>();
    }

    public Book(String title, ArrayList<Chapter> chapterList)
    {
        this.title       = title;
        this.chapterList = chapterList;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setChapterList(ArrayList<Chapter> chapterList)
    {
        this.chapterList = chapterList;
    }

    public String getTitle()
    {
        return this.title;
    }

    public Chapter getChapter(int index)
    {
        return this.chapterList.get(index);
    }

    public ArrayList<Chapter> getChapterList()
    {
        return chapterList;
    }

    public int getChapterCount()
    {
        return this.chapterList.size();
    }
}
