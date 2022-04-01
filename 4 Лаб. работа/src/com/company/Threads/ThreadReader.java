package com.company.Threads;

import com.company.Library;

public class ThreadReader extends Thread
{
    Library lib;

    public ThreadReader(Library library) {this.lib = library;}

    public void run()
    {
        for (int i=0;i<lib.getSize();i++)
        {
            System.out.println("Read: " + lib.GetPageCount(i) + " from position " + i + ". Current thread name: " + Thread.currentThread().getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
