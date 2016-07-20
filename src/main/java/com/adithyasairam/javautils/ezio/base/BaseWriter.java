package com.adithyasairam.javautils.ezio.base;

import com.adithyasairam.javautils.annotations.Exclude;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Adi on 9/12/2015.
 */
public class BaseWriter{
    public static abstract class WritingPreference {
        public static String newLineSeperator;
        public static String valueSeperator;
        public static boolean appendToFile;
    }

    final static class BaseWritingPreference extends WritingPreference{
        public static String newLineSeperator = "\n";
        public static String valueSeperator = "";
        public static boolean appendToFile = true;
    }

    protected File outFile;
    protected BufferedWriter bufferedWriter;

    public BaseWriter(File out) {
        try {
            outFile = out;
            bufferedWriter = new BufferedWriter(new FileWriter(out, BaseWritingPreference.appendToFile));
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void writeObject(Object object) {
        try {
            Field[] fields = object.getClass().getFields();
            String[] contents = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (fields[i].isAnnotationPresent(Exclude.class)) { /*do nothing */ } //Skip writing of an Excluded Object
                if (Modifier.isTransient(fields[i].getModifiers())) { /*do nothing */ } //Skip writing of a transient Object
                else { contents[i] = fields[i].get(null).toString(); }
            }
            writeArray(contents);
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void writeArray(String[] contents) {
        try {
            int nullCount = 0;
            for (String s : contents) { if (s == null) { nullCount++; } }
            for (int i = 0; i < contents.length; i++) {
                if (contents[i] != null) { write(contents[i]); }
            }
            writeAndFlush(BaseWritingPreference.newLineSeperator);
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void write(String string) {
        try {
            bufferedWriter.write(string);
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void writeAndFlush(String string) {
        try {
            write(string);
            flushStream();
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void flushStream() {
        try {
            bufferedWriter.flush();
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void closeStream() {
        try {
            bufferedWriter.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
