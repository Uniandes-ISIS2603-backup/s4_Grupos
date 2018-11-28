/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.ejb.PatrocinioLogic;
import co.edu.uniandes.csw.grupos.entities.PatrocinioEntity;
import java.io.Serializable;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * LocacionDTO Objeto de transferencia de datos de Patrocinio. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *     "nombre" : Strimng,
 *     "valor" : numero
 *   }
 * </pre> Por ejemplo un patrocinador se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "nombre" : JuanM,
 *      "valor" : 2000000
 *   }
 *
 * </pre>
 *
 * @author j.barbosaj  Josealejandro Barbosa ISIS2603
 */
public class PatrocinioDTO implements Serializable
 {
     private static final Logger LOGGER = Logger.getLogger(PatrocinioLogic.class.getName());
    
    private String nombre;
    private Integer valor;
    private Long id;
    
    /**
     * Constructor por defecto
     */
    public PatrocinioDTO ()
    {
           
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param patrocinioEntity: Es la entidad que se va a convertir a DTO
     */    
    public PatrocinioDTO(PatrocinioEntity patrocinioEntity)
    {
       LOGGER.info("comienza la creacion del DTO patrocinio");
       if (patrocinioEntity != null) {
       this.nombre =  (patrocinioEntity.getNombre());
       this.valor =(patrocinioEntity.getValor());
       this.id = (patrocinioEntity.getId());
       LOGGER.info("Termina la creacion del DTO patrocinio");
       }
    }
    

     /**
     * Devuelve el Nombre del patrocinador.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

     /**
     * Modifica el Nombre del patrocinador.
     *
     * @param nombre el nombre del nuevo patrocinador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     /**
     * Devuelve el Valor del patrocinio.
     *
     * @return la locacion
     */
    public Integer getValor() {
        return valor;
    }

     /**
     * Modifica el Valor del patrocinio.
     *
     * @param valor el valor del nuevo patrocinio
     */
    public void setValor(Integer valor) {
        this.valor = valor;
    }
   
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    
    
    public PatrocinioEntity toEntity() {
         PatrocinioEntity patrocinioEntity = new PatrocinioEntity();
         patrocinioEntity.setId(this.id);
        patrocinioEntity.setNombre(this.nombre);
        patrocinioEntity.setValor(this.valor);
              
        return patrocinioEntity;
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

   /**
     * Modifica el ID del patrocinio
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * retorna el id del patrocinio
     * @return  id  
     */
    public Long getId()
    {
        return this.id;
    }            
           
    
    
}
