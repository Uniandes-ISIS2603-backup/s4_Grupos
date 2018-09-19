/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
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

    CiudadanoDTO(CiudadanoEntity ciud) {


        // HAY QUE HACER ESTE CONSTRUCTOR       
        
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
    public void setContrasena(String contrasena) {
        super.setContrasena(contrasena); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContrasena() {
        return super.getContrasena(); //To change body of generated methods, choose Tools | Templates.
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
    public void setId(Long usuario) {
        super.setId(usuario); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
