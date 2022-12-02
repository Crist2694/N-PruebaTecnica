package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    String fecha = String.valueOf(LocalDateTime.now());

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




    public Movimiento(int id, String fecha, String tipo_movimiento, Double valor, Cuenta cuenta_origen, Cuenta cuenta_destino) {
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
