package com.hexagonal.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -2466709349338262769L;
    private String id;
    private String name;
    private String cpf;
    private String email;

    public User(String id, String name, String cpf, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String nome) {
        this.name = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) { this.email = email; }

    public void validate() {
        validateFields(null, name, Constants.NAME_NOT_NULL, null);
        validateFields(Constants.KEY_CPF, cpf, Constants.CPF_NOT_NULL, Constants.CPF_INVALID);
        validateFields(Constants.KEY_EMAIL, email, Constants.EMAIL_NOT_NULL, Constants.EMAIL_INVALID);
    }

    private void validateFields(String field, String value, String messageNotNull, String messageInvalid) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(messageNotNull);
        }

        if (Constants.KEY_EMAIL.equals(field)) {
            if (!value.matches(Constants.REGEX)) {
                throw new IllegalArgumentException(messageInvalid);
            }
        }

        if (Constants.KEY_CPF.equals(field)) {
            if (!CPF.isValid(value)) {
                throw new IllegalArgumentException(messageInvalid);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", cpf='" + cpf + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

}