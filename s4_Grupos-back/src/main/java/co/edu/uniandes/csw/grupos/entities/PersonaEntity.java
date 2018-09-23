/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que representa una PersonaEntity
 * @author Daniel Ramirez - Andrea Beltran.
 */
@Entity
public class PersonaEntity extends BaseEntity implements Serializable
{
    private String nombre;
    private String contrasena;
    
    public PersonaEntity()
    {
        super();
    }
    
    /**
     * Devuelve el nombre de la Persona
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Modifica el nombre de la Persona
     *
     * @param nombre el nombre de la Persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Devuelve la contrasena de la Persona
     *
     * @return la contrasena
     */
    public String getContrasena() {
        return contrasena;
    }
    
    /**
     * Modifica la contrasena de la Persona
     *
     * @param contrasena la contrasena de la Persona
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
   
}
