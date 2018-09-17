/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.grupos.dtos;

import co.edu.uniandes.csw.grupos.entities.AdministradorEntity;
import co.edu.uniandes.csw.grupos.entities.CategoriaEntity;
import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
import co.edu.uniandes.csw.grupos.entities.EventoEntity;
import co.edu.uniandes.csw.grupos.entities.GrupoDeInteresEntity;
import co.edu.uniandes.csw.grupos.entities.NoticiaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class GrupoDeInteresDetailDTO extends GrupoDeInteresDTO {
    
    private List<BlogDTO> blogs;
    
    private List<AdministradorDTO> administradores;
    
    private List<NoticiaDTO> noticias;
    
    private List<EventoDTO> eventos;
    
    private List<CategoriaDTO> categorias;
    
    private List<CiudadanoDTO> ciudadanos;
    
    
    public GrupoDeInteresDetailDTO(){
        
        super();
        
    }
    
    public GrupoDeInteresDetailDTO(GrupoDeInteresEntity grupoEntity) {
        
        super(grupoEntity);
        
        if(grupoEntity != null){
            
            
            if(grupoEntity.getAdministradores() != null){
                
                administradores = new ArrayList<>();
                
                for(AdministradorEntity admin : grupoEntity.getAdministradores()){
                    
                    administradores.add(new AdministradorDTO(admin));
                    
                }
                
            }
            
            
            if(grupoEntity.getNoticias() != null){
                
                noticias = new ArrayList<>();
                
                for(NoticiaEntity noti : grupoEntity.getNoticias()){
                    
                    noticias.add(new NoticiaDTO(noti));
                    
                }
                
            }
            
            
            if(grupoEntity.getEventos() != null){
                
                eventos = new ArrayList<>();
                
                for(EventoEntity event : grupoEntity.getEventos()){
                    
                    eventos.add(new EventoDTO(event));
                    
                }
                
            }
            
            
            if(grupoEntity.getCategorias() != null){
                
                categorias = new ArrayList<>();
                
                for(CategoriaEntity cat : grupoEntity.getCategorias()){
                    
                    categorias.add(new CategoriaDTO(cat));
                    
                }
                
            }
            
            if(grupoEntity.getCiudadanos() != null){
                
                ciudadanos = new ArrayList<>();
                
                for(CiudadanoEntity ciud : grupoEntity.getCiudadanos()){
                    
                    ciudadanos.add(new CiudadanoDTO(ciud));
                    
                }
                
            }           
            
        }       
        
    }
    
    
    public List<BlogDTO> getBlogs() {
        return blogs;
    }
    
    public void setBlogs(List<BlogDTO> blogs) {
        this.blogs = blogs;
    }
    
    public List<AdministradorDTO> getAdministradores() {
        return administradores;
    }
    
    public void setAdministradores(List<AdministradorDTO> administradores) {
        this.administradores = administradores;
    }
    
    public List<NoticiaDTO> getNoticias() {
        return noticias;
    }
    
    public void setNoticias(List<NoticiaDTO> noticias) {
        this.noticias = noticias;
    }
    
    public List<EventoDTO> getEventos() {
        return eventos;
    }
    
    public void setEventos(List<EventoDTO> eventos) {
        this.eventos = eventos;
    }
    
    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
    
    public List<CiudadanoDTO> getCiudadanos() {
        return ciudadanos;
    }
    
    public void setCiudadanos(List<CiudadanoDTO> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }
    
    
    
    
    
    
    
    
    
    
    
}
