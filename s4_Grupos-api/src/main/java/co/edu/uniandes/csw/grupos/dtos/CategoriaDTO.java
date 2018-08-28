/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

/**
 *
 * @author estudiante
 */
public class CategoriaDTO {
    
    private String descripcion;
    
    private String nombre;
    
    public CategoriaDTO(){
        
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
}
