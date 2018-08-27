/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.tests;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.grupos.dtos.PersonaDTO;
import co.edu.uniandes.csw.grupos.resources.CiudadanoResource;
import co.edu.uniandes.csw.grupos.resources.ComentarioResource;
import co.edu.uniandes.csw.postman.tests.PostmanTestBuilder;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author estudiante
 */
public class GruposIT 
{
    private int sumaPeticiones;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "frontstepbystep-api.war")//War del modulo api
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(PersonaDTO.class.getPackage())
                .addPackage(CiudadanoResource.class.getPackage())
                .addPackage(CiudadanoDTO.class.getPackage())
                .addPackage(ComentarioResource.class.getPackage()) //No importa cual recurso usar, lo importante es agregar el paquet
                .addPackage(ComentarioDTO.class.getPackage()) //No importa cual dto usar, lo importante es agregar el paquete.
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/glassfish-resources.xml"));
    }
    
    @Test
    @RunAsClient
    public void postman() throws IOException {
        File[] colecciones = new File(System.getProperty("user.dir").concat("\\collections")).listFiles();
        for (File coleccion : colecciones) {
            if (!coleccion.getName().contains("postman_environment")) {
                PostmanTestBuilder tp = new PostmanTestBuilder();
                tp.setTestWithoutLogin(coleccion.getName().replaceFirst(".json", ""), "Entorno-IT.postman_environment");
                String desiredResult = "0";
                String nombre = coleccion.getName().replaceFirst(".postman_environment.json", "");

                Assert.assertEquals("Error en Iterations de: " + nombre, desiredResult, tp.getIterations_failed());

                Assert.assertEquals("Error en Requests de: " + nombre, desiredResult, tp.getRequests_failed());

                Assert.assertEquals("Error en Test-Scripts de: " + nombre, desiredResult, tp.getTest_scripts_failed());

                Assert.assertEquals("Error en Prerequest-Scripts de: " + nombre, desiredResult, tp.getPrerequest_scripts_failed());

                Assert.assertEquals("Error en Assertions de: " + nombre, desiredResult, tp.getAssertions_failed());

                sumaPeticiones += Integer.parseInt(tp.getTotal_Requests());
            }
        }
    }
    
    @After
    public void totalPeticiones(){
        Logger.getLogger(GruposIT.class.getName()).log(Level.INFO, "TOTAL-PETICIONES: {0}", sumaPeticiones);
    }   
}
