/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que representa un ComentarioPersistence
 * @author Daniel Augusto Ramirez Dueñas
 */
@Stateless
public class ComentarioPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Crear un comentario
     *
     * Crea un nuevo comentario con la información recibida en la entidad.
     *
     * @param comentarioEntity  La entidad que representa la nueva comentario
     * @return La entidad creada
     */
    public ComentarioEntity create(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Creando un comentario nuevo");
        em.persist(comentarioEntity);
        LOGGER.log(Level.INFO, "Comentario creado");
        return comentarioEntity;
    }
    
    /**
     * Devuelve todos los comentario de la base de datos.
     *
     * @return una lista con todos los comentario que encuentre en la base de
     * datos, "select u from ComentarioEntity u" es como un "select * from
     * ComentarioEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ComentarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los comentario");
        // Se crea un query para buscar todas las comentario en la base de datos.
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de comentario.
        return query.getResultList();
    }

    public ComentarioEntity find(Long comentarioId) {
        LOGGER.log(Level.INFO, "Consultando comentario con id={0}", comentarioId);

        return em.find(ComentarioEntity.class, comentarioId);
    }
    /**
     * Actualizar una comentario
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param comentarioEntity  La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public ComentarioEntity update(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando comentario con id = {0}", comentarioEntity.getId());
        return em.merge(comentarioEntity);
    }

    /**
     * Eliminar un comentario
     *
     * Elimina un comentario asociado al  que recibe
     *
     * @param comentarioId El ID de la comentario que se desea borrar
     */
    public void delete(Long comentarioId) {
        LOGGER.log(Level.INFO, "Borrando comentario con Id = {0}", comentarioId);
        ComentarioEntity comentarioEntity = em.find(ComentarioEntity.class, comentarioId);
        em.remove(comentarioEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El comentario con Id = {0}", comentarioId);
    }
}

