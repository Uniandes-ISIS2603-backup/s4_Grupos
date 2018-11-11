/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *Clase que extiende de {@link EventoDTO} para manejar las relaciones entre los
 * EventoDTO y otros DTOs. Para conocer el contenido de un Evento vaya a la
 * documentacion de {@link EventoDTO}
 * @author ac.beltrans
 */
public class EventoDetailDTO extends EventoDTO implements Serializable {

//    // relación  cero o muchos patrocinios
     private List<PatrocinioDTO> patrocinios;
//
    
    /**
     * Constructor por defecto
     */
    public EventoDetailDTO() {
        super();
    }

      /**
       * Constructor para transformar un Entity a un DTO
       *
       * @param eventoEntity La entidad de la cual se construye el DTO
       */
      public EventoDetailDTO(EventoEntity eventoEntity) {
         super(eventoEntity);
        if (eventoEntity != null) {
            if (eventoEntity.getPatrocinios()!= null) {
                patrocinios = new ArrayList<>();
            for (PatrocinioEntity entityPatrocinio : eventoEntity.getPatrocinios()) {
                patrocinios.add(new PatrocinioDTO(entityPatrocinio));
            }}
        }
      }
      /**
       * Transformar el DTO a una entidad
       *
       * @return La entidad que representa el evento.
       */
      @Override
      public EventoEntity toEntity() {
        EventoEntity eventoEntity = super.toEntity();
        if (patrocinios!= null) {
            List<PatrocinioEntity> patrociniosEntity = new ArrayList<>();
            for (PatrocinioDTO dtoPatrocinio : getPatrocinios()) {
                patrociniosEntity.add(dtoPatrocinio.toEntity());
            }
            eventoEntity.setPatrocinios(patrociniosEntity);
        }
        return eventoEntity;
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
    
    /**
     * método toString
     * @return String- cadena de caracteres
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
