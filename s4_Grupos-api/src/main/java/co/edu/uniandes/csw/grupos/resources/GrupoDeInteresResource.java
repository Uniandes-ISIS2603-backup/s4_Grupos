/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.GrupoDeInteresDTO;
import co.edu.uniandes.csw.grupos.dtos.GrupoDeInteresDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.GrupoDeInteresLogic;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
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
 *
 * @author s.carrero
 */
@Path ("gruposdeinteres")
@Produces ("application/json")
@Consumes ("application/json")
@RequestScoped
public class GrupoDeInteresResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresResource.class.getName());
    
    @Inject
    private GrupoDeInteresLogic grupoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias
    
    
    
    /**
     * Crea un nuevo grupo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param pGrupo  - EL grupo que se desea guardar.
     * @return JSON - El grupo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el grupo o es inválido
     */
    @POST
    public GrupoDeInteresDTO createGrupo( GrupoDeInteresDTO pGrupo) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "GrupoDeInteresResource createGrupo: input: {0}", pGrupo.toString());
        
        GrupoDeInteresDetailDTO nuevoGrupoDTO = new GrupoDeInteresDetailDTO(grupoLogic.createGrupo(pGrupo.toEntity()));
        
        LOGGER.log(Level.INFO, "GrupoDeInteresResource createGrupo: output: {0}", nuevoGrupoDTO.toString());
        
        return nuevoGrupoDTO;
        
    }
    
    
     /**
     * 
     * Busca y devuelve todos los grupos que existen en la aplicacion.    
     * @return listaGrupos JSONArray Los grupos son encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     *
     */    
    @GET
    public List<GrupoDeInteresDetailDTO> getGrupos(){
        
        LOGGER.info("GrupoDeInteresResource getGrupos: input: void");        
        List<GrupoDeInteresDetailDTO> listaGrupos = entity2DetailDTO(grupoLogic.getGrupos());        
        LOGGER.log(Level.INFO, "GrupoDeInteresResource getGrupos: output: {0}", listaGrupos.toString());
        return listaGrupos;
    }
    
     /**
     * Busca el grupo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param pGrupoId Identificador de el grupo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON El grupo buscado
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el grupo.     
     */        
    @GET
    @Path("{grupoId: \\d+}")
    public GrupoDeInteresDetailDTO getGrupo(@PathParam("grupoId") Long pGrupoId)  {
        
        LOGGER.log(Level.INFO, "GrupoDeInteresResource getGrupoDeInteres: input: {0}", pGrupoId);
        GrupoDeInteresEntity grupoEntity =grupoLogic.getGrupo(pGrupoId);
        if (grupoEntity == null) {
            throw new WebApplicationException("El recurso /grupos/" + pGrupoId + " no existe.", 404);
        }
        GrupoDeInteresDetailDTO grupoDetailDTO = new GrupoDeInteresDetailDTO(grupoEntity);
        LOGGER.log(Level.INFO, "GrupoResourcec getGrupo: output: {0}", grupoDetailDTO.toString());
        return grupoDetailDTO;
    }
    
    /**
     * Actualiza el grupo con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param pGrupoId Identificador de el grupo que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param grupo El grupo que se desea
     * guardar.
     * @return JSON El grupo guardado.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el grupo a actualizar.
     * @throws BusinessLogicException Error de lógica que se genera cuando no se puede actualizar el grupo.
     */    
    @PUT
    @Path("{nombregrupo: \\d+}")
    public GrupoDeInteresDTO modifyGrupo(@PathParam("nombregrupo") Long pGrupoId, GrupoDeInteresDetailDTO grupo) throws BusinessLogicException {
        
         LOGGER.log(Level.INFO, "GrupoDeInteresResource modifyGrupo: input: id: {0} , Grupo: {1}", new Object[]{pGrupoId, grupo.toString()});
        grupo.setId(pGrupoId);
        if (grupoLogic.getGrupo(pGrupoId) == null) {
            throw new WebApplicationException("El recurso /grupos/" + pGrupoId + " no existe.", 404);
        }
        GrupoDeInteresDetailDTO detailDTO = new GrupoDeInteresDetailDTO(grupoLogic.updateGrupo(pGrupoId, grupo.toEntity()));
        LOGGER.log(Level.INFO, "GrupoDeInteresResource updateGrupo: output: {0}", detailDTO.toString());
        return detailDTO;
        
    }
    
    
     /**
     * Borra el grupo con el id asociado recibido en la URL.
     *
     * @param grupoId Identificador del grupo que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra el grupo.  
     */
    @DELETE
    @Path("{nombregrupo: \\d+}")
    public void deleteGrupo(@PathParam("nombregrupo") Long grupoId) {
        
        LOGGER.log(Level.INFO, "GrupoDeInteresResource deleteGrupo: input: {0}", grupoId);
        GrupoDeInteresEntity entity = grupoLogic.getGrupo(grupoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /distritos/" + grupoId + " no existe.", 404);
        }
        grupoLogic.deleteGrupo(grupoId);
        LOGGER.info("GrupoDeInteresResource deleteGrupo: output: void");
        
    }    
   
    /**
     * Conexión con el servicio de noticias para un grupo. {@link LocacionResource}
     *
     * Este método conecta la ruta de /grupos con las rutas de /noticias que
     * dependen de el grupo, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las noticias.
     *
     * @param gruposId El ID de el grupo con respecto al cual se accede al
     * servicio.
     * @return El servicio de Noticias para ese grupo en paricular.\
     */
    @Path("{gruposId: \\d+}/noticias")
    public Class<NoticiaResource> getNoticiaResource(@PathParam("gruposId") Long gruposId) {
        if (grupoLogic.getGrupo(gruposId) == null) {
          throw new WebApplicationException("El recurso /gruposdeinteres/" + gruposId + "/grupos no existe.", 404);
         }
         
        return NoticiaResource.class;
    }
    
    /**
     * Conexión con el servicio de eventos para un grupo. {@link LocacionResource}
     *
     * Este método conecta la ruta de /grupos con las rutas de /eventos que
     * dependen de el grupo, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los eventos.
     *
     * @param gruposId El ID de el grupo con respecto al cual se accede al
     * servicio.
     * @return El servicio de Eventos para ese grupo en paricular.\
     */
    @Path("{gruposId: \\d+}/eventos")
    public Class<EventoResource> getEventoResource(@PathParam("gruposId") Long gruposId) {
        if (grupoLogic.getGrupo(gruposId) == null) {
          throw new WebApplicationException("El recurso /gruposdeinteres/" + gruposId + "/grupos no existe.", 404);
         }
         
        return EventoResource.class;
    }
    
        /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos GrupoDeInteresEntity a una lista de
     * objetos GrupoDeInteresDetailDTO (json)
     *
     * @param entityList corresponde a la lista del GrupoDeInteres de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista del GrupoDeInteres en forma DTO (json)
     */
    private List<GrupoDeInteresDetailDTO> entity2DetailDTO(List<GrupoDeInteresEntity> entityList) {
        List<GrupoDeInteresDetailDTO> list = new ArrayList<>();
        for (GrupoDeInteresEntity entity : entityList) {
            list.add(new GrupoDeInteresDetailDTO(entity));
        }
        return list;
    }
    
}