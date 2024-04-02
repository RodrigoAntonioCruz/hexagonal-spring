package com.hexagonal.application;


import com.hexagonal.application.exception.UserNotFoundException;
import com.hexagonal.application.factory.FactoryBase;
import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.application.usecases.impl.FindByIdUserUseCaseImpl;
import com.hexagonal.application.usecases.impl.UpdateUserUseCaseImpl;
import com.hexagonal.domain.User;
import com.sun.jdi.request.DuplicateRequestException;
import org.assertj.core.api.Assertions;
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
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest extends FactoryBase {

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Mock
    private UserRepository repository;

    @Mock
    private FindByIdUserUseCaseImpl findByIdUserUseCase;

    private User user;

    @BeforeEach
    public void setUp() {
        user = getUser();
    }
    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    public void shouldUpdateUserSuccessfully() {
        when(findByIdUserUseCase.findById(USER_ID)).thenReturn(user);
        when(repository.save(getUser())).thenReturn(user);

        var response = updateUserUseCase.update(USER_ID, user);

        assertEquals(user, response);
        verify(repository, times(1)).save(user);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar usuário inexistente")
    public void shouldThrowExceptionWhenUpdatingNonExistentUser() {
        doThrow(new UserNotFoundException())
                .when(findByIdUserUseCase).findById(INVALID_USER_ID);

        Assertions.assertThatThrownBy(() ->  updateUserUseCase.update(INVALID_USER_ID, user))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(Constants.USER_NOT_FOUND);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar com CPF inválido")
    public void shouldThrowExceptionWhenUpdatingWithInvalidCPF() {
        user.setCpf(INVALID_USER_CPF);

        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_INVALID);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o nome é nulo")
    public void shouldThrowExceptionWhenNameIsNull() {
        user.setName(null);

        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.NAME_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF é nulo")
    public void shouldThrowExceptionWhenCPFIsNull() {
        user.setCpf(null);

        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.CPF_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail é nulo")
    public void shouldThrowExceptionWhenEmailIsNull() {
        user.setEmail(null);

        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Constants.EMAIL_NOT_NULL);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o CPF estiver duplicado")
    public void shouldThrowExceptionWhenCpfIsDuplicated() {
        when(findByIdUserUseCase.findById(USER_ID)).thenReturn(getUpdateUserCPF());
        when(repository.findByFilter(getUpdateUserCPF().getCpf())).thenReturn(Collections.singletonList(getUpdateUserCPF()));

        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, getUpdateUserCPF()))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_CPF);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o e-mail estiver duplicado")
    public void shouldThrowExceptionWhenEmailIsDuplicated() {
        when(findByIdUserUseCase.findById(USER_ID)).thenReturn(getUpdateUserEmail());
        when(repository.findByFilter(getUpdateUserEmail().getEmail())).thenReturn(Collections.singletonList(getUpdateUserEmail()));

        when(repository.findByFilter(getUpdateUserEmail().getCpf())).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> updateUserUseCase.update(USER_ID, getUpdateUserEmail()))
                .isInstanceOf(DuplicateRequestException.class)
                .hasMessage(Constants.DUPLICATION_EMAIL);
    }
}
