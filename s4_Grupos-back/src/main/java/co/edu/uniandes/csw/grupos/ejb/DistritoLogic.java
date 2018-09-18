
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.DistritoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Distrito.
 *
 * @location ISIS2603
 */
@Stateless
public class DistritoLogic {

    private static final Logger LOGGER = Logger.getLogger(DistritoLogic.class.getName());

    @Inject
    private DistritoPersistence persistence;

    /**
     * Guardar un nuevo distrito
     *
     * @param distritoEntity La entidad de tipo distrito del nuevo distrito a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el name es inválido o ya existe en la
     * persistencia.
     */
    public DistritoEntity createDistrito(DistritoEntity distritoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del distrito");
        
        if (!validatename(distritoEntity.getName())) {
            throw new BusinessLogicException("El name es inválido");
        }
        if (persistence.findByName(distritoEntity.getName()) != null) {
            throw new BusinessLogicException("El name ya existe");
        }
        persistence.create(distritoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del distrito");
        return distritoEntity;
    }

    /**
     * Devuelve todos los distritos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo distrito.
     */
    public List<DistritoEntity> getDistritos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los distritos");
        List<DistritoEntity> distritos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los distritos");
        return distritos;
    }

    /**
     * Busca un distrito por ID
     *
     * @param distritosId El id del distrito a buscar
     * @return El distrito encontrado, null si no lo encuentra.
     */
    public DistritoEntity getDistrito(Long distritosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el distrito con id = {0}", distritosId);
        DistritoEntity distritoEntity = persistence.find(distritosId);
        if (distritoEntity == null) {
            LOGGER.log(Level.SEVERE, "El distrito con el id = {0} no existe", distritosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el distrito con id = {0}", distritosId);
        return distritoEntity;
    }

    /**
     * Actualizar un distrito por ID
     *
     * @param distritosId El ID del distrito a actualizar
     * @param distritoEntity La entidad del distrito con los cambios deseados
     * @return La entidad del distrito luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public DistritoEntity updateDistrito(Long distritosId, DistritoEntity distritoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el distrito con id = {0}", distritosId);
        if (!validatename(distritoEntity.getName())) {
            throw new BusinessLogicException("El name es inválido");
        }
        DistritoEntity newEntity = persistence.update(distritoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el distrito con id = {0}", distritoEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un distrito por ID
     *
     * @param distritosId El ID del distrito a eliminar
     * @throws BusinessLogicException si el distrito tiene locaciones asociados
     */
    public void deleteDistrito(Long distritosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el distrito con id = {0}", distritosId);
        persistence.delete(distritosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el distrito con id = {0}", distritosId);
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
