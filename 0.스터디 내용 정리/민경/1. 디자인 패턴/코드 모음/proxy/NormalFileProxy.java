package org.example.proxy;

public class NormalFileProxy implements File{

    private NormalFile normalFile = new NormalFile();
    private String password = "";

    public NormalFileProxy(String password){
        this.password = password;
    }

    @Override
    public void read(String fileName) {
        if (password.equals("security"))
            normalFile.read(fileName);
        else
            System.out.println("Security File");
    }
}
