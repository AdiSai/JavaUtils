package com.adithyasairam.javautils.ezio.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adi on 9/13/2015.
 */
public class BaseReader {
    public static abstract class ReadingPreference {
        public static String newLineSeperator;
        public static String valueSeperator;
        public static boolean appendToFile;
    }

    final static class BaseReadingPreference extends ReadingPreference{
        public static String newLineSeperator = "\n";
        public static String valueSeperator = "";
        public static boolean appendToFile = true;
    }

    protected File inFile;
    protected BufferedReader bufferedReader;

    public BaseReader(File in) {
        try {
            inFile = in;
            bufferedReader = new BufferedReader(new FileReader(inFile));
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    //TODO implement fully
    public List<Object> read(Class<?> clazz, Class[] types) {
        List<Object> objects = new ArrayList<Object>();
        return objects;
    }
}
