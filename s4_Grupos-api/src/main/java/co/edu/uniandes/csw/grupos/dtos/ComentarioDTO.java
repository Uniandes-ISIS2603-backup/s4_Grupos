/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Daniel Augusto Ramirez Dueñas
 */
public class ComentarioDTO implements Serializable
{
    private long id;
    private String nombre;
    private String texto;
    
    public ComentarioDTO()
    {
        
    }
    
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if (comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();
            this.nombre = comentarioEntity.getNombre();
            this.texto = comentarioEntity.getTexto();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public ComentarioEntity toEntity()
    {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setId(this.id);
        comentarioEntity.setNombre(this.nombre);
        comentarioEntity.setTexto(this.texto);
        return comentarioEntity;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
