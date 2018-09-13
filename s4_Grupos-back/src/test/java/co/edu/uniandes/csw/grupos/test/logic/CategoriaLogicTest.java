/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.CategoriaLogic;
import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.CategoriaPersistence;
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
public class CategoriaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CategoriaLogic grupoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CategoriaEntity> data = new ArrayList<CategoriaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CategoriaEntity.class.getPackage())
                .addPackage(CategoriaLogic.class.getPackage())
                .addPackage(CategoriaPersistence.class.getPackage())
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
        em.createQuery("delete from CategoriaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            CategoriaEntity entity = factory.manufacturePojo(CategoriaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una categoria.     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createCategoriaTest() throws BusinessLogicException {
        CategoriaEntity newEntity = factory.manufacturePojo(CategoriaEntity.class);
               
        CategoriaEntity result = grupoLogic.createCategoria(newEntity);
        
        Assert.assertNotNull(result);
        CategoriaEntity entity = em.find(CategoriaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para crear una categoria con el mismo nombre de una categoria que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCategoriaConMismoNombreTest() throws BusinessLogicException {
        CategoriaEntity newEntity = factory.manufacturePojo(CategoriaEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        grupoLogic.createCategoria(newEntity);
    }

    /**
     * Prueba para consultar la lista de categorias.
     */
    @Test
    public void getCategoriasTest() {
        List<CategoriaEntity> list = grupoLogic.getCategorias();
        Assert.assertEquals(data.size(), list.size());
        for (CategoriaEntity entity : list) {
            boolean found = false;
            for (CategoriaEntity storedEntity : data) {
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
    public void getCategoriaTest() {
        CategoriaEntity entity = data.get(0);
        CategoriaEntity resultEntity = grupoLogic.getCategoria(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }

    /**
     * Prueba para actualizar un grupo.
     */
    @Test
    public void updateCategoriaTest() {
        CategoriaEntity entity = data.get(0);
        CategoriaEntity pojoEntity = factory.manufacturePojo(CategoriaEntity.class);
        pojoEntity.setId(entity.getId());
        grupoLogic.updateCategoria(pojoEntity.getId(), pojoEntity);
        CategoriaEntity resp = em.find(CategoriaEntity.class, entity.getId());
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
        CategoriaEntity entity = data.get(1);
        grupoLogic.deleteCategoria(entity.getId());
        CategoriaEntity deleted = em.find(CategoriaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
    
}
