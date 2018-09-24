/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CategoriaDTO;
import co.edu.uniandes.csw.grupos.dtos.CategoriaDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.CategoriaLogic;
import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author s.carrero
 */
@Path ("categorias")
@Produces ("application/json")
@Consumes ("application/json")
@RequestScoped
public class CategoriaResource {
    
    private static final Logger LOGGER = Logger.getLogger(CategoriaResource.class.getName());
    
    @Inject
    private CategoriaLogic grupoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias
    
    
    /**
     * Crea una nueva categoria con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param pCategoria  - La categoria que se desea guardar.
     * @return JSON - El grupo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException Error de lógica que se genera cuando ya existe el grupo o es inválido
     */
    @POST
    public CategoriaDTO createCategoria( CategoriaDTO pCategoria) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "CategoriaResource createCategoria: input: {0}", pCategoria.toString());
        
        CategoriaDetailDTO nuevaCategoriaDTO = new CategoriaDetailDTO(grupoLogic.createCategoria(pCategoria.toEntity()));
        
        LOGGER.log(Level.INFO, "CategoriaResource createCategoria: output: {0}", nuevaCategoriaDTO.toString());
        
        return nuevaCategoriaDTO;
        
    }
    
    /**
     * Busca y devuelve todas las categorias que existen en la aplicacion.
     * @return JSONArray Las categorias son encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    
    @GET
    public List<CategoriaDetailDTO> getCategorias(){
        
        LOGGER.info("GrupoDeInteresResource getGrupos: input: void");
        List<CategoriaDetailDTO> listaGrupos = entity2DetailDTO(grupoLogic.getCategorias());
        LOGGER.log(Level.INFO, "GrupoDeInteresResource getGrupos: output: {0}", listaGrupos.toString());
        return listaGrupos;
    }
    
    /**
     * Actualiza la categoria con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param pCatId Identificador de la categoria que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param categoria {@link CategoriaDetailDTO} La categoria que se desea
     * guardar.
     * @return JSON {@link CategoriaDetailDTO} - La categoria guardada.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra la categoria a
     * actualizar.
     * @throws BusinessLogicException Error de lógica que se genera cuando no se puede actualizar la categoria.
     */
    @PUT
    @Path("{nombrecategoria: \\d+}")
    public CategoriaDTO modifyCategoria(@PathParam("nombrecategoria") Long pCatId, CategoriaDetailDTO categoria) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "CategoriaResource modifyCategoria: input: id: {0} , Categoria: {1}", new Object[]{pCatId, categoria.toString()});
        
        categoria.setId(pCatId);
        
        if (grupoLogic.getCategoria(pCatId) == null) {
            throw new WebApplicationException("El recurso /categoria/" + pCatId + " no existe.", 404);
        }
        CategoriaDetailDTO detailDTO = new CategoriaDetailDTO(grupoLogic.updateCategoria(pCatId, categoria.toEntity()));
        LOGGER.log(Level.INFO, "CategoriaResource modifyCategoria: output: {0}", detailDTO.toString());
        return detailDTO;
        
        
    }
    
    
      /**
     * Borra la categoria con el id asociado recibido en la URL.
     *
     * @param categId Identificador de la categoria que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException Error de lógica que se genera cuando no se encuentra la categoria.  
     */    
    @DELETE
    @Path("{nombrecategoria: \\d+}")
    public void deleteCategoria(@PathParam("nombrecategoria") Long categId) {
        
         LOGGER.log(Level.INFO, "CategoriaResource deleteGrupo: input: {0}", categId);
        CategoriaEntity entity = grupoLogic.getCategoria(categId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /categorias/" + categId + " no existe.", 404);
        }
        grupoLogic.deleteCategoria(categId);
        LOGGER.info("CategoriaResource deleteGrupo: output: void");
        
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos GrupoDeInteresEntity a una lista de
     * objetos GrupoDeInteresDetailDTO (json)
     *
     * @param entityList corresponde a la lista del GrupoDeInteres de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista del GrupoDeInteres en forma DTO (json)
     */
    private List<CategoriaDetailDTO> entity2DetailDTO(List<CategoriaEntity> entityList) {
        List<CategoriaDetailDTO> list = new ArrayList<>();
        for (CategoriaEntity entity : entityList) {
            list.add(new CategoriaDetailDTO(entity));
        }
        return list;
    }
    
    
}
