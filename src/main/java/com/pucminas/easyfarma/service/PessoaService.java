package com.pucminas.easyfarma.service;

import com.pucminas.easyfarma.domain.Pessoa;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import com.pucminas.easyfarma.repository.PessoaRepository;
import com.pucminas.easyfarma.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository repository;

    public Pessoa find(Integer id) {
        Optional<Pessoa> obj = repository.findById(id);

        return obj.orElse(null);
    }

    public Pessoa insert(Pessoa obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Pessoa update(Pessoa obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Pessoa não pode ser deletada!");
        }
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public List<Pessoa> findAllByType(TipoPessoa tipo) {
        return repository.findAllByType(tipo.toString());
    }
}
