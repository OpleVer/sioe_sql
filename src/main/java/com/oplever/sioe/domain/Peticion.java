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
    @Lob
    @Column(name = "oficio", nullable = false)
    private byte[] oficio;

    @Column(name = "oficio_content_type", nullable = false)
    private String oficioContentType;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @Column(name = "status_prevencion")
    private Boolean status_prevencion;

    @Lob
    @Column(name = "oficio_prevencion")
    private byte[] oficio_prevencion;

    @Column(name = "oficio_prevencion_content_type")
    private String oficio_prevencionContentType;

    @Lob
    @Column(name = "notificacion_prevencion")
    private byte[] notificacion_prevencion;

    @Column(name = "notificacion_prevencion_content_type")
    private String notificacion_prevencionContentType;

    @Lob
    @Column(name = "respuesta_prevencion")
    private byte[] respuesta_prevencion;

    @Column(name = "respuesta_prevencion_content_type")
    private String respuesta_prevencionContentType;

    @Column(name = "tipo_evaluacion")
    private Integer tipo_evaluacion;

    @Lob
    @Column(name = "acta_procede")
    private byte[] acta_procede;

    @Column(name = "acta_procede_content_type")
    private String acta_procedeContentType;

    @Lob
    @Column(name = "acuerdo_procede")
    private byte[] acuerdo_procede;

    @Column(name = "acuerdo_procede_content_type")
    private String acuerdo_procedeContentType;

    @Lob
    @Column(name = "notificacion_procede")
    private byte[] notificacion_procede;

    @Column(name = "notificacion_procede_content_type")
    private String notificacion_procedeContentType;

    @Lob
    @Column(name = "acuerdo_noprocede")
    private byte[] acuerdo_noprocede;

    @Column(name = "acuerdo_noprocede_content_type")
    private String acuerdo_noprocedeContentType;

    @Lob
    @Column(name = "notificacion_noprocede")
    private byte[] notificacion_noprocede;

    @Column(name = "notificacion_noprocede_content_type")
    private String notificacion_noprocedeContentType;

    @Lob
    @Column(name = "acuerdo_presentacion")
    private byte[] acuerdo_presentacion;

    @Column(name = "acuerdo_presentacion_content_type")
    private String acuerdo_presentacionContentType;

    @Lob
    @Column(name = "notificacion_presentacion")
    private byte[] notificacion_presentacion;

    @Column(name = "notificacion_presentacion_content_type")
    private String notificacion_presentacionContentType;

    @Column(name = "status_trabajo")
    private Boolean status_trabajo;

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

    public byte[] getOficio() {
        return oficio;
    }

    public Peticion oficio(byte[] oficio) {
        this.oficio = oficio;
        return this;
    }

    public void setOficio(byte[] oficio) {
        this.oficio = oficio;
    }

    public String getOficioContentType() {
        return oficioContentType;
    }

    public Peticion oficioContentType(String oficioContentType) {
        this.oficioContentType = oficioContentType;
        return this;
    }

    public void setOficioContentType(String oficioContentType) {
        this.oficioContentType = oficioContentType;
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

    public byte[] getOficio_prevencion() {
        return oficio_prevencion;
    }

    public Peticion oficio_prevencion(byte[] oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
        return this;
    }

    public void setOficio_prevencion(byte[] oficio_prevencion) {
        this.oficio_prevencion = oficio_prevencion;
    }

    public String getOficio_prevencionContentType() {
        return oficio_prevencionContentType;
    }

    public Peticion oficio_prevencionContentType(String oficio_prevencionContentType) {
        this.oficio_prevencionContentType = oficio_prevencionContentType;
        return this;
    }

    public void setOficio_prevencionContentType(String oficio_prevencionContentType) {
        this.oficio_prevencionContentType = oficio_prevencionContentType;
    }

    public byte[] getNotificacion_prevencion() {
        return notificacion_prevencion;
    }

    public Peticion notificacion_prevencion(byte[] notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
        return this;
    }

    public void setNotificacion_prevencion(byte[] notificacion_prevencion) {
        this.notificacion_prevencion = notificacion_prevencion;
    }

    public String getNotificacion_prevencionContentType() {
        return notificacion_prevencionContentType;
    }

    public Peticion notificacion_prevencionContentType(String notificacion_prevencionContentType) {
        this.notificacion_prevencionContentType = notificacion_prevencionContentType;
        return this;
    }

    public void setNotificacion_prevencionContentType(String notificacion_prevencionContentType) {
        this.notificacion_prevencionContentType = notificacion_prevencionContentType;
    }

    public byte[] getRespuesta_prevencion() {
        return respuesta_prevencion;
    }

    public Peticion respuesta_prevencion(byte[] respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
        return this;
    }

    public void setRespuesta_prevencion(byte[] respuesta_prevencion) {
        this.respuesta_prevencion = respuesta_prevencion;
    }

    public String getRespuesta_prevencionContentType() {
        return respuesta_prevencionContentType;
    }

    public Peticion respuesta_prevencionContentType(String respuesta_prevencionContentType) {
        this.respuesta_prevencionContentType = respuesta_prevencionContentType;
        return this;
    }

    public void setRespuesta_prevencionContentType(String respuesta_prevencionContentType) {
        this.respuesta_prevencionContentType = respuesta_prevencionContentType;
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

    public byte[] getActa_procede() {
        return acta_procede;
    }

    public Peticion acta_procede(byte[] acta_procede) {
        this.acta_procede = acta_procede;
        return this;
    }

    public void setActa_procede(byte[] acta_procede) {
        this.acta_procede = acta_procede;
    }

    public String getActa_procedeContentType() {
        return acta_procedeContentType;
    }

    public Peticion acta_procedeContentType(String acta_procedeContentType) {
        this.acta_procedeContentType = acta_procedeContentType;
        return this;
    }

    public void setActa_procedeContentType(String acta_procedeContentType) {
        this.acta_procedeContentType = acta_procedeContentType;
    }

    public byte[] getAcuerdo_procede() {
        return acuerdo_procede;
    }

    public Peticion acuerdo_procede(byte[] acuerdo_procede) {
        this.acuerdo_procede = acuerdo_procede;
        return this;
    }

    public void setAcuerdo_procede(byte[] acuerdo_procede) {
        this.acuerdo_procede = acuerdo_procede;
    }

    public String getAcuerdo_procedeContentType() {
        return acuerdo_procedeContentType;
    }

    public Peticion acuerdo_procedeContentType(String acuerdo_procedeContentType) {
        this.acuerdo_procedeContentType = acuerdo_procedeContentType;
        return this;
    }

    public void setAcuerdo_procedeContentType(String acuerdo_procedeContentType) {
        this.acuerdo_procedeContentType = acuerdo_procedeContentType;
    }

    public byte[] getNotificacion_procede() {
        return notificacion_procede;
    }

    public Peticion notificacion_procede(byte[] notificacion_procede) {
        this.notificacion_procede = notificacion_procede;
        return this;
    }

    public void setNotificacion_procede(byte[] notificacion_procede) {
        this.notificacion_procede = notificacion_procede;
    }

    public String getNotificacion_procedeContentType() {
        return notificacion_procedeContentType;
    }

    public Peticion notificacion_procedeContentType(String notificacion_procedeContentType) {
        this.notificacion_procedeContentType = notificacion_procedeContentType;
        return this;
    }

    public void setNotificacion_procedeContentType(String notificacion_procedeContentType) {
        this.notificacion_procedeContentType = notificacion_procedeContentType;
    }

    public byte[] getAcuerdo_noprocede() {
        return acuerdo_noprocede;
    }

    public Peticion acuerdo_noprocede(byte[] acuerdo_noprocede) {
        this.acuerdo_noprocede = acuerdo_noprocede;
        return this;
    }

    public void setAcuerdo_noprocede(byte[] acuerdo_noprocede) {
        this.acuerdo_noprocede = acuerdo_noprocede;
    }

    public String getAcuerdo_noprocedeContentType() {
        return acuerdo_noprocedeContentType;
    }

    public Peticion acuerdo_noprocedeContentType(String acuerdo_noprocedeContentType) {
        this.acuerdo_noprocedeContentType = acuerdo_noprocedeContentType;
        return this;
    }

    public void setAcuerdo_noprocedeContentType(String acuerdo_noprocedeContentType) {
        this.acuerdo_noprocedeContentType = acuerdo_noprocedeContentType;
    }

    public byte[] getNotificacion_noprocede() {
        return notificacion_noprocede;
    }

    public Peticion notificacion_noprocede(byte[] notificacion_noprocede) {
        this.notificacion_noprocede = notificacion_noprocede;
        return this;
    }

    public void setNotificacion_noprocede(byte[] notificacion_noprocede) {
        this.notificacion_noprocede = notificacion_noprocede;
    }

    public String getNotificacion_noprocedeContentType() {
        return notificacion_noprocedeContentType;
    }

    public Peticion notificacion_noprocedeContentType(String notificacion_noprocedeContentType) {
        this.notificacion_noprocedeContentType = notificacion_noprocedeContentType;
        return this;
    }

    public void setNotificacion_noprocedeContentType(String notificacion_noprocedeContentType) {
        this.notificacion_noprocedeContentType = notificacion_noprocedeContentType;
    }

    public byte[] getAcuerdo_presentacion() {
        return acuerdo_presentacion;
    }

    public Peticion acuerdo_presentacion(byte[] acuerdo_presentacion) {
        this.acuerdo_presentacion = acuerdo_presentacion;
        return this;
    }

    public void setAcuerdo_presentacion(byte[] acuerdo_presentacion) {
        this.acuerdo_presentacion = acuerdo_presentacion;
    }

    public String getAcuerdo_presentacionContentType() {
        return acuerdo_presentacionContentType;
    }

    public Peticion acuerdo_presentacionContentType(String acuerdo_presentacionContentType) {
        this.acuerdo_presentacionContentType = acuerdo_presentacionContentType;
        return this;
    }

    public void setAcuerdo_presentacionContentType(String acuerdo_presentacionContentType) {
        this.acuerdo_presentacionContentType = acuerdo_presentacionContentType;
    }

    public byte[] getNotificacion_presentacion() {
        return notificacion_presentacion;
    }

    public Peticion notificacion_presentacion(byte[] notificacion_presentacion) {
        this.notificacion_presentacion = notificacion_presentacion;
        return this;
    }

    public void setNotificacion_presentacion(byte[] notificacion_presentacion) {
        this.notificacion_presentacion = notificacion_presentacion;
    }

    public String getNotificacion_presentacionContentType() {
        return notificacion_presentacionContentType;
    }

    public Peticion notificacion_presentacionContentType(String notificacion_presentacionContentType) {
        this.notificacion_presentacionContentType = notificacion_presentacionContentType;
        return this;
    }

    public void setNotificacion_presentacionContentType(String notificacion_presentacionContentType) {
        this.notificacion_presentacionContentType = notificacion_presentacionContentType;
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
            ", oficio='" + oficio + "'" +
            ", oficioContentType='" + oficioContentType + "'" +
            ", responsable='" + responsable + "'" +
            ", status_prevencion='" + status_prevencion + "'" +
            ", oficio_prevencion='" + oficio_prevencion + "'" +
            ", oficio_prevencionContentType='" + oficio_prevencionContentType + "'" +
            ", notificacion_prevencion='" + notificacion_prevencion + "'" +
            ", notificacion_prevencionContentType='" + notificacion_prevencionContentType + "'" +
            ", respuesta_prevencion='" + respuesta_prevencion + "'" +
            ", respuesta_prevencionContentType='" + respuesta_prevencionContentType + "'" +
            ", tipo_evaluacion='" + tipo_evaluacion + "'" +
            ", acta_procede='" + acta_procede + "'" +
            ", acta_procedeContentType='" + acta_procedeContentType + "'" +
            ", acuerdo_procede='" + acuerdo_procede + "'" +
            ", acuerdo_procedeContentType='" + acuerdo_procedeContentType + "'" +
            ", notificacion_procede='" + notificacion_procede + "'" +
            ", notificacion_procedeContentType='" + notificacion_procedeContentType + "'" +
            ", acuerdo_noprocede='" + acuerdo_noprocede + "'" +
            ", acuerdo_noprocedeContentType='" + acuerdo_noprocedeContentType + "'" +
            ", notificacion_noprocede='" + notificacion_noprocede + "'" +
            ", notificacion_noprocedeContentType='" + notificacion_noprocedeContentType + "'" +
            ", acuerdo_presentacion='" + acuerdo_presentacion + "'" +
            ", acuerdo_presentacionContentType='" + acuerdo_presentacionContentType + "'" +
            ", notificacion_presentacion='" + notificacion_presentacion + "'" +
            ", notificacion_presentacionContentType='" + notificacion_presentacionContentType + "'" +
            ", status_trabajo='" + status_trabajo + "'" +
            '}';
    }
}
