/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.AdministradorPersistence;
import static com.gs.collections.impl.block.factory.StringPredicates.contains;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import reactor.core.action.ForEachAction;

/**
 *
 * @author ac.beltrans
 */
@Stateless
public class AdministradorLogic {
    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence;

    /**
     * Se encarga de crear un Administrador en la base de datos.
     *
     * @param administradorEntity Objeto de AdministradorEntity con los datos nuevos
     * throws BusinessLogicException lanza la excepción cuando el administrador ya existe o noe es valido
     * @return Objeto de AdministradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        if((administradorEntity.getId() == null || administradorEntity.getId() < 0) || administradorEntity.getId().toString().contains("[a-zA-Z]+"))
        {
            throw new BusinessLogicException("El id no es válido");
        }
        if (persistence.find(administradorEntity.getId())!=null) {
            throw new BusinessLogicException("El id ya existe");
        }
        if(administradorEntity.getNombre() == null || administradorEntity.getNombre().isEmpty())
        {
            throw new BusinessLogicException("El nombre no es válido");
        }
        if(administradorEntity.getContrasena() == null || administradorEntity.getContrasena().isEmpty())
        {
            throw new BusinessLogicException("La contraseña no es válida");
        }
        AdministradorEntity newAdministradorEntity = persistence.create(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del administrador");
        return newAdministradorEntity;
    }

    /**
     * Obtiene la lista de los registros de Administrador.
     *
     * @return Colección de objetos de AdministradorEntity.
     */
    public List<AdministradorEntity> getAdministradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradores");
        List<AdministradorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID.
     *
     * @param administradoresId Identificador de la instancia a consultar
     * @return Instancia de AdministradorEntity con los datos del Administrador consultado.
     */
    public AdministradorEntity getAdministrador(Long administradoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el administrador con id = {0}", administradoresId);
        AdministradorEntity administradorEntity = persistence.find(administradoresId);
        if (administradorEntity == null) {
            LOGGER.log(Level.SEVERE, "El administrador con el id = {0} no existe", administradoresId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el administrador con id = {0}", administradoresId);
        return administradorEntity;
    }

    /**
     * Actualiza la información de una instancia de Administrador.
     *
     * @param administradorId Identificador de la instancia a actualizar
     * @param administradorEntity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de AdministradorEntity con los datos actualizados.
     */
    public AdministradorEntity updateAdministrador(Long administradorId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el administrador con id = {0}", administradorId);
        if(administradorEntity.getId() == null || administradorEntity.getId() < 0)
        {
            throw new BusinessLogicException("El id no es válido");
        }
        AdministradorEntity newAdministradorEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el administrador con id = {0}", administradorId);
        return newAdministradorEntity;
    }

    /**
     * Elimina una instancia de Administrador de la base de datos.
     *
     * @param administradorId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el administrador tiene grupos asociados.
     */
    public void deleteAdministrador(Long administradorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el administrador con id = {0}", administradorId);
        List<GrupoDeInteresEntity> grupos = getAdministrador(administradorId).getGruposDeInteres();
        if (grupos != null && !grupos.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el administrador con id = " + administradorId + " porque tiene grupos asociados");
        }
        persistence.delete(administradorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradorId);
    }
}
