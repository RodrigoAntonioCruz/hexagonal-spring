package com.hexagonal.application.usecases.impl;

import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.application.usecases.DeleteByIdUserUseCase;
import com.hexagonal.domain.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DeleteByIdUserUseCaseImpl implements DeleteByIdUserUseCase {
    private final UserRepository userRepository;
    private final FindByIdUserUseCaseImpl findByIdUserUseCase;

    @Inject
    public DeleteByIdUserUseCaseImpl(FindByIdUserUseCaseImpl findByIdUserUseCase, final UserRepository userRepository) {
        this.findByIdUserUseCase = findByIdUserUseCase;
        this.userRepository = userRepository;
    }

     @Override
    public void deleteById(final String id) {
        User user = findByIdUserUseCase.findById(id);
        userRepository.deleteById(user.getId());
    }
}
