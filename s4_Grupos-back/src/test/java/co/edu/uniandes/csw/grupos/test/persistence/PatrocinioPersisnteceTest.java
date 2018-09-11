/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.persistence;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import co.edu.uniandes.csw.grupos.persistence.PatrocinioPersistence;
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
 * Pruebas de persistencia de Patrocinios
 *
 * @author j.barbosa
 */
@RunWith(Arquillian.class)

public class PatrocinioPersisnteceTest {
    
    @Inject
    private PatrocinioPersistence patrocinioPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PatrocinioEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PatrocinioEntity.class.getPackage())
                .addPackage(PatrocinioPersistence.class.getPackage())
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
        em.createQuery("delete from PatrocinioEntity").executeUpdate();
    }

   /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PatrocinioEntity entity = factory.manufacturePojo(PatrocinioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Patrocinio.
     */
    @Test
    public void createPatrocinioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PatrocinioEntity  newEntity = factory.manufacturePojo(PatrocinioEntity.class);
        PatrocinioEntity result = patrocinioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PatrocinioEntity  entity = em.find(PatrocinioEntity .class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Patrocinios.
     */
    @Test
    public void getPatrociniosTest() {
        List<PatrocinioEntity > list = patrocinioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PatrocinioEntity  ent : list) {
            boolean found = false;
            for (PatrocinioEntity  entity : data) {
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
    public void getPatrocinioTest() {
        PatrocinioEntity entity = data.get(0);
       PatrocinioEntity  newEntity = patrocinioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getValor(), newEntity.getValor());
    }

    /**
     * Prueba para actualizar un Patrocinio.
     */
    @Test
    public void updatePatrocinioTest() {
        PatrocinioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PatrocinioEntity newEntity = factory.manufacturePojo( PatrocinioEntity.class);

        newEntity.setId(entity.getId());

        patrocinioPersistence.update(newEntity);

         PatrocinioEntity resp = em.find( PatrocinioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Patrocinio.
     */
    @Test
    public void deletePatrocinioTest() {
        PatrocinioEntity entity = data.get(0);
        patrocinioPersistence.delete(entity.getId());
        PatrocinioEntity deleted = em.find( PatrocinioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
