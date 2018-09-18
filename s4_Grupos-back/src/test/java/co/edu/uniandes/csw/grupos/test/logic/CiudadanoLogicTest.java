/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.logic;

import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.ejb.CiudadanoLogic;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.grupos.persistence.CiudadanoPersistence;
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
 * @ciudadano estudiante
 */
@RunWith(Arquillian.class)
public class CiudadanoLogicTest 
{
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private CiudadanoLogic ciudadanoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CiudadanoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadanoEntity.class.getPackage())
                .addPackage(CiudadanoLogic.class.getPackage())
                .addPackage(CiudadanoPersistence.class.getPackage())
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
        em.createQuery("delete from CiudadanoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CiudadanoEntity entity = factory.manufacturePojo(CiudadanoEntity.class);
            em.persist(entity);
            entity.setGruposDeInteres(new ArrayList<>());
            data.add(entity);
        }
//        CiudadanoEntity ciudadano = data.get(2);
//        GrupoDeInteresEntity entity = factory.manufacturePojo(GrupoDeInteresEntity.class);
//        entity.getCiudadanos().add(ciudadano);
//        em.persist(entity);
//        ciudadano.getGruposDeInteres().add(entity);

//        PrizeEntity prize = factory.manufacturePojo(PrizeEntity.class);
//        prize.setCiudadano(data.get(1));
//        em.persist(prize);
//        data.get(1).getPrizes().add(prize);
    }

    /**
     * Prueba para crear un Ciudadano.
     */
    @Test
    public void createCiudadanoTest() {
        CiudadanoEntity newEntity = factory.manufacturePojo(CiudadanoEntity.class);
        CiudadanoEntity result = ciudadanoLogic.createCiudadano(newEntity);
        Assert.assertNotNull(result);
        CiudadanoEntity entity = em.find(CiudadanoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getContraseña(), entity.getContraseña());
    }

    /**
     * Prueba para consultar la lista de Ciudadanos.
     */
    @Test
    public void getCiudadanosTest() {
        List<CiudadanoEntity> list = ciudadanoLogic.getCiudadanos();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadanoEntity entity : list) {
            boolean found = false;
            for (CiudadanoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Ciudadano.
     */
    @Test
    public void getCiudadanoTest() {
        CiudadanoEntity entity = data.get(0);
        CiudadanoEntity resultEntity = ciudadanoLogic.getCiudadano(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getContraseña(), resultEntity.getContraseña());
    }

    /**
     * Prueba para actualizar un Ciudadano.
     */
    @Test
    public void updateCiudadanoTest() {
        CiudadanoEntity entity = data.get(0);
        CiudadanoEntity pojoEntity = factory.manufacturePojo(CiudadanoEntity.class);

        pojoEntity.setId(entity.getId());

        ciudadanoLogic.updateCiudadano(pojoEntity.getId(), pojoEntity);

        CiudadanoEntity resp = em.find(CiudadanoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getContraseña(), resp.getContraseña());
    }

    /**
     * Prueba para eliminar un Ciudadano
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteCiudadanoTest() throws BusinessLogicException {
        CiudadanoEntity entity = data.get(0);
        ciudadanoLogic.deleteCiudadano(entity.getId());
        CiudadanoEntity deleted = em.find(CiudadanoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
