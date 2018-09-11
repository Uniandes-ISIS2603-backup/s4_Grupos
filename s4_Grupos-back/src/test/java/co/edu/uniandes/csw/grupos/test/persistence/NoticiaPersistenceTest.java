
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import co.edu.uniandes.csw.grupos.persistence.NoticiaPersistence;
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
 * Pruebas de persistencia de Noticias
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class NoticiaPersistenceTest {

    @Inject
    private NoticiaPersistence noticiaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<NoticiaEntity> data = new ArrayList<NoticiaEntity>();
	
    private List<GrupoDeInteresEntity> dataGrupoDeInteres = new ArrayList<GrupoDeInteresEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NoticiaEntity.class.getPackage())
                .addPackage(NoticiaPersistence.class.getPackage())
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
        em.createQuery("delete from NoticiaEntity").executeUpdate();
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
            dataGrupoDeInteres.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            NoticiaEntity entity = factory.manufacturePojo(NoticiaEntity.class);
            if (i == 0) {
                entity.setGrupoDeInteres(dataGrupoDeInteres.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Noticia.
     */
    @Test
    public void createNoticiaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        NoticiaEntity newEntity = factory.manufacturePojo(NoticiaEntity.class);
        NoticiaEntity result = noticiaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        NoticiaEntity entity = em.find(NoticiaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
    }

    /**
     * Prueba para consultar un Noticia.
     */
    @Test
    public void getNoticiaTest() {
        NoticiaEntity entity = data.get(0);
        NoticiaEntity newEntity = noticiaPersistence.find(dataGrupoDeInteres.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getRutaImagen(), newEntity.getRutaImagen());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }

    /**
     * Prueba para eliminar un Noticia.
     */
    @Test
    public void deleteNoticiaTest() {
        NoticiaEntity entity = data.get(0);
        noticiaPersistence.delete(entity.getId());
        NoticiaEntity deleted = em.find(NoticiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Noticia.
     */
    @Test
    public void updateNoticiaTest() {
        NoticiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        NoticiaEntity newEntity = factory.manufacturePojo(NoticiaEntity.class);

        newEntity.setId(entity.getId());

        noticiaPersistence.update(newEntity);

        NoticiaEntity resp = em.find(NoticiaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getRutaImagen(), resp.getRutaImagen());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
}
