/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que representa un AdministradorPersistence
 * @author ac.beltrans
 */
@Stateless
public class AdministradorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;
    
    /**
     * Constructor por defecto
     */
    public AdministradorPersistence ()
    {
        super();
    }

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param administradorEntity objeto administrador que se creará en la base de datos
     * @return devuelve la entidad creada con el nombre dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Creando un administrador nuevo");
        em.persist(administradorEntity);
        LOGGER.log(Level.INFO, "Administrador creado");
        return administradorEntity;
    }

    /**
     * Devuelve todos los administradores de la base de datos.
     *
     * @return una lista con todos los administradores que encuentre en la base de datos,
     * "select u from AdministradorEntity u" es como un "select * from AdministradorEntity;" -
     * "SELECT * FROM table_nombre" en SQL.
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los administradores");
        Query q = em.createQuery("select u from AdministradorEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun administrador con el nombre que se envía de argumento
     *
     * @param idAdministrador: nombre correspondiente al administrador buscado.
     * @return un administrador.
     */
    public AdministradorEntity find(Long idAdministrador) {
        //LOGGER.log(Level.INFO, "Consultando el administrador por id", idAdministrador);
        return em.find(AdministradorEntity.class, idAdministrador);
    }

    /**
     * Actualiza un administrador.
     * @param administradorEntity: el administrador que viene con los nuevos cambios.
     * @return un administrador con los cambios aplicados.
     */
    public AdministradorEntity update(AdministradorEntity administradorEntity) {
        return em.merge(administradorEntity);
    }

    /**
     *
     * Borra un administrador de la base de datos recibiendo como argumento el id del
     * administrador
     *
     * @param idAdministrador: id correspondiente al administrador a borrar.
     */
    public void delete(Long idAdministrador) {
        //LOGGER.log(Level.INFO, "Borrando el administrador con el id", idAdministrador);
        AdministradorEntity administradorEntity = em.find(AdministradorEntity.class, idAdministrador);
        em.remove(administradorEntity);
    }
    
    
    /**
     * Busca si hay alguna administrador con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la administrador que se está buscando
     * @return null si no existe ninguna administrador con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public AdministradorEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando administrador por nombre ", nombre);
        // Se crea un query para buscar administradores con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.nombre = :nombre", AdministradorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<AdministradorEntity> sameNombre = query.getResultList();
        AdministradorEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar administrador por nombre ", nombre);
        return result;
    }
    
}
