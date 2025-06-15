package org.example.proxy;

public class FileMain {
    public static void main(String[] args) {
        File securedFile = new NormalFileProxy("security");
        securedFile.read("readme.md");

        File normalFile = new NormalFileProxy("");
        normalFile.read("readme.md");
    }
}
