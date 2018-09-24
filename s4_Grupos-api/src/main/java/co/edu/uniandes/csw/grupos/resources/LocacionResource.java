/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import co.edu.uniandes.csw.grupos.dtos.LocacionDTO;
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
 * Clase que implementa el recurso locaciones".
 *
 * @author Josealejandro barbosa Jacome ISIS2603
 * @version 1.0
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LocacionResource {
  /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param locacionId Id de la locacion
     * @param locacionDto La locacion que se desea anadir.
     * @return JSON La locacion  guardado con su id
     */
    @POST
    public LocacionDTO crearLocacion(@PathParam("locacionId") Long locacionId, LocacionDTO locacionDto) {
    
        return  new LocacionDTO();
    }
    /**
     * Devuelve todos las locaciones registradas.
     * @return JSONArray {@link LocacionDTO} - Las locaciones
     * registradas. Si no hay ninguna retorna una lista vac�a.
     */
    @GET
    public List<LocacionDTO> getLocaciones() 
    {
        return new ArrayList<>();
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
    public LocacionDTO getLocacion(@PathParam("locacionId") Long localizacionId) 
    {
        return new LocacionDTO();
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
    public LocacionDTO updateLocacion( @PathParam("locacionId") Long locacionId, LocacionDTO locacion) 
    {
        return locacion;
    }

    /**
     * Borra la locacion con el id asociado recibido en la URL.     *
     * @param locacionId Identificador de la locacion que se desea borrar. Este debe ser
     * una cadena de d�gitos.
     * @return la locacion
     */
    @DELETE
    @Path("{locacionId: \\d+}")
    public LocacionDTO deleteLocaciones(@PathParam("locacionId") Long locacionId) 
    {
    	return new LocacionDTO();
    }
}
