/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.ejb;

import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.CategoriaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que representa un CategoriaLogic
 * @author s.carrero
 */
@Stateless
public class CategoriaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(GrupoDeInteresLogic.class.getName());

    @Inject
    private CategoriaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un grupo en la persistencia.
     *
     * @param categoriaEntity La entidad que representa el grupo a persistir.
     * @return La entidad del grupo luego de persistirla.
     * @throws BusinessLogicException Si el grupo a persistir ya existe.
     */
    public CategoriaEntity createCategoria(CategoriaEntity categoriaEntity) throws BusinessLogicException {
        
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la categoria");
                
        if (persistence.findByName(categoriaEntity.getNombre()) != null) {           
            
            throw new BusinessLogicException("Ya existe una categoria con el nombre \"" + categoriaEntity.getNombre() + "\"");
            
        }        
        // Invoca la persistencia para crear la categoria
        persistence.create(categoriaEntity);        
        
        LOGGER.log(Level.INFO, "Termina proceso de creación de la categoria");
        return categoriaEntity;
    }

    /**
     *
     * Obtener todos los grupos existentes en la base de datos.
     *
     * @return una lista de grupos.
     */
    public List<CategoriaEntity> getCategorias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las categorias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CategoriaEntity> categorias = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las categorias");
        return categorias;
    }

    /**
     *
     * Obtener un grupo por medio de su id.
     *
     * @param categoriaId: id de la categoria para ser buscada.
     * @return la categoria solicitada por medio de su id.
     */
    public CategoriaEntity getCategoria(Long categoriaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la categoria con id = {0}", categoriaId);
        CategoriaEntity categoriaEntity = persistence.find(categoriaId);
        if (categoriaEntity == null) {
            LOGGER.log(Level.SEVERE, "La categoria con el id = {0} no existe", categoriaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la categoria con id = {0}", categoriaId);
        return categoriaEntity;
    }

    /**
     *
     * Actualizar un grupo.
     *
     * @param categoriaId: id del grupo para buscar en la base de datos.
     * @param categoriaEntity: editorial con los cambios para ser actualizada, por ejemplo el nombre.
     * @return El grupo con los cambios actualizados en la base de datos.
     */
    public CategoriaEntity updateCategoria(Long categoriaId, CategoriaEntity categoriaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la categoria con id = {0}", categoriaId);
        CategoriaEntity newEntity = persistence.update(categoriaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la categoria con id = {0}", categoriaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar una categoria.     
     * @param categoriaId: id de la categoria a borrar
     */
    public void deleteCategoria(Long categoriaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el grupo editorial con id = {0}", categoriaId);        
        persistence.delete(categoriaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", categoriaId);
    }
    
    
    
}
