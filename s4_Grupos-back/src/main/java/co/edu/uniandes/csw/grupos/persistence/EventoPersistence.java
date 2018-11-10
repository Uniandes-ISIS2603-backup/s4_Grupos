
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
 * 
 * @author ac.beltrans
 */
@Stateless
public class EventoPersistence {
    


    private static final Logger LOGGER = Logger.getLogger(EventoPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Crear una evento
     *
     * Crea una nueva evento con la información recibida en la entidad.
     *
     * @param eventoEntity La entidad que representa la nueva evento
     * @return La entidad creada
     */
    public EventoEntity create(EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Creando un evento nuevo");
        em.persist(eventoEntity);
        LOGGER.log(Level.INFO, "Evento creado");
        return eventoEntity;
    }

    /**
     * Actualizar una evento
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param eventoEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public EventoEntity update(EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Actualizando evento con id = {0}", eventoEntity.getId());
        return em.merge(eventoEntity);
    }

    /**
     * Eliminar una evento
     *
     * Elimina la evento asociada al ID que recibe
     *
     * @param eventosId El ID de la evento que se desea borrar
     */
    public void delete(Long eventosId) {
        LOGGER.log(Level.INFO, "Borrando evento con id = {0}", eventosId);
        EventoEntity eventoEntity = em.find(EventoEntity.class, eventosId);
        em.refresh(eventoEntity);
        em.remove(eventoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El evento con id = {0}", eventosId);
    }

    /**
     * Buscar una evento
     *
     * Busca si hay alguna evento asociada a un libro y con un ID específico
     *
     * @param grupoId El ID del libro con respecto al cual se busca
     * @param eventosId El ID de la evento buscada
     * @return La evento encontrada o null. Nota: Si existe una o más eventos
     * devuelve siempre la primera que encuentra
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
        LOGGER.log(Level.INFO, "Saliendo de consultar el evento con id = {0} del libro con id =" + grupoId, eventosId);
        return evento;
    }
}

   
