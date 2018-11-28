/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.LocacionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * LocacionDTO Objeto de transferencia de datos de Locacion. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "locacion": string,
 *      "direcion": numero,
 *      "tipo": string,
 *      "latitud" : numero,
 *      "longitud" : numero,
 *      "id" : long
 *   }
 * </pre> Por ejemplo una locacion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "locacion": ParqueX,
 *      "direcion": 89876,
 *      "tipo": parque,
 *      "latitud" : 1729.9891,
 *      "longitud" : 1729.9893,
 *      "id" : 1
 *   }
 *
 * </pre>
 *
 * @author j.barbosaj Josealejandro Barbosa ISIS2603
 */
public class LocacionDTO implements Serializable
{
    private String locacion;
    private String direcion;
    private String tipo;
    private Long latitud;
    private Long longitud;
    private Long id;

   
    
    /**
     * Constructor por defecto
     */
    public LocacionDTO()
    {
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param locacionEntity: Es la entidad que se va a convertir a DTO
     */   
    public  LocacionDTO(LocacionEntity locacionEntity) {
        if (locacionEntity != null) {

         this.direcion=locacionEntity.getDirecion();
        this.id=locacionEntity.getId();
        this.latitud=locacionEntity.getLatitud();
        this.longitud=locacionEntity.getLongitud();
        this.locacion=locacionEntity.getLocacion();
        this.tipo=locacionEntity.getTipo();
        this.id=locacionEntity.getId();

        }
    }
    
    
    /**
     * Devuelve el Nombre de la locacion.
     *
     * @return la locacion
     */
    public String getLocacion() {
        return locacion;
    }
    
     /**
     * Modifica el Nombre de la locacion.
     *
     * @param locacion el nombre de la locacion
     */
    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

     /**
     * Devuelve la direcion de la locacion.
     *
     * @return la direcion
     */
    public String getDirecion() {
        return direcion;
    }

     /**
     * Modifica la direcion de la locacion.
     *
     * @param direcion la direcion de la locacion
     */
    public void setDirecion(String direcion) {
        this.direcion = direcion;
    }
    
     /**
     * Devuelve el tipo de la locacion.
     *
     * @return el tipo de locacion
     */
    public String getTipo() {
        return tipo;
    }

     /**
     * Modifica el tipo de la locacion.
     *
     * @param tipo el tipo de la locacion
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la latitud de la locacion.
     *
     * @return latitud de locacion
     */
    public Long getLatitud() {
        return latitud;
    }

     /**
     * Modifica la latitud de la locacion.
     *
     * @param latitud la latitud de la locacion
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * Devuelve la longitud de la locacion.
     *
     * @return longitud de locacion
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * Modifica la longitud de la locacion.
     *
     * @param longitud la longitud de la locacion
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

     /**
     * Devuelve el id de la locacion.
     *
     * @return id de locacion
     */
    public Long getId() {
        return id;
    }

     /**
     * Modifica la id de la locacion.
     *
     * @param id la longitud de la locacion
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    
    
    public LocacionEntity toEntity() {
        LocacionEntity locacionEntity = new LocacionEntity();
       
         locacionEntity.setId(this.id);
         locacionEntity.setLocacion(this.locacion);
         locacionEntity.setDirecion(this.direcion);
         locacionEntity.setTipo(this.tipo);
         locacionEntity.setLatitud(this.latitud);
         locacionEntity.setLongitud(this.longitud);
        
        return locacionEntity;
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
