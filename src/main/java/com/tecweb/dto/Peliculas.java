package com.tecweb.dto;

/**
 * Clase entidad Peliculas con todos los atributos de una pelicula que maneja la BD
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class Peliculas 
{
	//Declaracion de variables
	int id_peli;
    String titulo;
    String genero;
    String director;
    String actor1;
    String actor2;
    String anio;
    Float valoracion;
    String fotopeli;
    
    //Constructor vacio
    public Peliculas()
    {
    	this.id_peli=0;
    	this.titulo="";
    	this.genero="";
    	this.director="";
    	this.actor1="";
    	this.actor2="";
    	this.anio="";
    	this.fotopeli="";
    	this.valoracion=(float) 0;
    }
    
    //Constructor parametrizado
    public Peliculas(int id_peli,String titulo,String genero,String director,String actor1,String actor2,String anio,String fotopeli,Float valoracion)
    {
        this.id_peli=id_peli;
        this.titulo=titulo;
        this.genero=genero;
        this.director=director;
        this.actor1=actor1;
        this.actor2=actor2;
        this.anio=anio;
        this.fotopeli=fotopeli;
        this.valoracion=valoracion;
    }
    
    //Metodos getters y setters
    public int getId_peli() 
    {
		return id_peli;
	}

	public void setId_peli(int id_peli) 
	{
		this.id_peli = id_peli;
	}

	public String getTitulo() 
	{
		return titulo;
	}

	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}

	public String getGenero() 
	{
		return genero;
	}

	public void setGenero(String genero) 
	{
		this.genero = genero;
	}

	public String getDirector() 
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public String getActor1() 
	{
		return actor1;
	}

	public void setActor1(String actor1) 
	{
		this.actor1 = actor1;
	}

	public String getActor2() 
	{
		return actor2;
	}

	public void setActor2(String actor2) 
	{
		this.actor2 = actor2;
	}

	public String getAnio() 
	{
		return anio;
	}

	public void setAnio(String anio) 
	{
		this.anio = anio;
	}
	
	public String getFotopeli() 
	{
		return fotopeli;
	}

	public void setFotopeli(String fotopeli) 
	{
		this.fotopeli =fotopeli;
	}

	public Float getValoracion() 
	{
		return valoracion;
	}

	public void setValoracion(Float valoracion) 
	{
		this.valoracion = valoracion;
	}
}
