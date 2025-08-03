package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByExternalId(String externalId);

}
