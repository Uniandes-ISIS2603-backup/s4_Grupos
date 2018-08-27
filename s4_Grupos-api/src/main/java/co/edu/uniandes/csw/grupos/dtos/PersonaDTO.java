/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.PersonaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Daniel Augusto Ramirez Dueñas
 */
public abstract class PersonaDTO implements Serializable
{
    private long usuario;
    private String nombre;
    private String contraseña;
    
    /**
     * Constructor por defecto
     */
    public PersonaDTO()
    {
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param personaEntity: Es la entidad que se va a convertir a DTO
     */
    public PersonaDTO(PersonaEntity personaEntity)
    {
        if (personaEntity != null)
        {
            this.usuario = personaEntity.getUsuario();
            this.nombre = personaEntity.getNombre();
            this.contraseña = personaEntity.getContraseña();
        }   
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public PersonaEntity toEntity()
    {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setUsuario(this.usuario);
        personaEntity.setNombre(this.nombre);
        personaEntity.setContraseña(this.contraseña);
        return personaEntity;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
