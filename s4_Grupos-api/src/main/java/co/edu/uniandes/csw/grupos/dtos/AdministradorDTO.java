/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;


import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import java.io.Serializable;

/**
 * AdministradorDTO Objeto de transferencia de datos de Administradores. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author ac.beltrans
 */
public class AdministradorDTO extends PersonaDTO implements Serializable{
    

    /**
     * Constructor por defecto
     */
    public AdministradorDTO() {
        super();
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param administradorEntity: Es la entidad que se va a convertir a DTO
     */
    public AdministradorDTO(AdministradorEntity administradorEntity)
    {
        super (administradorEntity);
    }
    
    /**
     * Metodo para transformar el DTO a una entidad.
     *
     * @return La entidad del administrador asociado.
     */
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setId(this.id);
        administradorEntity.setNombre(this.nombre);
        administradorEntity.setContrasena(this.contrasena);
        return administradorEntity;
    }
}
