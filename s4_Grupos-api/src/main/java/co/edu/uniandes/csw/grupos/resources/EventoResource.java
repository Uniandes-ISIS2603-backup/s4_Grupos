/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.csw.grupos.dtos.EventoDTO;
import co.edu.uniandes.csw.grupos.dtos.EventoDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.EventoLogic;
import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.grupos.mappers.WebApplicationExceptionMapper;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
/**
 * Clase que implementa el recurso "/eventos/".
 *
 * @version 1.0
 */
@Path ("eventos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EventoResource {
    
     private static final Logger LOGGER = Logger.getLogger(EventoResource.class.getName());

    @Inject
    private EventoLogic eventoLogic;
   /**
     * Crea una nueva evento con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico.
     *
     * @param gruposId El Id del grupo del cual se le agrega la evento
     * @param evento La evento que se desea guardar.
     * @return JSON La evento guardada 
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe la evento.
     */
    @POST
    public EventoDetailDTO createEvento(@PathParam("gruposId") Long gruposId, EventoDTO evento) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "EventoResource createEvento: input: {0}", evento.toString());
        EventoDetailDTO nuevoEventoDTO = new EventoDetailDTO(eventoLogic.createEvento(gruposId, evento.toEntity()));
        LOGGER.log(Level.INFO, "EventoResource createEvento: output: {0}", nuevoEventoDTO.toString());
        return nuevoEventoDTO;
    }
   /**
     * Busca y devuelve todas las eventos que existen en un grupo.
     *
     * @param gruposID El ID del grupo del cual se buscan las eventos
     * @return JSONArray Las eventos encontradas en el
     * grupo. Si no hay ninguna retorna una lista vacía.
     * 
     */
    
    @GET
    public List<EventoDetailDTO> consultarEventos(@PathParam("gruposId") Long gruposID)
    {
         LOGGER.log(Level.INFO, "EventoResource getEventos: input: {0}", gruposID);
        List<EventoDetailDTO> listaDTOs = listEntity2DetailDTO(eventoLogic.getEventos(gruposID));
        LOGGER.log(Level.INFO, "EditorialGruposResource getGrupos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
     /**
     * Busca y devuelve el evento con el ID recibido en la URL, relativa a un
     * grupo.
     *
     * @param gruposId El ID de del grupo cual se buscan las eventos
     * @param id El ID de la evento que se busca
     * @return {@link EventoDTO} - La evento encontrada en el grupo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}Error de lógica que se genera cuando no se encuentra la evento.
     */
    @GET
    @Path("{id:\\d+}")
    public EventoDTO consultarEvento(@PathParam("gruposId") Long gruposId,@PathParam("id") Long id)
    {
        LOGGER.log(Level.INFO, "EventoResource getEvento: input: {0}", id);
        EventoEntity entity = eventoLogic.getEvento(gruposId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposId + "/eventos/" + id + " no existe", 404);
        }
        EventoDTO eventoDTO = new EventoDTO(entity);
        LOGGER.log(Level.INFO, "EventoResource getEvento: output: {0}", eventoDTO.toString());
        return eventoDTO;
    }
    
    
    /**
     * Actualiza una notcia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param gruposID El ID del grupo del cual se guarda la evento
     * @param id El ID de la evento que se va a actualizar
     * @param evento {@link EventoDTO} - La evento que se desea guardar.
     * @return JSON {@link EventoDTO} - La evento actualizada.
     *  @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la evento.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la evento.
    
     */
    @PUT
     @Path("{id:\\d+}")
    public EventoDTO editarEvento(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id,EventoDTO evento) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EventoResource updateEvento: input: gruposID: {0} , id: {1} , evento:{2}", new Object[]{gruposID, id, evento.toString()});
        if (id.equals(evento.getId())) {
            throw new BusinessLogicException("Los ids del Evento no coinciden.");
        }
        EventoEntity entity = eventoLogic.getEvento(gruposID, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposID + "/eventos/" + id + " no existe", 404);

        }
        EventoDTO eventoDTO = new EventoDTO(eventoLogic.updateEvento(gruposID, evento.toEntity()));
        LOGGER.log(Level.INFO, "EventoResource updateEvento: output:{0}", eventoDTO.toString());
        return eventoDTO;
    }
    /**
     * Borra la evento del grupo con el id con el id asociado recibido en la URL.
     * @param gruposID El ID del grupo del cual se va a eliminar la evento.
     * @param id Identificador de la evento que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la evento.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la evento.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id) throws BusinessLogicException {
       EventoEntity entity = eventoLogic.getEvento(gruposID, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposID + "/eventos/" + id + " no existe", 404);
        }
        eventoLogic.deleteEvento(gruposID, id);
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EventoEntity a una lista de
     * objetos EventoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de eventos de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de eventos en forma DTO (json)
     */
    private List<EventoDetailDTO> listEntity2DetailDTO(List<EventoEntity> entityList) {
        List<EventoDetailDTO> list = new ArrayList<>();
        for (EventoEntity entity : entityList) {
            list.add(new EventoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Conexión con el servicio de patrocinios para una evento. {@link PatrocinioResource}
     *
     * Este método conecta la ruta de /eventos con las rutas de /comentatios que
     * dependen de la evento, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las patrocinios.
     *
     * @param eventosId El ID de la evento con respecto al cual se accede al
     * servicio.
     * @return El servicio de Patrocinios para ese grupo en paricular.\
     */
    @Path("{id: \\d+}/patrocinios")
    public Class<PatrocinioResource> getPatrocinioResource(@PathParam("id") Long eventosId) {
        /**if (grupoLogic.getGrupo(gruposID) == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposID + "/eventos no existe.", 404);
        }
        * */
        return PatrocinioResource.class;
    }

}
