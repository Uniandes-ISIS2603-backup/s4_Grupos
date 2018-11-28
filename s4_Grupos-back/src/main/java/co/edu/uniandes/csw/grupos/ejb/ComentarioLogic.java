/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.grupos.persistence.GrupoDeInteresPersistence;
import co.edu.uniandes.csw.grupos.persistence.NoticiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que representa un ComentarioLogic
 * @author Daniel Augusto Ramirez Dueñas
 */
@Stateless
public class ComentarioLogic
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());

    @Inject
    private ComentarioPersistence persistence;
    
    @Inject
    private NoticiaPersistence noticiaPersistence;
    
    @Inject
    private GrupoDeInteresPersistence grupoPersistence;

    /**
     * Se encarga de crear un Comentario en la base de datos.
     *
     * @param comentarioEntity Objeto de ComentarioEntity con los datos nuevos
     * @return Objeto de ComentarioEntity con los datos nuevos y su ID.
     */
    public ComentarioEntity createComentario(Long noticiaId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear comentario");
        NoticiaEntity noticia = noticiaPersistence.find(noticiaId);
        if(noticia == null)
        {
            throw new BusinessLogicException("La noticia no es valida");
        }
        comentarioEntity.setNoticia(noticia);
        LOGGER.log(Level.INFO, "Termina proceso de creación del comentario");
        return persistence.create(comentarioEntity);
    }

    /**
     * Obtiene la lista de los registros de Comentario.
     *
     * @return Colección de objetos de ComentarioEntity.
     */
    public List<ComentarioEntity> getComentarios(Long noticiaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarioes");
        NoticiaEntity noticia = noticiaPersistence.find(noticiaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los comentarioes");
        return noticia.getComentarios();
    }

    /**
     * Obtiene los datos de una instancia de Comentario a partir de su ID.
     *
     * @param comentariosId Identificador de la instancia a consultar
     * @return Instancia de ComentarioEntity con los datos del Comentario consultado.
     */
    public ComentarioEntity getComentario(Long noticiaId, Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comentario con id = {0}", comentariosId);
        ComentarioEntity comentarioEntity = persistence.find( noticiaId, comentariosId);
        if (comentarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El comentario con el id = {0} no existe", comentariosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comentario con id = {0}", comentariosId);
        return comentarioEntity;
    }

    /**
     * Actualiza la información de una instancia de Comentario.
     *
     * @param comentariosId Identificador de la instancia a actualizar
     * @param comentarioEntity Instancia de ComentarioEntity con los nuevos datos.
     * @return Instancia de ComentarioEntity con los datos actualizados.
     */
    public ComentarioEntity updateComentario(Long noticiasId, Long comentariosId, ComentarioEntity comentarioEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comentario con id = {0}", comentariosId);
        NoticiaEntity noticiaEntity = noticiaPersistence.find(noticiasId);
        comentarioEntity.setNoticia(noticiaEntity);
        persistence.update(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el comentario con id = {0}", comentariosId);
        return comentarioEntity;
    }

    /**
     * Elimina una instancia de Comentario de la base de datos.
     *
     * @param comentariosId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el comentario tiene libros asociados.
     */
    public void deleteComentario(Long noticiasId, Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el comentario con id = {0}", comentariosId);
        ComentarioEntity old = getComentario(noticiasId, comentariosId);
        if (old == null)
        {
            throw new BusinessLogicException("El comentario con id = " + comentariosId + " no esta asociado a la noticia con id = " + noticiasId);
        }
        persistence.delete(comentariosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el comentario con id = {0}", comentariosId);
    }    

   
}
