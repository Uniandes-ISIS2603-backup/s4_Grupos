
package co.edu.uniandes.csw.grupos.resources;


import co.edu.uniandes.csw.grupos.dtos.DistritoDTO;
import co.edu.uniandes.csw.grupos.dtos.DistritoDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.DistritoLogic;
import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.mappers.BusinessLogicExceptionMapper;
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
 * Clase que implementa el recurso "distritos".
 *
 * @version 1.0
 */
@Path("distritos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DistritoResource {

     private static final Logger LOGGER = Logger.getLogger(DistritoResource.class.getName());

    @Inject
    private DistritoLogic distritoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias
    
    /**
     * Crea un nuevo libro con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param distrito {@link DistritoDTO} - EL libro que se desea guardar.
     * @return JSON {@link DistritoDTO} - El libro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el libro o el noombre es
     * inválido 
     */
    @POST
    public DistritoDetailDTO createDistrito(DistritoDTO distrito) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DistritoResource createDistrito: input: {0}", distrito.toString());
        DistritoDetailDTO nuevoDistritoDTO = new DistritoDetailDTO(distritoLogic.createDistrito(distrito.toEntity()));
        LOGGER.log(Level.INFO, "DistritoResource createDistrito: output: {0}", nuevoDistritoDTO.toString());
        return nuevoDistritoDTO;
    } 

    /**
     * Busca el distrito con el id asociado recibido en la URL y lo devuelve.
     *
     * @param distritosId Identificador de el distrito que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link DistritoDetailDTO} - El distrito buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el distrito.
     
     */
    @GET
    @Path("{distritosId: \\d+}")
    public DistritoDetailDTO getDistrito(@PathParam("distritosId") Long distritosId)  {
         LOGGER.log(Level.INFO, "DistritoResource getDistrito: input: {0}", distritosId);
        DistritoEntity distritoEntity = distritoLogic.getDistrito(distritosId);
        if (distritoEntity == null) {
            throw new WebApplicationException("El recurso /distritos/" + distritosId + " no existe.", 404);
        }
        DistritoDetailDTO distritoDetailDTO = new DistritoDetailDTO(distritoEntity);
        LOGGER.log(Level.INFO, "DistritoResource getDistrito: output: {0}", distritoDetailDTO.toString());
        return distritoDetailDTO;
    }

    /**
     * Actualiza la distrito con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param distritosId Identificador de el distrito que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param distrito {@link DistritoDetailDTO} El distrito que se desea
     * guardar.
     * @return JSON {@link DistritoDetailDTO} - El distrito guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el distrito a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el distrito.
     
     */
    @PUT
    @Path("{distritosId: \\d+}")
    public DistritoDetailDTO updateDistrito(@PathParam("distritosId") Long distritosId, DistritoDetailDTO distrito) throws BusinessLogicException {
       
         LOGGER.log(Level.INFO, "DistritoResource updateDistrito: input: id: {0} , Distrito: {1}", new Object[]{distritosId, distrito.toString()});
        distrito.setId(distritosId);
        if (distritoLogic.getDistrito(distritosId) == null) {
            throw new WebApplicationException("El recurso /distritos/" + distritosId + " no existe.", 404);
        }
        DistritoDetailDTO detailDTO = new DistritoDetailDTO(distritoLogic.updateDistrito(distritosId, distrito.toEntity()));
        LOGGER.log(Level.INFO, "DistritoResource updateDistrito: output: {0}", detailDTO.toString());
        return detailDTO;

    }

    /**
     * Borra la distrito con el id asociado recibido en la URL.
     *
     * @param distritosId Identificador de la distrito que se desea borrar.
     * Este debe ser una cadena de dígitos.
     *  @throws co.edu.uniandes.csw.distritostore.exceptions.BusinessLogicException
     * cuando el distrito tiene autores asociados.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el distrito.
  
     */
    @DELETE
    @Path("{distritosId: \\d+}")
    public void deleteDistrito(@PathParam("distritosId") Long distritosId) throws BusinessLogicException  {
       LOGGER.log(Level.INFO, "DistritoResource deleteDistrito: input: {0}", distritosId);
        DistritoEntity entity = distritoLogic.getDistrito(distritosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /distritos/" + distritosId + " no existe.", 404);
        }
        distritoLogic.deleteDistrito(distritosId);
        LOGGER.info("DistritoResource deleteDistrito: output: void");
    }
   /**
     * Busca y devuelve todas las distoitos que existen en la aplicacion.
     *
     * @return JSONArray {@link DistritoDetailDTO} - Las distritoes
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    /**/
    @GET
    public List<DistritoDetailDTO> getDistritos() {
        
        LOGGER.info("DistritoResource getDistritos: input: void");
        List<DistritoDetailDTO> listaDistritos = listEntity2DetailDTO(distritoLogic.getDistritos());
        LOGGER.log(Level.INFO, "DistritoResource getDistritos: output: {0}", listaDistritos.toString());
        return listaDistritos;
    }

     /**
     * Conexión con el servicio de locaciones para un distrito. {@link LocacionResource}
     *
     * Este método conecta la ruta de /distritos con las rutas de /locaciones que
     * dependen de el distrito, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las locaciones.
     *
     * @param distritosId El ID de el distrito con respecto al cual se accede al
     * servicio.
     * @return El servicio de Comentarios para ese distrito en paricular.\
     */
    @Path("{distritosId: \\d+}/locaciones")
    public Class<LocacionResource> getLocacionResource(@PathParam("distritosId") Long distritosId) {
        if (distritoLogic.getDistrito(distritosId) == null) {
            throw new WebApplicationException("El recurso /distritos/" + distritosId + "/locaciones no existe.", 404);
        }
        
        return LocacionResource.class;
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos DistritoEntity a una lista de
     * objetos DistritoDetailDTO (json)
     *
     * @param entityList corresponde a la lista del Distrito de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista del Distrito en forma DTO (json)
     */
    private List<DistritoDetailDTO> listEntity2DetailDTO(List<DistritoEntity> entityList) {
        List<DistritoDetailDTO> list = new ArrayList<>();
        for (DistritoEntity entity : entityList) {
            list.add(new DistritoDetailDTO(entity));
        }
        return list;
    }
}
