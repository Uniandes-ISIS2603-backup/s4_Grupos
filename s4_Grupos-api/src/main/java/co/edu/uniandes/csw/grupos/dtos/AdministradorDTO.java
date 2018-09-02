/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class AdministradorDTO extends PersonaDTO implements Serializable{
    //atributos

    /**
     * Constructor por defecto
     */
    public AdministradorDTO() {
    }

      /**
       * Constructor a partir de la entidad
       *
       * @param administradorEntity La entidad del administrador
       */
      public AdministradorDTO(AdministradorEntity administradorEntity) {
         return null;
      }
  
      /**
       * Método para transformar el DTO a una entidad.
       *
       * @return La entidad del administrador asociado.
       */
      public AdministradorEntity toEntity() {
          return null;
      }
//
//    //metodos de los atributos get y set
}
