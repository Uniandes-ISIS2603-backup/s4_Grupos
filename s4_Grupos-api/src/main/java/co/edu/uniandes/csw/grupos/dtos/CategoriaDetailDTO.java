/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link CategoriaDTO} para manejar las relaciones entre los
 * CategoriaDTO y otros DTOs. Para conocer el contenido de un Categoria vaya a la
 * documentacion de {@link CategoriaDTO}
 * @author s.carrero
 */
public class CategoriaDetailDTO extends CategoriaDTO implements Serializable {
    
    private List<GrupoDeInteresDTO> gruposDeInteres;
    
    public CategoriaDetailDTO(){
        
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param categEntity La entidad de el distrito para transformar a DTO.
     **/
    public CategoriaDetailDTO(CategoriaEntity categEntity) {
        super(categEntity);
        if (categEntity != null && categEntity.getGrupos()!= null) {
            gruposDeInteres = new ArrayList<>();
            for (GrupoDeInteresEntity entityGrup : categEntity.getGrupos()) {
                gruposDeInteres.add(new GrupoDeInteresDTO(entityGrup));
                
            }
        }
        
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de el distrito para transformar a Entity
     */
    @Override
    public CategoriaEntity toEntity() {
        CategoriaEntity categEntity = super.toEntity();
        if (gruposDeInteres != null) {
            List<GrupoDeInteresEntity> gruposEntity = new ArrayList<>();
            for (GrupoDeInteresDTO dtoGrupo : gruposDeInteres) {
                
                gruposEntity.add(dtoGrupo.toEntity());
            }
            categEntity.setGrupos(gruposEntity);
        }
        
        return categEntity;
    }
    
    
    public List<GrupoDeInteresDTO> getGruposDeInteres(){
        
        return gruposDeInteres;
        
    }
    
    public void setGruposDeInteres(List<GrupoDeInteresDTO> pGruposDeInteres){
        
        gruposDeInteres = pGruposDeInteres;
        
    }  
    
    
}
