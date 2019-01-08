package com.tecweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import com.tecweb.dto.Peliculas;

public class pelisDao {
	private Connection connection=null;
	ArrayList<Peliculas> listaPelis = new ArrayList<Peliculas>();
	private Peliculas peli;
	private void getConnectionDao(){
		if (connection==null){
		try {
		       Context initialcontext = new InitialContext();
		       DataSource ds = (DataSource) initialcontext.lookup("java:/comp/env/jdbc/mis-conexiones");
		       connection=ds.getConnection();
		} catch (NamingException e) {
		        e.printStackTrace();
		}
		 catch (SQLException e) {
			 e.printStackTrace();
		}

		
	}
  }

	public void consultapelis(){
		ResultSet result4=null;
		PreparedStatement ps4=null;
		try{ getConnectionDao();
		     String consulta6 = ("SELECT * FROM peliculas;");
		     ps4 = connection.prepareStatement(consulta6);
             result4=ps4.executeQuery();
    
            while(result4.next())
        {
  	      peli = new Peliculas(result4.getInt("id_peli"),result4.getString("titulo"),result4.getString("genero"),result4.getString("director"),result4.getString("actor1"),result4.getString("actor2"),result4.getString("anio"),result4.getString("fotopeli"),result4.getFloat("valoracion"));
  	      listaPelis.add(peli);
        }
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		
		
	}


}
  