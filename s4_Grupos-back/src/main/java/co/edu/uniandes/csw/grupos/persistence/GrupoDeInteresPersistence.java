/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
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
        LOGGER.log(Level.INFO, "Creando un noticia nuevo");
        em.persist(grupoEntity);
        LOGGER.log(Level.INFO, "Noticia creado");
        return grupoEntity;    }

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
     * Elimina el grupo de interes asociado al ID grupoId.
     * @param grupoId El ID del grupo que se desea borrar
     */
    public void delete(Long grupoId) {
        LOGGER.log(Level.INFO, "Borrando grupo con id = {0}", grupoId);
        GrupoDeInteresEntity grupoEntity = em.find(GrupoDeInteresEntity.class, grupoId);
        em.remove(grupoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el grupo con id = {0}", grupoId);
    }   
    
}
