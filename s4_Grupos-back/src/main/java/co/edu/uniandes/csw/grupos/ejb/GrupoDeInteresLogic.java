/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.GrupoDeInteresPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */

@Stateless
public class GrupoDeInteresLogic {
    
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresLogic.class.getName());

    @Inject
    private GrupoDeInteresPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un grupo en la persistencia.
     *
     * @param grupoEntity La entidad que representa el grupo a persistir.
     * @return La entidad del grupo luego de persistirla.
     * @throws BusinessLogicException Si el grupo a persistir ya existe.
     */
    public GrupoDeInteresEntity createGrupo(GrupoDeInteresEntity grupoEntity) throws BusinessLogicException {
        
        LOGGER.log(Level.INFO, "Inicia proceso de creación del grupo");
                
        if (persistence.findByName(grupoEntity.getNombre()) != null) {           
            
            throw new BusinessLogicException("Ya existe un grupo con el nombre \"" + grupoEntity.getNombre() + "\"");
            
        }
        
        // Invoca la persistencia para crear la editorial
        persistence.create(grupoEntity);
        
        
        LOGGER.log(Level.INFO, "Termina proceso de creación del grupo");
        return grupoEntity;
    }

    /**
     *
     * Obtener todos los grupos existentes en la base de datos.
     *
     * @return una lista de grupos.
     */
    public List<GrupoDeInteresEntity> getGrupos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los grupos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<GrupoDeInteresEntity> gruposDeInteres = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los grupos");
        return gruposDeInteres;
    }

    /**
     *
     * Obtener un grupo por medio de su id.
     *
     * @param grupoId: id del grupo para ser buscada.
     * @return el grupo solicitado por medio de su id.
     */
    public GrupoDeInteresEntity getGrupo(Long grupoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el grupo con id = {0}", grupoId);
        GrupoDeInteresEntity grupoEntity = persistence.find(grupoId);
        if (grupoEntity == null) {
            LOGGER.log(Level.SEVERE, "El grupo con el id = {0} no existe", grupoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el grupo con id = {0}", grupoId);
        return grupoEntity;
    }

    /**
     *
     * Actualizar un grupo.
     *
     * @param grupoId: id del grupo para buscar en la base de datos.
     * @param grupoEntity: editorial con los cambios para ser actualizada, por ejemplo el nombre.
     * @return El grupo con los cambios actualizados en la base de datos.
     */
    public GrupoDeInteresEntity updateGrupo(Long grupoId, GrupoDeInteresEntity grupoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el duerme con id = {0}", grupoId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        GrupoDeInteresEntity newEntity = persistence.update(grupoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el grupo con id = {0}", grupoEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un grupo.     
     * @param grupoId: id del grupo a borrar
     */
    public void deleteGrupo(Long grupoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el grupo con id = {0}", grupoId);        
        persistence.delete(grupoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", grupoId);
    }
   
    
}
