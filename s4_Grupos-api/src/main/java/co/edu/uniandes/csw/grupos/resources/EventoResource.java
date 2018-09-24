package co.edu.uniandes.csw.grupos.resources;
import co.edu.uniandes.csw.grupos.dtos.EventoDTO;
import co.edu.uniandes.csw.grupos.dtos.EventoDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.EventoLogic;
import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "eventos".
 *
 * @author ac.beltrans
 * @version 1.0
 */

@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EventoResource {

    private static final Logger LOGGER = Logger.getLogger(EventoResource.class.getName());
    
    @Inject
    private EventoLogic eventoLogic;
    
    /**
     * Crea un nuevo evento y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param gruposId EL ide del grupo sel cual se le agrega el evento
     * @param evento  EL evento que se desea a�adir.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el evento.
     * @return JSON El evento guardado con su id
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
     * Devuelve todos los eventos de un grupo de interés.     *
     * @param gruposId id del grupo de interés
     * @return JSONArray Los eventos registrados. Si no hay ninguno retorna una lista vac�a.
     */
    @GET
    public List<EventoDetailDTO> getEventos(@PathParam("gruposId") Long gruposId)
    {
         LOGGER.log(Level.INFO, "EventoResource getEventos: input: {0}", gruposId);
        List<EventoDetailDTO> listaDTOs = listEntity2DetailDTO(eventoLogic.getEventos(gruposId));
        LOGGER.log(Level.INFO, "eventoGruposResource getGrupos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Busca un evento por su nombre y lo retorna. 
     * @param gruposId El ID del grupo del cual se buscan los eventos
     * @param id Id del evento que se desea actualizar. Este debe
     * ser una cadena de caracteres.
     * @return JSON El evento que se deseaba buscar.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el evento.
     */
    @GET
    @Path("{id:\\d+}")
    public EventoDTO getEvento(@PathParam("gruposId") Long gruposId,@PathParam("id") Long id)
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
     * Actualiza el evento con el nombre recibido desde la petici�n.
     * @param gruposId El ID del grupo de interes del cual se guarda el evento
     * @param id Id del evento que se desea actualizar. Este debe
     * ser una cadena de caracteres.
     * @param evento {@link EventoDTO} El evento que se desea guardar.
     * @return JSON {@link EventoDTO} - El evento guardado.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el evento.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el evento.
     */
    @PUT
     @Path("{id:\\d+}")
    public EventoDTO updateEvento(@PathParam("gruposId") Long gruposId,@PathParam("id") Long id,EventoDTO evento) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EventoResource updateEvento: input: gruposId: {0} , id: {1} , evento:{2}", new Object[]{gruposId, id, evento.toString()});
        if (id.equals(evento.getId())) {
            throw new BusinessLogicException("Los ids del Evento no coinciden.");
        }
        EventoEntity entity = eventoLogic.getEvento(gruposId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposId + "/eventos/" + id + " no existe", 404);

        }
        EventoDTO eventoDTO = new EventoDTO(eventoLogic.updateEvento(gruposId, evento.toEntity()));
        LOGGER.log(Level.INFO, "EventoResource updateEvento: output:{0}", eventoDTO.toString());
        return eventoDTO;
    }

    /**
     * Borra el evento con el nobre asociado recibido en la URL.
     * @param gruposId El ID del grupo del cual se va a eliminar el evento.
     * @param id Id del evento que se desea borrar. Este debe ser
     * una cadena de caracteres.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el evento.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el evento.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("gruposId") Long gruposId,@PathParam("id") Long id) throws BusinessLogicException {
       EventoEntity entity = eventoLogic.getEvento(gruposId, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /grupos/" + gruposId + "/eventos/" + id + " no existe", 404);
        }
        eventoLogic.deleteEvento(gruposId, id);
    }
    
    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EventoEntity a una lista de
     * objetos EventoDTO (json)
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
}
