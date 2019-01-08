package com.tecweb.action;

import java.sql.Connection;
import java.sql.DriverManager;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Esta clase gestiona la conexion a la base de datos HSQLDB residente en memoria con JDBC. Sera extendida por todas las clases que necesiten
 * conectarse a la base de datos
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class Conexion extends ActionSupport {

	//Declaracion de variables
	private static final long serialVersionUID = 1L;
	protected Connection con=null;
	
	//Metodo que realiza la conexion a la BD en memoria
	protected void getConection()
	{
		try 
		{
		  //Se registra el HSQLDB JDBC driver
          Class.forName("org.hsqldb.jdbc.JDBCDriver") ;
          //Se crea la conexion con HSQLDB
          con = DriverManager.getConnection("jdbc:hsqldb:mem:twdb", "sa" , "") ;
          
		} catch (Exception e) 
        {
            System.err.println("Error"+e);          
        }
	}		
}
