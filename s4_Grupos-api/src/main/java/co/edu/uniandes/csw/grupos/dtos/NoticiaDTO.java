/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;
import java.io.Serializable;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;

/**
 * NoticiaDTO Objeto de transferencia de datos de Noticias. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author estudiante
 */
public class NoticiaDTO implements Serializable 
{
      
private String descripcion;
private String rutaImagen;
private Long id;
private String titular;


 /**
     * Constructor a partir de la entidad
     *
     * @param noticia La entidad de la noticia
     */
    public NoticiaDTO(NoticiaEntity noticia) {
        if (noticia != null) {
            this.id = noticia.getId();
            descripcion= noticia.getDescripcion();
            rutaImagen= noticia.getRutaImagen();
            titular=noticia.getTitular();
        
        }
    }
    /**Constructor sin parametros
     * 
     */
    public NoticiaDTO()
    {
        
    }


  /**
     * Devuelve la descripcion de la noticia..
     *
     * @return la descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     /**
     * Devuelve la descripcion de la noticia..
     *
     * @return la descripcion
     */
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
      /**
     * Devuelve la ruta de la imagen de noticia.
     *
     * @return las ruta
     */

    public String getRutaImagen() {
        return rutaImagen;
    }
 /**
     * Modifica laruta de la imagen de la noticia
     *
     * @param rutaImagen ruta nueva
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
  /**
     * Devuelve el ID de la noticia.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }
 /**
     * Modifica el ID de la noticia
     *
     * @param id nuevo
     */
    public void setId(Long id) {
        this.id = id;
    }
    public NoticiaEntity toEntity()
    {
        NoticiaEntity noticia= new NoticiaEntity();
        noticia.setId(id);
        noticia.setDescripcion(descripcion);
        noticia.setRutaImagen(rutaImagen);
        noticia.setTitular(titular);
        return noticia;
    }
    
}
