/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.GrupoDeInteresDTO;
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
 *
 * @author estudiante
 */
@Path ("gruposdeinteres")
@Produces ("application/json")
@Consumes ("application/json")
@RequestScoped
public class GrupoDeInteresResource {   
    
    @POST
    public GrupoDeInteresDTO createGrupo( GrupoDeInteresDTO pGrupo){
        
        return pGrupo;        
        
    }    
    
    @GET
    public List<GrupoDeInteresDTO> getGrupos(){
        
        return new ArrayList<GrupoDeInteresDTO>();      
    }
    
     @GET
     @Path("{nombreGrupo: [a-zA-Z][a-zA-Z]*}")
    public GrupoDeInteresDTO getGrupo(@PathParam("nombregrupo") String pGrupo){
        
        return new GrupoDeInteresDTO();      
    }
    
    
     @PUT
     @Path("{nombregrupo: [a-zA-Z][a-zA-Z]*}")
    public GrupoDeInteresDTO modifyGrupo(@PathParam("nombregrupo") String pGrupo){
        
        return new GrupoDeInteresDTO();        
        
    }
    
    @DELETE
    @Path("{nombregrupo: [a-zA-Z][a-zA-Z]*}")
     public void deleteGrupo(@PathParam("nombregrupo") String pGrupo)
     {              
        
    }
    
}