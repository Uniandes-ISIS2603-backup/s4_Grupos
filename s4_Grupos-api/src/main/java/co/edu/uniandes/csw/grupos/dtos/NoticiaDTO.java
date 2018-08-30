/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;
import co.uniandes.csw.grupos.dtos.*;
import java.io.Serializable;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;

/**
 *
 * @author estudiante
 */
public class NoticiaDTO implements Serializable {
      private String descripcion;
private String rutaImagen;
private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public NoticiaEntity toEntity()
    {
        NoticiaEntity noticia= new NoticiaEntity();
        noticia.setId(id);
        noticia.setDescripcion(descripcion);
        noticia.setRutaImagen(rutaImagen);
        return noticia;
    }
    
}
