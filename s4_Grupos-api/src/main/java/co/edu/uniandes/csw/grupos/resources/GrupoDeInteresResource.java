/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.GrupoDeInteresDTO;
import java.util.ArrayList;
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
    public GrupoDeInteresDTO createGrupo(@PathParam("nombregrupo") GrupoDeInteresDTO pGrupo){
        
        return pGrupo;        
        
    }    
    
    @GET
    public ArrayList<GrupoDeInteresDTO> getGrupos(ArrayList<GrupoDeInteresDTO> pGrupos){
        
        return pGrupos;      
    }
    
     @GET
    public GrupoDeInteresDTO getGrupo(@PathParam("nombregrupo") GrupoDeInteresDTO pGrupo){
        
        return pGrupo;      
    }
    
    
     @PUT
    public GrupoDeInteresDTO modifyGrupo(@PathParam("nombregrupo") GrupoDeInteresDTO pGrupo){
        
        return pGrupo;        
        
    }
    
    @DELETE
     public void deleteGrupo(@PathParam("nombregrupo") GrupoDeInteresDTO pGrupo){              
        
    }
    
}
