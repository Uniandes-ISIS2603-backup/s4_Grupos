
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.DistritoLogic;
import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.persistence.DistritoPersistence;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Distritos
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class DistritoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private DistritoLogic distritoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DistritoEntity> data = new ArrayList<DistritoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DistritoEntity.class.getPackage())
                .addPackage(DistritoLogic.class.getPackage())
                .addPackage(DistritoPersistence.class.getPackage())
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
        em.createQuery("delete from DistritoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
     
        
        for (int i = 0; i < 3; i++) {
            DistritoEntity entity = factory.manufacturePojo(DistritoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Distrito
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void createDistritoTest() throws BusinessLogicException {
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);
      
        DistritoEntity result = distritoLogic.createDistrito(newEntity);
        Assert.assertNotNull(result);
        DistritoEntity entity = em.find(DistritoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
     
    /**
     * Prueba para crear un Distrito con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createDistritoTestConNameInvalido() throws BusinessLogicException {
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);
        newEntity.setName("");
        distritoLogic.createDistrito(newEntity);
    }

    /**
     * Prueba para crear un Distrito con Name inválido
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createDistritoTestConNameInvalido2() throws BusinessLogicException {
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);
        newEntity.setName(null);
        distritoLogic.createDistrito(newEntity);
    }

    /**
     * Prueba para crear un Distrito con Name existente.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createDistritoTestConNameExistente() throws BusinessLogicException {
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);
        newEntity.setName(data.get(0).getName());
        distritoLogic.createDistrito(newEntity);
    }

    /**
     * Prueba para consultar la lista de Distritos.
     */
    @Test
    public void getDistritosTest() {
        List<DistritoEntity> list = distritoLogic.getDistritos();
        Assert.assertEquals(data.size(), list.size());
        for (DistritoEntity entity : list) {
            boolean found = false;
            for (DistritoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para obtener una distritoque no existe.
     *
     * 
     */
    public void getDistritoNulo() throws BusinessLogicException {
        DistritoEntity result = distritoLogic.getDistrito((long)-1);
        Assert.assertNull(result);
    }

    /**
     * Prueba para consultar un Distrito.
     */
    @Test
    public void getDistritoTest() {
        DistritoEntity entity = data.get(0);
        DistritoEntity resultEntity = distritoLogic.getDistrito(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para actualizar un Distrito.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void updateDistritoTest() throws BusinessLogicException {
        DistritoEntity entity = data.get(0);
        DistritoEntity pojoEntity = factory.manufacturePojo(DistritoEntity.class);
        pojoEntity.setId(entity.getId());
        distritoLogic.updateDistrito(pojoEntity.getId(), pojoEntity);
        DistritoEntity resp = em.find(DistritoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para actualizar un Distrito con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDistritoConNameInvalidoTest() throws BusinessLogicException {
        DistritoEntity entity = data.get(0);
        DistritoEntity pojoEntity = factory.manufacturePojo(DistritoEntity.class);
        pojoEntity.setName("");
        pojoEntity.setId(entity.getId());
        distritoLogic.updateDistrito(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Distrito con Name inválido.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateDistritoConNameInvalidoTest2() throws BusinessLogicException {
        DistritoEntity entity = data.get(0);
        DistritoEntity pojoEntity = factory.manufacturePojo(DistritoEntity.class);
        pojoEntity.setName(null);
        pojoEntity.setId(entity.getId());
        distritoLogic.updateDistrito(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para eliminar un Distrito.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteDistritoTest() throws BusinessLogicException {
        DistritoEntity entity = data.get(0);
        distritoLogic.deleteDistrito(entity.getId());
        DistritoEntity deleted = em.find(DistritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
}
