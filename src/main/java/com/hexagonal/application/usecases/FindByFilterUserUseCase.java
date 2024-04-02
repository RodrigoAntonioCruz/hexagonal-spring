package com.hexagonal.application.usecases;

import com.hexagonal.domain.User;

import java.util.List;

public interface FindByFilterUserUseCase {

    List<User> findByFilter(String query);
}
