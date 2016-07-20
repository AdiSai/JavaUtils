package com.adithyasairam.javautils.ezio.csv;

import com.adithyasairam.javautils.ezio.base.BaseWriter;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Adi on 9/12/2015.
 */
public class CSVWriter extends BaseWriter{
    final static class CSVWritingPreference extends WritingPreference{
        public static String newLineSeperator = "\n";
        public static String valueSeperator = ",";
        public static boolean appendToFile = true;
    }

    public CSVWriter(File out) { super(out); }

    public void writeArray(String[] contents) {
        try {
            int nullCount = 0;
            for (String s : contents) { if (s == null) { nullCount++; } }
            for (int i = 0; i < contents.length; i++) {
                if (contents[i] != null) {
                    write(contents[i]);
                    if (i < contents.length - nullCount - 1) { write(CSVWritingPreference.valueSeperator); }
                }
            }
            writeAndFlush(CSVWritingPreference.newLineSeperator);
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    public static String[] generateHeaders(Object object) {
        try {
            Field[] fields = object.getClass().getFields();
            String[] headers = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (Modifier.isTransient(fields[i].getModifiers())) { /*do nothing */ } //Skip generation of a transient Object's header
                else { headers[i] = fields[i].getName(); }
            }
            return headers;
        }
        catch(Exception e) { e.printStackTrace(); }
        return null;
    }
}
