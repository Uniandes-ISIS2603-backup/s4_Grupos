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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


/**
 * Clase que representa un distrito en la persistencia y permite su
 * serialización.
 *
 * @author ISIS2603
 */
@Entity
public class DistritoEntity extends BaseEntity implements Serializable {
   
    private String name;

    
    @PodamExclude
    @OneToMany(mappedBy = "distrito")
    private List<LocacionEntity> locaciones = new ArrayList<LocacionEntity>();
    

    /**
     * Devuelve el nombre de el distrito.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de el distrito.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve las locaciones de el distrito.
     *
     * @return Lista de entidades de Distrito.
     */
    public List<LocacionEntity> getLocaciones () {
        return locaciones;
    }
  

    /**
     * Modifica las locaciones de el distrito.
     *
     * @param locaciones Las nuevos locaciones.
     */
    public void setLocaciones(List<LocacionEntity> locaciones) {
        this.locaciones = locaciones;
    }
    


}
