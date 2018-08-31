package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.EventoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso "eventos".
 *
 * @author ac.beltrans
 * @version 1.0
 */
@Path("eventos")
@Produces("application/json")
@Consumes("application/json")
public class EventoResource {

    /**
     * Crea un nuevo evento y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param evento {@link EventoDTO} - EL evento que se desea a�adir.
     * @return JSON {@link EventoDTO} - El evento guardado con su id
     */
    @POST
    public EventoDTO createEvento(EventoDTO evento) {
        return evento;
    }

    /**
     * Devuelve todos los eventos registrados.
     *
     * @return JSONArray {@link EventoDTO} - Los eventos
     * registrados. Si no hay ninguno retorna una lista vac�a.
     */
    @GET
    public List<EventoDTO> getEventos(List<EventoDTO> eventos) {
        return eventos;
    }

    /**
     * Busca un evento por su nombre y lo retorna.     *
     * @param nombreEvento Nombre del evento que se desea actualizar. Este debe
     * ser una cadena de caracteres.
     * @return JSON {@link EventoDTO} - El evento que se deseaba buscar.
     */
    @GET
    @Path("{nombreEvento: [a-zA-Z][a-zA-Z]*}}")
    public EventoDTO getEvento(@PathParam("nombreEvento") String nombreEvento) {
        return new EventoDTO();
    }

    /**
     * Actualiza el evento con el nombre recibido desde la petici�n.
     * @param nombreEvento Nombre del evento que se desea actualizar. Este debe
     * ser una cadena de caracteres.
     * @param nombreEvento {@link EventoDTO} El evento que se desea guardar.
     * @return JSON {@link EventoDTO} - El evento guardado.
     */
    @PUT
    @Path("{nombreEvento: [a-zA-Z][a-zA-Z]*}}")
    public EventoDTO updateEvento(@PathParam("nombreEvento") String nombreEvento, EventoDTO evento) {
        return evento;
    }

    /**
     * Borra el evento con el nobre asociado recibido en la URL.
     *
     * @param nombreEvento Nombre del evento que se desea borrar. Este debe ser
     * una cadena de caracteres.
     */
    @DELETE
    @Path("{nombreEvento: [a-zA-Z][a-zA-Z]*}}")
    public EventoDTO deleteEvento(@PathParam("nombreEvento") String nombreEvento) {
    	return new EventoDTO();
    }
}
