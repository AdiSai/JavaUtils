package com.adithyasairam.javautils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Adi on 8/7/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR,
        ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE,
        ElementType.PACKAGE, ElementType.PARAMETER})
//@Target(ElementType.TYPE) //class level
public @interface Changeable {
    public enum When {
        MONTHLY, QUARTERLY, YEARLY, NEVER, STATIC, DYNAMIC
    }

    public enum Priority {
        LOW, MEDIUM, HIGH, NOW
    }
    public enum Type {
        CLASS, METHOD, PROPERTY, OTHER
    }
    public enum ChangeType {
        MODIFY, REMOVE, ADD, NOTHING
    }

    Class source();

    When when() default When.STATIC;

    Priority priority() default Priority.MEDIUM;

    Type type() default Type.CLASS;

    ChangeType changeType() default ChangeType.MODIFY;

    String createdBy() default "Adi Sai";
}
