package com.company;

import java.io.File;
import java.io.IOException;

public interface FlowInterface
{
    public void flowOpen(File file) throws IOException;
    public void flowClose() throws IOException;
    public void flowOutput (String arg) throws IOException;
    public String flowInput (File file) throws IOException;
}
