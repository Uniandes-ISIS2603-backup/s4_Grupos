/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ac.beltrans
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable {
      //relación  uno o muchos grupos de interés
      private List<GrupoDeInteresDTO> gruposDeInteres;

        public AdministradorDetailDTO() {
            super();
        }

    /**
     * Devuelve los grupos de interes asociados al administrador
     *
     * @return Lista de DTOs de grupos de interes
     */
    public List<GrupoDeInteresDTO> getGruposDeInteres() {
        return gruposDeInteres;
    }

    /**
     * Modifica los grupos de interes del administrador.
     *
     * @param gruposDeInteres grupos de interes que se quieren modificar
     */
    public void setGruposDeInteres(List<GrupoDeInteresDTO> gruposDeInteres) {
        this.gruposDeInteres = gruposDeInteres;
    }
}
