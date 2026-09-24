package com.example.ecotrackchem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecotrackchem.model.Lote;
import com.example.ecotrackchem.util.EstadoLote;

public interface LoteRepository extends JpaRepository<Lote, Integer> {

    Optional<Lote> findByCodigoLote(String codigoLote);

    List<Lote> findByEstado(EstadoLote estado);
}
