
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
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
 * Pruebas de persistencia de Locacions
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class LocacionPersistenceTest {

    @Inject
    private LocacionPersistence locacionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        em.createQuery("delete from DistritoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DistritoEntity entity = factory.manufacturePojo(DistritoEntity.class);
            em.persist(entity);
            dataDistrito.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            LocacionEntity entity = factory.manufacturePojo(LocacionEntity.class);
            if (i == 0) {
                entity.setDistrito(dataDistrito.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Locacion.
     */
    @Test
    public void createLocacionTest() {

        PodamFactory factory = new PodamFactoryImpl();
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);
        LocacionEntity result = locacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LocacionEntity entity = em.find(LocacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para consultar un Locacion.
     */
    @Test
    public void getLocacionTest() {
        LocacionEntity entity = data.get(0);
        LocacionEntity newEntity = locacionPersistence.find(dataDistrito.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
       Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }

    /**
     * Prueba para eliminar un Locacion.
     */
    @Test
    public void deleteLocacionTest() {
        LocacionEntity entity = data.get(0);
        locacionPersistence.delete(entity.getId());
        LocacionEntity deleted = em.find(LocacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Locacion.
     */
    @Test
    public void updateLocacionTest() {
        LocacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LocacionEntity newEntity = factory.manufacturePojo(LocacionEntity.class);

        newEntity.setId(entity.getId());

        locacionPersistence.update(newEntity);

        LocacionEntity resp = em.find(LocacionEntity.class, entity.getId());

       Assert.assertEquals(newEntity.getDirecion(), entity.getDirecion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        
        Assert.assertEquals(newEntity.getLocacion(), entity.getLocacion());
        
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
}
