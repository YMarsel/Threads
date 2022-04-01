package com.company.ThreadsRunnable;

import com.company.FlowInterface;
import com.company.MyStaticMethods;

import java.io.*;

public class ThreadFileSynchronized
{
    private FlowInterface flowInterface;
    private File file;
    private MyStaticMethods myStaticMethods;
    private boolean lockW = false;
    private boolean lockR = true;

    public ThreadFileSynchronized(FlowInterface object, File file){
        this.flowInterface = object;
        this.file = file;
    }

    public boolean getLockW() {
        return lockW;
    }
    public boolean getLockR() {
        return lockR;
    }

    public void read() throws IOException {
        synchronized(flowInterface) {
            System.out.printf("\n�������� ������ �� �����: %s.",flowInterface.flowInput(file));
        }
        this.lockR = true;
        this.lockW = false;
    }

    public void write(String arg) throws IOException {
        synchronized(flowInterface) {
            flowInterface.flowOpen(file);
            flowInterface.flowOutput(arg);
            System.out.printf("\n���� ������ � ����: %s.",arg);
            flowInterface.flowClose();
        }
        this.lockW = true;
        this.lockR = false;

    }
}
