package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.AdministradorDTO;
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
 * Clase que implementa el recurso "administradores".
 *
 * @author ac.beltrans
 * @version 1.0
 */
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {

    /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param administrador {@link AdministradorDTO} - EL administrador que se desea a�adir.
     * @return JSON {@link AdministradorDTO} - El administrador guardado con su id
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) {
        return administrador;    
    }

    /**
     * Devuelve todos los administradores registrados.
     *
     * @return JSONArray {@link AdministradorDTO} - Los administradores
     * registrados. Si no hay ninguno retorna una lista vac�a.
     */
    @GET
    public List<AdministradorDTO> getAdministradores() {
        return new ArrayList<AdministradorDTO>();
    }

    /**
     * Busca un administrador por su id y lo retorna.
     *
     * @param administradorId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de d�gitos.
     * @return JSON {@link AdministradorDTO} - El administrador que se deseaba buscar.
     */
    @GET
    @Path("{administradorId: \\d+}")
    public AdministradorDTO getAdministrador(@PathParam("administradorId") Long administradorId) {
        return new AdministradorDTO();
    }

    /**
     * Actualiza el administrador con el id recibido desde la petici�n.
     * @param administradorId Identificador del administrador que se desea actualizar. Este debe
     * ser una cadena de d�gitos.
     * @param administrador {@link AdministradorDTO} El administrador que se desea guardar.
     * @return JSON {@link AdministradorDTO} - El adminisrador guardado.
     */
    @PUT
    @Path("{administradorId: \\d+}")
    public AdministradorDTO updateAdministrador(@PathParam("administradorId") Long administradorId, AdministradorDTO administrador) {
        return administrador;    
    }

    /**
     * Borra el administrador con el id asociado recibido en la URL.
     *
     * @param administradorId Identificador del administrador que se desea borrar. Este debe ser
     * una cadena de d�gitos.
     * @return 
     */
    @DELETE
    @Path("{administradorId: \\d+}")
    public AdministradorDTO deleteAdministrador(@PathParam("administradorId") Long administradorId) {
    	return new AdministradorDTO();
    }
}
