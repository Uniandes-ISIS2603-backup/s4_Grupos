/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tempelte file, choose Tools | Tempeltes
 * and open the tempelte in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase que extiende de {@link DistritoDTO} para manejar las relaciones entre
 * los Distrito JSON y otros DTOs. Para conocer el contenido de la una
 * Distrito vaya a la documentacion de {@link DistritoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "locaciones": [{@link LocacionDTO}]
 *   }
 * @author sarabepu
 */
public class DistritoDetailDTO {
    
    /*
    * Esta lista de tipo BookDTO contiene los books que estan asociados a una distrito
     */
    private List<LocacionDTO> locaciones;

   

    /**
     * Constructor por defecto
     */
    public DistritoDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param distritoEntity La entidad de el distrito para transformar a DTO.
     *
    public DistritoDetailDTO(DistritoEntity distritoEntity) {
        super(distritoEntity);
        if (distritoEntity != null) {
            if (distritoEntity.getBooks() != null) {
                books = new ArrayList<>();
                for (BookEntity entityBook : distritoEntity.getBooks()) {
                    books.add(new BookDTO(entityBook));
                }
            }
        }
        
    }
    */

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de el distrito para transformar a Entity
     *
    @Override
    public DistritoEntity toEntity() {
        DistritoEntity distritoEntity = super.toEntity();
        if (books != null) {
            List<BookEntity> booksEntity = new ArrayList<>();
            for (BookDTO dtoBook : books) {
                booksEntity.add(dtoBook.toEntity());
            }
            distritoEntity.setBooks(booksEntity);
        }
        return distritoEntity;
        
    }
    * */

    /**
     * Devuelve el lista de locaciones de el distrito.
     *
     * @return els locaciones
     */
    public List<LocacionDTO> getLocaciones() {
        return locaciones;
    }

   
    /**
     * Modifica el lista de libros de el distrito.
     *
     * @param books the books to set
     */
    public void setLocaciones(List<LocacionDTO> locaciones) {
        this.locaciones = locaciones;
    }
/**
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    **/
}
