
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.GrupoDeInteresPersistence;
import co.edu.uniandes.csw.grupos.persistence.NoticiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Noticia(Noticia).
 *
 * @author ISIS2603
 */
@Stateless
public class NoticiaLogic {

    private static final Logger LOGGER = Logger.getLogger(NoticiaLogic.class.getName());

    @Inject
    private NoticiaPersistence persistence;

    @Inject
    private GrupoDeInteresPersistence grupoPersistence;

    /**
     * Se encarga de crear un Noticia en la base de datos.
     *
     * @param noticiaEntity Objeto de NoticiaEntity con los datos nuevos
     * @param gruposId id del GrupoDeInteres el cual sera padre del nuevo Noticia.
     * @return Objeto de NoticiaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si gruposId no es el mismo que tiene el
     * entity.
     *
     */
    public NoticiaEntity createNoticia(Long gruposId, NoticiaEntity noticiaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear noticia");
        GrupoDeInteresEntity grupo = grupoPersistence.find(gruposId);
        if(grupoPersistence.find(gruposId)==null)
        {
            throw new BusinessLogicException("El grupo no es valido");
        }
        noticiaEntity.setGrupoDeInteres(grupo);
        LOGGER.log(Level.INFO, "Termina proceso de creación del noticia");
        return persistence.create(noticiaEntity);
    }

    /**
     * Obtiene la lista de los registros de Noticia que pertenecen a un GrupoDeInteres.
     *
     * @param gruposId id del GrupoDeInteres el cual es padre de los Noticias.
     * @return Colección de objetos de NoticiaEntity.
     */
    public List<NoticiaEntity> getNoticias(Long gruposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los noticias asociados al grupo con id = {0}", gruposId);
        GrupoDeInteresEntity grupoEntity = grupoPersistence.find(gruposId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los noticias asociados al grupo con id = {0}", gruposId);
        return grupoEntity.getNoticias();
    }

    /**
     * Obtiene los datos de una instancia de Noticia a partir de su ID. La
     * existencia del elemento padre GrupoDeInteres se debe garantizar.
     *
     * @param gruposId El id del Libro buscado
     * @param noticiasId Identificador de la Noticia a consultar
     * @return Instancia de NoticiaEntity con los datos del Noticia consultado.
     *
     */
    public NoticiaEntity getNoticia(Long gruposId, Long noticiasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el noticia con id = {0} del libro con id = " + gruposId, noticiasId);
        return persistence.find(gruposId, noticiasId);
    }

    /**
     * Actualiza la información de una instancia de Noticia.
     *
     * @param noticiaEntity Instancia de NoticiaEntity con los nuevos datos.
     * @param gruposId id del GrupoDeInteres el cual sera padre del Noticia actualizado.
     * @return Instancia de NoticiaEntity con los datos actualizados.
     *
     */
    public NoticiaEntity updateNoticia(Long gruposId, NoticiaEntity noticiaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el noticia con id = {0} del libro con id = " + gruposId, noticiaEntity.getId());
        GrupoDeInteresEntity grupoEntity = grupoPersistence.find(gruposId);
        noticiaEntity.setGrupoDeInteres(grupoEntity);
        persistence.update(noticiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el noticia con id = {0} del libro con id = " + gruposId, noticiaEntity.getId());
        return noticiaEntity;
    }

    /**
     * Elimina una instancia de Noticia de la base de datos.
     *
     * @param noticiasId Identificador de la instancia a eliminar.
     * @param gruposId id del GrupoDeInteres el cual es padre del Noticia.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteNoticia(Long gruposId, Long noticiasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el noticia con id = {0} del libro con id = " + gruposId, noticiasId);
        NoticiaEntity old = getNoticia(gruposId, noticiasId);
        if (old == null) {
            throw new BusinessLogicException("El noticia con id = " + noticiasId + " no esta asociado a el libro con id = " + gruposId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el noticia con id = {0} del libro con id = " + gruposId, noticiasId);
    }
}
