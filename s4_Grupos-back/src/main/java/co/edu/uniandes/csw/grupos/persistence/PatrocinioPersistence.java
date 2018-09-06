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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author j.barbosaj
 */
public class PatrocinioPersistence 
{
      private static final Logger LOGGER = Logger.getLogger(PatrocinioEntity.class.getName());

    @PersistenceContext(unitName = "AdministradorStorePU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param patrocinioEntity  objeto locacion que se creará en la base de datos
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
     * @return una locacion.
     */
    public PatrocinioEntity find(String idPatrocinio) {
        LOGGER.log(Level.INFO, "Consultando la localizacion por id", idPatrocinio);
        return em.find(PatrocinioEntity .class, idPatrocinio);
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
    public void delete(String idLocacion) {
        LOGGER.log(Level.INFO, "Borrando la locacion con el id", idLocacion);
        LocacionEntity locacionEntity = em.find(LocacionEntity.class, idLocacion);
        em.remove(locacionEntity);
    }  
}
