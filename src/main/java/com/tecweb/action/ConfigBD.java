package com.tecweb.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.tecweb.dto.Peliculas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Esta clase gestiona la primera conexion y creacion de la BD desde los dos ficheros schema y data residentes en la carpeta webapp del 
 * proyecto.
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class ConfigBD extends Conexion implements SessionAware{
	
	//Declaracion de variables
	private static final long serialVersionUID = 1L;
	private static final String admin = "";
	private Map<String, Object> sesion;
	private Peliculas peli;
	ArrayList<Peliculas> listaPelis = new ArrayList<Peliculas>();
	
	//Metodo que devuelve un string para las anotaciones siguientes de struts. Es llamado desde index.jsp y supone el inicio ya que crea
	//y carga la BD por primera vez con schema y data,tras apoyarse en el metodo getConnection de la clase extendida Conexion
	@Action( value="welcome", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"),
	@Result(name=admin, type="dispatcher", location="/jsp/welcomeadmin.jsp")} )	 	
	public String consulta()
	{
		Statement st=null;
		ResultSet result=null;
		PreparedStatement ps=null;
	
		try 
		{
			getConection();
			
			// Se guarda la ruta de funcionamiento en el equipo
	        String path=Thread.currentThread().getContextClassLoader().getResource("/").getPath();           
    	    String query="";
		    String cadena="";
		    
		    //se abre el schema con la creacion de tablas
		    //FileReader f = new FileReader(path+"/../../jsp/files/schema.txt");
		    FileReader f = new FileReader("/schema.txt");
		    BufferedReader b = new BufferedReader(f);
		    while((cadena = b.readLine())!=null) 
	        {
	    	   query += cadena;
		    }
		    b.close();
		              
		    st = con.createStatement();
		    st.executeUpdate(query);
		           
		    query="";
	        cadena="";
	        
	        //se abre el data con todos los datos de usuarios y peliculas
		    //f = new FileReader(path+"/../../jsp/files/data.txt");
		    f = new FileReader("/data.txt");
		    b = new BufferedReader(f);
		    while((cadena = b.readLine())!=null)
		    {
		    	query += cadena;
		    }
		    b.close();
		           
		    st.executeUpdate(query);
		    
		    //Relaiza esta consulta para mostrar en la vista welcome un enlace directo a cada pelicula de la BD
		    String consulta = ("SELECT * FROM peliculas;");
	           
            ps = con.prepareStatement(consulta);
            result=ps.executeQuery();
          
            while(result.next())
           {     
        	   peli = new Peliculas(result.getInt("id_peli"),result.getString("titulo"),result.getString("genero"),result.getString("director"),result.getString("actor1"),result.getString("actor2"),result.getString("anio"),result.getString("fotopeli"),result.getFloat("valoracion"));
        	   listaPelis.add(peli);
           }
		                    
		} catch (Exception e) 
	    {
			System.err.println("Error"+e);          
	    } finally
	    {   try
	    {
	       	if(con != null) con.close();
	        if(ps != null) ps.close();
	        if(result !=null) result.close();
	                
	    } catch (Exception e) 
	    {
	        System.err.println("Error"+e); 
	    }
	    }			
		return SUCCESS;
	}
	
	//Metodos getters y setters
	public Map<String, Object> getSession()
	{
		return sesion;
	}

	public void setSession(Map<String, Object> sesion) 
	{
	   this.sesion = sesion;
	}

	public Peliculas getPeli() {
		return peli;
	}

	public void setPeli(Peliculas peli) 
	{
		this.peli = peli;
	}
	
    public ArrayList<Peliculas> getListaPelis() 
    {
		return listaPelis;
	}

	public void setListaPeliculas(ArrayList<Peliculas> listaPelis) 
	{
		this.listaPelis = listaPelis;
	}	
			  
}