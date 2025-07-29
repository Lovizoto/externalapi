package br.com.lovizoto.externalapi.repository;

import br.com.lovizoto.externalapi.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContextRepository extends JpaRepository<Context,Long> {
}
