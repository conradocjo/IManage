package br.imanage.service;

import br.imanage.entity.Vault;
import br.imanage.entity.dto.VaultDto;

import java.util.List;

public interface VaultService {


    void registerPass(Vault vault);

    List<VaultDto> listPasswordsByUser();

    String deleteVaultById(Integer id);

    VaultDto updateVaultById(Integer id, String system, String password);
}
