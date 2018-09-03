/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class LocacionEntity extends BaseEntity {
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.ALL)
    private DistritoEntity distrito;

    public DistritoEntity getDistrito() {
        return distrito;
    }

    public void setDistrito(DistritoEntity distrito) {
        this.distrito = distrito;
    }
}
