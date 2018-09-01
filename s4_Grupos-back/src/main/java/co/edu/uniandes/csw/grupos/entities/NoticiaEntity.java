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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author estudiante
 */
@Entity
public class NoticiaEntity extends BaseEntity implements Serializable{
    private String descripcion;
private String rutaImagen;
/**@PodamExclude
@ManyToOne(cascade = CascadeType.PERSIST)
private GruposInteresEntity grupo;
**/
@PodamExclude
    @OneToMany(mappedBy = "noticia", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentarios = new ArrayList<ComentarioEntity>();

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
 /**
public GrupoDeInteresEntity getGrupoDeInteres() {
        return grupo;
    }

    public void setGrupoDeInteres(GrupoDeInteresEntity grupo) {
        this.grupo = grupo;
    }
    * */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
   

   

    
}
