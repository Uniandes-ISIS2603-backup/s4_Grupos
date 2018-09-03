/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
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
 *
 * @author estudiante
 */
@Path("ciudadanos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CiudadanoResource 
{    
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
     * Actualiza un ciudadano con sus nuevas caacteristicas
     * @param ciudadano
     * @return un ciudadano actualizado
     */
    @PUT
    @Path("{usuer :\\d+`}")
    public CiudadanoDTO modificaCiudadano(@PathParam("user") Long user)
    {
        return new CiudadanoDTO();
    }
    
    /**
     * Elimina un ciudadano
     */
    @DELETE
    @Path("{user: \\d+}")
    public void eliminarCiudadano(@PathParam("user")Long user)
    {
        
    }
}
