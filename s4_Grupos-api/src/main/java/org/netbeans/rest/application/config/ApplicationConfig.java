/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author estudiante
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.grupos.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.grupos.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.AdministradorResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.CiudadanoGrupoDeInteresResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.CiudadanoResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.ComentarioResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.DistritoResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.GrupoDeInteresCiudadanoResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.GrupoDeInteresResource.class);
        resources.add(co.edu.uniandes.csw.grupos.resources.PatrocinioResource.class);
    }
    
}
