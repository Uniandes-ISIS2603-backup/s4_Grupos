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
public class CategoriaDetailDTO extends CategoriaDTO {
    
    private ArrayList<GrupoDeInteresDTO> gruposDeInteres;
    
    
    
    public CategoriaDetailDTO(String pDescripcion, String pNombre){
        
        super(pDescripcion, pNombre);
        
    }
    
    public ArrayList<GrupoDeInteresDTO> getGruposDeInteres(){
        
        return gruposDeInteres;
        
    }
    
    public void setGruposDeInteres(ArrayList<GrupoDeInteresDTO> pGruposDeInteres){
        
        gruposDeInteres = pGruposDeInteres;
        
    }
    
    
    
}
