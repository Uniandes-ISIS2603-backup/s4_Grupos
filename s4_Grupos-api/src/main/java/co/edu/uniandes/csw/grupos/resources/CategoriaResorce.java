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

/**
 *
 * @author estudiante
 */
public class CategoriaResorce {
    
    
    @POST
    public CategoriaDTO createCategoria(CategoriaDTO pCategoria){
        
        return pCategoria;        
        
    }    
    
    @GET
    public ArrayList<CategoriaDTO> getCategorias(ArrayList<CategoriaDTO> pCategorias){
        
        return pCategorias;      
    }
    
    
     @PUT
    public CategoriaDTO modifyCategoria(CategoriaDTO pCategoria){
        
        return pCategoria;        
        
    }
    
    @DELETE
     public void deleteCategoria(){              
        
    }       
    
}
