package com.example.ecotrackchem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecotrackchem.model.InsumoQuimico;

public interface InsumoQuimicoRepository extends JpaRepository<InsumoQuimico, Integer> {

    Optional<InsumoQuimico> findByCodigoCas(String codigoCas);
}
