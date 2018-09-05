/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;


/**
 *
 * @author estudiante
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable
{
   
    private String nombre;
    private String texto;
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private NoticiaEntity noticia;


    public NoticiaEntity getNoticia() {
        return noticia;
    }

    public void setNoticia(NoticiaEntity noticia) {
        this.noticia = noticia;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
}
