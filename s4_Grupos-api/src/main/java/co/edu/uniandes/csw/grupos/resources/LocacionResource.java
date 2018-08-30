/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import co.edu.cuniandes.csw.grupos.dtos.LocacionDTO;
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
  /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param LocacionDto {@link LocaiconDTO} - La locacion que se desea anadir.
     * @return JSON {@link LocaiconDTO} -La locacion  guardado con su id
     */
    @POST
    public LocacionDTO crearLocacion(LocacionDTO locacionDto) {
    
        return locacionDto;
    }
    /**
     * Devuelve todos las locaciones registradas.
     *
     * @return JSONArray {@link LocacionDTO} - Las locaciones
     * registradas. Si no hay ninguna retorna una lista vac�a.
     */
    @GET
    public List<LocacionDTO> getLocaciones() 
    {
        return new LocacionDTO();
    }

    /**
     * Busca un administrador por su id y lo retorna.
     *
     * @param localizacionId Identificador del localzacion que se esta buscando. Este debe
     * ser una cadena de d�gitos.
     * @return JSON {@link localizacionDTO} - La localizacion que se deseaba buscar.
     */
    @GET
    @Path("locacionId: \\d+}")
    public LocacionDTO getLocacion(@PathParam("locacionId") Long localizacionId) 
    {
        return new LocacionDTO();
    }

    /**
     * Actualiza el localizacion con el id recibido desde la petici�n.
     * @param localizacionId Identificador de la localizacion que se desea actualizar. Este debe
     * ser una cadena de d�gitos.
     * @param localizacion {@link LocalizacionDTO} El administrador que se desea guardar.
     * @return JSON {@link LocalizacionDTO} - La locacion guardado.
     */
    @PUT
    @Path("locacionId: \\d+}")
    public LocacionDTO updateLocacion(@PathParam("LocacionId") Long locacionId, LocacionDTO locacion) 
    {
        return locacion;
    }

    /**
     * Borra la locacion con el id asociado recibido en la URL.
     *
     * @param locacionId Identificador de la locacion que se desea borrar. Este debe ser
     * una cadena de d�gitos.
     */
    @DELETE
    @Path("{locacionId: \\d+}")
    public LocacionDTO deleteLocaciones(@PathParam("locacionId") Long locacionId) 
    {
    	return new LocacionDTO();
    }
}
