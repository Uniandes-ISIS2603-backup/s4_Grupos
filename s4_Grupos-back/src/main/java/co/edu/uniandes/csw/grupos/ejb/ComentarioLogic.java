/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.ComentarioPersistence;
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

    /**
     * Se encarga de crear un Comentario en la base de datos.
     *
     * @param comentarioEntity Objeto de ComentarioEntity con los datos nuevos
     * @return Objeto de ComentarioEntity con los datos nuevos y su ID.
     */
    public ComentarioEntity createComentario(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del comentario");
        ComentarioEntity newComentarioEntity = persistence.create(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del comentario");
        return newComentarioEntity;
    }

    /**
     * Obtiene la lista de los registros de Comentario.
     *
     * @return Colección de objetos de ComentarioEntity.
     */
    public List<ComentarioEntity> getComentarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarioes");
        List<ComentarioEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los comentarioes");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Comentario a partir de su ID.
     *
     * @param comentariosId Identificador de la instancia a consultar
     * @return Instancia de ComentarioEntity con los datos del Comentario consultado.
     */
    public ComentarioEntity getComentario(Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el comentario con id = {0}", comentariosId);
        ComentarioEntity comentarioEntity = persistence.find(comentariosId);
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
     * @throws BusinessLogicException
     */
    public ComentarioEntity updateComentario(Long comentariosId, ComentarioEntity comentarioEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comentario con id = {0}", comentariosId);
        ComentarioEntity newComentarioEntity = persistence.update(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el comentario con id = {0}", comentariosId);
        return newComentarioEntity;
    }

    /**
     * Elimina una instancia de Comentario de la base de datos.
     *
     * @param comentariosId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el comentario tiene libros asociados.
     */
    public void deleteComentario(Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el comentario con id = {0}", comentariosId);
        persistence.delete(comentariosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el comentario con id = {0}", comentariosId);
    }    

   
}
