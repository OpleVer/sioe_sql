package com.oplever.sioe.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Peticion.
 */
@Entity
@Table(name = "peticion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Peticion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero", nullable = false)
    private String numero;

    @NotNull
    @Column(name = "nombre_solicitante", nullable = false)
    private String nombre_solicitante;

    @NotNull
    @Column(name = "paterno_solicitante", nullable = false)
    private String paterno_solicitante;

    @NotNull
    @Column(name = "materno_solicitante", nullable = false)
    private String materno_solicitante;

    @NotNull
    @Column(name = "cargo_solicitante", nullable = false)
    private String cargo_solicitante;

    @NotNull
    @Column(name = "direccion_solicitante", nullable = false)
    private String direccion_solicitante;

    @NotNull
    @Size(max = 5000)
    @Column(name = "acto_constar", length = 5000, nullable = false)
    private String acto_constar;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private ZonedDateTime fecha;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "status_prevencion")
    private Boolean status_prevencion;

    @Column(name = "tipo_evaluacion")
    private Integer tipo_evaluacion;

    @Column(name = "status_trabajo")
    private Boolean status_trabajo;

    @NotNull
    @Column(name = "oficio", nullable = false)
    private String oficio;

    @Column(name = "oficio_prevencion")
    private String oficio_prevencion;

    @Column(name = "notificacion_prevencion")
    private String notificacion_prevencion;

    @Column(name = "respuesta_prevencion")
    private String respuesta_prevencion;

    @Column(name = "acta")
    private String acta;

    @Column(name = "acuerdo")
    private String acuerdo;

    @Column(name = "notificacion")
    private String notificacion;

    @Column(name = "respuesta")
    private String respuesta;

    @ManyToOne(optional = false)
    @NotNull
    private Origen origen;

    @ManyToOne(optional = false)
    @NotNull
    private Peticionario peticionario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Peticion numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public Peticion nombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
        return this;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getPaterno_solicitante() {
        return paterno_solicitante;
    }

    public Peticion paterno_solicitante(String paterno_solicitante) {
        this.paterno_solicitante = paterno_solicitante;
        return this;
    }

    public void setPaterno_solicitante(String paterno_solicitante) {
        this.paterno_solicitante = paterno_solicitante;
    }

    public String getMaterno_solicitante() {
        return materno_solicitante;
    }

    public Peticion materno_solicitante(String materno_solicitante) {
        this.materno_solicitante = materno_solicitante;
        return this;
    }

    public void setMaterno_solicitante(String materno_solicitante) {
        this.materno_solicitante = materno_solicitante;
    }

    public String getCargo_solicitante() {
        return cargo_solicitante;
    }

    public Peticion cargo_solicitante(String cargo_solicitante) {
        this.cargo_solicitante = cargo_solicitante;
        return this;
    }

    public void setCargo_solicitante(String cargo_solicitante) {
        this.cargo_solicitante = cargo_solicitante;
    }

    public String getDireccion_solicitante() {
        return direccion_solicitante;
    }

    public Peticion direccion_solicitante(String direccion_solicitante) {
        this.direccion_solicitante = direccion_solicitante;
        return this;
    }

    public void setDireccion_solicitante(String direccion_solicitante) {
        this.direccion_solicitante = direccion_solicitante;
    }

    public String getActo_constar() {
        return acto_constar;
    }

    public Peticion acto_constar(String acto_constar) {
        this.acto_constar = acto_constar;
        return this;
    }

    public void setActo_constar(String acto_constar) {
        this.acto_constar = acto_constar;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public Peticion fecha(ZonedDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public String getResponsable() {
        return responsable;
    }

    public Peticion responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Boolean isStatus_prevencion() {
        return status_prevencion;
    }

    public Peticion status_prevencion(Boolean status_prevencion) {
        this.status_prevencion = status_prevencion;
        return this;
    }

    public void setStatus_prevencion(Boolean status_prevencion) {
        this.status_prevencion = status_prevencion;
    }

    public Integer getTipo_evaluacion() {
        return tipo_evaluacion;
    }

    public Peticion tipo_evaluacion(Integer tipo_evaluacion) {
        this.tipo_evaluacion = tipo_evaluacion;
        return this;
    }

    public void setTipo_evaluacion(Integer tipo_evaluacion) {
        this.tipo_evaluacion = tipo_evaluacion;
    }

    public Boolean isStatus_trabajo() {
        return status_trabajo;
    }

    public Peticion status_trabajo(Boolean status_trabajo) {
        this.status_trabajo = status_trabajo;
        return this;
    }

    public void setStatus_trabajo(Boolean status_trabajo) {
        this.status_trabajo = status_trabajo;
    }

    public String getOficio() {
        return oficio;
    }

    public Peticion oficio(String oficio) {
        this.oficio = oficio;
        return this;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getOficio_prevencion() {
        return oficio_prevencion;
    }

    public Peticion oficio_prevencion(String oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
        return this;
    }

    public void setOficio_prevencion(String oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
    }

    public String getNotificacion_prevencion() {
        return notificacion_prevencion;
    }

    public Peticion notificacion_prevencion(String notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
        return this;
    }

    public void setNotificacion_prevencion(String notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
    }

    public String getRespuesta_prevencion() {
        return respuesta_prevencion;
    }

    public Peticion respuesta_prevencion(String respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
        return this;
    }

    public void setRespuesta_prevencion(String respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
    }

    public String getActa() {
        return acta;
    }

    public Peticion acta(String acta) {
        this.acta = acta;
        return this;
    }

    public void setActa(String acta) {
        this.acta = acta;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public Peticion acuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
        return this;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public Peticion notificacion(String notificacion) {
        this.notificacion = notificacion;
        return this;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Peticion respuesta(String respuesta) {
        this.respuesta = respuesta;
        return this;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Origen getOrigen() {
        return origen;
    }

    public Peticion origen(Origen origen) {
        this.origen = origen;
        return this;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public Peticionario getPeticionario() {
        return peticionario;
    }

    public Peticion peticionario(Peticionario peticionario) {
        this.peticionario = peticionario;
        return this;
    }

    public void setPeticionario(Peticionario peticionario) {
        this.peticionario = peticionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Peticion peticion = (Peticion) o;
        if (peticion.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, peticion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Peticion{" +
            "id=" + id +
            ", numero='" + numero + "'" +
            ", nombre_solicitante='" + nombre_solicitante + "'" +
            ", paterno_solicitante='" + paterno_solicitante + "'" +
            ", materno_solicitante='" + materno_solicitante + "'" +
            ", cargo_solicitante='" + cargo_solicitante + "'" +
            ", direccion_solicitante='" + direccion_solicitante + "'" +
            ", acto_constar='" + acto_constar + "'" +
            ", fecha='" + fecha + "'" +
            ", responsable='" + responsable + "'" +
            ", status_prevencion='" + status_prevencion + "'" +
            ", tipo_evaluacion='" + tipo_evaluacion + "'" +
            ", status_trabajo='" + status_trabajo + "'" +
            ", oficio='" + oficio + "'" +
            ", oficio_prevencion='" + oficio_prevencion + "'" +
            ", notificacion_prevencion='" + notificacion_prevencion + "'" +
            ", respuesta_prevencion='" + respuesta_prevencion + "'" +
            ", acta='" + acta + "'" +
            ", acuerdo='" + acuerdo + "'" +
            ", notificacion='" + notificacion + "'" +
            ", respuesta='" + respuesta + "'" +
            '}';
    }
}
