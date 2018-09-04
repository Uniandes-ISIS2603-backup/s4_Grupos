/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */

@Entity
public class CategoriaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "grupodeinteres")        
    
    private String nombre;
    
    private String descripcion;
    
    
    
    public String getNombre() {
        
        return nombre;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
     public void setDescripcion(String pDescripcion) {
        descripcion = pDescripcion;
    }

    
}
