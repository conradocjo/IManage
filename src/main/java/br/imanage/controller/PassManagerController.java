package br.imanage.controller;


import br.imanage.entity.Vault;
import br.imanage.entity.dto.VaultDto;
import br.imanage.service.VaultService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/vault/")
@CrossOrigin
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
    public ResponseEntity<String> registerNewPass(@RequestHeader("system") String system
            , @RequestHeader("password") String password) {
        service.registerPass(new Vault(system, password));
        return ResponseEntity.ok("Cadastro realizado com sucesso..");
    }

    @GetMapping(value = "/get-vaults")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.."),
            @ApiResponse(responseCode = "500", description = "Erro Interno")})
    public ResponseEntity<List<VaultDto>> getVaults() {
        return ResponseEntity.ok(service.listPasswordsByUser());
    }

    @DeleteMapping(value = "/delete-vaults")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.."),
            @ApiResponse(responseCode = "500", description = "Erro Interno")})
    public ResponseEntity<String> deleteVault(@RequestHeader("vaultId") Integer id) {
        return ResponseEntity.ok(service.deleteVaultById(id));
    }

    @PutMapping(value = "/update-vaults")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.."),
            @ApiResponse(responseCode = "500", description = "Erro Interno")})
    public ResponseEntity<VaultDto> updateVault(@RequestHeader("vaultId") Integer id
            , @RequestHeader("system") String system
            , @RequestHeader("password") String password) {
        return ResponseEntity.ok(service.updateVaultById(id, system, password));
    }

}
