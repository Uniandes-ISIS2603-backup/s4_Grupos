
package co.edu.uniandes.csw.grupos.persistence;

import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Noticia. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author sarabepu
 */
@Stateless
public class NoticiaPersistence {
    


    private static final Logger LOGGER = Logger.getLogger(NoticiaPersistence.class.getName());

    @PersistenceContext(unitName = "GroupTeamPU")
    protected EntityManager em;

    /**
     * Crear una noticia
     *
     * Crea una nueva noticia con la información recibida en la entidad.
     *
     * @param noticiaEntity La entidad que representa la nueva noticia
     * @return La entidad creada
     */
    public NoticiaEntity create(NoticiaEntity noticiaEntity) {
        LOGGER.log(Level.INFO, "Creando un noticia nuevo");
        em.persist(noticiaEntity);
        LOGGER.log(Level.INFO, "Noticia creado");
        return noticiaEntity;
    }

    /**
     * Actualizar una noticia
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param noticiaEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public NoticiaEntity update(NoticiaEntity noticiaEntity) {
        LOGGER.log(Level.INFO, "Actualizando noticia con id = {0}", noticiaEntity.getId());
        return em.merge(noticiaEntity);
    }

    /**
     * Eliminar una noticia
     *
     * Elimina la noticia asociada al ID que recibe
     *
     * @param noticiasId El ID de la noticia que se desea borrar
     */
    public void delete(Long noticiasId) {
        LOGGER.log(Level.INFO, "Borrando noticia con id = {0}", noticiasId);
        NoticiaEntity noticiaEntity = em.find(NoticiaEntity.class, noticiasId);
        em.remove(noticiaEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El noticia con id = {0}", noticiasId);
    }

    /**
     * Buscar una noticia
     *
     * Busca si hay alguna noticia asociada a un libro y con un ID específico
     *
     * @param booksId El ID del libro con respecto al cual se busca
     * @param noticiasId El ID de la noticia buscada
     * @return La noticia encontrada o null. Nota: Si existe una o más noticias
     * devuelve siempre la primera que encuentra
     */
    public NoticiaEntity find(Long booksId, Long noticiasId) {
        LOGGER.log(Level.INFO, "Consultando el noticia con id = {0} del libro con id = " + booksId, noticiasId);
        TypedQuery<NoticiaEntity> q = em.createQuery("select p from NoticiaEntity p where (p.book.id = :bookid) and (p.id = :noticiasId)", NoticiaEntity.class);
        q.setParameter("bookid", booksId);
        q.setParameter("noticiasId", noticiasId);
        List<NoticiaEntity> results = q.getResultList();
        NoticiaEntity noticia = null;
        if (results == null) {
            noticia = null;
        } else if (results.isEmpty()) {
            noticia = null;
        } else if (results.size() >= 1) {
            noticia = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el noticia con id = {0} del libro con id =" + booksId, noticiasId);
        return noticia;
    }
}

   
