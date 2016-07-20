package com.adithyasairam.javautils.ezio.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adi on 9/13/2015.
 */
public class CSVReader {
    private File inFile;
    private BufferedReader bufferedReader;

    public CSVReader(File in) {
        try {
            inFile = in;
            bufferedReader = new BufferedReader(new FileReader(inFile));
        }
        catch(Exception e) { e.printStackTrace(); }
    }

    //TODO implement fully
    public List<Object> read(Class<?> clazz, Class[] types) {
        List<Object> outputList = new ArrayList<Object>();
        return outputList;
    }
}
