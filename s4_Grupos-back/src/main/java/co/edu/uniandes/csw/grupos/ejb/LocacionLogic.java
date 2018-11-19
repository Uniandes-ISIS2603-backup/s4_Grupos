/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.DistritoPersistence;
import co.edu.uniandes.csw.grupos.persistence.LocacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que representa un LocacionLogic
 * @author j.barbosaj   
 */
@Stateless
public class LocacionLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(LocacionLogic.class.getName());

    @Inject
    private LocacionPersistence persistence;
     @Inject
    private DistritoPersistence distritoPersistence;

    /**
     * Guardar un nuevo locacion
     *
     * @param distritoId el Id del distrito
     * @param locacionEntity La entidad de tipo locacion del nuevo locacion a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el name es inválido o ya existe en la
     * persistencia.
     */
    public LocacionEntity createLocacion    (Long distritoId, LocacionEntity locacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del distrito");
        
        if (!validatename(locacionEntity.getLocacion())) {
            throw new BusinessLogicException("El name es inválido");
        }
        if (persistence.findByName(locacionEntity.getLocacion()) != null) {
            throw new BusinessLogicException("El name ya existe");
        }      
        DistritoEntity distrito = distritoPersistence.find(distritoId);
        if(distritoPersistence.find(distritoId)==null)
        {
            throw new BusinessLogicException("El distrito no es valido");
        }
        locacionEntity.setDistrito(distrito);
        persistence.create(locacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del distrito");
        return locacionEntity;
    }

    /**
     * Devuelve todos los locaciones que hay en la base de datos.
     *
     * @return Lista de entidades de tipo locaciones.
     */
    public List<LocacionEntity> getLocaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los distritos");
        List<LocacionEntity> distritos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los distritos");
        return distritos;
    }

    /**
     * Busca una locacion por ID
     *
     * @param locacionID El id del locacion a buscar
     * @return La locacion encontrado, null si no lo encuentra.
     */
    public LocacionEntity getLocacion(Long locacionID) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el distrito con id = {0}", locacionID);
        LocacionEntity locacionEntity = persistence.find(locacionID);
        if (locacionEntity == null) {
            LOGGER.log(Level.SEVERE, "El distrito con el id = {0} no existe", locacionID);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el distrito con id = {0}", locacionID);
        return locacionEntity;
    }

    /**
     * Actualizar un locacion por ID
     *
     * @param locacionID El ID del locacion a actualizarlo
     * @param locacionEntity La entidad del locaciones con los cambios deseados
     * @return La entidad del locacion luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public LocacionEntity updateLocacion(Long locacionID, LocacionEntity locacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el locaciones con id = {0}", locacionID);
        if (!validatename(locacionEntity.getLocacion())) {
            throw new BusinessLogicException("El name es inválido");
        }
        LocacionEntity newEntity = persistence.update(locacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el locaciones con id = {0}", locacionEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un locacion por ID
     *
     * @param locacionID El ID del locacion a eliminar
     * @throws BusinessLogicException si la locacion tiene eventos asociados
     */
    public void deleteLocacion(Long locacionID) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el distrito con id = {0}", locacionID);
        persistence.delete(locacionID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el distrito con id = {0}", locacionID);
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
