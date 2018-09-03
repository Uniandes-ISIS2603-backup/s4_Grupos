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
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
/**
 * Clase que implementa el recurso "noticias".
 *
 * @version 1.0
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class NoticiaResource {
   /**
     * Crea una nueva noticia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico.
     *
     * @param gruposId El ID del grupo del cual se le agrega la noticia
     * @param noticia {@link NoticiaDTO} - La noticia que se desea guardar.
     * @return JSON {@link NoticiaTO} - La noticia guardada 
     */
    @POST
    public NoticiaDTO createNoticia(@PathParam("gruposId") Long gruposID,NoticiaDTO noticia)
    {
        return noticia;
    }
   /**
     * Busca y devuelve todas las noticias que existen en un grupo.
     *
     * @param grupoId El ID del libro del cual se buscan las noticias
     * @return JSONArray {@link ReviewDTO} - Las noticias encontradas en el
     * grupo. Si no hay ninguna retorna una lista vacía.
     */
    
    @GET
    public List<NoticiaDTO> consultarNoticias(@PathParam("gruposId") Long gruposID)
    {
        return new ArrayList<NoticiaDTO>();
    }
    
     /**
     * Busca y devuelve la notcia con el ID recibido en la URL, relativa a un
     * grupo.
     *
     * @param grupoID El ID de del grupo cual se buscan las noticias
     * @param id El ID de la noticia que se busca
     * @return {@link NoticiaDTO} - La noticia encontrada en el grupo.
     */
    @GET
    @Path("{id:\\d+`}")
    public NoticiaDTO consultarNoticia(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id)
    {
        return new NoticiaDTO();
    }
    
    
    /**
     * Actualiza una notcia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param gruposId El ID del grupo del cual se guarda la reseña
     * @param id El ID de la noticia que se va a actualizar
     * @param noticia {@link ReviewDTO} - La noticia que se desea guardar.
     * @return JSON {@link ReviewDTO} - La noticia actualizada.
    
     */
    @PUT
     @Path("{id:\\d+`}")
    public NoticiaDTO editarNoticia(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id,NoticiaDTO noticia)
    {
        return new NoticiaDTO();
    }
    /**
     * Borra la noticia del grupo con el id con el id asociado recibido en la URL.
     * *@param gruposId El ID del grupo del cual se va a eliminar la noticia.
     * @param id Identificador de la noticia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteNoticia(@PathParam("gruposId") Long gruposID,@PathParam("id") Long id) {
       
    }
    /**
     * Conexión con el servicio de comentarios para una noticia. {@link ComentarioResource}
     *
     * Este método conecta la ruta de /noticias con las rutas de /comentatios que
     * dependen de la noticia, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las comentarios.
     *
     * @param noticiasId El ID de la noticia con respecto al cual se accede al
     * servicio.
     * @return El servicio de Comentarios para ese libro en paricular.\
     */
    @Path("{noticiasId: \\d+}/comentarios")
    public Class<ComentarioResource> getReviewResource(@PathParam("noticiasId") Long noticiassId) {
        /**if (bookLogic.getBook(booksId) == null) {
            throw new WebApplicationException("El recurso /books/" + booksId + "/reviews no existe.", 404);
        }
        * */
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
