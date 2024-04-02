package com.hexagonal.infrastructure.adapters.repositories.utils;


public class Constants {

    private Constants() {
    }

    /**
     * LOG_KEY
     */
    public static final String LOG_KEY_METHOD = "method={} ";
    public static final String LOG_KEY_MESSAGE = "msg=\"{}\" ";
    public static final String LOG_KEY_ENTITY = "entity=\"{}\" ";
    public static final String LOG_KEY_ENTITY_ID = "entityId={} ";


    /**
     * LOG_METHOD
     * */
    public static final Object LOG_METHOD_FIND_BY_FILTER = "findByFilter";
    public static final Object LOG_METHOD_DELETE_BY_ID = "deleteById";
    public static final String LOG_METHOD_FIND_USER_BY_ID = "findUserById";
    public static final String LOG_METHOD_SAVE = "save";
}
