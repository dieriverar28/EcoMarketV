package com.example.MicroPedido.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity //se conecta con entidad
@Table(name="cupondescuento") //a tabla nombre persona
@Data //antes de data ahora va lo de arriba
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un cupón de descuento")
public class CuponDescuento {

    @Id
    @Schema(description = "Identificador único del cupón", example = "1")
    private int id_cupon;

    @Column(name="codigo", nullable =false)
    @Schema(description = "Código del cupón", example = "123456")
    private int codigo;

    @Schema(description = "Porcentaje de descuento", example = "15")
    private int descuento_pct;

    @Schema(description = "Monto fijo de descuento", example = "5000")
    private int descuento_monto;

    @Schema(description = "Fecha de expiración del cupón")
    private Date fecha_expiracion;

    @Schema(description = "Indica si el cupón está activo", example = "true")
    private boolean activo;
}