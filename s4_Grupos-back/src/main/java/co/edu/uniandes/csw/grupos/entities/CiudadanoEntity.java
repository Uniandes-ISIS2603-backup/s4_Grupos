/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un CiudadanoEntity
 * @author Daniel Augusto Ramirez Dueñas
 */
@Entity
public class CiudadanoEntity extends PersonaEntity implements Serializable
{
    @PodamExclude
    @ManyToMany
    private List<GrupoDeInteresEntity> gruposDeInteres = new ArrayList<GrupoDeInteresEntity>();
    
    @PodamExclude
    @OneToOne
    private ComentarioEntity comentario;

    public List<GrupoDeInteresEntity> getGruposDeInteres() {
        return gruposDeInteres;
    }

    public void setGruposDeInteres(List<GrupoDeInteresEntity> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }

    public ComentarioEntity getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioEntity comentario) {
        this.comentario = comentario;
    }
    
    public CiudadanoEntity()
    {
        super();
    }   
    
}
