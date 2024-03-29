/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.io.Serializable;

/**
 * GrupoDeInteresDTO Objeto de transferencia de datos de GrupoDeInteres. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author s.carrero
 */
public class GrupoDeInteresDTO implements Serializable {
    
    private String nombre;
    
    private String descripcion;
    
    private Long id;
        
    
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
             id=grupoEntity.getId();
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
    
    public Long getId(){
        
        return id;
    }
   
    
    public void setNombre(String pNombre){
        
        nombre = pNombre;
    }
    
    public void setDescripcion(String pDescripcion){
        
        descripcion = pDescripcion;
    }
    
    public void setId(Long pId){
        
        id = pId;
    }    
 
    public GrupoDeInteresEntity toEntity()
    {
        GrupoDeInteresEntity grupoEntity = new GrupoDeInteresEntity();      
        grupoEntity.setNombre(nombre);
        grupoEntity.setDescripcion(descripcion);
        grupoEntity.setId(id);
        return grupoEntity;
    }     
}
