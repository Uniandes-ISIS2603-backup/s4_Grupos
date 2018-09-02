/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;

/**
 *
 * @author estudiante
 */
public class CategoriaDTO {
    
    private String descripcion;
    
    private String nombre;
    
    public CategoriaDTO(){
        
    }
    
      /**
     * Convierte un Entity a DTO con los valores del Entity que recibe por Parámetro.
     * @param catEntity Entity a convertir a DTO.
     */
    public CategoriaDTO(CategoriaEntity catEntity){
        
         if (catEntity != null) {
         
            nombre = catEntity.getNombre();
            descripcion = catEntity.getDescripcion();
        }   
    } 
    
    public String getDescripcion(){
        
        return descripcion;
    }
    
    public String getNombre(){
        
        return nombre;
    }    
    
    public void setDescripcion(String pDescripcion){
        
        descripcion = pDescripcion;
        
    }
    
    public void setNombre(String pNombre){
        
        nombre = pNombre;
        
    }
    
     public CategoriaEntity toEntity()
    {
        CategoriaEntity catEntity = new CategoriaEntity();      
        catEntity.setNombre(nombre);
        catEntity.setDescripcion(descripcion);
        return catEntity;
    }
    
}
