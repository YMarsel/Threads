package com.company.ThreadsRunnable;

import com.company.FlowInterface;

import java.io.IOException;

public class ThreadFileReader implements Runnable {

    ThreadFileSynchronized object;

    public ThreadFileReader(ThreadFileSynchronized object) {
        this.object = object;
    }

    @Override
    public void run() {
        //System.out.printf("����� %s �������...\n", Thread.currentThread().getName());
        int count = 0;

        try {
            while(count != 10) {
                if(object.getLockR()==false) {
                    object.read();
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
        }
    }
}
