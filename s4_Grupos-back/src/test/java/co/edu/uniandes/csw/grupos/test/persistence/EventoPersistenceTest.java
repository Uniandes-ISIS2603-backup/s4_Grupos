/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.test.persistence;

import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.persistence.EventoPersistence;
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
 * Pruebas de persistencia de Eventos
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class EventoPersistenceTest {

    @Inject
    private EventoPersistence eventoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<EventoEntity> data = new ArrayList<EventoEntity>();
	
    private List<GrupoDeInteresEntity> dataGrupoDeInteres = new ArrayList<GrupoDeInteresEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
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
        em.createQuery("delete from EventoEntity").executeUpdate();
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
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);
            if (i == 0) {
                entity.setGrupo(dataGrupoDeInteres.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Evento.
     */
    @Test
    public void createEventoTest() {

        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity result = eventoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EventoEntity entity = em.find(EventoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar un Evento.
     */
    @Test
    public void getEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = eventoPersistence.find(dataGrupoDeInteres.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
    }

    /**
     * Prueba para eliminar un Evento.
     */
    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(0);
        eventoPersistence.delete(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Evento.
     */
    @Test
    public void updateEventoTest() {
        EventoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

        newEntity.setId(entity.getId());

        eventoPersistence.update(newEntity);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
    }
}
