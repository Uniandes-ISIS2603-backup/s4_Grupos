package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que representa un LocacionPersistence
 * @author j.barbosaj
 */
@Stateless
public class LocacionPersistence {
    
    
    private static final Logger LOGGER = Logger.getLogger(LocacionEntity.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param locacionEntity  objeto locacion que se creará en la base de datos
     * @return devuelve la entidad creada con el nombre dado por la base de datos.
     */
    public LocacionEntity create(LocacionEntity locacionEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva locacion");
        em.persist(locacionEntity);
        LOGGER.log(Level.INFO, "Locacion creado");
        return locacionEntity;
    }

    /**
     * Devuelve todos las locaciones de la base de datos.
     *
     * @return una lista con todas las locaciones que encuentre en la base de datos,
     * "select u from LocacionesEntity u" es como un "select * from LocacionEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<LocacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las locaciones");
        Query q = em.createQuery("select u from LocacionEntity u");
        return q.getResultList();
    }

    /**
     * Buscar una locacion
     *
     * Busca si hay alguna locacion asociada a un libro y con un ID específico
     *
     * @param distritoId El ID del libro con respecto al cual se busca
     * @param locacionsId El ID de la locacion buscada
     * @return La locacion encontrada o null. Nota: Si existe una o más locacions
     * devuelve siempre la primera que encuentra
     */
    public LocacionEntity find(Long distritoId, Long locacionId) {
       LOGGER.log(Level.INFO, "Consultando el locacion con id = {0} del libro con id = " + distritoId, locacionId);
        TypedQuery<LocacionEntity> q = em.createQuery("select p from LocacionEntity p where (p.distrito.id = :distritoid) and (p.id = :locacionId)", LocacionEntity.class);
        q.setParameter("distritoid", distritoId);
        q.setParameter("locacionId", locacionId);
        List<LocacionEntity> results = q.getResultList();
        LocacionEntity locacion = null;
        if (results == null) {
            locacion = null;
        } else if (results.isEmpty()) {
            locacion = null;
        } else if (results.size() >= 1) {
            locacion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el locacion con id = {0} del libro con id =" + distritoId, locacionId);
        return locacion; }

    /**
     * Actualiza una locacion.
     * @param locacionEntity: la locacion que viene con los nuevos cambios.
     * @return la locacion con los cambios aplicados.
     */
    public LocacionEntity update(LocacionEntity locacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la localizacion por el id", locacionEntity.getId());
        return em.merge(locacionEntity);
    }

    /**
     *
     * Borra una locacion de la base de datos recibiendo como argumento el id de 
     *  la locacion
     *
     * @param locacionsId: id correspondiente a la locacion a borrar.
     */
    public void delete(Long locacionsId) {
        LOGGER.log(Level.INFO, "Borrando locacion con id = {0}", locacionsId);
        LocacionEntity locacionEntity = em.find(LocacionEntity.class, locacionsId);
        em.refresh(locacionEntity);
        em.remove(locacionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El locacion con id = {0}", locacionsId);
    
    }
    
     /**
     * Busca si hay alguna distrito con el nombre que se envía de argumento
     *
     * @param name: Nombre de la locacion que se está buscando
     * @return null si no existe ninguna locacion con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public LocacionEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando locacion por nombre ", name);
        // Se crea un query para buscar locacion con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From LocacionEntity e where e.locacion = :name", LocacionEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<LocacionEntity> sameName = query.getResultList();
        LocacionEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar locaion por nombre ", name);
        return result;
    }
}
