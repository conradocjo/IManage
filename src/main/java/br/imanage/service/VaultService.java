package br.imanage.service;

import br.imanage.entity.Vault;
import br.imanage.entity.dto.VaultDto;

import java.util.List;

public interface VaultService {


    void registerPass(Vault vault);

    List<VaultDto> listPasswordsByUser();
}
