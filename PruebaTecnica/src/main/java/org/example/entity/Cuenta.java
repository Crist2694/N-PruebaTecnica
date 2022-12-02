package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "numero_cuenta")
    Long numero_cuenta;
    @Column(name = "tipo_cuenta")
    String tipo_cuenta;
    @Column(name = "saldo_inicial")
    Double saldo_inicial;
    @Column(name = "estado")
    String estado;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false, updatable = false)
    private Cliente idcliente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Movimiento> movimiento;

    public Cuenta() {
    }

    public Cuenta(int id, Long numero_cuenta, String tipo_cuenta, Double saldo_inicial, String estado, Cliente idcliente) {
        this.id = id;
        this.numero_cuenta = numero_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_inicial = saldo_inicial;
        this.estado = estado;
        this.idcliente = idcliente;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(Long numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(Double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", numero_cuenta=" + numero_cuenta +
                ", tipo_cuenta='" + tipo_cuenta + '\'' +
                ", saldo_inicial=" + saldo_inicial +
                ", estado='" + estado + '\'' +
                ", idcliente=" + idcliente +
                '}';
    }
}
