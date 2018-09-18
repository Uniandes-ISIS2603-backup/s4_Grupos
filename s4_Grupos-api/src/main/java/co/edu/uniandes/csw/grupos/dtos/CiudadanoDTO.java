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

    public CiudadanoDTO(CiudadanoEntity ciudadanoEntity) {
       
        super(ciudadanoEntity);

    }
}
