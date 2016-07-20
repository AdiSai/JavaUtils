package com.adithyasairam.javautils.helpers;

import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by Adi on 8/30/2015.
 */
public class GetClasses {
    private GetClasses() { throw new IllegalAccessError("No Instances!"); }

    public static Class[] getClassesAnnotatedWith(String base, Class annotatedClass) {
        Reflections reflections = new Reflections(base);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(annotatedClass);
        Object[] objects = annotated.toArray();
        return Arrays.copyOf(objects, objects.length, Class[].class);
    }

    public static Class[] getClasses(String base) {
        Reflections reflections = new Reflections(base);
        Set<Class<? extends Object>> subTypes = reflections.getSubTypesOf(Object.class);
        return (Class<? extends Object>[])subTypes.toArray();
    }
}
