package com.locatech.api.repositories;

import com.locatech.api.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // O Spring Data JPA entende automaticamente o que esses métodos devem fazer
    // apenas pela nomenclatura deles!
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}