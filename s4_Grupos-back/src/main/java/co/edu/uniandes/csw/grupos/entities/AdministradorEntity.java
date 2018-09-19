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
 *
 * @author ac.beltrans
 */
@Entity
public class AdministradorEntity extends PersonaEntity implements Serializable {
    
    
    @PodamExclude
    @ManyToMany
    private List<GrupoDeInteresEntity> gruposDeInteres = new ArrayList<GrupoDeInteresEntity>();
    
    public AdministradorEntity()
    {
        super();
    }   
    
    @Override
    public void setContrasena(String contrasena) {
        super.getContrasena(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContrasena() {
        return super.getContrasena(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); 
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }
    
    /**
     * Devuelve los grupos de interes asociados al administrador
     *
     * @return Lista de DTOs de grupos de interes
     */
    public List<GrupoDeInteresEntity> getGruposDeInteres() {
        return gruposDeInteres;
    }

    /**
     * Modifica los grupos de interes del administrador.
     *
     * @param gruposDeInteres grupos de interes que se quieren modificar
     */
    public void setGruposDeInteres(List<GrupoDeInteresEntity> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }
    
}
