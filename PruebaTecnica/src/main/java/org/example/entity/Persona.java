package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "genero")
    String genero;

    @Column(name = "edad")
    int edad;

    @Column(name = "identificacion")
    String identifacion;

    @Column(name = "direccion")
    String direccion;

    @Column(name = "telefono")
    String telefono;

    @OneToOne(mappedBy = "idPersona", cascade = CascadeType.ALL)
    private Cliente cliente;

    public Persona(int id, String nombre, String genero, int edad, String identifacion, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.identifacion = identifacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona() {
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                ", identifacion='" + identifacion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdentifacion() {
        return identifacion;
    }

    public void setIdentifacion(String identifacion) {
        this.identifacion = identifacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
