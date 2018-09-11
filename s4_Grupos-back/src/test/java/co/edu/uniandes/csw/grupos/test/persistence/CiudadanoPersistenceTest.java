/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.persistence.CiudadanoPersistance;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CiudadanoPersistenceTest 
{
     @Inject
    private CiudadanoPersistance ciudadanoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CiudadanoEntity> data = new ArrayList<CiudadanoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadanoEntity.class.getPackage())
                .addPackage(CiudadanoPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CiudadanoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CiudadanoEntity entity = factory.manufacturePojo(CiudadanoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Distrito.
     */
    @Test
    public void createCiudadanoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CiudadanoEntity newEntity = factory.manufacturePojo(CiudadanoEntity.class);
        CiudadanoEntity result = ciudadanoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CiudadanoEntity entity = em.find(CiudadanoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getCiudadanosTest() {
        List<CiudadanoEntity> list = ciudadanoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadanoEntity ent : list) {
            boolean found = false;
            for (CiudadanoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getCiudadanoTest() {
        CiudadanoEntity entity = data.get(0);
        CiudadanoEntity newEntity = ciudadanoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar un Distrito.
     */
    @Test
    public void deleteCiudadanoTest() {
        CiudadanoEntity entity = data.get(0);
        ciudadanoPersistence.delete(entity.getId());
        CiudadanoEntity deleted = em.find(CiudadanoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Distrito.
     */
    @Test
    public void upadteCiudadanoTest() {
        CiudadanoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CiudadanoEntity newEntity = factory.manufacturePojo(CiudadanoEntity.class);

        newEntity.setId(entity.getId());

        ciudadanoPersistence.update(newEntity);

        CiudadanoEntity resp = em.find(CiudadanoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
