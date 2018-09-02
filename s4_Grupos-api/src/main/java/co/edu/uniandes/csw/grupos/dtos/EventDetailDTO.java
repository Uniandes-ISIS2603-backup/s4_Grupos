/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class EventDetailDTO extends EventoDTO implements Serializable {

//    // relación  cero o muchos patrocinio
//    private List<PatrocinioDTO> patrocinios;
//
    public EventDetailDTO() {
        super();
    }

      /**
       * Constructor para transformar un Entity a un DTO
       *
       * @param eventoEntity La entidad de la cual se construye el DTO
       */
      public EventoDetailDTO(EventoEntity eventoEntity) {
      }
      /**
       * Transformar el DTO a una entidad
       *
       * @return La entidad que representa el evento.
       */
      @Override
      public EventoEntity toEntity() {
        return null;
      }

    /**
     * Devuelve los patrocinios asociados a un evento
     *
     * @return Lista de DTOs de Patrocinios
     */
    public List<PatrocinioDTO> getPatrocinios() {
        return patrocinios;
    }

    /**
     * Modifica los patrocinios de este evento.
     *
     * @param patrocinios Los nuevos patrocinios
     */
    public void setPatrocinios(List<PatrocinioDTO> patrocinios) {
        this.patrocinios = patrocinios;
    
    }
    
}