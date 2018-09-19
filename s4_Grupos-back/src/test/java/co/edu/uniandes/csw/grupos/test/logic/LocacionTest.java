/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.LocacionLogic;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
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
 *
 * @author j.barbosaj
 */
@RunWith(Arquillian.class)
public class LocacionTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LocacionLogic locacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LocacionEntity> data = new ArrayList<LocacionEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LocacionEntity.class.getPackage())
                .addPackage(LocacionLogic.class.getPackage())
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
     
        
        for (int i = 0; i < 3; i++) {
            LocacionEntity entity = factory.manufacturePojo(LocacionEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Locacion
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void createLocacionTest() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
      
        LocacionEntity result = locacionLogic.createLocacion(newEntity);
        Assert.assertNotNull(result);
        LocacionEntity entity = em.find(LocacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLocacion() , entity.getLocacion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
     
    /**
     * Prueba para crear un Locacion con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLocacionTestConNameInvalido() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        newEntity.setLocacion("");
        locacionLogic.createLocacion(newEntity);
    }

    /**
     * Prueba para crear un Locacion con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLocacionTestConNameInvalido2() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        newEntity.setLocacion(null);
        locacionLogic.createLocacion(newEntity);
    }

    /**
     * Prueba para crear un Locacion con Name existente.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLocacionTestConNameExistente() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        newEntity.setLocacion(data.get(0).getLocacion());
        locacionLogic.createLocacion(newEntity);
    }

    /**
     * Prueba para consultar la lista de Locacions.
     */
    @Test
    public void getLocacionsTest() {
        List<LocacionEntity> list = locacionLogic.getLocaciones();
        Assert.assertEquals(data.size(), list.size());
        for (LocacionEntity entity : list) {
            boolean found = false;
            for (LocacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para obtener una locacionque no existe.
     *
     * 
     */
    public void getLocacionNulo() throws BusinessLogicException {
        LocacionEntity result = locacionLogic.getLocacion((long)-1);
        Assert.assertNull(result);
    }
    /**
     * Prueba para consultar un Locacion.
     */
    @Test
    public void getLocacionTest() {
        LocacionEntity entity = data.get(0);
        LocacionEntity resultEntity = locacionLogic.getLocacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLocacion(), resultEntity.getLocacion());
    }

    /**
     * Prueba para actualizar un Locacion.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void updateLocacionTest() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        LocacionEntity pojoEntity = factory.manufacturePojo(LocacionEntity.class);
        pojoEntity.setId(entity.getId());
        locacionLogic.updateLocacion(pojoEntity.getId(), pojoEntity);
        LocacionEntity resp = em.find(LocacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getLocacion(), resp.getLocacion());
    }

    /**
     * Prueba para actualizar un Locacion con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateLocacionConNameInvalidoTest() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        LocacionEntity pojoEntity = factory.manufacturePojo(LocacionEntity.class);
        pojoEntity.setLocacion("");
        pojoEntity.setId(entity.getId());
        locacionLogic.updateLocacion(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Locacion con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateLocacionConNameInvalidoTest2() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        LocacionEntity pojoEntity = factory.manufacturePojo(LocacionEntity.class);
        pojoEntity.setLocacion(null);
        pojoEntity.setId(entity.getId());
        locacionLogic.updateLocacion(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Locacion.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteLocacionTest() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        locacionLogic.deleteLocacion(entity.getId());
        LocacionEntity deleted = em.find(LocacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
