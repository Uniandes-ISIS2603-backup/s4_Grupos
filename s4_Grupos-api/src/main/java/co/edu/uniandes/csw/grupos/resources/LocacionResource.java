/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import co.edu.uniandes.csw.grupos.dtos.LocacionDTO;
import co.edu.uniandes.csw.grupos.ejb.LocacionLogic;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
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
 * Clase que implementa el recurso locaciones".
 *
 * @author Josealejandro barbosa Jacome ISIS2603
 * @version 1.0
 */
@Path("locaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LocacionResource {
    
    @Inject
    LocacionLogic locacionLogic;
    
    private final static String NOEXISTE1="El recurso /grupos/";
    private final static String NOEXISTE2="/locacions/";
    private final static String NOEXISTE3=" no existe";
     private static final Logger LOGGER = Logger.getLogger(LocacionResource.class.getName());

  /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param distritoId Id de la locacion
     * @param locacionDto La locacion que se desea anadir.
     * @return JSON La locacion  guardado con su id
     */
//    @PathParam("locacionId") Long locacionId,
    @POST
    public LocacionDTO crearLocacion(   @PathParam("distritosId") Long distritoId, LocacionDTO locacionDto) throws BusinessLogicException {
    
        LOGGER.log(Level.INFO, "LocacionResource createLocacion: input: {0}", locacionDto.toString()+ "hola mundo");    
        LocacionDTO nuevoLocacionDTO = new LocacionDTO( locacionLogic.createLocacion(distritoId ,locacionDto.toEntity()));
        LOGGER.log(Level.INFO, "LocacionResource termino createLocacion: output: {0}", nuevoLocacionDTO.toString());
        return nuevoLocacionDTO;
    }
    /**
     * Busca y devuelve todas las locacions que existen en un grupo.
     *
     * @param gruposID El ID del grupo del cual se buscan las locacions
     * @return JSONArray Las locacions encontradas en el
     * grupo. Si no hay ninguna retorna una lista vacía.
     * 
     */
    
    @GET
    public List<LocacionDTO> getLocaciones(@PathParam("distritosId") Long distritosID)
    {
         LOGGER.log(Level.INFO, "LocacionResource getLocacions: input: {0}", distritosID);
        List<LocacionDTO> listaDTOs = listEntity2DetailDTO(locacionLogic.getLocaciones(distritosID));
        LOGGER.log(Level.INFO, "EditorialGruposResource getGrupos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    /**
     * Busca un administrador por su id y lo retorna.
     *
     * @param localizacionId Identificador del localzacion que se esta buscando. Este debe
     * ser una cadena de d�gitos.
     * @return JSON La localizacion que se deseaba buscar.
     */
    @GET
    @Path("{locacionId: \\d+}")
    public LocacionDTO getLocacion( @PathParam("distritosId") Long distritoId,@PathParam("locacionId") Long locacionId) 
    {
       LOGGER.log(Level.INFO, "LocacionResource getLocacion: input: {0}", locacionId);
        LocacionEntity locacionEntity = locacionLogic.getLocacion(distritoId, locacionId);
        if (locacionEntity == null) {
            throw new WebApplicationException(NOEXISTE1 + locacionId + NOEXISTE2, 404);
        }
        LocacionDTO locacionDTO = new LocacionDTO(locacionEntity);
        LOGGER.log(Level.INFO, "LocacionResource getLocacion: output: {0}", locacionDTO.toString());
        return locacionDTO;
    }

    /**
     * Actualiza el localizacion con el id recibido desde la petici�n.
     * @param locacionId Identificador de la localizacion que se desea actualizar. Este debe
     * ser una cadena de d�gitos.
     * @param locacion DTO de la locacion
     * @return JSON La locacion guardado.
     */
    @PUT
    @Path("{locacionId: \\d+}")
    public LocacionDTO updateLocacion(  @PathParam("distritosId") Long distritoId,@PathParam("locacionId") Long id, LocacionDTO locacion) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "LocacionResource updateLocacion: input: distritoId: {0} , id: {1} , locacion:{2}", new Object[]{distritoId, id, locacion.toString()});
        if (!id.equals(locacion.getId())) {
            throw new BusinessLogicException("Los ids de la locacion no coinciden.");
        }
        LocacionEntity entity = locacionLogic.getLocacion(distritoId, id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + distritoId + NOEXISTE2 + id + NOEXISTE3, 404);

        }
        LocacionDTO locacionDTO = new LocacionDTO(locacionLogic.updateLocacion(distritoId, locacion.toEntity()));
        LOGGER.log(Level.INFO, "LocacionResource updateLocacion: output:{0}", locacionDTO.toString());
        return locacionDTO;
    }

    /**
     * Borra la locacion con el id asociado recibido en la URL.     *
     * @param locacionId Identificador de la locacion que se desea borrar. Este debe ser
     * una cadena de d�gitos.
     * @return la locacion
     */
    @DELETE
    @Path("{locacionId: \\d+}")
    public void deleteLocaciones( @PathParam("distritosId") Long distritosId,@PathParam("locacionId") Long id) throws BusinessLogicException 
    {LocacionEntity entity = locacionLogic.getLocacion(distritosId, id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + distritosId + NOEXISTE2 + id + NOEXISTE3, 404);
        }
        locacionLogic.deleteLocacion(distritosId, id);
    }
    
      /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos LocacionEntity a una lista de
     * objetos LocacionDetailDTO (json)
     *
     * @param entityList corresponde a la lista de locacions de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de locacions en forma DTO (json)
     */
    private List<LocacionDTO> listEntity2DetailDTO(List<LocacionEntity> entityList) {
        List<LocacionDTO> list = new ArrayList<>();
        for (LocacionEntity entity : entityList) {
            list.add(new LocacionDTO(entity));
        }
        return list;
    }
}
