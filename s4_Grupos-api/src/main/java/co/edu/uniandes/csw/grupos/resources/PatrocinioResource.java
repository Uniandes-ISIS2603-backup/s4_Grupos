/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import co.edu.uniandes.csw.grupos.dtos.PatrocinioDetailDTO;
import co.edu.uniandes.csw.grupos.dtos.PatrocinioDTO;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import co.edu.uniandes.csw.grupos.ejb.PatrocinioLogic;
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
 * Clase que implementa el recurso patrocinios".
 *
 * @author Josealejandro barbosa Jacome ISIS2603
 * @version 1.0
 */

@Path("patrocinios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PatrocinioResource {
    
    @Inject
    PatrocinioLogic patrocinioLogic;
    /**
     * loger del resorce
     */
    private static final Logger LOGGER = Logger.getLogger(PatrocinioResource.class.getName());
    
    /**
     * constantes para mensajes de retorno
     */
    private final static String NOEXISTE1="El recurso /patrocinios/";    
    private final static String NOEXISTE2=" no existe.";
    /**
     * Crea un nuevo patrocinador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param patrocionioDto El patrocinador que se desea anadir.
     * @return JSON La patrocinio  guardado con su id
     * @throws BusinessLogicException Si ya existia el patrocinador
     */
    @POST
    public PatrocinioDTO crearPatrocinador(PatrocinioDTO patrocionioDto) throws BusinessLogicException {
    
        LOGGER.log(Level.INFO, "PatrocinioResource createPatrocinio: input: {0}", patrocionioDto.toString()+ "hola mundo");
        PatrocinioDTO nuevoPatrocinioDTO = new PatrocinioDTO(patrocinioLogic.createPatrocinio(patrocionioDto.toEntity()));
        LOGGER.log(Level.INFO, "PatrocinioResource termino createPatrocinio: output: {0}", nuevoPatrocinioDTO.toString());
        return nuevoPatrocinioDTO;
        
    }

    /**
     * Devuelve todos los patrocinadores registrados.
     *
     * @return JSONArray {@link PatrocinioDTO} - Los patrocinadores
     * registrados. Si no hay ninguna retorna una lista vac�a.
     */
    @GET
    public List<PatrocinioDetailDTO> getPatrocinadores()
    {
       LOGGER.info("PatrocinioResource getPatrocinios: input: void");
        List<PatrocinioDetailDTO> listaPatrocinios = listEntity2DetailDTO(patrocinioLogic.getPatrocinios());
        LOGGER.log(Level.INFO, "LocacionResource getPatrocinios: output: {0}", listaPatrocinios.toString());
        return listaPatrocinios;
    }
    

    /**
     * Busca un patrocinio por su nombre y lo retorna.
     *
     * @param patrociniosId identificador del patrocinador
     * @return JSON El patrocinio que se deseaba buscar.
     */
    @GET

    @Path("{patrocinadorId:  \\d+}")
    public PatrocinioDTO getPatrocinador(@PathParam("patrocinadorId") Long patrociniosId) 
    {
        LOGGER.log(Level.INFO, "PatrocinioResource getPatrocinio: input: {0}", patrociniosId);
        PatrocinioEntity patrocinioEntity = patrocinioLogic.getPatrocinio(patrociniosId);
        if (patrocinioEntity == null) {
            throw new WebApplicationException(NOEXISTE1 + patrociniosId + NOEXISTE2, 404);
        }
        PatrocinioDetailDTO patrocinioDetailDTO = new PatrocinioDetailDTO(patrocinioEntity);
        LOGGER.log(Level.INFO, "PatrocinioResource getPatrocinio: output: {0}", patrocinioDetailDTO.toString());
        return patrocinioDetailDTO;
    }

    /**
     * Actualiza el patrocinio con el id recibido desde la petici�n.
     * @param patrociniosId Identificador del patrocinio que se desea actualizar
     * @param patrocinio {@link PatrocinioDTO} El patrocinio que se desea guardar.
     * @return JSON {@link PatrocinioDTO} - El patrocinio guardado.
     * @throws BusinessLogicException Si no existe el patrocinio a actualizar.
     */
    @PUT
   @Path("{patrocinadorId:  \\d+}")
    public PatrocinioDTO updatePatrocinador(@PathParam("patrocinadorId") Long patrociniosId, PatrocinioDTO patrocinio) throws BusinessLogicException 
    {
        
         LOGGER.log(Level.INFO, "PatrocinioResource updatePatrocinio: input: id: {0} , Patrocinio: {1}", new Object[]{patrociniosId, patrocinio.toString()});
        patrocinio.setId(patrociniosId);
        if (patrocinioLogic.getPatrocinio(patrociniosId) == null) {
            throw new WebApplicationException(NOEXISTE1 + patrociniosId + NOEXISTE2, 404);
        }
        PatrocinioDetailDTO detailDTO = new PatrocinioDetailDTO(patrocinioLogic.updatePatrocinio(patrociniosId, patrocinio.toEntity()));
        LOGGER.log(Level.INFO, "PatrocinioResource updatePatrocinio: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el patrocinador con el Id  asociado recibido en la URL.
     *
     * 
     * @param patrociniosId Identificador del patrocinio que se desea eliminar
     * @throws BusinessLogicException Si no existia el patrocinio
     */
    @DELETE
    @Path("{patrocinadorId :  \\d+}")
    public void deletePatrocinador(@PathParam("patrocinadorId") Long patrociniosId) throws BusinessLogicException {
    	  LOGGER.log(Level.INFO, "PatrocinioResource deletePatrocinio: input: {0}", patrociniosId);
        PatrocinioEntity entity = patrocinioLogic.getPatrocinio(patrociniosId);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + patrociniosId + NOEXISTE2, 404);
        }
        patrocinioLogic.deletePatrocinio(patrociniosId);
        LOGGER.info("PatrocinioResource deletePatrocinio: output: void");
    }
    
      /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PatrocinioEntity a una lista de
     * objetos DistritoDetailDTO (json)
     *
     * @param entityList corresponde a la lista del Patrocinio de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista del Patrocinio en forma DTO (json)
     */
    private List<PatrocinioDetailDTO> listEntity2DetailDTO(List<PatrocinioEntity> entityList) {
        List<PatrocinioDetailDTO> list = new ArrayList<>();
        for (PatrocinioEntity entity : entityList) {
            list.add(new PatrocinioDetailDTO(entity));
        }
        return list;
    }
}
