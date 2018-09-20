/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.carrero
 */
public class CategoriaDetailDTO extends CategoriaDTO {
    
    private ArrayList<GrupoDeInteresDTO> gruposDeInteres;   
    
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
        if (categEntity != null) {
            if (categEntity.getGrupos()!= null) {
                gruposDeInteres = new ArrayList<>();
                for (GrupoDeInteresEntity entityGrup : categEntity.getGrupos()) {
                   gruposDeInteres.add(new GrupoDeInteresDTO(entityGrup));
                }
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
    
    
    public ArrayList<GrupoDeInteresDTO> getGruposDeInteres(){
        
        return gruposDeInteres;
        
    }
    
    public void setGruposDeInteres(ArrayList<GrupoDeInteresDTO> pGruposDeInteres){
        
        gruposDeInteres = pGruposDeInteres;
        
    }
    
    
    
}
