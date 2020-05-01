package org.quickstart.javase.java7.coin.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewPath {
    private Path path = null;

    public void init() {
        path = Paths.get("c:\\Temp\\temp");
    }

    public void pathInfo() {
        System.out.println("Number of Nodes:" + path.getNameCount());
        System.out.println("File Name:" + path.getFileName());
        System.out.println("File Root:" + path.getRoot());
        System.out.println("File Parent:" + path.getParent());
    }

    public void pathDeleteOperations() {
        try {
            Files.delete(path);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        NewPath path = new NewPath();
        path.init();
        path.pathInfo();
        path.pathDeleteOperations();
    }

}
