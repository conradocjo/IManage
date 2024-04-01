package br.imanage.persistence.repository;

import br.imanage.persistence.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaultRepository extends JpaRepository<Vault, Integer> {


}
