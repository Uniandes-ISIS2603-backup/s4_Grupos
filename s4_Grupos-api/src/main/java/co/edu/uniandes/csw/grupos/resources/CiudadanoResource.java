/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
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
 *
 * @author estudiante
 */
@Path("ciudadanos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CiudadanoResource 
{
    private static final Logger LOGGER = Logger.getLogger(CiudadanoResource.class.getName());
    
    /**
     * Crea un nuevo ciudadano
     * @param ciudadano
     * @return ciudadno creado
     */
    @POST
    public CiudadanoDTO createCiudadano(CiudadanoDTO ciudadano)
    {
        //trhows Exception
        return ciudadano;
    }
    
    /**
     * Consulta la información de un ciudadano
     * @param ciudadano
     * @return la información de un ciudadano
     */
    @GET
    @PathParam("(ciudadanosId: \\d++)")
    public List<CiudadanoDTO> consultaDeCiudadanos(@PathParam("ciudadanosId") List<CiudadanoDTO> ciudadanos) throws BusinessLogicException
    {
        return ciudadanos;
    }
    
    /**
     * Actualiza un ciudadano con sus nuevas caacteristicas
     * @param ciudadano
     * @return un ciudadano actualizado
     */
    @PUT
    public CiudadanoDTO modificaCiudadano(CiudadanoDTO ciudadano)
    {
        return ciudadano;
    }
    
    /**
     * Elimina un ciudadano
     */
    @DELETE
    public void eliminarCiudadano()
    {
        
    }
}
