/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.grupos.ejb.ComentarioLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


/**
 *
 * @author Daniel Augusto Ramirez Dueñas
 */
public class ComentarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
    ComentarioLogic comentarioLogic;
    
    /**
     * Crea un comentario
     * @param comentario
     * @return un comentario
     */
    @POST
    public ComentarioDTO  createComentario (ComentarioDTO comentario) 
    {
        //throws Exception
        
        return comentario;
    }
    
    /**
     * Consulta los comentario de un usuario
     * @return los comentarios de un usuario
     */
    @GET
    public ComentarioDTO consultarComentario()
    {
        return new ComentarioDTO();
    }
    
    /**
     * Modifica un comentario con el texto que le entra por paramtro
     * @param texto
     * @return el comentario modificado
     */
    @PUT
    public ComentarioDTO modificarComentario(ComentarioDTO texto)
    {
        return texto;
    }
    
    /**
     * Elimina un comentario
     */
    @DELETE
    public void eliminarComentario()
    {
        
    }
}
