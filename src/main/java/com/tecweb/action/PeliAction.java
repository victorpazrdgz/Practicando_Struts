package com.tecweb.action;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.tecweb.dto.Peliculas;
 

/**
 * Clase controladora mediante anotaciones de todas las acciones referentes a la manipulacion de peliculas
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class PeliAction extends Conexion implements SessionAware
{
   //Declaracion de variables
   private static final long serialVersionUID = 1L;
   private static final String login = "";
   String eleccion;
   String parametro;
   String title;
   int id_peli;
   String titulo;
   String genero;
   String director;
   String actor1;
   String actor2;
   String anio;
   Float valoracion;
   String val;
   float value,inf,sup;
   String rol;
   String error;
   String titleok=null;
   ArrayList<Peliculas> listaPelis = new ArrayList<Peliculas>();
   float valorMedia=0;
   String fotopeli;
   private Peliculas peli;
   ArrayList<Peliculas> colecta = new ArrayList<Peliculas>();
   private Map<String, Object> session;
   
   
   //Metodo que se utiliza para direccionar el acceso desde el menu del administrador para borrar pelicula
   @Action( value="borrarPeliAction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/buscaBorrar.jsp") } ) 
   public String borrarPeli() 
   {
	   ActionContext contexto = ActionContext.getContext();
  	  	
       ResultSet result3=null;
       PreparedStatement ps3=null;
       try 
       {
       getConection();   
	   if ((String) contexto.getSession().get("loginId")!=null)
         { 
           String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
           ps3 = con.prepareStatement(consulta3);
           result3=ps3.executeQuery();
           
           while(result3.next())
           {
    	       rol=result3.getString("tipo");
           }
        }        
		 
	} catch (Exception e) 
	{
	    System.err.println("Error"+e); 
	} finally
	{   try
     {
	       if(con != null) con.close();
	       if(ps3 != null) ps3.close();
	       if(result3 !=null) result3.close();
	   } catch (Exception e) 
	   {
	       System.err.println("Error"+e); 
	   }
	}
	   return SUCCESS; 
   }
   
  //Metodo que se utiliza para mostrar la vista del contacto de los desarrolladores
   @Action( value="contactoAction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/contacto.jsp") } ) 
   public String contacto() 
   {
	   return SUCCESS; 
   }
   
   //Metodo que se utiliza para mostrar la vista del formulario para elegir peliculas
   @Action( value="buscarpelisjsp", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/buscarpeliculas.jsp") } ) 
   public String buscarpelisjsp() 
   {
	   ActionContext contexto = ActionContext.getContext();
	    
	   ResultSet result1=null;
	   PreparedStatement ps1=null;
	   try 
	   {
		  getConection();
		  if ((String) contexto.getSession().get("loginId")!=null)
		  {
			  String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
			  ps1 = con.prepareStatement(consulta1);
              result1=ps1.executeQuery();
           
              while(result1.next())
              {
            	  rol=result1.getString("tipo");
              }
		  }
	    } catch (Exception e) 
	    {
           System.err.println("Error"+e);          
        } finally
        {  try
           {
               if(con != null) con.close();
               if(ps1 != null) ps1.close();
               if(result1 !=null) result1.close();
           } catch (Exception e) 
           {
               System.err.println("Error"+e); 
           }
       }
   	   return SUCCESS;
   }
    
   //Metodo que se utiliza para acceder a la vista del menu de administrador
   @Action( value="welcomeaction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp") } ) 
   public String welcomeaction() 
   {
	   ActionContext contexto = ActionContext.getContext();
	   
	   ResultSet result1=null;
	   ResultSet result4=null;
	   PreparedStatement ps1=null;
	   PreparedStatement ps4=null;
	   try 
	   {
		   getConection();
   		   String consulta6 = ("SELECT * FROM peliculas;");
   		   ps4 = con.prepareStatement(consulta6);
           result4=ps4.executeQuery();
       
           while(result4.next())
           {
     	      peli = new Peliculas(result4.getInt("id_peli"),result4.getString("titulo"),result4.getString("genero"),result4.getString("director"),result4.getString("actor1"),result4.getString("actor2"),result4.getString("anio"),result4.getString("fotopeli"),result4.getFloat("valoracion"));
     	      listaPelis.add(peli);
           }
	       if ((String) contexto.getSession().get("loginId")!=null)
	       {
               String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
               ps1 = con.prepareStatement(consulta1);
               result1=ps1.executeQuery();
           
               while(result1.next())
               {
        	       rol=result1.getString("tipo");
               }
            }
	   }    catch (Exception e) 
	       { 
	    	   System.err.println("Error"+e); 
	    	   return SUCCESS;
	       } finally
	       {   try
           	   {
               		if(con != null) con.close();
               		if(ps1 != null) ps1.close();
               		if(result1 !=null) result1.close();
               		if(ps4 != null) ps4.close();
               		if(result4 !=null) result4.close();
               } catch (Exception e) 
	       	   {
            	   System.err.println("Error"+e); 
	       	   }
	       }
   		   return SUCCESS;
   	}
   
   //Metodo para direccionar a la vista del menu del administrador para añadir pelicula
   @Action( value="anadePeliAction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/anadirPelicula.jsp") } ) 
   public String anadePeli() 
   {
	   return SUCCESS; 
   }
   
   //Metodo que mostrara en caso de exito la vista de la ficha de una pelicula
   @Action( value="mostrarFichaAction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/ficha.jsp"), 
   @Result(name=ERROR, type="dispatcher", location="/jsp/login.jsp") } )     
   public String mostrarFicha() 
   {
  	  ActionContext contexto = ActionContext.getContext();
  	  
	  ResultSet result=null;
	  ResultSet result1=null;
	  PreparedStatement ps=null;
	  PreparedStatement ps1=null;
	
	  try 
	  {
		  getConection();
	           
	      String consulta = ("SELECT * FROM peliculas WHERE titulo= '"+ getTitle()+"';");
	      ps = con.prepareStatement(consulta);
	      result=ps.executeQuery();
	          
	      while(result.next())
	      {
	    	   titulo= result.getString("titulo");
		       genero=result.getString("genero");
		       director=result.getString("director");
		       actor1=result.getString("actor1");
		       actor2=result.getString("actor2");
		       anio=result.getString("anio");
		       valoracion=result.getFloat("valoracion");
		       fotopeli=result.getString("fotopeli");
	      }
	           
	      if ((String) contexto.getSession().get("loginId")!=null)
	      {
	    	  String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
		      ps1 = con.prepareStatement(consulta1);
		      result1=ps1.executeQuery();
		           
		      while(result1.next())
		      {
		    	  rol=result1.getString("tipo");
		      }
	      }
	  } catch (Exception e) 
	  {
		  System.err.println("Error"+e);          
	  } finally
	  {   try
	  	  {
            if(con != null) con.close();
            if(ps1 != null) ps1.close();
            if(result1 !=null) result1.close();
            if(ps != null) ps.close();
            if(result !=null) result.close();
	  	  } catch (Exception e) 
	      {
	  		  System.err.println("Error"+e); 
	      }
	  }
	  return SUCCESS;
   }
   
   //Metodo que mostrara la vista del listado de peliculas con el filtrado realizado
   @Action( value="busquedaAction", results= {
   @Result(name=SUCCESS, type="dispatcher", location="/jsp/busqueda.jsp"), 
   @Result(name=ERROR, type="dispatcher", location="/jsp/welcome.jsp") } )     
   public String busquedaPeli()
   {
	   ActionContext contexto = ActionContext.getContext();
	   ResultSet result=null;
	   PreparedStatement ps1=null;
	   ResultSet result3=null;
       PreparedStatement ps3=null;  
	   if(getEleccion().equals("val"))
	   {
		  value=Float.parseFloat(getParametro());
		  
		   inf= value - (float)0.5;
		   sup= value + (float)0.5;
		   if(inf<=0) inf=(float) 0;
		   if(sup>=5) sup=(float) 5;
	    }
	   	if(!(getEleccion().equals(val)) || ( value>0 && value<5))
	   	{  
			try 
			{
				getConection();
		           
		        String consulta;
		        
		        //Se condiciona el tipo de eleccion de atributo de la pelicula para realizar la busqueda en la BD en su medida,obviando mayusculas
		        if(getEleccion().equals("val"))
                {
                   consulta = ("SELECT * FROM peliculas WHERE valoracion BETWEEN "+inf+" AND "+sup+" ORDER BY valoracion DESC;");
                } else if(getEleccion().equals("actorX"))
                {
                   consulta = ("SELECT * FROM peliculas WHERE UPPER (actor1) LIKE UPPER('%"+getParametro()+"%') OR UPPER (actor2) LIKE UPPER('%"+getParametro()+"%');");
                } else 
                {
                   consulta = ("SELECT * FROM peliculas WHERE UPPER ("+getEleccion()+") LIKE UPPER('%"+getParametro()+"%');");
                }
		           
		        ps1 = con.prepareStatement(consulta);
		        result=ps1.executeQuery();
		          	           
		        while(result.next())
		        {
		        	peli = new Peliculas(result.getInt("id_peli"),result.getString("titulo"),result.getString("genero"),result.getString("director"),result.getString("actor1"),result.getString("actor2"),result.getString("anio"),result.getString("fotopeli"),result.getFloat("valoracion"));
		        	colecta.add(peli);
           		}
		        if ((String) contexto.getSession().get("loginId")!=null)
		         {
	            	 
		             String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
		             ps3 = con.prepareStatement(consulta3);             
		             result3=ps3.executeQuery();
		           
		             while(result3.next())
		             {
		        	     rol=result3.getString("tipo");
		             }
		         }   
		           
		  } catch (Exception e) 
	      {
	          System.err.println("Error"+e);          
	      } finally
	      {   try
	          {
	              if(con != null) con.close();
	              if(ps1 != null) ps1.close();
	              if(result !=null) result.close();
	              
	          } catch (Exception e) 
	          {
	              System.err.println("Error"+e); 
	          }
	       }
		   return SUCCESS;
	   	}
		else
		   return ERROR;
	}
		  
    //Metodo que invoca la vista de ver la pelicula escogida
	@Action( value="verPeliAction", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/pelicula.jsp"), 
	@Result(name=login, type="dispatcher", location="/jsp/login.jsp"),
	@Result(name=ERROR, type="dispatcher", location="/jsp/ficha.jsp") } )     
	public String verPeli() 
	{
		ActionContext contexto = ActionContext.getContext();
		
		if ((String) contexto.getSession().get("loginId")!= null) 
		{	
			ResultSet result=null;
			ResultSet result1=null;
			PreparedStatement ps=null;
			PreparedStatement ps1=null;
			
		 try 
		 {
		     getConection();
			 
		     String consulta = ("SELECT * FROM peliculas WHERE titulo= '"+ getTitle()+"';");
			 ps = con.prepareStatement(consulta);
			 result=ps.executeQuery();
			          
			 while(result.next())
			 {
			  	   titulo= result.getString("titulo");
				   valoracion=result.getFloat("valoracion");
				   fotopeli=result.getString("fotopeli");
		     }
			 if ((String) contexto.getSession().get("loginId")!=null)
			 {
			     String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
			     ps1 = con.prepareStatement(consulta1);
				 result1=ps1.executeQuery();
				           
				 while(result1.next())
				 {
					 rol=result1.getString("tipo");
				 }
			  }
			          
		   } catch (Exception e) 
		   {
		        System.err.println("Error"+e);          
		   } finally
		   {   try
		       {
		            if(con != null) con.close();
		            if(ps1 != null) ps1.close();
		            if(result !=null) result.close();
		            if(ps != null) ps.close();
		            if(result1 !=null) result1.close();
		        } catch (Exception e) 
		        {
		            System.err.println("Error"+e); 
		        }
		    }
			return SUCCESS;
		}
		else
		return login;
	 }
	
	//Metodo que realiza la vista de la videoteca con las peliculas pendientes de ver del usuario
	@Action( value="verVideotecaAction", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/busquedavideo.jsp"), 
	@Result(name=login, type="dispatcher", location="/jsp/login.jsp"),
	@Result(name=ERROR, type="dispatcher", location="/jsp/ficha.jsp") } )     
	public String verVideoteca() 
	{
		ActionContext contexto = ActionContext.getContext();
		
		if ((String) contexto.getSession().get("loginId")!= null) 
		{
		    ResultSet result=null;
		    ResultSet result1=null;
		    PreparedStatement ps=null;
		    PreparedStatement ps1=null;
		    ResultSet result3=null;
	        PreparedStatement ps3=null;
		     
		    try 
		    {
		    	 getConection();
			           
		    	 String consulta = ("SELECT * FROM pendientes WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
			     ps = con.prepareStatement(consulta);
			     result=ps.executeQuery();
			          
			     while(result.next())
			     {  
			    	 String consulta1 = ("SELECT * FROM peliculas WHERE titulo= '"+ result.getString("titulo")+"';");
			    	 ps1 = con.prepareStatement(consulta1);
			    	 result1 =ps1.executeQuery();
			    	 while(result1.next())
			    	 {
		    		 	peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
		    		 	colecta.add(peli);}
			     	 }
			     String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
                 ps3 = con.prepareStatement(consulta3);
                 result3=ps3.executeQuery();
             
                 while(result3.next())
                 {
          	       rol=result3.getString("tipo");
                 }
			            
			 } catch (Exception e) 
		     {
		        System.err.println("Error"+e);          
		     } finally
		     {   try
		         {
		            if(con != null) con.close();
		            if(ps1 != null) ps1.close();
		            if(ps != null) ps.close();
		            if(result !=null) result.close();
		            if(result1 !=null) result1.close();
		         } catch (Exception e) 
	        	 {
		            System.err.println("Error"+e); 
	        	 }
		     }
			 return SUCCESS;
		}
		else
			return login;
	}
	
	//Metodo que recoge la valoracion de una pelicula y la trata para devolver al usuario a su tipo de menu
	@Action( value="valorarPeliAction", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
	@Result(name=login, type="dispatcher", location="/jsp/login.jsp"),
	@Result(name=ERROR, type="dispatcher", location="/jsp/pelicula.jsp") } )     
	public String valorarPeli() 
	{
		 ActionContext contexto = ActionContext.getContext();
		 
		 if ((String) contexto.getSession().get("loginId")!= null) 
		 {
			 ResultSet result=null;
			 PreparedStatement ps=null;
			 PreparedStatement ps1=null;
			 ResultSet result2=null;
			 PreparedStatement ps2=null;
			 ResultSet result3=null;
			 PreparedStatement ps3=null;
			 ResultSet result4=null;
			 PreparedStatement ps4=null;
			 ResultSet result5=null;
			 PreparedStatement ps5=null;
			 ResultSet result0=null;
			 PreparedStatement ps0=null;
			 ResultSet result10=null;
			 PreparedStatement ps10=null;
			
		     try 
		     {
		    	 getConection();
		    	
			     float valor=0;
			     int count=0;
			     
			     //Si la pelicula estaba en videoteca o lista de pendientes de ver se elimina
			     String consulta0 = "SELECT * FROM pendientes WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';";
			     ps0 = con.prepareStatement(consulta0);
			     result0=ps0.executeQuery();
			     boolean existe2=false;
			     while(result0.next())
			     {
			    	 if(result0.getString("titulo").equals(getTitulo()))
			    	 {
			    		 existe2=true;
			    	 }
			     }
			     if(existe2)
			     {
			    	 String consulta10 = "DELETE FROM pendientes WHERE titulo= '"+getTitulo()+"';";
				     ps10 = con.prepareStatement(consulta10);
				     ps10.executeUpdate();
			     }
			     
			     //Consulta para lograr las valoraciones actuales de la pelicula
			     String consulta = "SELECT * FROM valoraciones WHERE titulo= '"+ getTitulo()+"';";
			     String consulta2;
			     ps = con.prepareStatement(consulta);
			     result=ps.executeQuery();
			     boolean existe=false;
			     while(result.next())
			     {
			    	 if(result.getString("nick").equals((String) contexto.getSession().get("loginId")))
			    	 {
			    		 existe=true;
			    	 }
			     }
			    
			     //Se condiciona si el usuario ya ha valorado esta pelicula
			     if(existe)  
			     {
			    	 //En caso de haberla valorado se actualiza su valoracion
			    	 consulta2 = "UPDATE valoraciones SET valoracion= '"+ getValoracion()+"' WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"' AND titulo= '"+ getTitulo()+"';";
			    	 ps1 = con.prepareStatement(consulta2);
			    	 ps1.executeUpdate();
			     } else 
			     {
			    	 //En caso de no haberla valorado se crea una valoracion nueva en su tabla
			    	 consulta2 = "INSERT INTO valoraciones(titulo,nick,valoracion) values (?,?,?)";
			    	 ps1 = con.prepareStatement(consulta2);
			    	 ps1.setString(1, getTitulo());
			    	 ps1.setString(2, (String) contexto.getSession().get("loginId"));
			    	 ps1.setFloat(3, getValoracion()); 
			    	 ps1.executeUpdate();
			     }
			        	   
			     String consulta3 = "SELECT * FROM valoraciones WHERE titulo= '"+ getTitulo()+"';";
			     ps2 = con.prepareStatement(consulta3);
			     result2=ps.executeQuery();
			 
			     while(result2.next())
			     {
			    	 valor += result2.getFloat("valoracion");
			    	 count++;
			     }
			     valorMedia = valor/count;
			     
			     //Con las valoraciones actuales se logra la media y se actualiza su valoracion en la tabla de las peliculas
			     String consulta4 = "UPDATE peliculas SET valoracion= '"+ getValorMedia()+"' WHERE titulo= '"+ getTitulo()+"';";
			     ps3 = con.prepareStatement(consulta4);
			     ps3.executeUpdate();
			 
			     String consulta6 = ("SELECT * FROM peliculas;");
			     ps4 = con.prepareStatement(consulta6);
			     result4=ps4.executeQuery();
     		          
			     while(result4.next())
			     {
			    	 peli = new Peliculas(result4.getInt("id_peli"),result4.getString("titulo"),result4.getString("genero"),result4.getString("director"),result4.getString("actor1"),result4.getString("actor2"),result4.getString("anio"),result4.getString("fotopeli"),result4.getFloat("valoracion"));
			    	 listaPelis.add(peli);
			     }
     		           
			     String consulta5 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
			     ps5 = con.prepareStatement(consulta5);
			     result5=ps5.executeQuery();
			           
			     while(result5.next())
			     {
			    	 rol=result5.getString("tipo");
			     }
			    
			          
	    	 	 } catch (Exception e) 
	    	 	 {
	    	 		 System.err.println("Error"+e);          
	    	 	 } finally
	    	 	 {   try
	    	 	 {
	    	 		 if(con != null) con.close();
	    	 		 if(ps1 != null) ps.close();
	    	 		 if(result !=null) result.close();
	    	 		 if(ps10 != null) ps10.close();
	    	 		 if(result10 !=null) result10.close();
	    	 		 if(ps0 != null) ps0.close();
	    	 		 if(result0 !=null) result0.close();
	    	 		 if(ps2 != null) ps2.close();
	    	 		 if(result2 !=null) result2.close();
	    	 		 if(ps3 != null) ps3.close();
	    	 		 if(result3 !=null) result3.close();
	    	 		 if(ps4 != null) ps4.close();
	    	 		 if(result4 !=null) result4.close();
	    	 		 if(ps5 != null) ps5.close();
	    	 		 if(result5 !=null) result5.close();
		        } catch (Exception e) 
		        {
		            System.err.println("Error"+e); 
		        }
		    }
			
		    	return SUCCESS;
		 }
		 	else
		 		return login;
	 }

    //Metodo que recoge los datos obtenidos y mostrara la vista del listado filtrado de peliculas a borrar
	@Action( value="busquedaBorrarPeliculaAction", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/borrarPeli.jsp") } )     
	public String BusquedaPeliBorrar() 
	{
		ResultSet result=null;
		PreparedStatement ps=null;
		
		try 
		{
			getConection();
			           
			String consulta;
			
			
			if(getEleccion().equals("actorX"))
			{				
				consulta = ("SELECT * FROM peliculas WHERE UPPER (actor1) LIKE UPPER('%"+getParametro()+"%') OR UPPER (actor2) LIKE UPPER('%"+getParametro()+"%');");
			} else 
			{
				 consulta = ("SELECT * FROM peliculas WHERE UPPER ("+getEleccion()+") LIKE UPPER('%"+getParametro()+"%');");
			}
			           
			ps = con.prepareStatement(consulta);
			result=ps.executeQuery();
			          	           
			while(result.next())
			{
				peli = new Peliculas(result.getInt("id_peli"),result.getString("titulo"),result.getString("genero"),result.getString("director"),result.getString("actor1"),result.getString("actor2"),result.getString("anio"),result.getString("fotopeli"),result.getFloat("valoracion"));
				colecta.add(peli);
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
 
	//Metodo que añade una pelicula nueva a la BD por parte del administrador
	@Action( value="anadirPeliculaAction", results= {
	@Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"),
	@Result(name=ERROR, type="dispatcher", location="/jsp/error.jsp")} )     
	public String anadirPelicula() 
	{
		ActionContext contexto = ActionContext.getContext();
  	  	ResultSet result=null;
  	  	ResultSet result1=null;
  	  	PreparedStatement ps=null;
  	  	PreparedStatement ps1=null;
  	    ResultSet result3=null;
        PreparedStatement ps3=null;
        ResultSet result4=null;
        PreparedStatement ps4=null;
  	  	setValoracion((float)0.0);

  	  	setFotopeli("jsp/images/negro.jpg");
			
  	  	try 
  	  	{
  	  		getConection();
  	  	   
  		   if ((String) contexto.getSession().get("loginId")!=null)
	         {
              String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
              ps3 = con.prepareStatement(consulta3);
              result3=ps3.executeQuery();
          
              while(result3.next())
              {
       	       rol=result3.getString("tipo");
              }
                
	         }
  		   String consulta4 =("SELECT * FROM peliculas WHERE UPPER (titulo) LIKE UPPER( '"+getTitulo()+"');");
           ps4 = con.prepareStatement(consulta4);
           result4=ps4.executeQuery();
  
           while(result4.next())
           {
	           titleok=result4.getString("titulo");
	          
           }
        
           if ((getTitulo()!=" ") && (titleok==null) )
           {

        	   String consulta = ("INSERT INTO peliculas (titulo,genero,director,actor1,actor2,anio,fotopeli,valoracion) values (?,?,?,?,?,?,?,?);");
			           
  	  		ps = con.prepareStatement(consulta);
  	  		ps.setString(1, getTitulo());
  	  		ps.setString(2, getGenero());
  	  		ps.setString(3, getDirector());
  	  		ps.setString(4, getActor1());
  	  		ps.setString(5, getActor2());
  	  		ps.setString(6, getAnio());
  	  		ps.setString(7, getFotopeli());
  	  		ps.setFloat(8, (float) getValoracion());
  	  		ps.executeUpdate() ;
			              	  	  
           }   else
           {
        	  // 	JOptionPane.showMessageDialog(null, "La pelicula que ha introducido ya existe");
        	   error="PELI";
        	   	return ERROR;}
           	String consulta1 = ("SELECT * FROM peliculas;");
           	ps1 = con.prepareStatement(consulta1);
           	result1=ps1.executeQuery();
           	while(result1.next()){
           		peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
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
		        if(ps1 != null) ps1.close();
		        if(result !=null) result.close();
		        if(result1 !=null) result1.close();
		            
        	} catch (Exception e) 
	    	{
	            System.err.println("Error"+e); 
	    	}
		}
		return SUCCESS;
	}

	//Metodo que borra de la BD la pelicula escogida por el administrador y mostrara la vista de su menu de nuevo
	 @Action( value="borrarPeliculaAction", results= {
	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
	 @Result(name=ERROR, type="dispatcher", location="/jsp/borrarPeli.jsp") } )     
	 public String borrarPelicula() 
	 {
		 ActionContext contexto = ActionContext.getContext();
		 ResultSet result=null;
		 ResultSet result1=null;
		 PreparedStatement ps=null;
		 PreparedStatement ps1=null;
		 ResultSet result3=null;
	     PreparedStatement ps3=null;	
		 try 
		 {
  	 		getConection();
  	 	// int confirmado = JOptionPane.showConfirmDialog(null,"¿Seguro que desea borrar la pelicula "+ getTitle()+"?");

 		    //if (JOptionPane.OK_OPTION == confirmado){
  	 		String consulta = ("DELETE FROM peliculas WHERE titulo= '"+ getTitle()+"';");
			ps = con.prepareStatement(consulta);
			ps.executeUpdate();
 		    //}        
            String consulta1 = ("SELECT * FROM peliculas;");
   			ps1 = con.prepareStatement(consulta1);
   			result1=ps1.executeQuery();
      		          
      		while(result1.next())
      		{
      			peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
      			listaPelis.add(peli);
      		}
      		if ((String) contexto.getSession().get("loginId")!=null)
  	         {
                 String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
                 ps3 = con.prepareStatement(consulta3);
                 result3=ps3.executeQuery();
             
                 while(result3.next())
                 {
          	       rol=result3.getString("tipo");
                 }
              }            
		    } catch (Exception e) 
		    {
		        System.err.println("Error"+e);          
		    } finally
		    {   try
		        {
		            if(con != null) con.close();
		            if(ps != null) ps.close();
			        if(ps1 != null) ps1.close();
			        if(result !=null) result.close();
			        if(result1 !=null) result1.close();
		            
		        } catch (Exception e) 
		        {
		            System.err.println("Error"+e); 
		        }
		    }
			return SUCCESS;
	}
	 
	//Metodo que inserta en la tabla de peliculas pendientes o videoteca la pelicula escogida 
	 @Action( value="videotecaAction", results= {
 	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
 	 @Result(name=login, type="dispatcher", location="/jsp/login.jsp"),
 	 @Result(name=ERROR, type="dispatcher", location="/jsp/ficha.jsp") } )     
	 public String anadirPeli() 
	 {
		 ActionContext contexto = ActionContext.getContext();
		 		 
		 if ((String) contexto.getSession().get("loginId")!= null) 
		 {
			 ResultSet result0=null;
			 ResultSet result=null;
			 ResultSet result1=null;
			 ResultSet result2=null;
			 PreparedStatement ps=null;
			 PreparedStatement ps0=null;
			 PreparedStatement ps1=null;
			 PreparedStatement ps2=null;
			 PreparedStatement ps3=null;
			 	 
		     try 
		     {
		    	 getConection();
		    	 
		    	 boolean existe=false;
		    	 String consulta0 =("SELECT * FROM pendientes WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
		      	 ps0 = con.prepareStatement(consulta0);
				 result0=ps0.executeQuery();
				 while(result0.next())
	             {
	            	 if(result0.getString("titulo").equals(getTitle()))
					 {
						 existe=true;
					 }
				 }
				 if(!existe)
				 {
		    	 String consulta = ("INSERT INTO pendientes (nick, titulo) values (?,?);");         
				 ps = con.prepareStatement(consulta);
				 ps.setString(1, (String) contexto.getSession().get("loginId"));
				 ps.setString(2, getTitle());
				 ps.executeUpdate();
				 }
				 ps1 = con.prepareStatement("SELECT * FROM pendientes  ");
	    		 result=ps1.executeQuery();
	    		 
	             String consulta1 = ("SELECT * FROM peliculas;");
		      	 ps2 = con.prepareStatement(consulta1);
		      	 result1=ps2.executeQuery();
		      		          
		      	 while(result1.next())
		      	 {
		      		 peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
		      		 listaPelis.add(peli);
		      	 }
		      		           
		      	 String consulta2 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
		      	 ps3 = con.prepareStatement(consulta2);
				 result2=ps3.executeQuery();
					           
				  while(result2.next())
				  {
					  rol=result2.getString("tipo");
				  }
					   
			    } catch (Exception e) 
			    {
			        System.err.println("Error"+e);          
			    } finally
			    {   try
			        {
			            if(con != null) con.close();
			            if(ps != null) ps.close();
			            if(ps0 != null) ps0.close();
			            if(ps1 != null) ps1.close();
			            if(ps2 != null) ps2.close();
			            if(ps3 != null) ps3.close();
			            if(result !=null) result.close();
			            if(result1 !=null) result1.close();
			            if(result2 !=null) result2.close();
			            if(result0 !=null) result0.close();
			        } catch (Exception e) 
			        {
			            System.err.println("Error"+e); 
			        }
			    }
			    return SUCCESS;
		 }
		 else
			 return login;
	 }
	
	//Metodo que se utiliza para borrar peliculas de la videoteca o tabla de peliculas pendientes de ver
	 @Action( value="borrarvideoteca", results= {
  	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
 	 @Result(name=login, type="dispatcher", location="/jsp/login.jsp"),
 	 @Result(name=ERROR, type="dispatcher", location="/jsp/ficha.jsp") } )     
	 public String borrarvideoteca() 
	 {
		 ActionContext contexto = ActionContext.getContext();
		 
		 if ((String) contexto.getSession().get("loginId")!= null) 
		 {
	 	 ResultSet result=null;
	 	 ResultSet result1=null;
	 	 PreparedStatement ps=null;
	 	 PreparedStatement ps1=null;
	 	 ResultSet result8=null;
	 	 PreparedStatement ps8=null;
				
	 	 try 
	 	 {
	 		 getConection();
	 		 
	 		 String consulta = ("DELETE FROM pendientes WHERE titulo= '"+ getTitle()+"'AND nick= '"+ (String) contexto.getSession().get("loginId")+"';");
			 ps = con.prepareStatement(consulta);
			 ps.executeUpdate();
        	           
        	 String consulta1 = ("SELECT * FROM peliculas;");
	   		 ps1 = con.prepareStatement(consulta1);
	   		 result1=ps1.executeQuery();
	      		          
	      	 while(result1.next())
	      	 {
	      		 peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
	      		 listaPelis.add(peli);
	      	 }
	      	
	      	 String consulta8 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
	      	 ps8 = con.prepareStatement(consulta8);
	      	 result8=ps8.executeQuery();
	      	           
	      	 while(result8.next())
	      	 {
	      		 rol=result8.getString("tipo");
	      	 }
				            
    	 } catch (Exception e) 
	 	 {
    		 System.err.println("Error"+e);          
	 	 } finally
	 	 {   try
	 	 	 {
	 		 if(con != null) con.close();
			 if(ps1 != null) ps1.close();
			 if(result1 !=null) result1.close();
			 if(ps != null) ps.close();
			 if(result !=null) result.close();
			 if(ps8 != null) ps8.close();
			 if(result8 !=null) result8.close();
			 } catch (Exception e) 
	 	 	 {
				 System.err.println("Error"+e); 
	 	 	 }
	 	 }
					
	 	 	return SUCCESS;
		 }
		 else {
			 return login;
		 }
	 }
	 
	//Metodo que se activa desde el menu para acceder a la vista que carga la memoria del proyecto en pdf
	 @Action( value="memoria", results= {
 	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/memoria.jsp") } )     
	 public String memoria() 
	 {
		 ActionContext contexto = ActionContext.getContext();
		 
		 ResultSet result1=null;
		 PreparedStatement ps1=null;
		 		 
		 try 
		 {
			 getConection();
				           
             if ((String) contexto.getSession().get("loginId")!=null)
             {
            	 String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
            	 ps1 = con.prepareStatement(consulta1);
            	 result1=ps1.executeQuery();
					           
            	 while(result1.next())
            	 {
            		 rol=result1.getString("tipo");
            	 }
				           
             } 
	      } catch (Exception e) 
		  {
	    	  System.err.println("Error"+e);          
		  } finally
		  {   try
	  		  {
			  		if(con != null) con.close();
			        if(ps1 != null) ps1.close();
			        if(result1 !=null) result1.close();
	          } catch (Exception e) 
	  		  {
	        	  System.err.println("Error"+e); 
	  		  }
    	   }
	 	   return SUCCESS;
	}	
		
	//Metodo que se activa desde el menu para acceder a la vista que carga la ayuda del proyecto en pdf
		 @Action( value="ayuda", results= {
	 	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/faq.jsp") } )     
		 public String ayuda() 
		 {
			 ActionContext contexto = ActionContext.getContext();
			 
			 ResultSet result1=null;
			 PreparedStatement ps1=null;
			 		 
			 try 
			 {
				 getConection();
					           
	             if ((String) contexto.getSession().get("loginId")!=null)
	             {
	            	 String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
	            	 ps1 = con.prepareStatement(consulta1);
	            	 result1=ps1.executeQuery();
						           
	            	 while(result1.next())
	            	 {
	            		 rol=result1.getString("tipo");
	            	 }
					           
	             } 
		      } catch (Exception e) 
			  {
		    	  System.err.println("Error"+e);          
			  } finally
			  {   try
		  		  {
				  		if(con != null) con.close();
				        if(ps1 != null) ps1.close();
				        if(result1 !=null) result1.close();
		          } catch (Exception e) 
		  		  {
		        	  System.err.println("Error"+e); 
		  		  }
	    	   }
		 	   return SUCCESS;
		}		 
		 //Metodo que utiliza el administrador para modificar los datos de las peliculas
	     @Action( value="editPeli", results= {
	     @Result(name=SUCCESS, type="dispatcher", location="/jsp/editpeli.jsp") } ) 
	     public String editPeli() 
	     {
	  	   ActionContext contexto = ActionContext.getContext();
	  	   
	  	   ResultSet result1=null;
	  	
	  	   PreparedStatement ps1=null;
	  	   try 
	  	   {
	  		   getConection();
	     		  
	  	       if ((String) contexto.getSession().get("loginId")!=null)
	  	       {
	                 String consulta1 =("SELECT * FROM peliculas WHERE titulo= '"+ getTitle()+"';");
	                 ps1 = con.prepareStatement(consulta1);
	                 result1=ps1.executeQuery();
	             
	                 while(result1.next())
	                 {
	                   titulo=result1.getString("titulo");
	                   genero= result1.getString("genero");
	                   actor1=result1.getString("actor1");
	                   actor2= result1.getString("actor2");
	                   director=result1.getString("director");
	                   anio=result1.getString("anio");
	                 }
	              }
	  	   }    catch (Exception e) 
	  	       { 
	  	    	   System.err.println("Error"+e); 
	  	    	   return SUCCESS;
	  	       } finally
	  	       {   try
	             	   {
	                 		if(con != null) con.close();
	                 		if(ps1 != null) ps1.close();
	                 		if(result1 !=null) result1.close();
	                 		
	                 } catch (Exception e) 
	  	       	   {
	              	   System.err.println("Error"+e); 
	  	       	   }
	  	       }
	     		   return SUCCESS;
	     	}
	     
	   //Metodo para modificar pelicuas en la BD
	     @Action( value="modificaPeli", results= {
	     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp") } )     	    
	     public String modificaPeli() 
	     {	    	 
	    	 
	    	 ActionContext contexto = ActionContext.getContext();
	    	 
	    	 ResultSet result1=null;
	    	 ResultSet result3=null;
	    	 PreparedStatement ps=null;
	    	 PreparedStatement ps2=null;
	    	 PreparedStatement ps3=null;
	    	 try 
	    	 {	  
	    		 getConection();
	    		 
	    		 String consulta2 = ("SELECT * FROM peliculas;");
	   		     ps2 = con.prepareStatement(consulta2);
	   		     result1=ps2.executeQuery();
	   		          
	   		     while(result1.next())
	   		     {
	   		        peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
	   		        listaPelis.add(peli);
	   		     }
	   		     if ((String) contexto.getSession().get("loginId")!=null)
		         {
	                 String consulta3 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
	                 ps3 = con.prepareStatement(consulta3);
	                 result3=ps3.executeQuery();
	          
	                while(result3.next()) {
	       	            rol=result3.getString("tipo");
	                }
	             }  
	   		     
	            	 String consulta = ("UPDATE peliculas SET genero='"+ getGenero()+"',director='"+ getDirector()+"',actor1='"+ getActor1()+"',actor2='"+ getActor2()+"',anio='"+ getAnio()+"' WHERE titulo='"+ getTitulo()+"';");
	            	 ps = con.prepareStatement(consulta);
	    		            
	            	 ps.executeUpdate();	  
	    		 
	    	} catch (Exception e) 
	    	{
	    	    System.err.println("Error"+e); 
	    	    return ERROR;
	    	} finally
	    	{   try
	           {
	    	       if(con != null) con.close();
	    	       if(ps != null) ps.close();
	    	       if(ps2 != null) ps2.close();
	    	       if(result1 !=null) result1.close();
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
		return session;
	}

	public void setSession(Map<String, Object> session) 
	{
		this.session = session;
	}		
		
	public Peliculas getPeliculas() 
	{
	   return peli;
	}

	public void setPeliculas(Peliculas peli) 
	{
	   this.peli = peli;
	}
	  
	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
		
	public String getEleccion()
	{
		return eleccion;
	}

	public void setEleccion(String eleccion) 
	{
		this.eleccion = eleccion;
	}

	public String getParametro() 
	{
		return parametro;
	}

	public void setParametro(String parametro) 
	{
		this.parametro = parametro;
	}
		
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

	public ArrayList<Peliculas> getColecta() 
	{
		return colecta;
	}

	public void setColecta(ArrayList<Peliculas> colecta) 
	{
		this.colecta = colecta;
	}		
	
	public float getValorMedia() 
	{
		return valorMedia;
	}

	public void setValorMedia(float valorMedia) 
	{
		this.valorMedia = valorMedia;
	}
			
	public String getVal() 
	{
		return val;
	}

	public void setVal(String val) 
	{
		this.val = val;
	}

	public Peliculas getPeli() 
	{
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
	
	public void setoRol(String rol) 
	{
		this.rol = rol;
	}
	
	public String getRol() 
	{
		return rol;
	}
	public void setoError(String error) 
	{
		this.error = error;
	}
	
	public String getError() 
	{
		return error;
	}
 
}