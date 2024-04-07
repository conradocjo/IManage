package br.imanage.entity.dto;

import br.imanage.entity.Vault;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class VaultDto {

    private String system;
    private String password;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;


    public static List<VaultDto> getVaultDtoList(List<Vault> vaultList) {
        List<VaultDto> response = new ArrayList<>();
        vaultList.forEach(vault -> {
            response.add(new VaultDto(vault.getSystem(), vault.getPassword()
                    , vault.getCreationDate(), vault.getUpdateDate()));
        });
        return response;
    }


}
