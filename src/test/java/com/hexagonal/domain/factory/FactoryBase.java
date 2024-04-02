package com.hexagonal.domain.factory;

import com.hexagonal.domain.User;

public abstract class FactoryBase {
    public static final String USER_ID = "6488760121467b0d0b2e53de";
    public static final String USER_NAME = "João Ribeiro da Silva Teixeira";
    public static final String USER_CPF = "82167705026";
    public static final String INVALID_USER_CPF = "576012146de";
    public static final String USER_EMAIL = "joaoribeiro@msn.com";
    public static final String INVALID_EMAIL = "XPTO.example.com";
    public static final String USER_ID1 = "354760121467b0d0b2e53de";
    public static final String USER_NAME1 = "Alice dos Santos Neves";
    public static final String USER_CPF1 = "91537862030";
    public static final String USER_EMAIL1 = "alicesantoso@msn.com";
    public static final String USER_ID2 = "9628760121467b0d0b2e53de";
    public static final String USER_NAME2 = "Matias da Silva";
    public static final String USER_CPF2 = "88211554046";
    public static final String USER_EMAIL2 = "matias@msn.com";
    public static final String TO_STRING = "User{id='"+USER_ID1+"', name='"+USER_NAME1+"', cpf='"+USER_CPF1+"', email='"+USER_EMAIL1+"'}";
    public static User getUser() { return new User(USER_ID, USER_NAME, USER_CPF, USER_EMAIL); }
    public static User getUser1() {
        return new User(USER_ID1, USER_NAME1, USER_CPF1, USER_EMAIL1);
    }

    public static User getUser2() {
        return new User(USER_ID2, USER_NAME2, USER_CPF2, USER_EMAIL2);
    }
}