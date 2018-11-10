
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.GrupoDeInteresPersistence;
import co.edu.uniandes.csw.grupos.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Evento(Evento).
 *
 * @author ISIS2603
 */
@Stateless
public class EventoLogic {

    private static final Logger LOGGER = Logger.getLogger(EventoLogic.class.getName());

    @Inject
    private EventoPersistence persistence;

    @Inject
    private GrupoDeInteresPersistence grupoPersistence;

    /**
     * Se encarga de crear un Evento en la base de datos.
     *
     * @param eventoEntity Objeto de EventoEntity con los datos nuevos
     * @param gruposId id del GrupoDeInteres el cual sera padre del nuevo Evento.
     * @return Objeto de EventoEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si gruposId no es el mismo que tiene el
     * entity.
     *
     */
    public EventoEntity createEvento(Long gruposId, EventoEntity eventoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear evento");
        GrupoDeInteresEntity grupo = grupoPersistence.find(gruposId);
        if (!validateString(eventoEntity.getNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
        if(grupoPersistence.find(gruposId)==null)
        {
            throw new BusinessLogicException("El grupo no es valido");
        }
        eventoEntity.setGrupo(grupo);
        LOGGER.log(Level.INFO, "Termina proceso de creación del evento");
        return persistence.create(eventoEntity);
    }

    /**
     * Obtiene la lista de los registros de Evento que pertenecen a un GrupoDeInteres.
     *
     * @param gruposId id del GrupoDeInteres el cual es padre de los Eventos.
     * @return Colección de objetos de EventoEntity.
     */
    public List<EventoEntity> getEventos(Long gruposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los eventos asociados al grupo con id = {0}", gruposId);
        GrupoDeInteresEntity grupoEntity = grupoPersistence.find(gruposId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los eventos asociados al grupo con id = {0}", gruposId);
        return grupoEntity.getEventos();
    }

    /**
     * Obtiene los datos de una instancia de Evento a partir de su ID. La
     * existencia del elemento padre GrupoDeInteres se debe garantizar.
     *
     * @param gruposId El id del Grupo buscado
     * @param eventosId Identificador de la Evento a consultar
     * @return Instancia de EventoEntity con los datos del Evento consultado.
     *
     */
    public EventoEntity getEvento(Long gruposId, Long eventosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el evento con id = {0} del grupo con id = " + gruposId, eventosId);
        return persistence.find(gruposId, eventosId);
    }

    /**
     * Actualiza la información de una instancia de Evento.
     *
     * @param eventoEntity Instancia de EventoEntity con los nuevos datos.
     * @param gruposId id del GrupoDeInteres el cual sera padre del Evento actualizado.
     * @return Instancia de EventoEntity con los datos actualizados.
     *
     */
    public EventoEntity updateEvento(Long gruposId, EventoEntity eventoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el evento con id = {0} del grupo con id = " + gruposId, eventoEntity.getId());
        GrupoDeInteresEntity grupoEntity = grupoPersistence.find(gruposId);
        eventoEntity.setGrupo(grupoEntity);
        persistence.update(eventoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el evento con id = {0} del grupo con id = " + gruposId, eventoEntity.getId());
        return eventoEntity;
    }

    /**
     * Elimina una instancia de Evento de la base de datos.
     *
     * @param eventosId Identificador de la instancia a eliminar.
     * @param gruposId id del GrupoDeInteres el cual es padre del Evento.
     * @throws BusinessLogicException Si la evento no esta asociada al grupo.
     *
     */
    public void deleteEvento(Long gruposId, Long eventosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el evento con id = {0} del grupo con id = " + gruposId, eventosId);
        EventoEntity old = getEvento(gruposId, eventosId);
        if (old == null) {
            throw new BusinessLogicException("El evento con id = " + eventosId + " no esta asociado a el grupo con id = " + gruposId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el evento con id = {0} del grupo con id = " + gruposId, eventosId);
    }
    /**
     * Verifica que el string no sea invalido.
     *
     * @param string a verificar
     * @return true si el name es valido.
     */
    private boolean validateString(String string) {
        return !(string == null || string.isEmpty());
    }
}
