package com.adithyasairam.javautils.annotations.processing.changeableprocessor;

import com.adithyasairam.javautils.annotations.processing.base.BaseAnnotatedClass;
import com.adithyasairam.javautils.annotations.Changeable;

import java.io.PrintStream;

/**
 * Created by Adi on 8/30/2015.
 */
public class ChangeableClass extends BaseAnnotatedClass {
    private Changeable changeable;

    public ChangeableClass(Changeable c) { changeable = c; }

    public void processAnnotation(PrintStream printStream) {
        printStream.println("Source: " + changeable.source());
        printStream.println("Author: " + changeable.createdBy());
        printStream.println("Code type: " + changeable.type());
        printStream.println("Priority: " + changeable.priority());
        printStream.println("When to change: " + changeable.when());
        printStream.println("Change type: " + changeable.changeType());
        printStream.println(); //Clean Output
    }
}
