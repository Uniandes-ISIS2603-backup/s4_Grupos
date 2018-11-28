/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un EventoEntity
 * @author ac.beltrans
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private String fecha;
    
    @PodamExclude
    @ManyToOne()
    private GrupoDeInteresEntity grupo;
    
    @PodamExclude
    @ManyToMany
    private List<PatrocinioEntity> patrocinios = new ArrayList<>();
    
    @PodamExclude
    @OneToOne
    private LocacionEntity locacion;
    
    public EventoEntity()
    {
        super();
    }
    
    /**
     * Devuelve el nombre del evento.
     *
     * @return el nombre
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Modifica el nombre del evento.
     *
     * @param nombre nombre a cambiar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Devuelve la fecha del evento.
     *
     * @return la fecha
     */
    public String getFecha()
    {
        return fecha;
    }
    
    /**
     * Modifica la fecha del evento.
     *
     * @param fecha fecha a cambiar
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Devuelve el grupo de interés al que pertenece el evento.
     *
     * @return Una entidad de grupo de interes.
     */
    public GrupoDeInteresEntity getGrupo() {
        return grupo;
    }
    
    /**
     * Modifica el grupo de interes relacionado a un evento.
     *
     * @param grupoDeInteresEntity El nuevo grupo de interés.
     */
    public void setGrupo(GrupoDeInteresEntity grupoDeInteresEntity) {
        this.grupo = grupoDeInteresEntity;
    }
    
    /**
     * Devuelve la locacion a la que pertenece el evento.
     *
     * @return Una entidad de locacion.
     */
    public LocacionEntity getLocacion() {
        return locacion;
    }
    
    /**
     * Modifica la locacion de un evento.
     *
     * @param locacionEntity la nueva locacion.
     */
    public void setLocacion(LocacionEntity locacionEntity) {
        this.locacion = locacionEntity;
    }
    
    /**
     * Devuelve los patrocinios de un evento
     *
     * @return los patrocinios
     */
    public List<PatrocinioEntity> getPatrocinios() {
        return patrocinios;
    }

    /**
     * Modifica los patrocinios de un evento
     *
     * @param patrocinios los patrocinios a modificar
     */
    public void setPatrocinios(List<PatrocinioEntity> patrocinios) {
        this.patrocinios = patrocinios;
    }
    
     @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }  
}