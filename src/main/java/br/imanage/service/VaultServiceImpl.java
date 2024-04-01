package br.imanage.service;

import br.imanage.persistence.entity.Vault;
import br.imanage.persistence.repository.VaultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VaultServiceImpl implements VaultService {

    private final VaultRepository vaultRepository;

    public VaultServiceImpl(VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    @Override
    public void registerPass(Vault vault) {
        try {
            vaultRepository.save(vault);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gravar vault.");
        }
    }

}
