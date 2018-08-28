/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.GrupoDeInteresDTO;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author estudiante
 */
public class GrupoDeInteresResource {
    
     @POST
    public GrupoDeInteresDTO createGrupo(GrupoDeInteresDTO pGrupo){
        
        return pGrupo;        
        
    }    
    
    @GET
    public ArrayList<GrupoDeInteresDTO> getGrupos(ArrayList<GrupoDeInteresDTO> pGrupos){
        
        return pGrupos;      
    }
    
    
     @PUT
    public GrupoDeInteresDTO modifyGrupo(GrupoDeInteresDTO pGrupo){
        
        return pGrupo;        
        
    }
    
    @DELETE
     public void deleteGrupo(){              
        
    }
    
}
