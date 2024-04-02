package com.hexagonal.application.usecases;

import com.hexagonal.domain.User;

public interface CreateUserUseCase {

    User create(User user);

}
