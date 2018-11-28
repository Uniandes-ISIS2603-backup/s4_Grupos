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
       
        LOGGER.log(Level.INFO, "Termina proceso de creación del distrito");
        return  persistence.create(locacionEntity);
    }

   /**
     * Obtiene la lista de los registros de Locacion que pertenecen a un Distrito.
     *
     * @param gruposId id del Distrito el cual es padre de los Locacions.
     * @return Colección de objetos de LocacionEntity.
     */
    public List<LocacionEntity> getLocaciones(Long gruposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los noticias asociados al grupo con id = {0}", gruposId);
        DistritoEntity grupoEntity = distritoPersistence.find(gruposId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los noticias asociados al grupo con id = {0}", gruposId);
        return grupoEntity.getLocaciones();
    }

    /**
     * Busca una locacion por ID
     *
     * @param locacionID El id del locacion a buscar
     * @return La locacion encontrado, null si no lo encuentra.
     */
    public LocacionEntity getLocacion(Long distritoId,  Long locacionID) {
         LOGGER.log(Level.INFO, "Inicia proceso de consultar el locacion con id = {0} del distrito con id = " + distritoId, locacionID);
        return persistence.find(distritoId, locacionID);
    }

    /**
     * Actualizar un locacion por ID
     *
     * @param locacionID El ID del locacion a actualizarlo
     * @param locacionEntity La entidad del locaciones con los cambios deseados
     * @return La entidad del locacion luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public LocacionEntity updateLocacion(Long distritoID, LocacionEntity locacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el locacion con id = {0} del grupo con id = " + distritoID, locacionEntity.getId());
        DistritoEntity grupoEntity = distritoPersistence.find(distritoID);
        locacionEntity.setDistrito(grupoEntity);
        persistence.update(locacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el locacion con id = {0} del grupo con id = " + distritoID, locacionEntity.getId());
        return locacionEntity;
    }

    /**
     * Eliminar un locacion por ID
     *
     * @param locacionID El ID del locacion a eliminar
     * @throws BusinessLogicException si la locacion tiene eventos asociados
     */
    public void deleteLocacion(Long distritoId, Long locacionsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el locacion con id = {0} del grupo con id = " + distritoId, locacionsId);
        LocacionEntity old = getLocacion(distritoId, locacionsId);
        if (old == null) {
            throw new BusinessLogicException("El locacion con id = " + locacionsId + " no esta asociado a el grupo con id = " + distritoId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el locacion con id = {0} del grupo con id = " + distritoId, locacionsId);
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
