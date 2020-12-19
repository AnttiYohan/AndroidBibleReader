package com.biblereader;

import java.util.ArrayList;

public class Chapter
{
    public ArrayList<Verse> verseList;
    public int              number;

    public Chapter()
    {
        verseList = new ArrayList<>();
        number    = 0;
    }

    public Chapter(ArrayList<Verse> list, int n)
    {
        verseList = list;
        number    = n;
    }

    public void setVerseList(ArrayList<Verse> list)
    {
        verseList = list;
    }

    public void setNumber(int n)
    {
        number = n;
    }

    public ArrayList<Verse> getVerseList()
    {
        return verseList;
    }

    public int getNumber()
    {
        return number;
    }
}
