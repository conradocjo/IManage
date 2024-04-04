package br.imanage.controller;




import br.imanage.entity.User;
import br.imanage.entity.request.LoginRequest;
import br.imanage.entity.request.UserRequest;
import br.imanage.entity.response.TokenResponse;
import br.imanage.entity.response.UserResponse;
import br.imanage.service.TokenService;
import br.imanage.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "UserController", description = "Gerenciamento de usuários...")
@RestController
@RequestMapping("users")
public class UserController {

    private UserService service;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public UserController(UserService service, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Operation(description = "Endpoint de login dos usuários..")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso...")
    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> signin(@RequestBody @Valid LoginRequest login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.userName(), login.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @Operation(description = "Endpoint de cadastro de usuários..")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso...")
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signup(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(service.createUser(userRequest));
    }


}