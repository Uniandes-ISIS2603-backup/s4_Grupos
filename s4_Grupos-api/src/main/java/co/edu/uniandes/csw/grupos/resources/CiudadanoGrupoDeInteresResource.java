/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Clase que modela la relacion entre un ciudadano y un grupo de interes.
 * @author Daniel Augusto Ramirez Dueñas
 */
@Path("ciudadanosGruposDeInteres")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class CiudadanoGrupoDeInteresResource 
{
    
}
