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
 * Clase que representa un ComentarioDTO
 * @author Daniel Augusto Ramirez Dueñas
 */
public class ComentarioDTO implements Serializable
{
    /**
     * Identificador de un comentario
     */
    private Long id;
    
    /**
     * Nombre de quien hace un comentario
     */
    private String nombre;
    
    /**
     * Texto del comentario
     */
    private String texto;
    
    /**
     * Constructor por defecto
     */
    public ComentarioDTO()
    {
        
    }
    
    /**
     * Crea un objeto ComentarioDetailDTO a partir de un objeto ComentarioEntity
     * incluyendo los atributos de ComentarioDTO.
     * @param comentarioEntity Entidad ComentarioEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if (comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();
            this.nombre = comentarioEntity.getNombre();
            this.texto = comentarioEntity.getTexto();
        }
    }

    /**
     * Devuelve Identificador un comentario 
     * @return identificador del comentario
     */
    public Long getId() {
        return id;
    }

    /**
     * Actualiza el identificador un comentario
     * @param id identificador del comentario a actualizar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de quien hizo un comentario
     * @return nombre de quien hizo el comentario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre de quien hizo un comentario
     * @param nombre nombre de quien hizo el comentario a actualizar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el texto de un comentario
     * @return texto del comentario.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Actualiza el texto de un comentario
     * @param texto texto a actualizar.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    /**
     * Convierte un objeto ComentarioDetailDTO a ComentarioEntity incluyendo los
     * atributos de ComentarioDTO.
     *
     * @return Nuevo objeto ComentarioEntity.
     *
     */
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
