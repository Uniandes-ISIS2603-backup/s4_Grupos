/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
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
public class CiudadanoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(CiudadanoPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Crear un ciudadano
     *
     * Crea un nuevo ciudadano con la información recibida en la entidad.
     *
     * @param ciudadanoEntity  La entidad que representa la nueva ciudadano
     * @return La entidad creada
     */
    public CiudadanoEntity create(CiudadanoEntity ciudadanoEntity) {
        LOGGER.log(Level.INFO, "Creando un ciudadano nuevo");
        em.persist(ciudadanoEntity);
        LOGGER.log(Level.INFO, "Ciudadano creado");
        return ciudadanoEntity;
    }
    
    /**
     * Devuelve todos los ciudadano de la base de datos.
     *
     * @return una lista con todas las ciudadano que encuentre en la base de
     * datos, "select u from CiudadanoEntity u" es como un "select * from
     * CiudadanoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CiudadanoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las ciudadano");
        // Se crea un query para buscar todas los ciudadano en la base de datos.
        TypedQuery query = em.createQuery("select u from CiudadanoEntity u", CiudadanoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de ciudadano.
        return query.getResultList();
    }

    public CiudadanoEntity find(Long ciudadanoUser) {
        LOGGER.log(Level.INFO, "Consultando ciudadano con id={0}", ciudadanoUser);

        return em.find(CiudadanoEntity.class, ciudadanoUser);
    }
    /**
     * Actualizar un ciudadano
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param ciudadanoEntity  La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public CiudadanoEntity update(CiudadanoEntity ciudadanoEntity) {
        LOGGER.log(Level.INFO, "Actualizando ciudadano con id = {0}", ciudadanoEntity.getId());
        return em.merge(ciudadanoEntity);
    }

    /**
     * Eliminar un ciudadano
     *
     * Elimina un ciudadano asociado al usuario que recibe
     *
     * @param ciudadanoUser El ID de la ciudadano que se desea borrar
     */
    public void delete(Long ciudadanoUser) {
        LOGGER.log(Level.INFO, "Borrando ciudadano con usuario = {0}", ciudadanoUser);
        CiudadanoEntity ciudadanoEntity = em.find(CiudadanoEntity.class, ciudadanoUser);
        em.remove(ciudadanoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El ciudadano con usuario = {0}", ciudadanoUser);
    }
}
