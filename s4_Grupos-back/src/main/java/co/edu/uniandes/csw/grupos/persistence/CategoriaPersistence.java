/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class CategoriaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**     
     * Crea una nueva categoria con la información recibida en la entidad.     *
     * @param categoriaEntity La entidad que representa el nuevo grupo
     * @return La entidad creada
     */
    public CategoriaEntity create(CategoriaEntity categoriaEntity) {
        LOGGER.log(Level.INFO, "Creando una categoria nueva");
        em.persist(categoriaEntity);
        LOGGER.log(Level.INFO, "Categoria creada");
        return categoriaEntity;
    }

    /**  
     * Actualiza la categoria que recibe      
     * @param grupoEntity El grupo actualizado que se quiere persistir
     * @return La entidad luego de la acutalización
     */
    public CategoriaEntity update(CategoriaEntity categoriaEntity) {
        LOGGER.log(Level.INFO, "Actualizando categoria con id = {0}", categoriaEntity.getId());
        return em.merge(categoriaEntity);
    }

    /**
     * Elimina la categoria asociada al ID categoriaId.
     * @param categoriaId El ID del grupo que se desea borrar
     */
    public void delete(Long categoriaId) {
        LOGGER.log(Level.INFO, "Borrando categoria con id = {0}", categoriaId);
        CategoriaEntity categoriaEntity = em.find(CategoriaEntity.class, categoriaId);
        em.remove(categoriaEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la categoria con id = {0}", categoriaId);
    }   
    
     /**
     * Devuelve todas las categorias de la base de datos.     *
     * @return una lista con todas las categorias que encuentre en la base de datos.
     */
    public List<CategoriaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las categorias");
        // Se crea un query para buscar todas las categorias en la base de datos.
        TypedQuery query = em.createQuery("select u from CategoriaEntity u", CategoriaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de distritos.
        return query.getResultList();
    }
    
     /**
     * Retorna la categoria con el id ingresado por parámetro.   
     * @param categoriaId: id correspondiente a la categoria buscada.
     * @return categoria con el id asociado.
     */
    public CategoriaEntity find(Long categoriaId) {
        LOGGER.log(Level.INFO, "Consultando categoría con id={0}", categoriaId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from DistritoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CategoriaEntity.class, categoriaId);
    }    
    
    
    
     /**
     * Retorna el grupo de interés con el nombre ingresado por parámetro
     * @param name: Nombre del grupo de interes que se está buscando
     * @return El grupo fe interés con el nombre ingresado por parámetro, null si no existe ninguno.
     */
    public CategoriaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando categoria por nombre ", name);
        // Se crea un query para buscar categorias con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("select e from CategoriaEntity e where e.nombre = :name", CategoriaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<CategoriaEntity> sameName = query.getResultList();
        CategoriaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar categoria por nombre ", name);
        return result;
    }
    
    
    
    
    
}
