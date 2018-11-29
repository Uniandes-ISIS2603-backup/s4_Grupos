/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.dtos.CiudadanoDetailDTO;
import co.edu.uniandes.csw.grupos.ejb.CiudadanoLogic;
import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
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
 * Clase que representa un CiudadanoResource
 * @author Daniel Augusto Ramirez Dueñas
 */
@Path("ciudadanos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CiudadanoResource 
{    
    private static final  String NOEXISTE1= "/ciudadanos/";
    private static final  String NOEXISTE2= " no existe";
    private static final Logger LOGGER = Logger.getLogger(CiudadanoResource.class.getName());
    
    @Inject
    CiudadanoLogic ciudadanoLogic;
    /**
     * Crea un nuevo ciudadano
     * @param ciudadano Dto del ciudadano por parametro
     * @return Ciudadano creado
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException Si ya existia el ciudadano
     */
    @POST
    public CiudadanoDetailDTO createCiudadano(CiudadanoDetailDTO ciudadano) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CiudadanoResource createCiudadano: input: {0}");
        List<CiudadanoDetailDTO> listaDTOs = entity2DetailDTO(ciudadanoLogic.getCiudadanos());
        if (listaDTOs.isEmpty())
        {
            CiudadanoDetailDTO nuevoCiudadanoDetailDTO = new CiudadanoDetailDTO(ciudadanoLogic.createCiudadano(ciudadano.toEntity()));
            LOGGER.log(Level.INFO, "CiudadanoResource createCiudadano: output: {0}");
            return nuevoCiudadanoDetailDTO;
        }
        else
        {
            for (int i = 0; i < listaDTOs.size(); i++) 
            {
                if (ciudadano.getNombre().compareTo(listaDTOs.get(i).getNombre()) == 0 )
                {
                    throw new BusinessLogicException("El usuario con nombre: " + ciudadano.getNombre() + " ya existe. Por favor intente otro nombre de usuario.");
                }
                else
                {
                    CiudadanoDetailDTO nuevoCiudadanoDTO = new CiudadanoDetailDTO(ciudadanoLogic.createCiudadano(ciudadano.toEntity()));
                    LOGGER.log(Level.INFO, "CiudadanoResource createCiudadano: output: {0}");
                    return nuevoCiudadanoDTO;    
                }
            }    
        }
        throw new BusinessLogicException("El ciudadano no se pudo crear correctamente.");
    }
    
    /**
     * Consulta la lista de todos los ciudadanos
     * @return lista de todos los ciudadanos
     */
    @GET
    public List<CiudadanoDetailDTO> getCiudadanos()
    {
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadanos: input: {0}");
        List<CiudadanoDetailDTO> listaDTOs = entity2DetailDTO(ciudadanoLogic.getCiudadanos());
        LOGGER.log(Level.INFO, "CiudadanosResource getCiudadanos: output: {0}");
        return listaDTOs;
    }
    
    /**
     * Consulta un ciudadano con el numero de usuario dado parametro
     * @param id id del ciudadano a consultar
     * @return ciudadano con el numero de usuario dado por parametro
     */
    @GET
    @Path("{id :\\d+}")
    public CiudadanoDetailDTO getCiudadano(@PathParam("id") Long id)
    {
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadano: input: {0}", id);
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) {
            throw new WebApplicationException( NOEXISTE1 + id + NOEXISTE2, 404);
        }
        CiudadanoDetailDTO ciudadanoDTO = new CiudadanoDetailDTO(entity);
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadano: output: {0}");
        return ciudadanoDTO;
    }
  
    
    /**
     * Actualiza un ciudadano con sus nuevas caacteristicas
     * @param id id del ciudadano a actualizar
     * @param ciudadano Dto del ciudadano ingresado por parametro
     * @return un ciudadano actualizado
     * @throws BusinessLogicException Si no existe el ciudadano a actualizar.
     */
    @PUT
    @Path("{id :\\d+}")
    public CiudadanoDetailDTO modificarCiudadano(@PathParam("id") Long id, CiudadanoDetailDTO ciudadano ) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CiudadanoResource updateCiudadano: input: id: {0} , ciudadano:{1}", new Object[]{id, ciudadano.toString()});
        ciudadano.setId(id);
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) 
        {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2, 404);

        }
        CiudadanoDetailDTO ciudadanoDetailDTO = new CiudadanoDetailDTO(ciudadanoLogic.updateCiudadano(id,ciudadano.toEntity()));
        LOGGER.log(Level.INFO, "CiudadanoResource updateCiudadano: output:{0}");
        return ciudadanoDetailDTO;
    }
    
    /**
     * Elimina un ciudadano
     * @param id id del ciudadano a eliminar.
     * @throws BusinessLogicException Si no existe el ciudadano a eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarCiudadano(@PathParam("id") Long id) throws BusinessLogicException
    {
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2 , 404);
        }
        ciudadanoLogic.deleteCiudadano(id);
    }
    
    @DELETE
    public void eliminiarCiudadanos() throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadanos: input: {0}");
        List<CiudadanoDTO> listaDTOs = listEntityToDTO(ciudadanoLogic.getCiudadanos());
        for (int i = 0; i < listaDTOs.size(); i++)
        {
            ciudadanoLogic.deleteCiudadano(listaDTOs.get(i).getId());
        }
        LOGGER.log(Level.INFO, "CiudadanosResource getCiudadanos: output: {0}");
    }

    private List<CiudadanoDTO> listEntityToDTO(List<CiudadanoEntity> entityList) 
    {
        List<CiudadanoDTO> list = new ArrayList<>();
        for (CiudadanoEntity entity : entityList) {
            list.add(new CiudadanoDTO(entity));
        }
        return list;    
    }
    
        private List<CiudadanoDetailDTO> entity2DetailDTO(List<CiudadanoEntity> entityList) {
        List<CiudadanoDetailDTO> list = new ArrayList<>();
        for (CiudadanoEntity entity : entityList) {
            list.add(new CiudadanoDetailDTO(entity));
        }
        return list;
    }
}
