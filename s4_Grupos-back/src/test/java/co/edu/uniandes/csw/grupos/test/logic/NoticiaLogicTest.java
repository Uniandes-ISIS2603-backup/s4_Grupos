
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.ejb.NoticiaLogic;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
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
 * Pruebas de logica de Noticias
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class NoticiaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private NoticiaLogic noticiaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

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
                .addPackage(NoticiaLogic.class.getPackage())
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
        
        for (int i = 0; i < 3; i++) {
            GrupoDeInteresEntity entity = factory.manufacturePojo(GrupoDeInteresEntity.class);
            em.persist(entity);
            dataGrupoDeInteres.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            NoticiaEntity entity = factory.manufacturePojo(NoticiaEntity.class);
            entity.setGrupoDeInteres(dataGrupoDeInteres.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Noticia.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void createNoticiaTest() throws BusinessLogicException {
        NoticiaEntity newEntity = factory.manufacturePojo(NoticiaEntity.class);
        newEntity.setGrupoDeInteres(dataGrupoDeInteres.get(1));
        NoticiaEntity result = noticiaLogic.createNoticia(dataGrupoDeInteres.get(1).getId(), newEntity);
        Assert.assertNotNull(result);
        NoticiaEntity entity = em.find(NoticiaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getRutaImagen(), entity.getRutaImagen());
    }

    /**
     * Prueba para consultar la lista de Noticias.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void getNoticiasTest() throws BusinessLogicException {
        List<NoticiaEntity> list = noticiaLogic.getNoticias(dataGrupoDeInteres.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (NoticiaEntity entity : list) {
            boolean found = false;
            for (NoticiaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Noticia.
     */
    @Test
    public void getNoticiaTest() {
        NoticiaEntity entity = data.get(0);
        NoticiaEntity resultEntity = noticiaLogic.getNoticia(dataGrupoDeInteres.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getRutaImagen(), resultEntity.getRutaImagen());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }

    /**
     * Prueba para actualizar un Noticia.
     */
    @Test
    public void updateNoticiaTest() {
        NoticiaEntity entity = data.get(0);
        NoticiaEntity pojoEntity = factory.manufacturePojo(NoticiaEntity.class);

        pojoEntity.setId(entity.getId());

        noticiaLogic.updateNoticia(dataGrupoDeInteres.get(1).getId(), pojoEntity);

        NoticiaEntity resp = em.find(NoticiaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getRutaImagen(), resp.getRutaImagen());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }

    /**
     * Prueba para eliminar un Noticia.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteNoticiaTest() throws BusinessLogicException {
        NoticiaEntity entity = data.get(0);
        noticiaLogic.deleteNoticia(dataGrupoDeInteres.get(1).getId(), entity.getId());
        NoticiaEntity deleted = em.find(NoticiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminarle un noticia a un grupo del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteNoticiaConGrupoDeInteresNoAsociadoTest() throws BusinessLogicException {
        NoticiaEntity entity = data.get(0);
        noticiaLogic.deleteNoticia(dataGrupoDeInteres.get(0).getId(), entity.getId());
    }
}
