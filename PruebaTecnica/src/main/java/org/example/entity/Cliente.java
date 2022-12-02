package org.example.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "id")
    int id;
    @Column(name = "passwd")
    String passwd;
    @Column(name = "estado")
    String estado;
    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona idPersona;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cuenta> cuenta;

    public Cliente(int id, String passwd, String estado, Persona idPersona) {
        this.id = id;
        this.passwd = passwd;
        this.estado = estado;
        this.idPersona = idPersona;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }
}
