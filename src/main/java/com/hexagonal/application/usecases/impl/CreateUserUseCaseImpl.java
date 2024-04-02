package com.hexagonal.application.usecases.impl;


import com.hexagonal.application.Constants;
import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.application.usecases.CreateUserUseCase;
import com.hexagonal.domain.User;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import static com.hexagonal.application.exception.ExceptionUtil.throwExceptionIf;


@Named
@ApplicationScoped
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public CreateUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User user) {
        user.validate();

        throwExceptionIf(userRepository.findByFilter(user.getCpf()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_CPF));

        throwExceptionIf(userRepository.findByFilter(user.getEmail()).stream().findFirst().isPresent(),
                new DuplicateRequestException(Constants.DUPLICATION_EMAIL));

        return userRepository.save(user);
    }
}
