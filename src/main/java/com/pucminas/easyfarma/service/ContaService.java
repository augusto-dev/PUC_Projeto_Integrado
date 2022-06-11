package com.pucminas.easyfarma.service;

import com.pucminas.easyfarma.domain.Conta;
import com.pucminas.easyfarma.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService implements UserDetailsService {

    @Autowired
    private ContaRepository repository;

    public Conta findByName(String name) {
        return repository.findByUserName(name);
    }

    public void insert(Conta conta) {
        conta.setId(null);
        repository.save(conta);
    }
    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Conta conta = Optional.ofNullable(repository.findByUserName(nome)).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER");
        return new User(conta.getNome(), conta.getSenha(), authorities);
    }
}
