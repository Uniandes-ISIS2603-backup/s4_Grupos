/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un CiudadanoDetailDTO
 * @ciudadano Daniel Augusto Ramirez Dueñas
 */
public class CiudadanoDetailDTO extends CiudadanoDTO
{
    /**
     * Relación  cero o muchos grupos de interes
     */
    private List<GrupoDeInteresDTO> gruposDeInteres;
    
    /**
     * Constructor de la clase Ciudadano Detail DTO
     */
    public CiudadanoDetailDTO() 
    {
        super();
    }
    
    /**
     * Crea un objeto CiudadanoDetailDTO a partir de un objeto CiudadanoEntity
     * incluyendo los atributos de CiudadanoDTO.
     * @param ciudadanoEntity Entidad CiudadanoEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public CiudadanoDetailDTO(CiudadanoEntity ciudadanoEntity)
    {
        super(ciudadanoEntity);
        if (ciudadanoEntity != null) {
            gruposDeInteres = new ArrayList<>();
            for (GrupoDeInteresEntity entityGrupoDeInteress : ciudadanoEntity.getGruposDeInteres()) {
                gruposDeInteres.add(new GrupoDeInteresDTO(entityGrupoDeInteress));
            }
        }
    }

        /**
     * Convierte un objeto CiudadanoDetailDTO a CiudadanoEntity incluyendo los
     * atributos de CiudadanoDTO.
     *
     * @return Nueva objeto CiudadanoEntity.
     *
     */
    @Override
    public CiudadanoEntity toEntity() {
        CiudadanoEntity authorEntity = (CiudadanoEntity) super.toEntity();
        if (gruposDeInteres != null) {
            List<GrupoDeInteresEntity> gruposDeInteresEntity = new ArrayList<>();
            for (GrupoDeInteresDTO dtoGrupoDeInteres : gruposDeInteres) {
                gruposDeInteresEntity.add(dtoGrupoDeInteres.toEntity());
            }
            authorEntity.setGruposDeInteres(gruposDeInteresEntity);
        }
        return authorEntity;
    }
    /**
     * Lista de los grupos de interes a los cuales pertenece un ciudadano.
     * @return la lista de grupos de interes a los que pertenece un ciudadano
     */
    public List<GrupoDeInteresDTO> getGruposDeInteres() {
        return gruposDeInteres;
    }

    /**
     * Actualiza la lista de grupos de interes a los que pertenece un ciudadano
     * @param gruposDeInteres lista de grupos de interes que se va a actualizar.
     */
    public void setGruposDeInteres(List<GrupoDeInteresDTO> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }
    
    
}

