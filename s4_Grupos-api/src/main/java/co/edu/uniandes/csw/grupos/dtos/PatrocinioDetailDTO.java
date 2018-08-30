/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que extiende de {@link PatrocinioDTO} para manejar las relaciones entre los
 * PatrocinioDTO y otros DTOs. Para conocer el contenido de un Patrocinio vaya a la
 * documentacion de {@link PatrocinoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *     
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *     
 *   }
 *
 * </pre>
 *
 * @author Josealejandro Barbosa Jacome
 */
public class PatrocinioDetailDTO extends PatrocinioDTO implements Serializable
{
    /**
    // relación  cero a muchos Eventos
    private List<EventosDTO> eventos;

    public PatrocinioDetailDTO() {
        super();
    }
  
      /**
       * Constructor para transformar un Entity a un DTO
       *
       * @param PatrocinioEntity La entidad de la cual se construye el DTO
       */
    /**
      public PatrocinioDetailDTO(PatrocinioEntity patrocinadorEntity) {

      }
  
      /**
       * Transformar el DTO a una entidad
       *
       * @return La entidad que representa un administrador.
       */
    /**
      public PatrocinadorEntity toEntity() {
          
          
          return  PatrocinadorEntity;
      }

    /**
     * Devuelve los eventos asociados al Patrocinador
     *
     * @return Lista de DTOs de eventos
     */
    /**
    public List<EventosDTO> getEventos() {
        return eventos;
    }

    /**
     * Modifica los eventos del patrocinador.
     *
     * @param eventos eventos que se quieren modificar
     */
    /**
    public void setEventos(List<EventosDTO> gruposDeInteres) {
        this.eventos = gruposDeInteres;
    }    
    */
}
