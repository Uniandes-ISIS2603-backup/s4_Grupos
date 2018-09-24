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
 * PersonaDTO Objeto de transferencia de datos de Personas. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author Daniel Augusto Ramirez Dueñas
 */
public abstract class PersonaDTO implements Serializable
{
    protected Long id;
    protected String nombre;
    protected String contrasena;
    
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
            this.id = personaEntity.getId();
            this.nombre = personaEntity.getNombre();
            this.contrasena = personaEntity.getContrasena();
        }   
    }
    
    /**
     * Devuelve el Id de la Persona
     *
     * @return el id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Modifica el Id de la Persona
     *
     * @param id el id de la Persona
     */
    public void setId(Long id) {
        this.id = id;
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
    
    /**
       * M�todo para transformar el DTO a una entidad.
       *
       * @return La entidad de la Persona asociada.
       */
    public PersonaEntity toEntity()
    {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setId(this.id);
        personaEntity.setNombre(this.nombre);
        personaEntity.setContrasena(this.contrasena);
        return personaEntity;
    }
    
    /**
     * Método toString
     * @return la cadena de caracteres
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
