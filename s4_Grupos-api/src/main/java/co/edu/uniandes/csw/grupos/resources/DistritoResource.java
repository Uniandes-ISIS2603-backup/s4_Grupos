
package co.edu.uniandes.csw.grupos.resources;


import co.edu.uniandes.csw.grupos.dtos.DistritoDTO;
import co.edu.uniandes.csw.grupos.dtos.DistritoDetailDTO;
import java.util.ArrayList;
import java.util.List;
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

    
    /**
     * Crea un nuevo distrito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico 
     *
     * @param distrito {@link DistritoDTO} - La distrito que se desea
     
     */
    @POST
    public DistritoDTO createDistrito(DistritoDTO distrito) {
        return distrito;
    }

 

    /**
     * Busca el distrito con el id asociado recibido en la URL y lo devuelve.
     *
     * @param distritosId Identificador de el distrito que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link DistritoDetailDTO} - El distrito buscada
     
     */
    @GET
    @Path("{distritosId: \\d+}")
    public DistritoDetailDTO getDistrito(@PathParam("distritosId") Long distritosId)  {
        
        return new DistritoDetailDTO();
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
     
     */
    @PUT
    @Path("{distritosId: \\d+}")
    public DistritoDetailDTO updateDistrito(@PathParam("distritosId") Long distritosId, DistritoDetailDTO distrito) {
       
        return distrito;

    }

    /**
     * Borra la distrito con el id asociado recibido en la URL.
     *
     * @param distritosId Identificador de la distrito que se desea borrar.
     * Este debe ser una cadena de dígitos.
  
     */
    @DELETE
    @Path("{distritosId: \\d+}")
    public void deleteDistrito(@PathParam("distritosId") Long distritosId)  {
        
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
        
        List<DistritoDetailDTO> listaDistritoes = new ArrayList<>();
        return listaDistritoes;
    }
    
}
