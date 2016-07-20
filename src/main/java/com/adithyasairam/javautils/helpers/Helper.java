package com.adithyasairam.javautils.helpers;

import java.lang.reflect.Array;

/**
 * Created by Adi on 9/5/2015.
 */
public class Helper {
    private Helper() { throw new IllegalAccessError("No Instances!"); }

    public static <T> T[] mergeArrays(T[] array1, T[] array2) {
        assert array1.getClass().getComponentType().equals(array2.getClass().getComponentType());
        int newSize = array1.length + array2.length;
        T[] array = (T[]) Array.newInstance(array1.getClass().getComponentType(), newSize);
        System.arraycopy(array1, 0, array, 0, array1.length);
        System.arraycopy(array2, 0, array, array1.length, array2.length);
        return array;
    }

    public static <T> boolean arrayContains(T[] array, T object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) { return true; }
        }
        return false;
    }
}
