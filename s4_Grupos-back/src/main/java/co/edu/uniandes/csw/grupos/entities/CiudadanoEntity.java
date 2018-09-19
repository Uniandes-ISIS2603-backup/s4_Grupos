/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CiudadanoEntity extends PersonaEntity implements Serializable
{
    @PodamExclude
    @OneToMany
//(mappedBy = "grupodeinteres", fetch = FetchType.LAZY )
    List<GrupoDeInteresEntity> gruposDeInteres = new ArrayList<GrupoDeInteresEntity>();
    
    @PodamExclude
    @OneToOne
    ComentarioEntity comentario;

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

    @Override
    public void setContrasena(String contrasena) {
        super.setContrasena(contrasena); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContrasena() {
        return super.getContrasena(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
