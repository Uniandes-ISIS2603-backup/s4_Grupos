
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.AdministradorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Administrador.
 *
 * @author sarabepu
 */
@Stateless
public class AdministradorLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());

    @Inject
    private AdministradorPersistence persistence;

    /**
     * Guardar un nuevo administrador
     *
     * @param administradorEntity La entidad de tipo administrador del nuevo administrador a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el name es inválido o ya existe en la
     * persistencia.
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del administrador");
        
        if (!validatename(administradorEntity.getNombre())) {
            throw new BusinessLogicException("El name es inválido");
        }
        if (persistence.findByNombre(administradorEntity.getNombre()) != null) {
            throw new BusinessLogicException("El name ya existe");
        }
        persistence.create(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del administrador");
        return administradorEntity;
    }

    /**
     * Devuelve todos los administradores que hay en la base de datos.
     *
     * @return Lista de entidades de tipo administrador.
     */
    public List<AdministradorEntity> getAdministradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradores");
        List<AdministradorEntity> administradores = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradores");
        return administradores;
    }

    /**
     * Busca un administrador por ID
     *
     * @param administradoresId El id del administrador a buscar
     * @return El administrador encontrado, null si no lo encuentra.
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
     * Actualizar un administrador por ID
     *
     * @param administradoresId El ID del administrador a actualizar
     * @param administradorEntity La entidad del administrador con los cambios deseados
     * @return La entidad del administrador luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public AdministradorEntity updateAdministrador(Long administradoresId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el administrador con id = {0}", administradoresId);
        if (!validatename(administradorEntity.getNombre())) {
            throw new BusinessLogicException("El name es inválido");
        }
        AdministradorEntity newEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el administrador con id = {0}", administradorEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un administrador por ID
     *
     * @param administradoresId El ID del administrador a eliminar
     * @throws BusinessLogicException si el administrador tiene locaciones asociados
     */
    public void deleteAdministrador(Long administradoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el administrador con id = {0}", administradoresId);
        persistence.delete(administradoresId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradoresId);
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
