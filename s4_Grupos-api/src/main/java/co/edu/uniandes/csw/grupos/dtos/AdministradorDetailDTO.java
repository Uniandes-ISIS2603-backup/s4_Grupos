/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link AdministradorDTO} para manejar las relaciones entre los
 * AdministradorDTO y otros DTOs. Para conocer el contenido de un Administrador vaya a la
 * documentacion de {@link AdministradorDTO}
 * @author ac.beltrans
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {
      //relación  uno o muchos grupos de interés
      private List<GrupoDeInteresDTO> gruposDeInteres;

    /**
     * Constructor vacío
     */
    public AdministradorDetailDTO() {
        super();
    }

    /**
     * Crea un objeto AdministradorDetailDTO a partir de un objeto AdministradorEntity
     * incluyendo los atributos de AdministradorDTO.
     *
     * @param administradorEntity Entidad AdministradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public AdministradorDetailDTO (AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            gruposDeInteres  = new ArrayList<>();
            for (GrupoDeInteresEntity entityGrupos : administradorEntity.getGruposDeInteres()) {
                gruposDeInteres.add(new GrupoDeInteresDTO(entityGrupos));
            }
        }
    }
    
    /**
     * Convierte un objeto AdministradorDetailDTO a AdministradorEntity incluyendo los
     * atributos de AdministradorDTO.
     *
     * @return Nueva objeto AdministradorEntity.
     *
     */
      @Override
    public AdministradorEntity ToEntity() {
        AdministradorEntity administradorEntity = super.ToEntity();
        if (gruposDeInteres != null) {
            List<GrupoDeInteresEntity> gruposEntity = new ArrayList<>();
            for (GrupoDeInteresDTO dtoGrupos : gruposDeInteres) {
                gruposEntity.add(dtoGrupos.toEntity());
            }
            administradorEntity.setGruposDeInteres(gruposEntity);
        }
        
        return administradorEntity;
    }

    /**
     * Devuelve los grupos de interes asociados al administrador
     *
     * @return Lista de DTOs de grupos de interes
     */
    public List<GrupoDeInteresDTO> getGruposDeInteres() {
        return gruposDeInteres;
    }

    /**
     * Modifica los grupos de interes del administrador.
     *
     * @param gruposDeInteres grupos de interes que se quieren modificar
     */
    public void setGruposDeInteres(List<GrupoDeInteresDTO> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }
    
    /**
     * Método toString
     * @return cadena de caracteres
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
