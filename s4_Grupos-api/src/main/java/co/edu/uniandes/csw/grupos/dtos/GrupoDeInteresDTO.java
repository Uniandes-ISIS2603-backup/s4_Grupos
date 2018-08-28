/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class GrupoDeInteresDTO {
    
    private String nombre;
    
    private String descripcion;
    
    private String id;
    
    private ArrayList<CiudadanoDetailDTO> ciudadanos;
    
    public GrupoDeInteresDTO(){
        
    }
    
    public String getNombre(){
        
        return nombre;
    }
    
    public String getDescripcion(){
        
        return descripcion;
    }
    
    public String getId(){
        
        return id;
    }
    
    public ArrayList<CiudadanoDetailDTO> getCiudadanos(){
        
        return ciudadanos;        
    }
    
       public void setNombre(String pNombre){
        
        nombre = pNombre;
    }
    
    public void setDescripcion(String pDescripcion){
        
        descripcion = pDescripcion;
    }
    
    public void setId(String pId){
        
        id = pId;
    }
    
    public void setCiudadanos(ArrayList<CiudadanoDetailDTO> pCiudadanos){
        
        ciudadanos = pCiudadanos;        
    }
    
    
    
    
    
}
