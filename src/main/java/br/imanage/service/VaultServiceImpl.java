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

import java.time.LocalDateTime;
import java.util.List;

import static br.imanage.entity.dto.VaultDto.getVaultDtoFromVaultEntity;

@Service
@Slf4j
public class VaultServiceImpl implements VaultService {

    public static final String AUTH_ERROR = "Erro ao recuperar contexto de autenticação.";
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
                throw new BusinessException(AUTH_ERROR);
            }

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<VaultDto> listPasswordsByUser() {
        if (isUserAccredited()) {
            var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("Buscando lista de vault do usuário {}", user.getName());
            return VaultDto.getVaultDtoList(vaultRepository.findAllByUserId(user.getId()));
        } else {
            throw new BusinessException(AUTH_ERROR);
        }
    }

    @Override
    public String deleteVaultById(Integer id) {
        try {
            if (isUserAccredited()) {
                var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                vaultRepository.deleteVaultById(user.getId(), id);
                return String.format("Id [%s] deletado com sucesso...", id);
            }
            throw new BusinessException(AUTH_ERROR);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public VaultDto updateVaultById(Integer id, String system, String password) {
        try {
            if (isUserAccredited()) {
                var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                var selectedVault = vaultRepository.findByIdAndUserId(user.getId(), id);
                selectedVault.setSystem(system);
                selectedVault.setPassword(hashService.encodePass(password));
                selectedVault.setUpdateDate(LocalDateTime.now());
                log.info("Atualizando vault do usuário {} id {} ...", user.getName(), selectedVault.getId());
                return getVaultDtoFromVaultEntity(vaultRepository.save(selectedVault));
            } else {
                throw new BusinessException(AUTH_ERROR);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }


    private static boolean isUserAccredited() {
        return ObjectUtils.isNotEmpty(SecurityContextHolder.getContext())
                && ObjectUtils.isNotEmpty(SecurityContextHolder.getContext().getAuthentication())
                && ObjectUtils.isNotEmpty(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
