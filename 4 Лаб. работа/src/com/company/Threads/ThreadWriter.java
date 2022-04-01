package com.company.Threads;

import com.company.Library;

public class ThreadWriter extends Thread
{
    Library lib;
    int begin=10;
    int end = 130;

    public ThreadWriter(Library library) {this.lib = library;}

    public void run ()
    {
        for (int i =0;i<lib.getSize();i++)
        {
            int number = begin + (int) (Math.random() * 130);
            lib.SetPageCount(i, number);
            System.out.println("Write: " + number + " to position " + i + ". Current thread name: " + Thread.currentThread().getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
