package com.locatech.api.repositories;

import com.locatech.api.domain.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    // Métodos padrão do JpaRepository (save, findById, findAll, deleteById) já estão disponíveis
}