package com.company;

import com.company.Threads.ThreadReader;
import com.company.Threads.ThreadWriter;
import com.company.Threads.Threads;
import com.company.ThreadsRunnable.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static Scanner scan = new Scanner(System.in);
    private static int objectsCount;
    private static ArrayList<Library> Texts = new ArrayList<>();
    private static ArrayList<Library> SameResult = new ArrayList<>();
    private static ArrayList<CollectionOfArticles> Collections = new ArrayList<>();
    private static ArrayList<SeriesOfEssays> Series = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        OutputStream A = new ByteArrayOutputStream();
        FileOutputStream file = new FileOutputStream("C:\\Users\\Зверь\\Desktop\\file.txt");
        Writer B = new CharArrayWriter();
        //byte[] arr = A;
        FillArrayList(1);

        //Threads threads = new Threads(Texts.get(0));
        //threads.WR_Start();


        LibrarySynchronizer librarySynchronizer = new LibrarySynchronizer(Texts.get(0));
        Thread threadWrite = new Thread(new ThreadWriterRunnable(librarySynchronizer));
        Thread threadRead = new Thread(new ThreadReaderRunnable(librarySynchronizer));

        threadWrite.start();
        threadRead.start();

        threadWrite.join();
        threadRead.join();

        //File fileA = new File ("src/Files/ThreadTest.txt");

        //MyStaticMethods myStaticMethods = new MyStaticMethods();

        //ThreadFileSynchronized synchFile = new ThreadFileSynchronized(myStaticMethods, fileA);
        //Thread threadFileWrite = new Thread(new ThreadFileWriter(synchFile),"WriteFileRunnable");
        //Thread threadFileRead = new Thread(new ThreadFileReader(synchFile),"ReadFileRunnable");

        //threadFileWrite.start();
        //threadFileRead.start();

        //try {
        //    threadFileWrite.join();
        //    threadFileRead.join();
        //}
        //catch(InterruptedException e) {
        //
        //}






        //InputStream in = null;
        //MyStaticMethods.serializeLibrary(Texts.get(0),file);
        //FileInputStream inputStream = new FileInputStream("C:\\Users\\Зверь\\Desktop\\file.txt");
        //Texts.add(MyStaticMethods.deserializeLibrary(inputStream));

        //System.out.println(Texts.get(0));
        //System.out.println(Texts.get(1));

    }

    public static void SplitArrayByType(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            if (arr.get(i) instanceof CollectionOfArticles)
                Collections.add((CollectionOfArticles) arr.get(i));
            if (arr.get(i) instanceof SeriesOfEssays)
                Series.add((SeriesOfEssays)arr.get(i));
        }
    }
    public static void ArrayOfSameResults()
    {
        for (int start = 0; start < Texts.size()-1; start++) {
            for (int index = start + 1; index < Texts.size(); index++)
            {
                {
                    if (Texts.get(start).QuotedPages() == Texts.get(index).QuotedPages())
                    {
                        SameResult.add(Texts.get(start));
                        SameResult.add(Texts.get(index));
                    }
                }
            }
        }
    }
    public static void OutputArray(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
    }
    public static void FillArrayList(int count)
    {
        int i;
        for (i=0;i<count;i++)
        {
            GenerateNewObject();
        }
    }
    public static void SetObjectsCount(int count)
    {
        if (!(count < 0))
        {
            objectsCount = count;
        }
        else
        {
            System.out.println("Введено некорректное значение! Количество элементов не может равняться нулю или меньше нуля");
        }
    }
    public static int GetObjectsCount()
    {
        return objectsCount;
    }
    private static void GenerateNewObject()
    {
        String inputType;
        String inputName;
        int inputCount = 0;
        int inputUnquotedPagesCount;

        System.out.println("Какой экземпляр создать? Серию(книг) или коллекцию(статей)? Введите слово Серия или слово Коллекция: ");
            inputType = scan.nextLine();
        System.out.println("Введите имя серии/коллекции: ");
            inputName = scan.nextLine();
        System.out.println("Введите кол-во книг или статей: ");
            inputCount = Integer.parseInt(scan.nextLine());
        System.out.println("Сколько из страниц незначащие (Вводная информация или аннотация):");
        inputUnquotedPagesCount = Integer.parseInt(scan.nextLine());
        int[] temp = new int[inputCount];

        //Заполнение случайными числами от 10 до 130
        int begin=10;
        int end = 130;
        for (int i =0;i<inputCount;i++)
        {
            temp[i] = begin + (int)(Math.random() * 130);
        }

        //Создаём экземпляр нужный пользователю и передаём его в массив интерфейса
        if (inputType.equals("Серия") || inputType.equals("серия") || inputType.equals("СЕРИЯ"))
        {
            Texts.add(new SeriesOfEssays(temp,inputName,inputUnquotedPagesCount));
        }
        if (inputType.equals("Коллекция") || inputType.equals("коллекция") || inputType.equals("КОЛЛЕКЦИЯ"))
        {
            Texts.add(new CollectionOfArticles(temp,inputName,inputUnquotedPagesCount));
        }
    }
}

class ValidationException extends Exception
{
    ValidationException(String errorMessage,Throwable err)
    {
        super(errorMessage, err);
    }
}

class OutOfBounds extends RuntimeException
{
    OutOfBounds(String errorMessage, Throwable err)
    {
        super(errorMessage,err);
    }
}

