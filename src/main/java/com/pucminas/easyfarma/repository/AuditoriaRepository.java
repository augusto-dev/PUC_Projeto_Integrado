package com.pucminas.easyfarma.repository;

import com.pucminas.easyfarma.domain.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
}
