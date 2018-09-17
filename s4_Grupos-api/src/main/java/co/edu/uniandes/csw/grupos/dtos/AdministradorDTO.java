/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import co.edu.uniandes.csw.grupos.entities.PersonaEntity;
import java.io.Serializable;

/**
 *
 * @author ac.beltrans
 */
public class AdministradorDTO extends PersonaDTO implements Serializable{
    //atributos

    /**
     * Constructor por defecto
     */
    public AdministradorDTO() {
        super();
    }

    AdministradorDTO(AdministradorEntity admin) {

        // HAY QUE HACER ESTE CONSTRUCTOR        
        
    }
    
    @Override
    public PersonaEntity toEntity() {
        return super.toEntity(); //To change body of generated methods, choose Tools | Templates.
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
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUsuario(Long usuario) {
        super.setUsuario(usuario); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getUsuario() {
        return super.getUsuario(); //To change body of generated methods, choose Tools | Templates.
    }
}
