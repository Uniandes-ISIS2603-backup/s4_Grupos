/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.grupos.resources;


import co.edu.uniandes.csw.grupos.dtos.DistritoDTO;
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
 * @author ISIS2603
 * @version 1.0
 */
@Path("distritos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DistritoResource {

    
    /**
     * Crea un nuevo distrito con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
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
     * @return JSON {@link DistritoDetailDTO} - La distrito buscada
     
     */
    @GET
    @Path("{distritosId: \\d+}")
    public DistritoDTO getDistrito(@PathParam("distritosId") Long distritosId)  {
        
        return new DistritoDTO();
    }

    /**
     * Actualiza la distrito con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param distritosId Identificador de el distrito que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param distrito {@link DistritoDetailDTO} El distrito que se desea
     * guardar.
     * @return JSON {@link DistritoDetailDTO} - La distrito guardada.
     
     */
    @PUT
    @Path("{distritosId: \\d+}")
    public DistritoDTO updateDistrito(@PathParam("distritosId") Long distritosId, DistritoDTO distrito) {
       
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
    /**
    @GET
    public List<DistritoDTO> getDistritos() {
        LOGGER.info("DistritoResource getDistritos: input: void");
        List<DistritoDetailDTO> listaDistritoes = listEntity2DetailDTO(distritoLogic.getDistritos());
        LOGGER.log(Level.INFO, "DistritoResource getDistritos: output: {0}", listaDistritoes.toString());
        return listaDistritoes;
    }
    */
}
