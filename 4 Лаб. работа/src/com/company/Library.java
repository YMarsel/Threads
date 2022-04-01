package com.company;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Library
{
    String GetName();

    int getSize();

    boolean SameType(Object obj);

    void SetName(String name) throws ValidationException;

    void SetPagesArray(int[] array);

    void SetPageCount(int pageNumber, int value);

    void SetCountOfUnquotedPages(int count);

    void Output(OutputStream out) throws IOException;

    void Write(Writer out) throws IOException;

    int[] GetPagesArray();

    int GetPageCount(int pageNumber);

    int QuotedPages();

    int GetCountOfUnquotedPages();
}
