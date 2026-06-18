package com.example.MicroTiendaUbicacion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "horario_tienda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HorarioTienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_horario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda", nullable = false)
    private Tienda tienda;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 10)
    private DiaSemana dia_semana;

    @Column(name = "hora_apertura", nullable = false)
    private LocalTime hora_apertura;

    @Column(name = "hora_cierre", nullable = false)
    private LocalTime hora_cierre;

    @Column(name = "cerrado")
    private Boolean cerrado = false;

    public enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES,
        VIERNES, SABADO, DOMINGO
    }
}