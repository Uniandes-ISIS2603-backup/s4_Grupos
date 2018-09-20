/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.carrero
 */
@Entity
public class GrupoDeInteresEntity extends BaseEntity implements Serializable {
    
    
    private String nombre;
    
    private String descripcion;
    
    
    @PodamExclude
    @OneToMany (mappedBy = "grupoDeInteres")
    private List<EventoEntity> eventos;
    
    @PodamExclude
    @ManyToMany
    private List<AdministradorEntity> administradores = new ArrayList<AdministradorEntity>();
    
    @PodamExclude
    @ManyToMany
    private List<CiudadanoEntity> ciudadanos = new ArrayList<CiudadanoEntity>();
    
    @PodamExclude
    @ManyToMany
    private List<CategoriaEntity> categorias = new ArrayList<CategoriaEntity>();
      
    
//    @PodamExclude
//    @OneToMany
//    private List<BlogEntity> blogs;      
    
    
    @PodamExclude
    @OneToMany(mappedBy = "grupo", orphanRemoval=true, cascade=CascadeType.ALL)
    private List<NoticiaEntity> noticias = new ArrayList<NoticiaEntity>();
    
    public GrupoDeInteresEntity()
    {
        
    }
    public List<CategoriaEntity> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<CategoriaEntity> categorias) {
        this.categorias = categorias;
    }
    
    
    public List<EventoEntity> getEventos() {
        return eventos;
    }
    
    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }
    
    public List<AdministradorEntity> getAdministradores() {
        return administradores;
    }
    
    public void setAdministradores(List<AdministradorEntity> administradores) {
        this.administradores = administradores;
    }
    
    public List<CiudadanoEntity> getCiudadanos() {
        return ciudadanos;
    }
    
    public void setCiudadanos(List<CiudadanoEntity> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }
    
    public String getNombre() {
        
        return nombre;
    }
    
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String pDescripcion) {
        descripcion = pDescripcion;
    }
    
    
    /**
     * Devuelve las locaciones de el distrito.
     *
     * @return Lista de entidades de Distrito.
     */
    public List<NoticiaEntity> getNoticias () {
        return noticias;
    }
    
    
    /**
     * Modifica las locaciones de el distrito.
     *
     * @param locaciones Las nuevos locaciones.
     */
    public void setNoticias(List<NoticiaEntity> noticias) {
        this.noticias = noticias;
    }
    
    
}

