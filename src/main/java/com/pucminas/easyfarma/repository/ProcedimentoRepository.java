package com.pucminas.easyfarma.repository;

import com.pucminas.easyfarma.domain.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {
}
