package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
