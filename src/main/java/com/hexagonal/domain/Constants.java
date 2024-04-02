package com.hexagonal.domain;


public class Constants {

    private Constants() {
    }

    public static final String KEY_CPF = "cpf";
    public static final String KEY_EMAIL = "email";
    public static final String CPF_NOT_NULL = "O CPF não pode ser nulo";
    public static final String CPF_INVALID = "O CPF digitado é inválido";
    public static final String EMAIL_INVALID = "O e-mail digitado é inválido";
    public static final String EMAIL_NOT_NULL = "O e-mail não pode ser nulo";
    public static final String NAME_NOT_NULL = "O nome não pode ser nulo";
    public static final String REGEX = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,4}$";
}
