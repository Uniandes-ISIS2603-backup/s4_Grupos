/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 * Clase que maneja la persistencia para Evento. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author ac.beltrans
 */
@Stateless
public class EventoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EventoPersistence.class.getName());

    @PersistenceContext(unitName = "EventoStorePU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param eventoEntity objeto evento que se creará en la base de datos
     * @return devuelve la entidad creada con el nombre dado por la base de datos.
     */
    public EventoEntity create(EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Creando un evento nuevo");
        em.persist(eventoEntity);
        LOGGER.log(Level.INFO, "Evento creado");
        return eventoEntity;
    }

    /**
     * Devuelve todos los eventos de la base de datos.
     *
     * @return una lista con todos los eventos que encuentre en la base de datos,
     * "select u from EventoEntity u" es como un "select * from EventoEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<EventoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los eventos");
        Query q = em.createQuery("select u from EventoEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun evento con el nombre que se envía de argumento
     *
     * @param nombreEvento: nombre correspondiente al evento buscado.
     * @return un evento.
     */
    public EventoEntity find(String nombreEvento) {
        LOGGER.log(Level.INFO, "Consultando el evento por el nombre", nombreEvento);
        return em.find(EventoEntity.class, nombreEvento);
    }

    /**
     * Actualiza un evento.
     * @param eventoEntity: el evento que viene con los nuevos cambios.
     * @return un evento con los cambios aplicados.
     */
    public EventoEntity update(EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el evento por el nombre", eventoEntity.getId());
        return em.merge(eventoEntity);
    }

    /**
     *
     * Borra un evento de la base de datos recibiendo como argumento el nombre del
     * evento
     *
     * @param nombreEvento: nombre correspondiente al evento a borrar.
     */
    public void delete(String nombreEvento) {
        LOGGER.log(Level.INFO, "Borrando el evento por el nombre", nombreEvento);
        EventoEntity eventoEntity = em.find(EventoEntity.class, nombreEvento);
        em.remove(eventoEntity);
    }   
    
}
