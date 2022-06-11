package com.pucminas.easyfarma.repository;

import com.pucminas.easyfarma.domain.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentosRepository extends JpaRepository<Medicamentos, Integer> {
}
