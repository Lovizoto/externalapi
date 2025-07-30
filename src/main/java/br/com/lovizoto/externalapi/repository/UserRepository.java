package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByExternalId(String externalId);

}
