/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;


/**
 * Clase que representa un ComentarioEntity
 * @author Daniel Augusto Ramirez Dueñas
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne
    private CiudadanoEntity ciudadano;
    
    @ManyToOne
    NoticiaEntity noticia;
    
    private String nombre;
    private String texto;


    public String getNombre() {
        return nombre;
    }

    public CiudadanoEntity getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(CiudadanoEntity ciudadano) {
        this.ciudadano = ciudadano;
    }

    public NoticiaEntity getNoticia() {
        return noticia;
    }

    public void setNoticia(NoticiaEntity noticia) {
        this.noticia = noticia;
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
    
     @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }  
    
    
}
