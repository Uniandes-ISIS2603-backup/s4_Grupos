/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;
import co.edu.cuniandes.csw.grupos.dtos.LocacionDTO;
import co.edu.cuniandes.csw.grupos.dtos.PatrocinioDTO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso locaciones".
 *
 * @author Josealejandro barbosa Jacome ISIS2603
 * @version 1.0
 */
@Path("patrocinios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PatrocinioResource {
    /**
     * Crea un nuevo administrador y se regresa un objeto de tipo JSON generado 
     * por la base de datos.     *
     * @param patrocionioDto {@link PatrocionioDto} - El patrocinador que se desea anadir.
     * @return JSON {@link PatrocionioDto} -La locacion  guardado con su id
     */
    @POST
    public PatrocinioDTO crearPatrocinador(PatrocinioDTO patrocionioDto) {
    
    }

    /**
     * Devuelve todos los patrocinadores registrados.
     *
     * @return JSONArray {@link PatrocinioDTO} - Los patrocinadores
     * registrados. Si no hay ninguna retorna una lista vac�a.
     */
    @GET
    public List<PatrocinioDTO> getPatrocinadores() {
    }

    /**
     * Busca un patrocinio por su nombre y lo retorna.
     *
     * @param patrocinadorNombre Identificador del patrocinador que se esta buscando. Este debe
     * ser una cadena de caracteres.
     * @return JSON {@link patrocinioDTO} - EL patrocinio que se deseaba buscar.
     */
    @GET
    @Path("patrocinadorNombre: [a-zA-Z][a-zA-Z]*")
    public LocacionDTO getPatrocinador(@PathParam("patrocinadorNombre") String patrocinadorNombre) {
    }

    /**
     * Actualiza el localizacion con el id recibido desde la petici�n.
     * @param patrocinadorNombre Identificador del patrocinio que se desea actualizar. Este debe
     * ser una cadena de caracteres.
     * @param patrocinio {@link PatrocinioDTO} El patrocinioo que se desea guardar.
     * @return JSON {@link PatrocinioDTO} - El patrocinio guardado.
     */
    @PUT
    @Path("patrocinadorNombre: [a-zA-Z][a-zA-Z]*")
    public PatrocinioDTO updatePatrocinador(@PathParam("patrocinadorNombre") String patrocinadorNombre, PatrocinadorDTO patrocinio) {
    }

    /**
     * Borra el patrocinador con el Nombre asociado recibido en la URL.
     *
     * @param patrocniadorNombre Identificador del patrocinador que se desea borrar. Este debe ser
     * una cadena de caracteres.
     */
    @DELETE
    @Path("patrocinadorNombre: [a-zA-Z][a-zA-Z]*")
    public void deletePatrocinador(@PathParam("patrocinadorNombre") String patrocniadorNombre) {
    	
    }
}
