package com.oplever.sioe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Origen.
 */
@Entity
@Table(name = "origen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Origen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "privilegio")
    private Integer privilegio;

    @Column(name = "zona")
    private String zona;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "municipio")
    private String municipio;

    @OneToMany(mappedBy = "origen")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Peticion> peticions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Origen nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrivilegio() {
        return privilegio;
    }

    public Origen privilegio(Integer privilegio) {
        this.privilegio = privilegio;
        return this;
    }

    public void setPrivilegio(Integer privilegio) {
        this.privilegio = privilegio;
    }

    public String getZona() {
        return zona;
    }

    public Origen zona(String zona) {
        this.zona = zona;
        return this;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDistrito() {
        return distrito;
    }

    public Origen distrito(String distrito) {
        this.distrito = distrito;
        return this;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Origen municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Set<Peticion> getPeticions() {
        return peticions;
    }

    public Origen peticions(Set<Peticion> peticions) {
        this.peticions = peticions;
        return this;
    }

    public Origen addPeticion(Peticion peticion) {
        this.peticions.add(peticion);
        peticion.setOrigen(this);
        return this;
    }

    public Origen removePeticion(Peticion peticion) {
        this.peticions.remove(peticion);
        peticion.setOrigen(null);
        return this;
    }

    public void setPeticions(Set<Peticion> peticions) {
        this.peticions = peticions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Origen origen = (Origen) o;
        if (origen.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, origen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Origen{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", privilegio='" + privilegio + "'" +
            ", zona='" + zona + "'" +
            ", distrito='" + distrito + "'" +
            ", municipio='" + municipio + "'" +
            '}';
    }
}
