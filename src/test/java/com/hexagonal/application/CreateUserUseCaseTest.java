package com.hexagonal.application;



import com.hexagonal.application.factory.FactoryBase;
import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.application.usecases.impl.CreateUserUseCaseImpl;
import com.hexagonal.application.utils.Constants;
import com.hexagonal.domain.User;
import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest extends FactoryBase {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;
    @Mock
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }

    @Test
    @DisplayName("Deve criar usuário com sucesso")
    public void shouldCreateUserSuccessfully() {
        when(repository.save(user)).thenReturn(user);
        var response = createUserUseCase.create(user);

        assertEquals(user, response);
        verify(repository, times(1)).save(user);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar com CPF inválido")
    public void shouldThrowExceptionWhenUpdatingWithInvalidCPF() {
        user.setCpf(INVALID_USER_CPF);

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_INVALID);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é nulo")
    public void shouldThrowExceptionWhenNameIsNull() {
        user.setName(null);

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é nulo")
    public void shouldThrowExceptionWhenCPFIsNull() {
        user.setCpf(null);

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é nulo")
    public void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);

        assertThatThrownBy(() -> createUserUseCase.create(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF estiver duplicado")
    public void shouldThrowExceptionWhenCpfIsDuplicated() {
        when(repository.findByFilter(getUpdateUserCPF().getCpf())).thenReturn(Collections.singletonList(getUpdateUserCPF()));

        assertThatThrownBy(() -> createUserUseCase.create(getUpdateUserCPF()))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_CPF);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail estiver duplicado")
    public void shouldThrowExceptionWhenEmailIsDuplicated() {
        when(repository.findByFilter(getUpdateUserEmail().getEmail())).thenReturn(Collections.singletonList(getUpdateUserEmail()));
        when(repository.findByFilter(getUpdateUserEmail().getCpf())).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> createUserUseCase.create(getUpdateUserEmail()))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_EMAIL);
    }
}