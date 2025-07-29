package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
