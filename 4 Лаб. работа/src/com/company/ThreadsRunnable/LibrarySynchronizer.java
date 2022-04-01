package com.company.ThreadsRunnable;

import com.company.Library;

public class LibrarySynchronizer {

    private Library object;
    private boolean lockW = false;
    private boolean lockR = true;
    private int indexW = 0;
    private int indexR = 0;
    private int value = 2;
    private int begin=10;
    private int end = 130;

    public LibrarySynchronizer(Library object){
        this.object = object;
    }

    public int getIndexW() {
        return indexW;
    }
    public int getIndexR() {
        return indexR;
    }
    public int getObjectLong() {
        return object.getSize();
    }
    public boolean getLockW() {
        return lockW;
    }
    public boolean getLockR() {
        return lockR;
    }

    public void read() {
        synchronized(object) {
            System.out.printf("Read: " + object.GetPageCount(indexR) + " from position " + indexR + ". Current thread name: " + Thread.currentThread().getName() + "\n");
            indexR++;

        }
        this.lockR = true;
        this.lockW = false;
    }

    public void write() {
        synchronized(object) {
            int number = begin + (int) (Math.random() * 130);
            object.SetPageCount(indexW, number);
            System.out.printf("Write: " + number + " to position " + indexW + ". Current thread name: " + Thread.currentThread().getName() + "\n");
            indexW++;
        }
        this.lockW = true;
        this.lockR = false;

    }
}
