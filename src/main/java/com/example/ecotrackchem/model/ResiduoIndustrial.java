package com.example.ecotrackchem.model;

import java.math.BigDecimal;

import com.example.ecotrackchem.util.DisposicionFinal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Subproducto contaminante o reutilizable generado por un lote de
 * produccion. Un lote puede generar uno o varios registros de residuos.
 */
@Entity
@Table(name = "residuos_industriales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResiduoIndustrial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_residuo", nullable = false, length = 100)
    private String tipoResiduo;

    @Column(name = "volumen_litros", nullable = false, precision = 10, scale = 2)
    private BigDecimal volumenLitros;

    @Enumerated(EnumType.STRING)
    @Column(name = "disposicion_final", nullable = false, length = 20)
    private DisposicionFinal disposicionFinal;

    @Column(name = "requiere_certificado", nullable = false)
    private Boolean requiereCertificado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;
}
