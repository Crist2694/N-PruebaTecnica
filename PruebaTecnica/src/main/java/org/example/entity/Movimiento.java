package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha = LocalDate.now();

    @Column(name = "tipo_movimiento")
    String tipo_movimiento;

    @Column(name = "valor")
    Double valor;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen")
    private Cuenta cuenta_origen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino")
    private Cuenta cuenta_destino;

    public Movimiento(int id, LocalDate fecha, String tipo_movimiento, Double valor, Cuenta cuenta_origen, Cuenta cuenta_destino) {
        this.id = id;
        this.fecha = fecha;
        this.tipo_movimiento = tipo_movimiento;
        this.valor = valor;
        this.cuenta_origen = cuenta_origen;
        this.cuenta_destino = cuenta_destino;
    }

    public Movimiento() {
    }



}
