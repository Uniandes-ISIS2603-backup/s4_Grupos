/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;
import co.edu.uniandes.csw.grupos.entities.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Distrito. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author sarabepu
 */
@Stateless

public class DistritoPersistence {
   

    private static final Logger LOGGER = Logger.getLogger(DistritoPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param distritoEntity objeto distrito que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DistritoEntity create(DistritoEntity distritoEntity) {
        LOGGER.log(Level.INFO, "Creando una distrito nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la distrito en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(distritoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una distrito nueva");
        return distritoEntity;
    }
    
    /**
     * Devuelve todas las distritos de la base de datos.
     *
     * @return una lista con todas las distritos que encuentre en la base de
     * datos, "select u from DistritoEntity u" es como un "select * from
     * DistritoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DistritoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las distritos");
        // Se crea un query para buscar todas las distritos en la base de datos.
        TypedQuery query = em.createQuery("select u from DistritoEntity u", DistritoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de distritos.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna distrito con el id que se envía de argumento
     *
     * @param distritosId: id correspondiente a la distrito buscada.
     * @return una distrito.
     */
    public DistritoEntity find(Long distritosId) {
        LOGGER.log(Level.INFO, "Consultando distrito con id={0}", distritosId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from DistritoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(DistritoEntity.class, distritosId);
    }

     /**
     * Actualiza una distrito.
     *
     * @param distritoEntity: la distrito que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una distrito con los cambios aplicados.
     */
    public DistritoEntity update(DistritoEntity distritoEntity) {
        LOGGER.log(Level.INFO, "Actualizando distrito con id = {0}", distritoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la distrito con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la distrito con id = {0}", distritoEntity.getId());
        return em.merge(distritoEntity);
    }
    
    /**
     *
     * Borra una distrito de la base de datos recibiendo como argumento el id
     * de la distrito
     *
     * @param distritosId: id correspondiente a la distrito a borrar.
     */
    public void delete(Long distritosId) {
        LOGGER.log(Level.INFO, "Borrando distrito con id = {0}", distritosId);
        // Se hace uso de mismo método que esta explicado en public DistritoEntity find(Long id) para obtener la distrito a borrar.
        DistritoEntity entity = em.find(DistritoEntity.class, distritosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from DistritoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la distrito con id = {0}", distritosId);
    }
    
    /**
     * Busca si hay alguna distrito con el nombre que se envía de argumento
     *
     * @param name: Nombre de la distrito que se está buscando
     * @return null si no existe ninguna distrito con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public DistritoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando distrito por nombre ", name);
        // Se crea un query para buscar distritos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From DistritoEntity e where e.name = :name", DistritoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<DistritoEntity> sameName = query.getResultList();
        DistritoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar distrito por nombre ", name);
        return result;
    }
}

    
