package br.imanage.repository;

import br.imanage.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaultRepository extends JpaRepository<Vault, Integer> {

    List<Vault> findAllByUserId(Integer userId);

}
