package com.adithyasairam.javautils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Adi on 8/7/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER})
//@Target(ElementType.TYPE) //class level
public @interface Exclude { }
