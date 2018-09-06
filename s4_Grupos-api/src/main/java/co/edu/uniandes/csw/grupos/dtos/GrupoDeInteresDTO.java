/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class GrupoDeInteresDTO implements Serializable {
    
    private String nombre;
    
    private String descripcion;
    
    private long id;
    
    private ArrayList<CiudadanoDetailDTO> ciudadanos;
    
    
    /**
     * Constructor por defecto.
     */    
    public GrupoDeInteresDTO(){
        
    }
    
    /**
     * Convierte un Entity a DTO con los valores del Entity que recibe por Parámetro.
     * @param grupoEntity Entity a convertir a DTO.
     */

    public GrupoDeInteresDTO(GrupoDeInteresEntity grupoEntity){        

         if (grupoEntity != null) {
         
            nombre = grupoEntity.getNombre();
            descripcion = grupoEntity.getDescripcion();

        }   

    }    
 
    
    public String getNombre(){
        
        return nombre;
    }
    
    public String getDescripcion(){
        
        return descripcion;
    }
    
    public long getId(){
        
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
    
    public void setId(long pId){
        
        id = pId;
    }
    
    public void setCiudadanos(ArrayList<CiudadanoDetailDTO> pCiudadanos){
        
        ciudadanos = pCiudadanos;        
    }
    
 
    public GrupoDeInteresEntity toEntity()
    {
        GrupoDeInteresEntity grupoEntity = new GrupoDeInteresEntity();      
        grupoEntity.setNombre(nombre);
        grupoEntity.setDescripcion(descripcion);
        return grupoEntity;
    }  
    
    
    
}
