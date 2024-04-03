package com.hexagonal.infrastructure.adapters.repositories;


import com.hexagonal.application.repositories.UserRepository;
import com.hexagonal.domain.User;
import com.hexagonal.infrastructure.adapters.repositories.mappers.UserOutputMapper;
import com.hexagonal.infrastructure.adapters.repositories.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserOutputMapper mapper;

    private final UserEntityRepository repository;

    @Override
    public User save(User user) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Início da persistência de um usuário ", Constants.LOG_METHOD_SAVE, user);

        var entity = mapper.toUserEntity(user);

        entity = repository.save(entity);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da persistência de um usuário ", Constants.LOG_METHOD_SAVE, entity);

        return mapper.toDomain(entity);
    }

    @Override
    public Optional<User> findUserById(String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da busca de um usuário por id ", Constants.LOG_METHOD_FIND_USER_BY_ID, id);

        var entity = repository.findById(id);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da busca de um usuário por id ", Constants.LOG_METHOD_FIND_USER_BY_ID, entity);

        return entity.map(mapper::toDomain);
    }

    @Override
    public List<User> findByFilter(String query) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da busca da lista de usuários por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, query);

        var userEntityList = repository.findByFilter(query);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da busca da lista de usuários por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, userEntityList);

        return userEntityList.stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da exclusão de um usuário ", Constants.LOG_METHOD_DELETE_BY_ID, id);

        repository.deleteById(id);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Fim da exclusão de um usuário ", Constants.LOG_METHOD_DELETE_BY_ID, id);
    }
}


