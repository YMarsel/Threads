package com.company;

import java.io.*;
import java.util.Arrays;

public class MyStaticMethods implements FlowInterface
{
    private FileOutputStream flow;

    public static void outputLibrary(Library lib,OutputStream out) throws IOException
    {
        lib.Output(out);
    }

    public static Library inputLibrary(InputStream in)
    {
        //Считать строку из байтового потока
        String temp;
        temp = in.toString();
        //Разбить на подстроки по "системному" символу "|"
        String[] data = temp.split("\\|");
        //CollectionOfArticles|Name|Int|Array
        if (data[0] == "CollectionOfArticles")
        {
            //CollectionOfArticles(int[] CountOfPages, String CollectionName, int MaxPagesOfAnnot)
            return new CollectionOfArticles(StringToArray(data[4]),data[2],Integer.parseInt(data[3]));
        }
        else if (data[0] == "SeriesOfEssays")
        {
            return new SeriesOfEssays(StringToArray(data[4]),data[2],Integer.parseInt(data[3]));
        }
        else
        {
            return null;
        }
    }

    public static void writeLibrary(Library lib, Writer out) throws IOException {
        lib.Write(out);
    }

    public static Library readLibrary(Reader in)
    {
        String temp;
        temp = in.toString();
        //Разбить на подстроки по "системному" символу "|"
        String[] data = temp.split("\\|");
        //CollectionOfArticles|Name|Int|Array
        if (data[0] == "CollectionOfArticles")
        {
            //CollectionOfArticles(int[] CountOfPages, String CollectionName, int MaxPagesOfAnnot)
            return new CollectionOfArticles(StringToArray(data[4]),data[2],Integer.parseInt(data[3]));
        }
        else if (data[0] == "SeriesOfEssays")
        {
            return new SeriesOfEssays(StringToArray(data[4]),data[2],Integer.parseInt(data[3]));
        }
        else
        {
            return null;
        }
    }

    public static void serializeLibrary(Library library, OutputStream in) throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(in);
        objectOutputStream.writeObject(library);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Library deserializeLibrary(InputStream in) throws IOException, ClassNotFoundException
    {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        Library obj = (Library) objectInputStream.readObject();
        return obj;
    }

    private static int[] StringToArray(String str)
    {
        str = str.replaceAll("[\\[\\], ]", "");
        String[] strArray = str.split("");
        int size = str.length();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++)
        {
            arr[i] = Integer.parseInt(strArray[i]);
        }
        return arr;
    }

    @Override
    public void flowOpen(File file) throws IOException
    {
        this.flow = new FileOutputStream(file);
    }

    @Override
    public void flowClose() throws IOException {
        flow.close();
    }

    @Override
    public void flowOutput(String arg) throws IOException {
        flow.write(arg.getBytes());
    }

    @Override
    public String flowInput(File file) throws IOException {
        FileInputStream flow = new FileInputStream(file);
        String out ="";
        int a;
        while((a = flow.read())!=-1) {
            out = out + (char)a + " ";
        }

        flow.close();
        return out;
    }
}
