package br.com.lovizoto.externalapi.services;

import br.com.lovizoto.externalapi.model.Session;
import br.com.lovizoto.externalapi.repository.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private Logger logger = LoggerFactory.getLogger(SessionService.class);

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    //VERSION WITHOUT DTO
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(String id) {
        return sessionRepository.findById(id).get(); //make a exception handler
    }

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSession(Session session) {
        return sessionRepository.save(session);
    }




}
