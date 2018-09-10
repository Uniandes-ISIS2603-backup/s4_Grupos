/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.persistence.LocacionPersistence;
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
 * Pruebas de persistencia de Locacion
 *
 * @author j.barbosa
 */
@RunWith(Arquillian.class)

public class LocacionPersistenceTest {
            @Inject
    private LocacionPersistence locacionesPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<LocacionEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LocacionEntity.class.getPackage())
                .addPackage(LocacionPersistence.class.getPackage())
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
        em.createQuery("delete from LocacionEntity").executeUpdate();
    }

   /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LocacionEntity entity = factory.manufacturePojo(LocacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Patrocinio.
     */
    @Test
    public void createAuthorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LocacionEntity  newEntity = factory.manufacturePojo(LocacionEntity.class);
        LocacionEntity result = locacionesPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LocacionEntity entity = em.find(LocacionEntity .class, result.getId());

        Assert.assertEquals(newEntity.getLocacion() , entity.getLocacion());
    }

    /**
     * Prueba para consultar la lista de Locaciones.
     */
    @Test
    public void getAuthorsTest() {
        List<LocacionEntity > list = locacionesPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LocacionEntity  ent : list) {
            boolean found = false;
            for (LocacionEntity  entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Patrocinio.
     */
    @Test
    public void getAuthorTest() {
        LocacionEntity entity = data.get(0);
        LocacionEntity newEntity = locacionesPersistence.find(entity.getLocacion());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getLocacion(), newEntity.getLocacion());
        Assert.assertEquals(entity.getDirecion(), newEntity.getDirecion());
    }

    /**
     * Prueba para actualizar un Patrocinio.
     */
    @Test
    public void updateAuthorTest() {
        LocacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LocacionEntity newEntity = factory.manufacturePojo( LocacionEntity.class);

        newEntity.setId(entity.getId());

        locacionesPersistence.update(newEntity);

         LocacionEntity resp = em.find( LocacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getLocacion(), resp.getLocacion());
    }

    /**
     * Prueba para eliminar un Patrocinio.
     */
    @Test
    public void deleteAuthorTest() {
        LocacionEntity entity = data.get(0);
        locacionesPersistence.delete(entity.getLocacion());
        LocacionEntity deleted = em.find( LocacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
