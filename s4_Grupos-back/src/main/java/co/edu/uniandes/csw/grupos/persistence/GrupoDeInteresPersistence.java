/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.carrero
 */
@Stateless
public class GrupoDeInteresPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresPersistence.class.getName());
    
    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;
    
    /**
     * Crea un nuevo ngrupo de interes con la información recibida en la entidad.     *
     * @param grupoEntity La entidad que representa el nuevo grupo
     * @return La entidad creada
     */
    public GrupoDeInteresEntity create(GrupoDeInteresEntity grupoEntity) {
        LOGGER.log(Level.INFO, "Creando un grupo de interes nuevo");
        em.persist(grupoEntity);
        LOGGER.log(Level.INFO, "Grupo de interes creado");
        return grupoEntity;
    }
    
    /**
     * Actualiza el grupo de interes que recibe
     * @param grupoEntity El grupo actualizado que se quiere persistir
     * @return La entidad luego de la acutalización
     */
    public GrupoDeInteresEntity update(GrupoDeInteresEntity grupoEntity) {
        LOGGER.log(Level.INFO, "Actualizando grupo con id = {0}", grupoEntity.getId());
        return em.merge(grupoEntity);
    }
    
    /**
     * Devuelve todos los grupos de interes de la base de datos.     *
     * @return una lista con todas los grupos de interes que encuentre en la base de datos
     */
    public List<GrupoDeInteresEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los grupos");
        // Se crea un query para buscar todos los grupos en la base de datos.
        TypedQuery query = em.createQuery("select u from GrupoDeInteresEntity u", GrupoDeInteresEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de distritos.
        return query.getResultList();
    }
    
    
    /**
     * Retorna el grupo con el id ingresado por parámetro.   
     * @param grupoId: id correspondiente al grupo buscado.
     * @return grupo con el id asociado.
     */
    public GrupoDeInteresEntity find(Long grupoId) {
        LOGGER.log(Level.INFO, "Consultando grupo de interés con id={0}", grupoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from DistritoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(GrupoDeInteresEntity.class, grupoId);
    }    
    
    /**
     * Elimina el grupo de interes asociado al ID grupoId.
     * @param grupoId El ID del grupo que se desea borrar
     */
    public void delete(Long grupoId) {
        LOGGER.log(Level.INFO, "Borrando grupo con id = {0}", grupoId);
        GrupoDeInteresEntity grupoEntity = em.find(GrupoDeInteresEntity.class, grupoId);
        em.remove(grupoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el grupo con id = {0}", grupoId);
    }
    
    
     /**
     * Retorna el grupo de interés con el nombre ingresado por parámetro
     * @param name: Nombre del grupo de interes que se está buscando
     * @return El grupo fe interés con el nombre ingresado por parámetro, null si no existe ninguno.
     */
    
    public GrupoDeInteresEntity findByName(String name) {
        
        LOGGER.log(Level.INFO, "Consultando grupo de interés por nombre ", name);
        // Se crea un query para buscar distritos con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
                
        TypedQuery query = em.createQuery("select e from GrupoDeInteresEntity e where e.nombre = :name", GrupoDeInteresEntity.class);
                
        
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);    
        
        
        // Se invoca el query se obtiene la lista resultado
        List<GrupoDeInteresEntity> sameName = query.getResultList();
        GrupoDeInteresEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar grupo por nombre ", name);
        return result;
    }
    
    
    
    
}
