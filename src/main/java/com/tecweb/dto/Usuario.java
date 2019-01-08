package com.tecweb.dto;

/**
 * Clase entidad Usuario con todos los atributos de un usuario que maneja la BD
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class Usuario 
{
	//Declaracion de variables
	private String nombre;
	private String nick;
	private String passwd;
	private String apellido;
	private String mail;
	private String tipo;
	
	//Constructor de la clase
	public Usuario(String nick, String nombre, String apellido,String passwd, String mail, String tipo) 
	{
		this.nombre = nombre;
		this.nick = nick;
		this.passwd = passwd;
		this.apellido = apellido;
		this.mail = mail;
		this.tipo = tipo;
	}
	
	//Metodos getters y setters
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getNick() 
	{
		return nick;
	}
	
	public void setNick(String nick) 
	{
		this.nick = nick;
	}
	
	public String getPasswd() 
	{
		return passwd;
	}
	
	public void setPasswd(String passwd) 
	{
		this.passwd = passwd;
	}
	
	public String getApellido() 
	{
		return apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	
	public String getMail() 
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public String getTipo() 
	{
		return tipo;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}	
}
