package br.com.lovizoto.externalapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table(name = "context")
public class Context {

    private Long id;
    private Long session_id;
    private ... context_json;
    private Date updated_at;

}
