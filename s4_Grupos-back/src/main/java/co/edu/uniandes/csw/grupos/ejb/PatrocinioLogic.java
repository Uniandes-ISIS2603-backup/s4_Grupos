/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.PatrocinioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que representa un PatrocinioLogic
 * @author estudiante
 */
@Stateless
public class PatrocinioLogic
{
        
    private static final Logger LOGGER = Logger.getLogger(PatrocinioLogic.class.getName());

    @Inject
    private PatrocinioPersistence persistence;

    /**
     * Guardar un nuevo patrocinio
     *
     * @param patrocinioEntity La entidad de tipo patrocinio del nuevo patrocinio a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el name es inválido o ya existe en la
     * persistencia.
     */
    public PatrocinioEntity createPatrocinio    (PatrocinioEntity patrocinioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del patrocinio");
        
        if (!validatename(patrocinioEntity.getNombre())) {
            throw new BusinessLogicException("El name es inválido");
        }
        if (persistence.findByName(patrocinioEntity.getNombre()) != null) {
            throw new BusinessLogicException("El name ya existe");
        }
        persistence.create(patrocinioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del patrocinio");
       
        return patrocinioEntity;
    }

    /**
     * Devuelve todos los patrocinioes que hay en la base de datos.
     *
     * @return Lista de entidades de tipo patrocinioes.
     */
    public List<PatrocinioEntity> getPatrocinios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los patrocinios");
        List<PatrocinioEntity> patrocinios = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los patrocinios");
        return patrocinios;
    }

    /**
     * Busca una patrocinio por ID
     *
     * @param patrocinioID El id del patrocinio a buscar
     * @return La patrocinio encontrado, null si no lo encuentra.
     */
    public PatrocinioEntity getPatrocinio(Long patrocinioID) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el patrocinio con id = {0}", patrocinioID);
        PatrocinioEntity patrocinioEntity = persistence.find(patrocinioID);
        if (patrocinioEntity == null) {
            LOGGER.log(Level.SEVERE, "El patrocinio con el id = {0} no existe", patrocinioID);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el patrocinio con id = {0}", patrocinioID);
        return patrocinioEntity;
    }

    /**
     * Actualizar un patrocinio por ID
     *
     * @param patrocinioID El ID del patrocinio a actualizarlo
     * @param patrocinioEntity La entidad del patrocinioes con los cambios deseados
     * @return La entidad del patrocinio luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public PatrocinioEntity updatePatrocinio(Long patrocinioID, PatrocinioEntity patrocinioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el patrocinioes con id = {0}", patrocinioID);
        if (!validatename(patrocinioEntity.getNombre())) {
            throw new BusinessLogicException("El name es inválido");
        }
        PatrocinioEntity newEntity = persistence.update(patrocinioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el patrocinioes con id = {0}", patrocinioEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un patrocinio por ID
     *
     * @param patrocinioID El ID del patrocinio a eliminar
     * @throws BusinessLogicException si la patrocinio tiene eventos asociados
     */
    public void deletePatrocinio(Long patrocinioID) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el patrocinio con id = {0}", patrocinioID);
        persistence.delete(patrocinioID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el patrocinio con id = {0}", patrocinioID);
    }
    
       /**
     * Verifica que el name no sea invalido.
     *
     * @param name a verificar
     * @return true si el name es valido.
     */
    private boolean validatename(String name) {
        return !(name == null || name.isEmpty());
    }
}
