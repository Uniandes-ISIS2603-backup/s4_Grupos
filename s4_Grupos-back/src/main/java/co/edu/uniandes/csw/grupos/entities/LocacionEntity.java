/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un LocacionEntity
 * @author j.barbosaj
 */
@Entity
public class LocacionEntity extends BaseEntity implements Serializable  {
    

    
    /**
     * Representa un el distrito al cual pertenece la locacion
     */
    @PodamExclude
    @ManyToOne()
    private DistritoEntity distrito;
    
    /**
     * representa un evento vinculado a la locacion
     */
    @PodamExclude
    @OneToOne
    private EventoEntity evento;
    
  
     /**
     * El nombre de la locacion
     */
     private String locacion;
      /**
     *La direcion de la locacion
     * por ejemplo car 1# 12-43
     */
     private String direcion;
     
     /**
      * representa el tipo de locacion
      */
     private String tipo;
      /**
     *componente de latitud geografica de la locacion
     */
     private Long latitud;
      /**
     * componente de longitud geografica de la locacion
     */
     private Long longitud;
            

       public LocacionEntity()
    {
        super();
    }
       /**
      * retorna el distrito al cual pertenece
      * @return DistritoEntity
      */ 
    public DistritoEntity getDistrito() {
        return distrito;
    }
    /**
      * modifica el distrito al cual pertenece
      * @param distrito DistritoEntity
      */   
    public void setDistrito(DistritoEntity distrito) {
        this.distrito = distrito;
    }

    

    /**
     * retorna el nombre de la localizacion
     * @return String
     */
    
    public String getLocacion() {
        return locacion;
    }
    
    /**
     * modifica el nombre de la locacion
     * @param locacion String
     */
    
    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }
    
    /**
     * retorna la direcion escrita de la locacion   
     * @return String
     */

    public String getDirecion() {
        return direcion;
    }

    /**
     * modifica la dorecion asociada a la locacion
     * @param direccion Direccion 
     */
    public void setDirecion(String direccion) {
        this.direcion = direccion;
    }
    
    /**
     * retorna la latitud de la locacion
     * @return la latitud
     */

    public Long getLatitud() {
        return latitud;
    }
    
    /**
     * modifica la latitud de la locacion
     * @param latitud La latitud ingresada por parametro
     */

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }
    
    /**
     * retorna la longitud de la locacion
     * @return la longitud
     */

    public Long getLongitud() {
        return longitud;
    }

    /**
     * modifica la longitud de la locacion
     * @param longitud la nueva longitud
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * retorna el evento vinculado a la locacion (este puede ser Nulo)
     * @return el evento relacionado
     */
    public EventoEntity getEvento() {
        return evento;
    }

    /**
     * modifica el evento relacionado con la locacion (puede ser Nulo)
     * @param evento el nuevo evento
     */
    public void setEvento(EventoEntity evento) {
        this.evento = evento;
    }

    /**
     * retorna el tipo de locacion
     * @return el tipo
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * modifica el tipo de locacion 
     * @param tipo el nuevo tipo
     */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }  
    
    
     @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }  
    
    
}
