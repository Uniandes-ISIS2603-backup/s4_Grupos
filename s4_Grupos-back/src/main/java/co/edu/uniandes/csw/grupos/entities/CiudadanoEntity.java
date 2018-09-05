/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class CiudadanoEntity extends PersonaEntity implements Serializable
{
    public CiudadanoEntity()
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
    public void setUsuario(long usuario) {
        super.setUsuario(usuario); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getUsuario() {
        return super.getUsuario(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
