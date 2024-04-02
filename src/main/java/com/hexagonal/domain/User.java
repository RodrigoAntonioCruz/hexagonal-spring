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

    static class CPF {
        private static final int[] WEIGHT_SSN = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        private static int calculate(final String str) {
            int sum = 0;
            for (int i = str.length() - 1, digit; i >= 0; i--) {
                digit = Integer.parseInt(str.substring(i, i + 1));
                sum += digit * WEIGHT_SSN[WEIGHT_SSN.length - str.length() + i];
            }
            sum = 11 - sum % 11;
            return sum > 9 ? 0 : sum;
        }

        public static boolean isValid(final String ssn) {
            if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}")) return false;

            var digit1 = calculate(ssn.substring(0, 9));
            var digit2 = calculate(ssn.substring(0, 9) + digit1);
            return ssn.equals(ssn.substring(0, 9) + digit1 + digit2);
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