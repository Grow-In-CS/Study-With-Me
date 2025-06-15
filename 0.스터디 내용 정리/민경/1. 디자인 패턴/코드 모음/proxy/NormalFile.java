package org.example.proxy;

public class NormalFile implements File{
    @Override
    public void read(String fileName) {
        System.out.println("Reading.. " + fileName);
    }
}
