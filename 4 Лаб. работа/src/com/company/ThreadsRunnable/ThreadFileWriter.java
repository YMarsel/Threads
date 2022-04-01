package com.company.ThreadsRunnable;

import java.io.IOException;

public class ThreadFileWriter implements Runnable
{
    ThreadFileSynchronized object;

    public ThreadFileWriter(ThreadFileSynchronized object) {
        this.object = object;
    }

    @Override
    public void run() {
        System.out.printf("\n\n-----------------------------\n����� %s �������...\n", Thread.currentThread().getName());
        int count = 0;
        String arg = "";
        try {
            while(count != 10) {
                if(object.getLockW()==false) {
                    arg = arg+count;
                    object.write(arg);
                    count++;
                }
                Thread.sleep(500);
            }
            Thread.sleep(500);
        }
        catch(InterruptedException e) {
            System.out.println("����� ��� �������.");
        }
        catch (IOException e) {
            System.out.println("����� ��� �������.");
        }
        System.out.printf("\n\n����� %s ����������..", Thread.currentThread().getName());
    }
}
