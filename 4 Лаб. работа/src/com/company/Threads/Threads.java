package com.company.Threads;

import com.company.Library;

//lab 5 task 1
public class Threads
{
    Library library;
    ThreadWriter writer;
    ThreadReader reader;

    public Threads(Library lib)
    {
        this.library = lib;
        writer = new ThreadWriter(library);
        reader = new ThreadReader(library);
    }

    public void WR_Start()
    {
        writer.start();
        reader.start();
    }
}
