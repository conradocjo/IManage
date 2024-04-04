package br.imanage.repository;

import br.imanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    public UserDetails findByNameuser(String username);

}
