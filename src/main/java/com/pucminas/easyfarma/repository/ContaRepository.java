package com.pucminas.easyfarma.repository;

import com.pucminas.easyfarma.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query(value = "SELECT * FROM conta WHERE nome = ?1 AND senha = ?2", nativeQuery = true)
    Conta findByUsingPass(String nome, String senha);

    @Query(value = "SELECT * FROM conta WHERE nome = ?1", nativeQuery = true)
    Conta findByUserName(String nome);
}
