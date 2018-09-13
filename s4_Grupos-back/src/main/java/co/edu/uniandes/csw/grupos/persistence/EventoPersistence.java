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
import javax.persistence.TypedQuery;
/**
 * Clase que maneja la persistencia para Evento. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author ac.beltrans
 */
@Stateless
public class EventoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EventoPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
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
     * Buscar un evento
     *
     * Busca si hay algun evento asociada a un Grupo de interés y con un ID específico
     *
     * @param grupoId El ID del grupo con respecto al cual se busca
     * @param eventosId El ID del evento buscado
     * @return El evento encontrado o null. Nota: Si existe uno o más eventos
     * devuelve siempre el primero que encuentra
     */
    public EventoEntity find(Long grupoId, Long eventosId) {
        LOGGER.log(Level.INFO, "Consultando el evento con id = {0} del grupo con id = " + grupoId, eventosId);
        TypedQuery<EventoEntity> q = em.createQuery("select p from EventoEntity p where (p.grupo.id = :grupoid) and (p.id = :eventosId)", EventoEntity.class);
        q.setParameter("grupoid", grupoId);
        q.setParameter("eventosId", eventosId);
        List<EventoEntity> results = q.getResultList();
        EventoEntity evento = null;
        if (results == null) {
            evento = null;
        } else if (results.isEmpty()) {
            evento = null;
        } else if (results.size() >= 1) {
            evento = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el evento con id = {0} del grupo con id =" + grupoId, eventosId);
        return evento;
    }

    /**
     * Actualiza un evento.
     * @param eventoEntity: el evento que viene con los nuevos cambios.
     * @return un evento con los cambios aplicados.
     */
    public EventoEntity update(EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el evento por el id = {0}", eventoEntity.getId());
        return em.merge(eventoEntity);
    }

    /**
     *
     * Borra un evento de la base de datos recibiendo como argumento el id del
     * evento
     *
     * @param idEvento: id correspondiente al evento a borrar.
     */
    public void delete(Long idEvento) {
        LOGGER.log(Level.INFO, "Borrando el evento por el id = {0}", idEvento);
        EventoEntity eventoEntity = em.find(EventoEntity.class, idEvento);
        em.remove(eventoEntity);
    }   
    
}
