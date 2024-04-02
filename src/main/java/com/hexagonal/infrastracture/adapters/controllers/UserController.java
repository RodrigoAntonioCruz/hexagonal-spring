package com.hexagonal.infrastracture.adapters.controllers;


import com.hexagonal.application.usecases.CreateUserUseCase;
import com.hexagonal.application.usecases.DeleteByIdUserUseCase;
import com.hexagonal.application.usecases.FindByFilterUserUseCase;
import com.hexagonal.application.usecases.UpdateUserUseCase;
import com.hexagonal.application.usecases.impl.FindByIdUserUseCaseImpl;
import com.hexagonal.infrastracture.adapters.controllers.dto.request.UserRequest;
import com.hexagonal.infrastracture.adapters.controllers.dto.response.UserResponse;
import com.hexagonal.infrastracture.adapters.controllers.mapper.UserInputMapper;
import com.hexagonal.infrastracture.adapters.controllers.utils.Constants;
import com.hexagonal.infrastracture.adapters.controllers.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;


@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Usuários")
@RequestMapping("/users")
public class UserController {

    private final UserInputMapper mapper;

    private final CreateUserUseCase createUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final FindByIdUserUseCaseImpl findByIdUserUseCase;

    private final FindByFilterUserUseCase findByFilterUserUseCase;

    private final DeleteByIdUserUseCase deleteByIdUserUseCase;

    @PostMapping
    @Operation(description = "Cria um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Início da criação de um usuário ", Constants.LOG_METHOD_CREATE, userRequest);

        var user = mapper.toEntity(userRequest);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da criação de um usuário ", Constants.LOG_METHOD_CREATE, userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(createUserUseCase.create(user)));
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID + Constants.LOG_KEY_ENTITY, "Início da atualização de um usuário ", Constants.LOG_METHOD_UPDATE, id, userRequest);

        var user = mapper.toEntity(userRequest);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID + Constants.LOG_KEY_ENTITY, "Fim da atualização de um usuário ", Constants.LOG_METHOD_UPDATE, user.getId(), user);

        return ResponseEntity.ok(mapper.toResponse(updateUserUseCase.update(id, user)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca um usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<UserResponse> findById(@Valid @PathVariable String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da busca de um usuário por id ", Constants.LOG_METHOD_FIND_BY_ID, id);

        var response = mapper.toResponse(findByIdUserUseCase.findById(id));

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da busca de um usuário por id ", Constants.LOG_METHOD_FIND_BY_ID, response);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(description = "Busca paginada de usuários por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Page<UserResponse>> findByFilter(@Valid
                                                           @RequestParam(value = "query", defaultValue = "") String query,
                                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                           @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                           @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

        var list = findByFilterUserUseCase.findByFilter(query);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da busca da paginada de usuários por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, list);

        var pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        var pages = PageUtils.toPage(list, pageable, list.size(), mapper::toResponse, orderBy, UserResponse.class);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY, "Fim da busca da paginada de usuários por filtros ", Constants.LOG_METHOD_FIND_BY_FILTER, pages.getContent());

        return ResponseEntity.ok().body(pages);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove um usuário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação sem conteúdo realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Void> deleteById(@Valid @PathVariable String id) {

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Início da exclusão de um usuário ", Constants.LOG_METHOD_DELETE_BY_ID, id);

        deleteByIdUserUseCase.deleteById(id);

        log.info(Constants.LOG_KEY_MESSAGE + Constants.LOG_KEY_METHOD + Constants.LOG_KEY_ENTITY_ID, "Fim da exclusão de um usuário ", Constants.LOG_METHOD_DELETE_BY_ID, id);

        return ResponseEntity.noContent().build();
    }
}
