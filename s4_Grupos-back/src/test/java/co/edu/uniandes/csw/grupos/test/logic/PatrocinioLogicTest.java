/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.PatrocinioLogic;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
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
 *
 * @author j.barbosaj
 */
@RunWith(Arquillian.class)
public class PatrocinioLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PatrocinioLogic patrocinioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PatrocinioEntity> data = new ArrayList<PatrocinioEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PatrocinioEntity.class.getPackage())
                .addPackage(PatrocinioLogic.class.getPackage())
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
     
        
        for (int i = 0; i < 3; i++) {
            PatrocinioEntity entity = factory.manufacturePojo(PatrocinioEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Patrocinio
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void createPatrocinioTest() throws BusinessLogicException {
        PatrocinioEntity newEntity = factory.manufacturePojo(PatrocinioEntity.class);
      
        PatrocinioEntity result = patrocinioLogic.createPatrocinio(newEntity);
        Assert.assertNotNull(result);
        PatrocinioEntity entity = em.find(PatrocinioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre() , entity.getNombre());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
     
    /**
     * Prueba para crear un Patrocinio con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPatrocinioTestConNameInvalido() throws BusinessLogicException {
        PatrocinioEntity newEntity = factory.manufacturePojo(PatrocinioEntity.class);
        newEntity.setNombre("");
        patrocinioLogic.createPatrocinio(newEntity);
    }

    /**
     * Prueba para crear un Patrocinio con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPatrocinioTestConNameInvalido2() throws BusinessLogicException {
        PatrocinioEntity newEntity = factory.manufacturePojo(PatrocinioEntity.class);
        newEntity.setNombre(null);
        patrocinioLogic.createPatrocinio(newEntity);
    }

    /**
     * Prueba para crear un Patrocinio con Name existente.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPatrocinioTestConNameExistente() throws BusinessLogicException {
        PatrocinioEntity newEntity = factory.manufacturePojo(PatrocinioEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        patrocinioLogic.createPatrocinio(newEntity);
    }

    /**
     * Prueba para consultar la lista de Patrocinios.
     */
    @Test
    public void getPatrociniosTest() {
        List<PatrocinioEntity> list = patrocinioLogic.getPatrocinios();
        Assert.assertEquals(data.size(), list.size());
        for (PatrocinioEntity entity : list) {
            boolean found = false;
            for (PatrocinioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para obtener una patrocinioque no existe.
     *
     * 
     */
    public void getPatrocinioNulo() throws BusinessLogicException {
        PatrocinioEntity result = patrocinioLogic.getPatrocinio((long)-1);
        Assert.assertNull(result);
    }

    /**
     * Prueba para consultar un Patrocinio.
     */
    @Test
    public void getPatrocinioTest() {
        PatrocinioEntity entity = data.get(0);
        PatrocinioEntity resultEntity = patrocinioLogic.getPatrocinio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para actualizar un Patrocinio.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void updatePatrocinioTest() throws BusinessLogicException {
        PatrocinioEntity entity = data.get(0);
        PatrocinioEntity pojoEntity = factory.manufacturePojo(PatrocinioEntity.class);
        pojoEntity.setId(entity.getId());
        patrocinioLogic.updatePatrocinio(pojoEntity.getId(), pojoEntity);
        PatrocinioEntity resp = em.find(PatrocinioEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para actualizar un Patrocinio con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePatrocinioConNameInvalidoTest() throws BusinessLogicException {
        PatrocinioEntity entity = data.get(0);
        PatrocinioEntity pojoEntity = factory.manufacturePojo(PatrocinioEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(entity.getId());
        patrocinioLogic.updatePatrocinio(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Patrocinio con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePatrocinioConNameInvalidoTest2() throws BusinessLogicException {
        PatrocinioEntity entity = data.get(0);
        PatrocinioEntity pojoEntity = factory.manufacturePojo(PatrocinioEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(entity.getId());
        patrocinioLogic.updatePatrocinio(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Patrocinio.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void deletePatrocinioTest() throws BusinessLogicException {
        PatrocinioEntity entity = data.get(0);
        patrocinioLogic.deletePatrocinio(entity.getId());
        PatrocinioEntity deleted = em.find(PatrocinioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
}
