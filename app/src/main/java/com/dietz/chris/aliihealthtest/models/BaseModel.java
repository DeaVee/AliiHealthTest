package com.dietz.chris.aliihealthtest.models;

/**
 * Just an object that contains shared items between server models.
 */
abstract class BaseModel {
    /**
     * Default value for null integer items in a JSON string
     */
    public static int NOT_SET_INT = Integer.MIN_VALUE;
    /**
     * Default value for a null String item in a JSON string
     */
    public static String NOT_SET_STRING = "";
}
