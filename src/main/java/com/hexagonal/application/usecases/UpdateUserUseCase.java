package com.hexagonal.application.usecases;

import com.hexagonal.domain.User;

public interface UpdateUserUseCase {
    User update(String id, User user);
}
