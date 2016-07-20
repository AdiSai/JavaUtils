package com.adithyasairam.javautils.helpers;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Adi on 9/1/2015.
 */
public class UnsafeAccess {
    private UnsafeAccess() { throw new IllegalAccessError("No Instances!"); }

    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        }
        catch(Exception e) { e.printStackTrace(); }
        return null;
    }
}
