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
import co.edu.uniandes.csw.grupos.dtos.NoticiaDTO;
import co.edu.uniandes.csw.grupos.dtos.NoticiaDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.NoticiaLogic;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
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
 * Clase que implementa el recurso NOEXISTE2.
 *
 * @version 1.0
 */
@Path ("noticias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class NoticiaResource {
    
    private final static String NOEXISTE1="El recurso /grupos/";
    private final static String NOEXISTE2="/noticias/";
    private final static String NOEXISTE3=" no existe";
     private static final Logger LOGGER = Logger.getLogger(NoticiaResource.class.getName());

    @Inject
    private NoticiaLogic noticiaLogic;
   /**
     * Crea una nueva noticia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico.
     *
     * @param gruposId El Id del grupo del cual se le agrega la noticia
     * @param noticia La noticia que se desea guardar.
     * @return JSON La noticia guardada 
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe la noticia.
     */
    @POST
    public NoticiaDetailDTO createReview(@PathParam("gruposId") Long gruposId, NoticiaDTO noticia) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "NoticiaResource createNoticia: input: {0}", noticia.toString());
        NoticiaDetailDTO nuevoNoticiaDTO = new NoticiaDetailDTO(noticiaLogic.createNoticia(gruposId, noticia.toEntity()));
        LOGGER.log(Level.INFO, "NoticiaResource createNoticia: output: {0}", nuevoNoticiaDTO.toString());
        return nuevoNoticiaDTO;
    }
   /**
     * Busca y devuelve todas las noticias que existen en un grupo.
     *
     * @param gruposID El ID del grupo del cual se buscan las noticias
     * @return JSONArray Las noticias encontradas en el
     * grupo. Si no hay ninguna retorna una lista vacía.
     * 
     */
    
    @GET
    public List<NoticiaDetailDTO> consultarNoticias(@PathParam("gruposId") Long gruposID)
    {
         LOGGER.log(Level.INFO, "NoticiaResource getNoticias: input: {0}", gruposID);
        List<NoticiaDetailDTO> listaDTOs = listEntity2DetailDTO(noticiaLogic.getNoticias(gruposID));
        LOGGER.log(Level.INFO, "EditorialGruposResource getGrupos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
     /**
     * Busca y devuelve la notcia con el ID recibido en la URL, relativa a un
     * grupo.
     *
     * @param gruposId El ID de del grupo cual se buscan las noticias
     * @param id El ID de la noticia que se busca
     * @return {@link NoticiaDTO} - La noticia encontrada en el grupo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}Error de lógica que se genera cuando no se encuentra la noticia.
     */
    @GET
    @Path("{id:\\d+}")
    public NoticiaDTO consultarNoticia(@PathParam("id") Long id)
    {
        LOGGER.log(Level.INFO, "NoticiaResource getNoticia: input: {0}", id);
        NoticiaEntity entity = noticiaLogic.getNoticia(id);
        if (entity == null) {
            throw new WebApplicationException( NOEXISTE2 + id + NOEXISTE3, 404);
        }
        NoticiaDetailDTO noticiaDTO = new NoticiaDetailDTO(entity);
        LOGGER.log(Level.INFO, "NoticiaResource getNoticia: output: {0}", noticiaDTO.toString());
        return noticiaDTO;
    }
    
    
    /**
     * Actualiza una notcia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param gruposID El ID del grupo del cual se guarda la noticia
     * @param id El ID de la noticia que se va a actualizar
     * @param noticia {@link NoticiaDTO} - La noticia que se desea guardar.
     * @return JSON {@link NoticiaDTO} - La noticia actualizada.
     *  @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la noticia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la noticia.
    
     */
    @PUT
     @Path("{id:\\d+}")
    public NoticiaDTO editarNoticia(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id,NoticiaDTO noticia) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "NoticiaResource updateNoticia: input: gruposID: {0} , id: {1} , noticia:{2}", new Object[]{gruposID, id, noticia.toString()});
        if (!id.equals(noticia.getId())) {
            throw new BusinessLogicException("Los ids del Noticia no coinciden.");
        }
        NoticiaEntity entity = noticiaLogic.getNoticia(id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + gruposID + NOEXISTE2 + id + NOEXISTE3, 404);

        }
        NoticiaDTO noticiaDTO = new NoticiaDTO(noticiaLogic.updateNoticia(gruposID, noticia.toEntity()));
        LOGGER.log(Level.INFO, "NoticiaResource updateNoticia: output:{0}", noticiaDTO.toString());
        return noticiaDTO;
    }
    /**
     * Borra la noticia del grupo con el id con el id asociado recibido en la URL.
     * @param gruposID El ID del grupo del cual se va a eliminar la noticia.
     * @param id Identificador de la noticia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la noticia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la noticia.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteNoticia(@PathParam("id") Long id) throws BusinessLogicException {
       NoticiaEntity entity = noticiaLogic.getNoticia(id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE2 + id + NOEXISTE3, 404);
        }
        noticiaLogic.deleteNoticia(id);
    }
    /**
     * Conexión con el servicio de comentarios para una noticia. {@link ComentarioResource}
     *
     * Este método conecta la ruta de /noticias con las rutas de /comentatios que
     * dependen de la noticia, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las comentarios.
     *
     * @param noticiassId El ID de la noticia con respecto al cual se accede al
     * servicio.
     * @return El servicio de Comentarios para ese grupo en paricular.\
     */
    @Path("{noticiasId: \\d+}/comentarios")
    public Class<ComentarioResource> getComentarioResource(@PathParam("noticiasId") Long noticiasId) {
        if (noticiaLogic.getNoticia(noticiasId) == null) {
            throw new WebApplicationException(NOEXISTE2 + noticiasId + "/comentarios no existe", 404);
        }

        return ComentarioResource.class;
        
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos NoticiaEntity a una lista de
     * objetos NoticiaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de noticias de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de noticias en forma DTO (json)
     */
    private List<NoticiaDetailDTO> listEntity2DetailDTO(List<NoticiaEntity> entityList) {
        List<NoticiaDetailDTO> list = new ArrayList<>();
        for (NoticiaEntity entity : entityList) {
            list.add(new NoticiaDetailDTO(entity));
        }
        return list;
    }

}
