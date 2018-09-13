/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un evento en la persistencia y permite su serialización
 * @author ac.beltrans
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable{
    
    private String nombre;    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.ALL)
    private GrupoDeInteresEntity grupoDeInteres;
    
    @PodamExclude
    @ManyToMany(mappedBy = "eventos", fetch = javax.persistence.FetchType.LAZY)
    private List<PatrocinioEntity> patrocinios = new ArrayList<PatrocinioEntity>();
    
    @PodamExclude
    @OneToOne
    private LocacionEntity locacion;
    
    public EventoEntity()
    {
        
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
    public Date getFecha()
    {
        return fecha;
    }
    
    /**
     * Modifica la fecha del evento.
     *
     * @param fecha fecha a cambiar
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
     * Devuelve el grupo de interés al que pertenece el evento.
     *
     * @return Una entidad de grupo de interes.
     */
    public GrupoDeInteresEntity getGrupoDeInteres() {
        return grupoDeInteres;
    }
    
    /**
     * Modifica el grupo de interes relacionado a un evento.
     *
     * @param grupoDeInteresEntity El nuevo grupo de interés.
     */
    public void setGrupoDeInteres(GrupoDeInteresEntity grupoDeInteresEntity) {
        this.grupoDeInteres = grupoDeInteresEntity;
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
    
}
