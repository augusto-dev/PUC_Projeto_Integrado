package com.pucminas.easyfarma.repository;

import com.pucminas.easyfarma.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Query(value = "SELECT * FROM pessoa WHERE tipo_pessoa = ?1", nativeQuery = true)
    List<Pessoa> findAllByType(String tipo);
}
