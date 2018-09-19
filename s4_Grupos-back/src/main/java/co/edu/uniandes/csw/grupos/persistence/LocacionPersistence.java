/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;


import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
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
 *
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
     * @param LocacionEntity  objeto locacion que se creará en la base de datos
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
     * Busca si hay alguna localizacion con el nombre que se envía de argumento
     *
     * @param idLocacion: nombre correspondiente la localizacon buscada.
     * @return una locacion.
     */
    public LocacionEntity find(Long idLocacion) {
        LOGGER.log(Level.INFO, "Consultando la localizacion por id", idLocacion);
        return em.find(LocacionEntity.class, idLocacion);
    }

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
     * @param idLocacion: id correspondiente a la locacion a borrar.
     */
    public void delete(Long idLocacion) {
        LOGGER.log(Level.INFO, "Borrando la locacion con el id", idLocacion);
        LocacionEntity locacionEntity = em.find(LocacionEntity.class, idLocacion);
        em.remove(locacionEntity);
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
        TypedQuery query = em.createQuery("Select e From LocacionEntity e where e.name = :name", LocacionEntity.class);
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
