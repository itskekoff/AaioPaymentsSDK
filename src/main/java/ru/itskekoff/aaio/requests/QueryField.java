package ru.itskekoff.aaio.requests;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {
    String value();
    boolean required() default false;
}