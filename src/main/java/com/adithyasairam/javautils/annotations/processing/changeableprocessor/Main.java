package com.adithyasairam.javautils.annotations.processing.changeableprocessor;

import com.adithyasairam.javautils.annotations.Changeable;

/**
 * Created by Adi on 8/30/2015.
 */
@Changeable(source = Main.class)
public class Main {
    public static void main(String[] args) {
        try {
            Class[] classes = new Class[1];
            classes[0] = Main.class;
            ChangeableProcessor changeableProcessor = new ChangeableProcessor(classes);
            changeableProcessor.run();
        }
        catch(Exception e) { e.printStackTrace(); }
    }
}
