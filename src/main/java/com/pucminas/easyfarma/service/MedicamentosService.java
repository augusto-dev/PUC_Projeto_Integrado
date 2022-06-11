package com.pucminas.easyfarma.service;

import com.pucminas.easyfarma.domain.Medicamentos;
import com.pucminas.easyfarma.repository.MedicamentosRepository;
import com.pucminas.easyfarma.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentosRepository repository;

    public Medicamentos find(Integer id) {
        Optional<Medicamentos> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public Medicamentos insert(Medicamentos obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Medicamentos update(Medicamentos obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Medicamentos não pode ser deletada!");
        }
    }

    public List<Medicamentos> findAll() {
        return repository.findAll();
    }
}
