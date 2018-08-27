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
import co.uniandes.csw.grupos.dtos.NoticiaDTO;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
/**
 * Clase que implementa el recurso "editorials".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("noticias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class NoticiaResourse {
   
    @POST
    public NoticiaDTO createNoticia(NoticiaDTO noticia)
    {
        return noticia;
    }
    
    @GET
    @Path("{id:\\d+`}")
    public NoticiaDTO consultarNoticia(@PathParam("id") Long id)
    {
        return new NoticiaDTO();
    }
    @PUT
     @Path("{id:\\d+`}")
    public NoticiaDTO editarNoticia(@PathParam("id") Long id)
    {
        return new NoticiaDTO();
    }
    /**
     * Borra la noticia con el id asociado recibido en la URL.
     *
     * @param id Identificador de la editorial que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEditorial(@PathParam("id") Long id) {
       
    }
}
