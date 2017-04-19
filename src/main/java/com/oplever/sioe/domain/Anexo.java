package com.oplever.sioe.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Anexo.
 */
@Entity
@Table(name = "anexo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "arhivo", nullable = false)
    private String arhivo;

    @NotNull
    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArhivo() {
        return arhivo;
    }

    public Anexo arhivo(String arhivo) {
        this.arhivo = arhivo;
        return this;
    }

    public void setArhivo(String arhivo) {
        this.arhivo = arhivo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Anexo tipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Anexo descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Anexo anexo = (Anexo) o;
        if (anexo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, anexo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Anexo{" +
            "id=" + id +
            ", arhivo='" + arhivo + "'" +
            ", tipo='" + tipo + "'" +
            ", descripcion='" + descripcion + "'" +
            '}';
    }
}
