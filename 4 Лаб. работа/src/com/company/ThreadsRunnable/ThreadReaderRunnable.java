package com.company.ThreadsRunnable;

import static java.lang.Thread.sleep;

public class ThreadReaderRunnable implements Runnable
{
    LibrarySynchronizer object;

    public ThreadReaderRunnable(LibrarySynchronizer obj)
    {
        this.object = obj;
    }

    @Override
    public void run()
    {
        while(object.getIndexR()<object.getObjectLong())
        {
            if(object.getLockR()==false)
            {
                object.read();
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
