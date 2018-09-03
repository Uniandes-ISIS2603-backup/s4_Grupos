/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ac.beltrans
 */
public abstract class AdministradorEntity extends PersonaEntity implements Serializable {
    
    
    @PodamExclude
    @ManyToMany(mappedBy = "gruposDeInteres", fetch = javax.persistence.FetchType.LAZY)
    private List<GrupoDeInteresEntity> gruposDeInteres = new ArrayList<GrupoDeInteresEntity>();
    
    public AdministradorEntity()
    {
        super();
    }   
    
    @Override
    public void setContraseña(String contraseña) {
        super.setContraseña(contraseña); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContraseña() {
        return super.getContraseña(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); 
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    
    public void setUsuario(Long usuario) {
        super.setUsuario(usuario);
    }

    
    @Override
    public long getUsuario() {
        return super.getUsuario();
    }
    
    /**
     * Devuelve los grupos de interes asociados al administrador
     *
     * @return Lista de DTOs de grupos de interes
     */
    public List<GrupoDeInteresDTO> getGruposDeInteres() {
        return gruposDeInteres;
    }

    /**
     * Modifica los grupos de interes del administrador.
     *
     * @param gruposDeInteres grupos de interes que se quieren modificar
     */
    public void setGruposDeInteres(List<GrupoDeInteresDTO> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }
    
}
