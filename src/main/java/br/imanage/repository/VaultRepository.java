package br.imanage.repository;

import br.imanage.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaultRepository extends JpaRepository<Vault, Integer> {

    List<Vault> findAllByUserId(Integer userId);

    @Query(nativeQuery = true, value = "delete from tb_vault where fk_user_id = :userId and id = :id")
    void deleteVaultById(@Param("userId") Integer userId, @Param("id") Integer id);

    @Query(nativeQuery = false, value = "select vlt from Vault vlt where vlt.user.id = :userId and vlt.id = :id")
    Vault findByIdAndUserId(@Param("userId") Integer userId, @Param("id") Integer id);

}
