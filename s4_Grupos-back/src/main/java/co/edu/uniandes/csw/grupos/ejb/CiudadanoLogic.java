/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.CiudadanoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que representa un CiudadanoLogic
 * @author Daniel Augusto Ramirez Dueñas
 */
@Stateless
public class CiudadanoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CiudadanoLogic.class.getName());

    @Inject
    private CiudadanoPersistence persistence;

    /**
     * Se encarga de crear un Ciudadano en la base de datos.
     *
     * @param ciudadanoEntity Objeto de CiudadanoEntity con los datos nuevos
     * @return Objeto de CiudadanoEntity con los datos nuevos y su ID.
     */
    public CiudadanoEntity createCiudadano(CiudadanoEntity ciudadanoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del ciudadano");
        CiudadanoEntity newCiudadanoEntity = persistence.create(ciudadanoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del ciudadano");
        return newCiudadanoEntity;
    }

    /**
     * Obtiene la lista de los registros de Ciudadano.
     *
     * @return Colección de objetos de CiudadanoEntity.
     */
    public List<CiudadanoEntity> getCiudadanos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los ciudadanoes");
        List<CiudadanoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los ciudadanoes");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Ciudadano a partir de su ID.
     *
     * @param ciudadanosId Identificador de la instancia a consultar
     * @return Instancia de CiudadanoEntity con los datos del Ciudadano consultado.
     */
    public CiudadanoEntity getCiudadano(Long ciudadanosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ciudadano con id = {0}", ciudadanosId);
        CiudadanoEntity ciudadanoEntity = persistence.find(ciudadanosId);
        if (ciudadanoEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", ciudadanosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ciudadano con id = {0}", ciudadanosId);
        return ciudadanoEntity;
    }

    /**
     * Actualiza la información de una instancia de Ciudadano.
     *
     * @param ciudadanosId Identificador de la instancia a actualizar
     * @param ciudadanoEntity Instancia de CiudadanoEntity con los nuevos datos.
     * @return Instancia de CiudadanoEntity con los datos actualizados.
     */
    public CiudadanoEntity updateCiudadano(Long ciudadanosId, CiudadanoEntity ciudadanoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el ciudadano con id = {0}", ciudadanosId);
        CiudadanoEntity newCiudadanoEntity = persistence.update(ciudadanoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el ciudadano con id = {0}", ciudadanosId);
        return newCiudadanoEntity;
    }

    /**
     * Elimina una instancia de Ciudadano de la base de datos.
     *
     * @param ciudadanosId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el ciudadano tiene libros asociados.
     */
    public void deleteCiudadano(Long ciudadanosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el ciudadano con id = {0}", ciudadanosId);
        List<GrupoDeInteresEntity> gruposDeInteres = getCiudadano(ciudadanosId).getGruposDeInteres();
        if (gruposDeInteres != null && !gruposDeInteres.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el ciudadano con id = " + ciudadanosId + " porque tiene books asociados");
        }
        
        persistence.delete(ciudadanosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el ciudadano con id = {0}", ciudadanosId);
    }    
}
