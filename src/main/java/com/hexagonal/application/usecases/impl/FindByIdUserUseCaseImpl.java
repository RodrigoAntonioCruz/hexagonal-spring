package com.hexagonal.application.usecases.impl;


import com.hexagonal.application.exception.UserNotFoundException;
import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.application.usecases.FindByIdUserUseCase;
import com.hexagonal.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class FindByIdUserUseCaseImpl implements FindByIdUserUseCase {
    private final UserRepository userRepository;

    @Inject
    public FindByIdUserUseCaseImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(final String id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
}
