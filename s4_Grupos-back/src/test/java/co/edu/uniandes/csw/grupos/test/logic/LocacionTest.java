
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.LocacionLogic;
import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.LocacionPersistence;
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
 * Pruebas de logica de Locacions
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class LocacionTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LocacionLogic noticiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LocacionEntity> data = new ArrayList<LocacionEntity>();

    private List<DistritoEntity> dataDistrito = new ArrayList<DistritoEntity>();


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
            dataDistrito.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            LocacionEntity entity = factory.manufacturePojo(LocacionEntity.class);
            entity.setDistrito(dataDistrito.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Locacion.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void createLocacionTest() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        newEntity.setDistrito(dataDistrito.get(1));
        LocacionEntity result = noticiaLogic.createLocacion(dataDistrito.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        LocacionEntity entity = em.find(LocacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
         Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
      /**
     * Prueba para crear una noticia a un grupo que no existe.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLocacionConDistritoNulo() throws BusinessLogicException {
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        LocacionEntity result = noticiaLogic.createLocacion((long)-1, newEntity);
        Assert.assertNull(result);
    }
 
    /**
     * Prueba para consultar la lista de Locacions.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void getLocacionsTest() throws BusinessLogicException {
        List<LocacionEntity> list = noticiaLogic.getLocaciones(dataDistrito.get(1).getId());
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
     * Prueba para consultar un Locacion.
     */
    @Test
    public void getLocacionTest() {
        LocacionEntity entity = data.get(0);
        LocacionEntity newEntity = noticiaLogic.getLocacion(dataDistrito.get(1).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(),newEntity.getId());
           Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para actualizar un Locacion.
     */
    @Test
    public void updateLocacionTest() {
        LocacionEntity entity = data.get(0);
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);

        newEntity.setId(entity.getId());

        noticiaLogic.updateLocacion(dataDistrito.get(1).getId(), newEntity);

        LocacionEntity resp = em.find(LocacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
          Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para eliminar un Locacion.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteLocacionTest() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        noticiaLogic.deleteLocacion(dataDistrito.get(1).getId(), entity.getId());
        LocacionEntity deleted = em.find(LocacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un noticia a un grupo del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteLocacionConDistritoNoAsociadoTest() throws BusinessLogicException {
        LocacionEntity entity = data.get(0);
        noticiaLogic.deleteLocacion(dataDistrito.get(0).getId(), entity.getId());
    }
}
