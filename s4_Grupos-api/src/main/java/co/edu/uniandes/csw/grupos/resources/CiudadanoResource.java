/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.dtos.CiudadanoDetailDTO;
import java.util.ArrayList;
import java.util.List;
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
    public CiudadanoDetailDTO createCiudadano(CiudadanoDetailDTO ciudadano)
    {
        //trhows Exception
        return ciudadano;
    }
    
    @GET
    public List<CiudadanoDetailDTO> getCiudadanos( )
    {
        return new ArrayList<CiudadanoDetailDTO>();
    }
    
    @GET
    @Path("{user :\\d+}")
    public CiudadanoDetailDTO getCiudadano(@PathParam("user") Long user)
    {
        return new CiudadanoDetailDTO();
    }
  
    
    /**
     * Actualiza un ciudadano con sus nuevas caacteristicas
     * @param ciudadano
     * @return un ciudadano actualizado
     */
    @PUT
    @Path("{user :\\d+}")
    public CiudadanoDetailDTO modificaCiudadano(@PathParam("user") Long user, CiudadanoDTO ciudadano )
    {
        return new CiudadanoDetailDTO();
    }
    
    /**
     * Elimina un ciudadano
     */
    @DELETE
    @Path("{user: \\d+}")
    public void eliminarCiudadano(@PathParam("user") Long user)
    {
        
    }
}
