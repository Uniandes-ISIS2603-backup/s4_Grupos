
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link NoticiaDTO} para manejar las relaciones entre
 * los Noticia JSON y otros DTOs. Para conocer el contenido de una
 * Noticia vaya a la documentacion de {@link NoticiaDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "descripcion": string,
 *      "rutaImagen": string,
 *      "comentarios": [{@link ComentarioDTO}]
 *   }
 * </pre> 
 *
 * @author sarabepu
 */
public class NoticiaDetailDTO extends NoticiaDTO implements Serializable {

    /*
    * Esta lista de tipo ComentarioDTO contiene los books que estan asociados a una editorial
     */
    private List<ComentarioDTO> comentarios;

    /**
     * Constructor por defecto
     */
    public NoticiaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param editorialEntity La entidad de la editorial para transformar a DTO.
     *
    public NoticiaDetailDTO(NoticiaEntity editorialEntity) {
        super(editorialEntity);
        if (editorialEntity != null) {
            if (editorialEntity.getBooks() != null) {
                books = new ArrayList<>();
                for (BookEntity entityBook : editorialEntity.getBooks()) {
                    books.add(new BookDTO(entityBook));
                }
            }
        }
    }
    * */

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     *
    @Override
    public NoticiaEntity toEntity() {
        NoticiaEntity editorialEntity = super.toEntity();
        if (books != null) {
            List<BookEntity> booksEntity = new ArrayList<>();
            for (BookDTO dtoBook : books) {
                booksEntity.add(dtoBook.toEntity());
            }
            editorialEntity.setBooks(booksEntity);
        }
        return editorialEntity;
    }
    * */

    /**
     * Devuelve la lista de libros de la editorial.
     *
     * @return the books
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * Modifica la lista de libros de la editorial.
     *
     * @param books the books to set
     */
    public void setBooks(List<ComentarioDTO> comentarios) {
        this.comentarios    = comentarios;
    }
/**
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    * **/
}
