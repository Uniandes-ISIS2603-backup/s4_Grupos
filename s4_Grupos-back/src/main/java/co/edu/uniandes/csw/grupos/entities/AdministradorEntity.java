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
 * Clase que representa un AdministradorEntity
 * @author ac.beltrans
 */
@Entity
public class AdministradorEntity extends PersonaEntity implements Serializable {
    
    
    @PodamExclude
    @ManyToMany
    private List<GrupoDeInteresEntity> gruposDeInteres = new ArrayList<>();
    
    public AdministradorEntity()
    {
        super();
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
    
    
     @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }  
}
