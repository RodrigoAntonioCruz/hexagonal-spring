package com.hexagonal.application.usecases;

import com.hexagonal.domain.User;

public interface FindByIdUserUseCase {

    User findById(String id);
}
