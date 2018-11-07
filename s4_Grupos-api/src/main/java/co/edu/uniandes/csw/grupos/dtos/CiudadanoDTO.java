/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import java.io.Serializable;

/**
 * Clase que representa un CiduadanoDTO
 * @author Daniel Augusto Ramirez Dueñas
 */
public class CiudadanoDTO extends PersonaDTO implements Serializable
{
    /**
     * 
     */
    public CiudadanoDTO()
    {
    }
    
    /**
     * Crea un objeto CiudadanoDTO a partir de un objeto CiudadanoEntity
     * incluyendo los atributos de CiudadanoDTO.
     * @param ciudadanoEntity Entidad CiudadanoEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public CiudadanoDTO(CiudadanoEntity ciudadanoEntity) {
       
        super(ciudadanoEntity);

    }
    
        /**
       * M?todo para transformar el DTO a una entidad.
       *
       * @return La entidad del administrador asociado.
       */
    public CiudadanoEntity toEntity() {
        CiudadanoEntity ciudadanoEntity = new CiudadanoEntity();
        ciudadanoEntity.setId(this.id);
        ciudadanoEntity.setNombre(this.nombre);
        ciudadanoEntity.setContrasena(this.contrasena);
        return ciudadanoEntity;
    }
}
