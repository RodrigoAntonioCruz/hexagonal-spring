package com.hexagonal.application.factory;


import com.hexagonal.domain.User;

public abstract class FactoryBase {
    public static final String USER_ID = "6488760121467b0d0b2e53de";
    public static final String UPDATE_USER_ID = "5988760121467b0d0b2e53de";
    public static final String INVALID_USER_ID = "5760121467b0d0b2e53de";
    public static final String USER_NAME = "João Ribeiro da Silva Teixeira";
    public static final String UPDATE_USER_NAME = "Carlos Bozano";
    public static final String USER_CPF = "19748004074";
    public static final String UPDATE_USER_CPF = "85891470020";
    public static final String INVALID_USER_CPF = "576012146de";
    public static final String USER_EMAIL = "joaoribeiro@msn.com";
    public static final String UPDATE_USER_EMAIL = "carlosbozano@gmail.com";
    public static final String FILTER = "Joã";
    public static User getUser() {
        return new User(USER_ID, USER_NAME, USER_CPF, USER_EMAIL);
    }
    public static User getUpdateUserCPF() {
        return new User(UPDATE_USER_ID, UPDATE_USER_NAME, USER_CPF, UPDATE_USER_EMAIL);
    }
    public static User getUpdateUserEmail() {
        return new User(UPDATE_USER_ID, UPDATE_USER_NAME, UPDATE_USER_CPF, USER_EMAIL);
    }
}