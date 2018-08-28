/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.dtos;

import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class GrupoDeInteresDetailDTO extends GrupoDeInteresDTO {
    
    private ArrayList<BlogDTO> blogs;
    
    private ArrayList<AdministradorDTO> administradores;
    
    private ArrayList<NoticiaDTO> noticias;
    
    private ArrayList<EventoDTO> eventos;
    
    private ArrayList<CategoriaDTO> categorias;
    
    public GrupoDeInteresDetailDTO(){
        
       
    }
    
    public  ArrayList<BlogDTO> getBlogs(){
        
        return blogs;
    }
    
    public ArrayList<AdministradorDTO> getAdministradores(){
        
        return administradores;
        
    }
    
    public ArrayList<NoticiaDTO> getNoticias(){
        
        return noticias;
    }
    
    public ArrayList<EventoDTO> getEventos(){
        
        return eventos;
    }
    
    public ArrayList<CategoriaDTO> getCategorias(){
        
        return categorias;
    }
    
    
    
    
    
    public void setBlogs(ArrayList<BlogDTO> pBlogs){
        
        blogs = pBlogs;
    }
    
    public void setAdministradores(ArrayList<AdministradorDTO> pAdministradores){
        
        administradores = pAdministradores;
        
    }
    
    public void setNoticias(ArrayList<NoticiaDTO> pNoticias){
        
       noticias = pNoticias;
    }
    
    public void setEventos(ArrayList<EventoDTO> pEventos){
        
        eventos = pEventos;
    }
    
    public void setCategorias(ArrayList<CategoriaDTO> pCategorias){
        
        categorias = pCategorias;
    }
    
    
    
    
    
   
    
}
