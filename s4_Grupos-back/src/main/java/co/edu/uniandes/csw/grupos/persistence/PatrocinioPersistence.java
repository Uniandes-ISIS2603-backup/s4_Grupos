/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que representa un PatrocinioPersistence
 * @author j.barbosaj
 */
@Stateless
public class PatrocinioPersistence 
{
      private static final Logger LOGGER = Logger.getLogger(PatrocinioEntity.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param patrocinioEntity  objeto patrocinio que se creará en la base de datos
     * @return devuelve la entidad creada con el nombre dado por la base de datos.
     */
    public PatrocinioEntity create(PatrocinioEntity patrocinioEntity) {
        LOGGER.log(Level.INFO, "Creando una nuevo patrocinio");
        em.persist(patrocinioEntity);
        LOGGER.log(Level.INFO, "Patrocinio creado");
        return patrocinioEntity;
    }
    /**
     * Devuelve todos los patrocinadores de la base de datos.
     *
     * @return una lista con todas los patrocinadores que encuentre en la base de datos,
     * "select u from PatrocinioEntity u" es como un "select * from PatrocinioEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<PatrocinioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los patrocinadores");
        Query q = em.createQuery("select u from PatrocinioEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun patrocinador con el nombre que se envía de argumento
     *
     * @param idPatrocinio: nombre correspondiente al patrocinio buscado.
     * @return un patrocinio.
     */
    public PatrocinioEntity find(Long idPatrocinio) {
        LOGGER.log(Level.INFO, "Consultando la localizacion por id", idPatrocinio);
        return em.find(PatrocinioEntity .class, idPatrocinio);
    }

    /**
     * Actualiza un patrocinio .
     * @param patrocinioEntity: la patrocinio que viene con los nuevos cambios.
     * @return la patrocinio con los cambios aplicados.
     */
    public PatrocinioEntity update(PatrocinioEntity patrocinioEntity) {
        LOGGER.log(Level.INFO, "Actualizando la localizacion por el id", patrocinioEntity.getId());
        return em.merge(patrocinioEntity);
    }

    /**
     *
     * Borra una patrocinio de la base de datos recibiendo como argumento el id de 
     *  la patrocinio
     *
     * @param idPatrocinio: id correspondiente a la patrocinio a borrar.
     */
    public void delete(Long idPatrocinio) {
        LOGGER.log(Level.INFO, "Borrando la patrocinio con el id", idPatrocinio);
        PatrocinioEntity patrocinioEntity = em.find(PatrocinioEntity.class, idPatrocinio);
        em.remove(patrocinioEntity);
    }  
    
    /**
     * Busca si hay algun Patrocicio con el nombre que se envía de argumento
     *
     * @param name: Nombre del Patrocinio que se está buscando
     * @return null si no existe ningun patrocinio con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public PatrocinioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando patrocinio por nombre ", name);
        // Se crea un query para buscar parpcino con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PatrocinioEntity e where e.nombre = :name", PatrocinioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PatrocinioEntity> sameName = query.getResultList();
        PatrocinioEntity result;
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
