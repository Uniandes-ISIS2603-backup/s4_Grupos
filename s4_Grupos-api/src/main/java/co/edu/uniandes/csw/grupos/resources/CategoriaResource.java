/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CategoriaDTO;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/**
 *
 * @author estudiante
 */
public class CategoriaResource {
    
    
    @POST
    public CategoriaDTO createCategoria(@PathParam("nombrecategoria") CategoriaDTO pCategoria){
        
        return pCategoria;        
        
    }    
    
    @GET
    public ArrayList<CategoriaDTO> getCategorias(ArrayList<CategoriaDTO> pCategorias){
        
        return pCategorias;      
    }
    
    
     @PUT
    public CategoriaDTO modifyCategoria(@PathParam("nombrecategoria") CategoriaDTO pCategoria){
        
        return pCategoria;        
        
    }
    
    @DELETE
     public void deleteCategoria(@PathParam("nombrecategoria") CategoriaDTO pCategoria){              
        
    }       
    
}
