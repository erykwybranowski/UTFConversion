package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String line = "Pawe\u00c5\u0082";
        String textFromJSON = "";
        File file = new File("new.txt");
        try {
            Scanner scanner = new Scanner(file);
            textFromJSON = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties p = new Properties();
        try {
            p.load(new StringReader("key="+textFromJSON));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String f = p.getProperty("key");

        ByteBuffer s = StandardCharsets.ISO_8859_1.encode(f);
        CharBuffer t = StandardCharsets.UTF_8.decode(s);
        char[] c = t.array();
        System.out.println(c);
    }
}
