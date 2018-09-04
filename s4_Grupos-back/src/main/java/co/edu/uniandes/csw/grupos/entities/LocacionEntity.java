/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class LocacionEntity extends BaseEntity {
    
    /**
     * primary key
     * representa un identificador unico asignado por la DB
     */
    @Id
    private Long id;
    
    /**
     * Representa un el distrito al cual pertenece la locacion
     */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.ALL)
    private DistritoEntity distrito;
    
     /**
     * El nombre de la locacion
     */
     private String locacion;
      /**
     *La direcion de la locacion
     * por ejemplo car 1# 12-43
     */
     private String direccion;
      /**
     *componente de latitud geografica de la locacion
     */
     private Long latitud;
      /**
     * componente de longitud geografica de la locacion
     */
     private Long longitud;
     
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
     * retorna el identificador unico de la locacion
     * @return Long
     */
    public Long getId() {
        return id;
    }
    /**
     * modifica el identificador unico de la locacion
     * @param id Long
     */

    public void setId(Long id) {
        this.id = id;
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

    public String getDireccion() {
        return direccion;
    }

    /**
     * modifica la dorecion asociada a la locacion
     * @param direccion 
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * retorna la latitud de la locacion
     * @return 
     */

    public Long getLatitud() {
        return latitud;
    }
    
    /**
     * modifica la latitud de la locacion
     * @param latitud 
     */

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }
    
    /**
     * retorna la longitud de la locacion
     * @return 
     */

    public Long getLongitud() {
        return longitud;
    }

    /**
     * modifica la longitud de la locacion
     * @param longitud 
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }
    
    
}
