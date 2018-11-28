
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
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
    * Esta lista de tipo ComentarioDTO contiene los books que estan asociados a una noticia
     */
    private List<ComentarioDTO> comentarios;

    /**
     * Constructor por defecto
     */
    public NoticiaDetailDTO() {
    super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param noticiaEntity La entidad de la noticia para transformar a DTO.
     **/
    public NoticiaDetailDTO(NoticiaEntity noticiaEntity) {
        super(noticiaEntity);
        if (noticiaEntity != null) {
            if (noticiaEntity.getComentarios()!= null) {
                comentarios = new ArrayList<>();
                for (  ComentarioEntity entityCom : noticiaEntity.getComentarios()) {
                    comentarios.add(new ComentarioDTO(entityCom));
                }
            }
        }
    }
    

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la noticia para transformar a Entity
     *
    * */
    @Override
    public NoticiaEntity toEntity() {
        NoticiaEntity noticiaEntity = super.toEntity();
        if (comentarios!= null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO dtoComentario : getComentarios()) {
                comentariosEntity.add(dtoComentario.toEntity());
            }
            noticiaEntity.setComentarios(comentariosEntity);
        }
        return noticiaEntity;
    }
    

    /**
     * Devuelve la lista de libros de la noticia.
     *
     * @return the books
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * Modifica la lista de comentarios de la noticia.
     *
     * @param comentarios  Los comentarios a establecer
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios    = comentarios;
    }
/**
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    * **/
}
