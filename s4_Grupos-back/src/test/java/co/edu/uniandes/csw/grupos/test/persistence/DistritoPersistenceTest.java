
package co.edu.uniandes.csw.grupos.test.persistence;
import co.edu.uniandes.csw.grupos.entities.DistritoEntity;
import co.edu.uniandes.csw.grupos.persistence.DistritoPersistence;
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
 * Pruebas de persistencia de Books
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class DistritoPersistenceTest {

    
    @Inject
    private DistritoPersistence distritoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Distrito.
     */
    @Test
    public void createDistritoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);
        DistritoEntity result = distritoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        DistritoEntity entity = em.find(DistritoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getDistritosTest() {
        List<DistritoEntity> list = distritoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DistritoEntity ent : list) {
            boolean found = false;
            for (DistritoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getDistritoTest() {
        DistritoEntity entity = data.get(0);
        DistritoEntity newEntity = distritoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Distrito.
     */
    @Test
    public void deleteDistritoTest() {
        DistritoEntity entity = data.get(0);
        distritoPersistence.delete(entity.getId());
        DistritoEntity deleted = em.find(DistritoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Distrito.
     */
    @Test
    public void updateDistritoTest() {
        DistritoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DistritoEntity newEntity = factory.manufacturePojo(DistritoEntity.class);

        newEntity.setId(entity.getId());

        distritoPersistence.update(newEntity);

        DistritoEntity resp = em.find(DistritoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
