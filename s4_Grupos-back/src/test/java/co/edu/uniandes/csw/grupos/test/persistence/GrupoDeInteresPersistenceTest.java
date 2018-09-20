/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
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
public class GrupoDeInteresPersistenceTest {
    
    @Inject
    private GrupoDeInteresPersistence grupoInteresPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
            UserTransaction utx;
    
    private List<GrupoDeInteresEntity>  data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(GrupoDeInteresEntity.class.getPackage())
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
        em.createQuery("delete from GrupoDeInteresEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            
            GrupoDeInteresEntity entity = factory.manufacturePojo(GrupoDeInteresEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un grupo..
     */
    @Test
    public void createGrupoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        GrupoDeInteresEntity  newEntity = factory.manufacturePojo(GrupoDeInteresEntity.class);
        GrupoDeInteresEntity result = grupoInteresPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        GrupoDeInteresEntity  entity = em.find(GrupoDeInteresEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    
    /**
     * Prueba para consultar la lista de grupos.
     */
    @Test
    public void getGruposTest() {
        List<GrupoDeInteresEntity > list = grupoInteresPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (GrupoDeInteresEntity  ent : list) {
            boolean found = false;
            for (GrupoDeInteresEntity  entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Grupo.
     */
    @Test
    public void getGrupoTest() {
        GrupoDeInteresEntity entity = data.get(0);
        GrupoDeInteresEntity  newEntity = grupoInteresPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }
    
    /**
     * Prueba para actualizar un grupo.
     */
    @Test
    public void updateGrupoTest() {
        GrupoDeInteresEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        GrupoDeInteresEntity newEntity = factory.manufacturePojo( GrupoDeInteresEntity.class);
        
        newEntity.setId(entity.getId());
        
        grupoInteresPersistence.update(newEntity);
        
        GrupoDeInteresEntity resp = em.find( GrupoDeInteresEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar un Grupo.
     */
    @Test
    public void deleteGrupoTest() {
        GrupoDeInteresEntity entity = data.get(0);
        grupoInteresPersistence.delete(entity.getId());
        GrupoDeInteresEntity deleted = em.find( GrupoDeInteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
