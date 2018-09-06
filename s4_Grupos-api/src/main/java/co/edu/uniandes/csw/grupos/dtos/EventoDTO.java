/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ac.beltrans
 */
public class EventoDTO implements Serializable {

    private Long id;
    private String nombre;
    private Date fecha;

    /*
    * Relaci�n a una locaci�n 
    * dado que esta tiene cardinalidad 1.
     */
     private LocacionDTO locacion;
    
    /*
    * Relaci�n con un grupo de inter�s 
    * dado que esta tiene cardinalidad 1.
     */
      private GrupoDeInteresDTO grupoDeInteres;

    /**
     * Constructor por defecto
     */
    public EventoDTO() {
    }

      /**
       * Constructor a partir de la entidad
       *
       * @param eventoEntity La entidad del evento
       */
//      public EventoDTO(EventoEntity eventoEntity) {
//          if (eventoEntity != null) {
//            this.nombre = eventoEntity.getNombre();
//            this.fecha = eventoEntity.getFecha();
//            this.id = eventoEntity.getId();
//            if (eventoEntity.getGrupoDeInteres() != null) {
//                this.grupoDeInteres = new GrupoDeInteresDTO(eventoEntity.getGrupoDeInteres());
//            } else {
//                this.grupoDeInteres = null;
//            }
//        }
//      }
//  
//      /**
//       * M�todo para transformar el DTO a una entidad.
//       *
//       * @return La entidad del evento asociado.
//       */
//      public EventoEntity toEntity() {
//        EventoEntity eventoEntity = new EventoEntity();
//        eventoEntity.setNombre(this.nombre);
//        eventoEntity.setFecha(this.fecha);
//        eventoEntity.setId(this.id);
//        if (this.grupoDeInteres != null) {
//            eventoEntity.setGrupoDeInteres(this.grupoDeInteres.toEntity());
//        }
//        return eventoEntity;
//      }
     
     /**
     * Devuelve el id del evento
     *
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del evento
     *
     * @param id id del evento a modificar
     */
    public void setId(Long id) {
        this.id = id;
    } 
      
      
    /**
     * Devuelve el nombre del evento
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del evento
     *
     * @param nombre nombre del evento a modificar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la fecha del evento.
     *
     * @return fecha evento
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Modifica la fecha de publicaci�n del libro.
     *
     * @param fecha fecha evento a modificar
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la locacion del evento.
     *
     * @return la locacion
     */
    public LocacionDTO getLocacion() {
        return locacion;
    }

    /**
     * Modifica la locacion asociada al evento
     *
     * @param locacion locacion a modificar
     */
    public void setLocacion(LocacionDTO locacion) {
        this.locacion = locacion;
    }
    
    /**
     * Devuelve el grupo de interes del evento.
     *
     * @return grupo de interes
     */
    public GrupoDeInteresDTO getGrupoDeInteres() {
        return grupoDeInteres;
    }

    /**
     * Modifica el grupo de interes asociada al evento
     *
     * @param grupoDeInteres el grupo de interes a modificar
     */
    public void setGrupoDeInteres(GrupoDeInteresDTO grupoDeInteres) {
        this.grupoDeInteres = grupoDeInteres;
    }
}