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
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un CategoriaEntity
 * @author s.carrero
 */

@Entity
public class CategoriaEntity extends BaseEntity implements Serializable {
         
    
    private String nombre;
    
    private String descripcion;
    
    
    @PodamExclude
    @ManyToMany
    private List<GrupoDeInteresEntity> grupos = new ArrayList<>();       
    
    
    public List<GrupoDeInteresEntity> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoDeInteresEntity> grupos) {
        this.grupos = grupos;
    }
    
    
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

      @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }  
    
}
