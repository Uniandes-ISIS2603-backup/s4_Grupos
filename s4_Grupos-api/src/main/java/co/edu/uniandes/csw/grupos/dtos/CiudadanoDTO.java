/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.PersonaEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Augusto Ramirez Dueñas
 */
public class CiudadanoDTO extends PersonaDTO implements Serializable
{
    public CiudadanoDTO()
    {
        
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
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
