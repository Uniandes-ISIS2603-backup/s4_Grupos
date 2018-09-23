/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.GrupoDeInteresLogic;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.GrupoDeInteresPersistence;
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
 * @author s.carrero
 */
@RunWith(Arquillian.class)
public class GrupoDeInteresLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private GrupoDeInteresLogic grupoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<GrupoDeInteresEntity> data = new ArrayList<GrupoDeInteresEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GrupoDeInteresEntity.class.getPackage())
                .addPackage(GrupoDeInteresLogic.class.getPackage())
                .addPackage(GrupoDeInteresPersistence.class.getPackage())
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
        em.createQuery("delete from GrupoDeInteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            GrupoDeInteresEntity entity = factory.manufacturePojo(GrupoDeInteresEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un grupo.     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createGrupoTest() throws BusinessLogicException {
        GrupoDeInteresEntity newEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);
        
        System.out.println("++"+newEntity.getNombre());
        
        GrupoDeInteresEntity result = grupoLogic.createGrupo(newEntity);
        
        System.out.println("++"+result.getNombre());
        Assert.assertNotNull(result);
        GrupoDeInteresEntity entity = em.find(GrupoDeInteresEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para crear un grupo con el mismo nombre de un grupo que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createGrupoConMismoNombreTest() throws BusinessLogicException {
        GrupoDeInteresEntity newEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        grupoLogic.createGrupo(newEntity);
    }

    /**
     * Prueba para consultar la lista de grupo.
     */
    @Test
    public void getGruposTest() {
        List<GrupoDeInteresEntity> list = grupoLogic.getGrupos();
        Assert.assertEquals(data.size(), list.size());
        for (GrupoDeInteresEntity entity : list) {
            boolean found = false;
            for (GrupoDeInteresEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un grupo.
     */
    @Test
    public void getGrupoTest() {
        GrupoDeInteresEntity entity = data.get(0);
        GrupoDeInteresEntity resultEntity = grupoLogic.getGrupo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para actualizar un grupo.
     */
    @Test
    public void updateGrupoTest() {
        GrupoDeInteresEntity entity = data.get(0);
        GrupoDeInteresEntity pojoEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);
        pojoEntity.setId(entity.getId());
        grupoLogic.updateGrupo(pojoEntity.getId(), pojoEntity);
        GrupoDeInteresEntity resp = em.find(GrupoDeInteresEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un grupo.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteGrupoTest() {
        GrupoDeInteresEntity entity = data.get(1);
        grupoLogic.deleteGrupo(entity.getId());
        GrupoDeInteresEntity deleted = em.find(GrupoDeInteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
}
