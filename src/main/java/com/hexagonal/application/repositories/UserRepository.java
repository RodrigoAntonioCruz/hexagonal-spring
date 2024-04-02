package com.hexagonal.application.repositories;





import com.hexagonal.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findUserById(String id);
    List<User> findByFilter(String query);
    void deleteById(String id);
}
