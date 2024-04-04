package br.imanage.controller;


import br.imanage.entity.Vault;
import br.imanage.service.VaultService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/v1")
@Tag(name = "PassManagerController", description = "Cadastro de senhas para sistemas (Vault)..")
public class PassManagerController {

    private final VaultService service;

    public PassManagerController(VaultService service) {
        this.service = service;
    }

    @PostMapping(value = "/new-pass")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso.."),
            @ApiResponse(responseCode = "500", description = "Erro Interno")})
    public Optional<String> registerNewPass(@RequestHeader("system") String system
            , @RequestHeader("password") String password) {
        service.registerPass(new Vault(system, password));
        return Optional.of("Cadastro realizado com sucesso..");
    }

}
