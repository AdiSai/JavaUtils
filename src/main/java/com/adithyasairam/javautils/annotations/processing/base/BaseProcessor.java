package com.adithyasairam.javautils.annotations.processing.base;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adi on 8/30/2015.
 */
public abstract class BaseProcessor {
    private Class[] classes;

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }

    private List<Annotation[]> annotations;

    public List<Annotation[]> getAnnotations() {
        return annotations;
    }

    public BaseProcessor(Class[] c) {
        setClasses(c);
        annotations = new ArrayList<Annotation[]>();
        for (Class cl : classes) {
            if (cl != null) { annotations.add(cl.getAnnotations()); }
        }
    }

    public abstract void process(PrintStream printStream);
}
