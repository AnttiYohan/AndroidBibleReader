package com.biblereader;

public class Verse {

    public String   content;
    public int      number;

    public Verse()
    {
        number  = 0;
        content = "";
    }

    public Verse(String text)
    {
        number  = 0;
        content = text;
    }

    public Verse(String text, int n)
    {
        number  = n;
        content = text;
    }

    public String getNumberAsString()
    {
        return Integer.toString(number);
    }
}
