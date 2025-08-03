package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,String> {
}
