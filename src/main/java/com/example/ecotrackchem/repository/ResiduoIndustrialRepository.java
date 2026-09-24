package com.example.ecotrackchem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecotrackchem.model.ResiduoIndustrial;

public interface ResiduoIndustrialRepository extends JpaRepository<ResiduoIndustrial, Integer> {

    List<ResiduoIndustrial> findByLoteId(Integer loteId);
}
