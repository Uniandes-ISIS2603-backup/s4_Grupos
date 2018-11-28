/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import java.io.Serializable;


/**
 * CategoriaDTO Objeto de transferencia de datos de Categoriaes. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author s.carrero
 */
public class CategoriaDTO implements Serializable{
    
    private String descripcion;
    
    private String nombre;
    
    private Long id;
    
    public CategoriaDTO(){
        
    }
    
    /**
     * Convierte un Entity a DTO con los valores del Entity que recibe por Parámetro.
     * @param catEntity Entity a convertir a DTO.
     */
    public CategoriaDTO(CategoriaEntity catEntity){
        if (catEntity != null) {
            id = catEntity.getId();
            nombre = catEntity.getNombre();
            descripcion = catEntity.getDescripcion();
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getDescripcion(){
        
        return descripcion;
    }
    
    public String  getNombre(){
        
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
