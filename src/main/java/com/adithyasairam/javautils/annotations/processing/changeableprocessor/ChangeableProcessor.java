package com.adithyasairam.javautils.annotations.processing.changeableprocessor;

import com.adithyasairam.javautils.annotations.processing.base.BaseProcessor;
import com.adithyasairam.javautils.annotations.Changeable;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by Adi on 8/30/2015.
 */

public class ChangeableProcessor extends BaseProcessor implements Runnable {
    public ChangeableProcessor(Class[] classes) { super(classes); }
    @Override
    public void process(PrintStream printStream) {
        List<Annotation[]> annotationArr = super.getAnnotations();
        for (Annotation[] arr : annotationArr) {
            for (Annotation annotation : arr) {
                if (annotation instanceof Changeable) {
                    Changeable changeable = (Changeable)annotation;
                    ChangeableClass changeableClass = new ChangeableClass(changeable);
                    changeableClass.processAnnotation(printStream);
                }
            }
        }
    }

    public void run() {
        process(System.out);
    }
}
