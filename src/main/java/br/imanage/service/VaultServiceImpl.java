package br.imanage.service;

import br.imanage.entity.User;
import br.imanage.entity.Vault;
import br.imanage.repository.VaultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VaultServiceImpl implements VaultService {

    private final VaultRepository vaultRepository;
    private final HashServiceImpl hashService;

    public VaultServiceImpl(VaultRepository vaultRepository, HashServiceImpl hashService) {
        this.vaultRepository = vaultRepository;
        this.hashService = hashService;
    }

    @Override
    public void registerPass(Vault vault) {
        try {
            vault.setPassword(hashService.encodePass(vault.getPassword()));
            log.info("Salvando vault...");
            vaultRepository.save(vault);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar vault.");
        }
    }

    public List<Vault> listPasswordsByUser(User user) {
        return null;
    }

}
