package com.company;

import org.json.JSONObject;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.apache.commons.text.StringEscapeUtils.unescapeJava;


public class Main {

    public static void main(String[] args) {
        convertUTF("new.json");
    }

    private static void convertUTF(String filename) {
        String textFromJSON = "";
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                textFromJSON = textFromJSON.concat(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pls = unescapeJava(textFromJSON);
        System.out.println(pls);

        ByteBuffer s = StandardCharsets.ISO_8859_1.encode(pls);
        CharBuffer t = StandardCharsets.UTF_8.decode(s);
        char[] c = t.array();

        System.out.println(c);

        while(c[c.length-1] != '}'){
            char[] d = new char[c.length-1];
            System.arraycopy(c, 0, d, 0, d.length);
            c = d;
        }
        System.out.println(c);

        String cs = new String(c);

        JSONObject json = new JSONObject(cs);
        String get = json.getString("text");
        System.out.println(get);

    }
}
