package br.imanage.service;

import br.imanage.entity.User;
import br.imanage.entity.Vault;
import br.imanage.entity.dto.VaultDto;
import br.imanage.exception.BusinessException;
import br.imanage.repository.VaultRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
            if (isUserAccredited()) {
                vault.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                vault.setPassword(hashService.encodePass(vault.getPassword()));
                log.info("Salvando vault...");
                vaultRepository.save(vault);
            } else {
                throw new BusinessException("Erro ao recuperar contexto de autenticação.");
            }

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<VaultDto>listPasswordsByUser() {
        if (isUserAccredited()) {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return VaultDto.getVaultDtoList(vaultRepository.findAllByUserId(user.getId()));
        } else {
            throw new BusinessException("Erro ao recuperar contexto de autenticação.");
        }
    }


    private static boolean isUserAccredited() {
        return ObjectUtils.isNotEmpty(SecurityContextHolder.getContext())
                && ObjectUtils.isNotEmpty(SecurityContextHolder.getContext().getAuthentication())
                && ObjectUtils.isNotEmpty(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
