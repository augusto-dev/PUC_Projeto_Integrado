package com.pucminas.easyfarma.service;

import com.pucminas.easyfarma.domain.Procedimento;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import com.pucminas.easyfarma.repository.ProcedimentoRepository;
import com.pucminas.easyfarma.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProcedimentoService {
    
    @Autowired
    private ProcedimentoRepository repository;

    public Procedimento find(Integer id) {
        Optional<Procedimento> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Procedimento insert(Procedimento obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Procedimento update(Procedimento obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Procedimento não pode ser deletada!");
        }
    }

    public List<Procedimento> findAll() {
        return repository.findAll();
    }
}
