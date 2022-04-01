package com.company.ThreadsRunnable;

import static java.lang.Thread.sleep;

public class ThreadWriterRunnable implements Runnable
{
    LibrarySynchronizer object;

    public ThreadWriterRunnable(LibrarySynchronizer obj)
    {
        this.object = obj;
    }

    @Override
    public void run()
    {
        while(object.getIndexW()<object.getObjectLong())
        {
            if(!object.getLockW())
            {
                object.write();
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
