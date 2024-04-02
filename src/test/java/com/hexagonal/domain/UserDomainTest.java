package com.hexagonal.domain;


import com.hexagonal.domain.factory.FactoryBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class UserDomainTest extends FactoryBase {

    private User user;
    private User user1;

    @BeforeEach
    public void setUp() {
        user = getUser();
        user1 = getUser1();
    }

    @Test
    @DisplayName("Deve construir um objeto usuário com sucesso")
    public void shouldBuildObjectUserSuccessfully() {
        assertEquals(user.getId(), USER_ID);
        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getEmail(), USER_EMAIL);
        assertEquals(user.getCpf(), USER_CPF);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é nulo")
    public void shouldThrowExceptionWhenNameIsNull() {
        user.setName(null);
        validateException(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é nulo")
    public void shouldThrowExceptionWhenCPFIsNull() {
        user.setCpf(null);
        validateException(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é nulo")
    public void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);
        validateException(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é vazio")
    public void shouldThrowExceptionWhenNameIsEmpty() {
        user.setName("");
        validateException(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é vazio")
    public void shouldThrowExceptionWhenCPFIsEmpty() {
        user.setCpf("");
        validateException(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é vazio")
    public void shouldThrowExceptionWhenEmailIsEmpty() {
        user.setEmail("");
        validateException(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é inválido")
    public void shouldThrowExceptionWhenEmailIsInvalid() {
        user.setEmail(INVALID_EMAIL);
        validateException(Constants.EMAIL_INVALID);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é inválido")
    public void shouldThrowExceptionWhenCPFIsInvalid() {
        user.setCpf(INVALID_USER_CPF);
        validateException(Constants.CPF_INVALID);
    }

    @Test
    @DisplayName("Deve testar a igualdade entre dois IDS")
    public void  shouldTestEqualityBetweenTwoIDS() {
        user.setId(USER_ID);
        assertNotNull(user);
    }
    @Test
    @DisplayName("Deve testar a igualdade entre dois usuários com o mesmo ID")
    public void  shouldTestEqualityBetweenTwoUsersWithSameID() {
        User user1 = getUser1();
        User user2 = getUser1();
        User user3 = getUser2();

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
    }

    @Test
    @DisplayName("Deve testar o hashCode de dois usuários com o mesmo ID")
    public void shouldTestHashCodeBetweenTwoUsersWithSameID() {
        User user1 = getUser1();
        User user2 = getUser1();

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    @DisplayName("Deve testar a representação em string de um usuário")
    public void shouldTestTheStringRepresentationOfUser() {
        assertEquals(TO_STRING, user1.toString());
    }

    private void validateException(String message) {
        assertThatThrownBy(() -> user.validate())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}
