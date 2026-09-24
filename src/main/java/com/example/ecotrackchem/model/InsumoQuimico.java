package com.example.ecotrackchem.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.example.ecotrackchem.util.NivelPeligrosidad;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Materia prima (solvente, concentrado, aceite base, etc.) identificada mediante su codigo CAS (identificador quimico internacional)//

@Entity
@Table(name = "insumos_quimicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsumoQuimico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_cas", nullable = false, unique = true, length = 20)
    private String codigoCas;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(name = "nivel_pureza", nullable = false, precision = 5, scale = 2)
    private BigDecimal nivelPureza;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_peligrosidad", nullable = false, length = 20)
    private NivelPeligrosidad nivelPeligrosidad;

    @Column(name = "stock_disponible", nullable = false, precision = 10, scale = 2)
    private BigDecimal stockDisponible;

    @JsonIgnore
    @OneToMany(mappedBy = "insumoPrincipal", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Lote> lotes = new HashSet<>();
}
