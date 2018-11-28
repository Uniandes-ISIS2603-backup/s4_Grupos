package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.AdministradorDTO;
import co.edu.uniandes.csw.grupos.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.AdministradorLogic;
import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
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

    private static final Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

    @Inject
    private AdministradorLogic administradorLogic;
    
    /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param administrador {@link AdministradorDTO} - EL administrador que se desea a�adir.
     * @return JSON {@link AdministradorDTO} - El administrador guardado con su id
     * @throws BusinessLogicException cuando no se puede crear un administrador
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administrador.toString());
        AdministradorDTO administradorDTO;
        administradorDTO = new AdministradorDTO(administradorLogic.createAdministrador(administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", administradorDTO.toString());
        return administradorDTO;    
    }

    /**
     * Devuelve todos los administradores registrados.
     *
     * @return JSONArray {@link AdministradorDTO} - Los administradores
     * registrados. Si no hay ninguno retorna una lista vac�a.
     */
    @GET
    public List<AdministradorDetailDTO> getAdministradores() {
        LOGGER.info("AdministradorResource getAdministradores: input: void");
        List<AdministradorDetailDTO> listaAdministradores = listEntity2DTO(administradorLogic.getAdministradores());
        LOGGER.log(Level.INFO, "AdministradorResource getAdministradores: output: {0}", listaAdministradores.toString());
        return listaAdministradores;
    }

    /**
     * Busca un administrador por su id y lo retorna.
     *
     * @param administradorId Identificador del administrador que se esta buscando. Este debe
     * ser una cadena de d�gitos.
     * @return JSON {@link AdministradorDTO} - El administrador que se deseaba buscar.
     */
    @GET
    @Path("{administradorId: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("administradorId") Long administradorId) {
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: input: {0}", administradorId);
        AdministradorEntity administradorEntity = administradorLogic.getAdministrador(administradorId);
        if (administradorEntity == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradorId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorEntity);
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza el administrador con el id recibido desde la petici�n.
     * @param administradorId Identificador del administrador que se desea actualizar. Este debe
     * ser una cadena de d�gitos.
     * @param administrador {@link AdministradorDTO} El administrador que se desea guardar.
     * @return JSON {@link AdministradorDTO} - El adminisrador guardado.
     * @throws WebApplicationException error de lógica que se genera cuando no se encuentra el administrador.
     * @throws BusinessLogicException cuando no se puede crear un administrador
     */
    @PUT
    @Path("{administradoresId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("administradoresId") Long administradoresId, AdministradorDetailDTO administrador) throws BusinessLogicException {
       
         LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: input: id: {0} , Administrador: {1}", new Object[]{administradoresId, administrador.toString()});
        administrador.setId(administradoresId);
        if (administradorLogic.getAdministrador(administradoresId) == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId + " no existe.", 404);
        }
        AdministradorDetailDTO detailDTO = new AdministradorDetailDTO(administradorLogic.updateAdministrador(administradoresId, administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: output: {0}", detailDTO.toString());
        return detailDTO;

    }

    /**
     * Borra el administrador con el id asociado recibido en la URL.
     *
     * @param administradorId Identificador del administrador que se desea borrar. Este debe ser
     * una cadena de d�gitos.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el administrador.
     */
    @DELETE
    @Path("{administradorId: \\d+}")
    public void deleteAdministrador(@PathParam("administradorId") Long administradorId) throws BusinessLogicException {
    	LOGGER.log(Level.INFO, "AdministradorResource deleteAdministrador: input: {0}", administradorId);
        if (administradorLogic.getAdministrador(administradorId) == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradorId + " no existe.", 404);
        }
        administradorLogic.deleteAdministrador(administradorId);
        LOGGER.info("AdministradorResource deleteAdministrador: output: void");
    }
    
    /**
     * Convierte una lista de AdministradorEntity a una lista deAdministradorDetailDTO.
     *
     * @param entityList Lista de AdministradorEntity a convertir.
     * @return Lista de AdministradorDetailDTO convertida.
     */
    private List<AdministradorDetailDTO> listEntity2DTO(List<AdministradorEntity> entityList) {
        List<AdministradorDetailDTO> list = new ArrayList<>();
        for (AdministradorEntity entity : entityList) {
            list.add(new AdministradorDetailDTO(entity));
        }
        return list;
    }
}
