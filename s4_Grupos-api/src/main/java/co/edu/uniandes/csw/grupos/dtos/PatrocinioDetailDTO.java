/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    // relación  cero a muchos Eventos
    private List<EventoDTO> eventos ;

    public PatrocinioDetailDTO() {
        super();
    }
  
      /**
       * Constructor para transformar un Entity a un DTO
       *
       * @param PatrocinioEntity La entidad de la cual se construye el DTO
       */
    
      public PatrocinioDetailDTO(PatrocinioEntity patrocinadorEntity) {
          super( patrocinadorEntity);
           eventos  = new ArrayList<>();
          if(patrocinadorEntity != null)
          {
              if(patrocinadorEntity.getEventos() != null)
              {
                    for(EventoEntity enty : patrocinadorEntity.getEventos()  )
                     {
                        eventos.add(new EventoDTO(enty));
                     }
              }
          }
      }
       
    
         /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de el patrocinio para transformar a Entity
     */
    @Override
    public PatrocinioEntity toEntity() {
        PatrocinioEntity patroEntity = super.toEntity();
        if (eventos != null) {
            List<EventoEntity> gruposEntity = new ArrayList<>();
            for (EventoDTO dtoEvento : eventos) {
                
                gruposEntity.add(dtoEvento.toEntity());
            }
             patroEntity.setEventos(gruposEntity);
        }
        
        return patroEntity;        
    }

    /**
     * Devuelve los eventos asociados al Patrocinador
     *
     * @return Lista de DTOs de eventos
     */
    
    public List<EventoDTO> getEventos() {
        return new ArrayList<EventoDTO>();
    }

    /**
     * Modifica los eventos del patrocinador.
     *
     * @param eventos eventos que se quieren modificar
     */
    
    public void setEventos(List<EventoDTO> pEventos) {
        this.eventos   = pEventos;
    }    
    
}
