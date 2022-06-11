package com.pucminas.easyfarma.service;

import com.pucminas.easyfarma.domain.Auditoria;
import com.pucminas.easyfarma.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    public Auditoria insert(Auditoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public List<Auditoria> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
