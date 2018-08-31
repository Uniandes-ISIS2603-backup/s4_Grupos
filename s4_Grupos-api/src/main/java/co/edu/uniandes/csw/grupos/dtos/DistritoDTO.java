
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DistritoDTO Objeto de transferencia de datos de Distritos. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string
 *   }
 * </pre> Por ejemplo una distrito se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "name": "Norma"
 *   }
 *
 * </pre>
 *
 * @author ISIS2603
 */
public class DistritoDTO implements Serializable {

   
    private String name;
    private Long id;

    /**
     * Constructor por defecto
     */
    public DistritoDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param distritoEntity: Es la entidad que se va a convertir a DTO
     
    public DistritoDTO(DistritoEntity distritoEntity) {
        if (distritoEntity != null) {
            this.id = distritoEntity.getId();
            this.name = distritoEntity.getName();
        }
    }
    * /

    /**
     * Devuelve el ID de la distrito.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la distrito.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la distrito.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de la distrito.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    /**
    public DistritoEntity toEntity() {
        DistritoEntity distritoEntity = new DistritoEntity();
        distritoEntity.setId(this.id);
        distritoEntity.setName(this.name);
        return distritoEntity;
    }
    * 

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    */
}
