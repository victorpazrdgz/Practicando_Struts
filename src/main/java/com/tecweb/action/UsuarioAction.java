package com.tecweb.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

//import javax.swing.JOptionPane;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.tecweb.dto.Peliculas;
import com.tecweb.dto.Usuario;

/**
 * Clase controladora mediante anotaciones de todas las acciones referentes a la manipulacion de usuarios y administradores
 * 
 * @author Victor Paz Rodriguez
 * @author Luis Miguel Garcia Santurtun 
 * @version 1.0
 */

public class UsuarioAction extends Conexion implements SessionAware{

	//Declaracion de variables
	private static final long serialVersionUID = 1L;
	String nombre;
	String nick;
	String passwd;
	String apellido;
	String mail;
	String tipo;
    String username;
    String password;
    String rol;
    String error;
    String nameuser;
	private Peliculas peli;
    ArrayList<Peliculas> listaPelis = new ArrayList<Peliculas>();
    private Map<String, Object> session;
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private Usuario user;
     
     //Metodo puente para enlazar jsp. Redirecciona a login.jsp
     @Action( value="loginAction", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/login.jsp"), 
     @Result(name=ERROR, type="dispatcher", location="/jsp/login.jsp") } ) 
     public String login() 
     {
    	 return SUCCESS; 
     }
     
     //Metodo para cumplimentar el registro. Si exito;se direcciona al registro.jsp donde se visualiza el formulario.  
     @Action( value="registroAction", results= {
 	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/registro.jsp"), 
 	 @Result(name=ERROR, type="dispatcher", location="/jsp/welcome.jsp") } ) 
     public String registro() 
     {	 	
    	 ActionContext contexto = ActionContext.getContext();
   	  	
    	 ResultSet result=null;
    	 ResultSet result1=null;
    	 PreparedStatement ps=null;
    	 PreparedStatement ps1=null;
	 
    	 try 
    	 {
    		 getConection();
	         //Consulta para seleccionar todas las peliculas  
	         String consulta2 = ("SELECT * FROM peliculas;");
	            
	         ps1 = con.prepareStatement(consulta2);
	         result1=ps1.executeQuery();
	         
	         //Se añaden todas las peliculas de la BD a un arrayList
	         while(result1.next())
	         {	       
	        	 peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
	        	 listaPelis.add(peli);
	         }

             if ((String) contexto.getSession().get("loginId")!=null)
	         {
            	 
	             String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"';");
	             ps = con.prepareStatement(consulta1);             
	             result=ps.executeQuery();
	           
	             while(result.next())
	             {
	        	     rol=result.getString("tipo");
	             }
	             if (rol.equals("ADMIN"))
	            	 return SUCCESS;
	        
	         } else 
	          return SUCCESS;
      } catch (Exception e) 
      {
         System.err.println("Error"+e); 
         return ERROR;
      } finally
      {   try
          {
             if(con != null) con.close(); 
             if(ps1 != null) ps1.close();
             if(ps != null) ps1.close();
             if(result !=null) result.close();
             if(result1 !=null) result.close();
             
          } catch (Exception e) 
          {
             System.err.println("Error"+e); 
          }
       }
    	  return ERROR;
     }     
     
     //Metodo para salir de la sesion
     @Action( value="logoutAction", results= {
 	 @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
 	 @Result(name=ERROR, type="dispatcher", location="/jsp/welcome.jsp") } ) 
     public String logout() 
     {
    	 ActionContext contexto = ActionContext.getContext();
   	     ResultSet result1=null;
   	     PreparedStatement ps1=null;
         try 
         {
        	 getConection();
        	 if ((String) contexto.getSession().get("loginId")!= null)  
             {
            	  // int confirmado = JOptionPane.showConfirmDialog(null,"¿Seguro que desea cerrar Sesion?");

            		//if (JOptionPane.OK_OPTION == confirmado)
            	//	{
            			 session.remove("loginId");
            			 session.remove("nombreuser");
            	//	}
             }
        	 String consulta1 = ("SELECT * FROM peliculas;");
         
        	 ps1 = con.prepareStatement(consulta1);
        	 result1=ps1.executeQuery();
        
        	 while(result1.next())
        	 {  
        		 peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
        		 listaPelis.add(peli);
        	 }
         } catch (Exception e) 
         {
             System.err.println("Error"+e); 
             return ERROR;
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
     
     //Metodo para verificar usuario
     @Action( value="verificarUsuarioAccion", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
     @Result(name=ERROR, type="dispatcher", location="/jsp/login.jsp") } )     
     public String verificarUsuario() 
     {
    	  ActionContext contexto = ActionContext.getContext();
    	  Map<String, Object> session = contexto.getSession();
    
    	  ResultSet result=null;
    	  ResultSet result1=null;
    	  PreparedStatement ps=null;
    	  PreparedStatement ps1=null;
    	  try 
    	  {	  
    		  getConection();
	      
    		  String consulta1 = ("SELECT * FROM peliculas;");       
    		  ps1 = con.prepareStatement(consulta1);
    		  result1=ps1.executeQuery();
	      
    		  while(result1.next())
    		  {     
	               peli = new Peliculas(result1.getInt("id_peli"),result1.getString("titulo"),result1.getString("genero"),result1.getString("director"),result1.getString("actor1"),result1.getString("actor2"),result1.getString("anio"),result1.getString("fotopeli"),result1.getFloat("valoracion"));
	        	   listaPelis.add(peli);
    		  }
    		  //Se consulta a la tabla usuario los usuarios por nick y password
	          String consulta = ("SELECT * FROM USUARIO where( nick LIKE'"+getNick()+"' AND passwd LIKE '"+getPasswd()+"') ;");
	          ps = con.prepareStatement(consulta);
	          result=ps.executeQuery();;
   
	          while(result.next())
	          {
	        	   nombre=result.getString("nombre");
	        	   username=result.getString("nick");
	        	   password=result.getString("passwd");
	        	   rol=result.getString("tipo");
	          }
	           
	           
	           if(getNick().equals(username)&& getPasswd().equals(password))
	           {
	            	 session.put("loginId", username );
	            	 session.put("nombreuser", nombre);
	            }
	            else
	            {
	            	return ERROR;
	           	}
                
    	  } catch (Exception e) 
    	  {
    		  System.err.println("Error"+e); 
    		  return ERROR;
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
     
     //Metodo para añadir usuario a la BD
     @Action( value="anadirAction", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"),
     @Result(name=ERROR, type="dispatcher", location="/jsp/error.jsp") } )     	    
     public String anadirUsuario() 
     {	    	 
    	 
    	 ActionContext contexto = ActionContext.getContext();
    	 
    	 ResultSet result1=null;
    	 ResultSet result3=null;
    	 PreparedStatement ps=null;
    	 PreparedStatement ps2=null;
    	 PreparedStatement ps3=null;
    	 PreparedStatement ps4=null;
    	 ResultSet result4=null;
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
   		     
   		     String consulta4 =("SELECT * FROM USUARIO WHERE nick= '"+getNick()+"';");
             ps4 = con.prepareStatement(consulta4);
             result4=ps4.executeQuery();
      
             while(result4.next())
             {
   	           nameuser=result4.getString("nick");
             }
             
             if (nameuser==null)
             {
            	 String consulta = ("INSERT INTO USUARIO values (?,?,?,?,?,?);");
            	 ps = con.prepareStatement(consulta);
            	 ps.setString(1, getNick());
            	 ps.setString(2, getPasswd());
            	 ps.setString(3, getNombre());
            	 ps.setString(4, getApellido());
            	 ps.setString(5, getMail());
            	 ps.setString(6, getTipo());
    		            
            	 ps.executeUpdate();
             }else
             {
            	 error="USER";
            	 // JOptionPane.showMessageDialog(null, "El usuario que ha introducido ya existe");
            	 return ERROR;
             }
    		  
    		 
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

     //Metodo para controlar la vista de los usuarios
     @Action( value="listarUsuariosAction", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/borrarUsuario.jsp"), 
     @Result(name=ERROR, type="dispatcher", location="/jsp/welcome.jsp") } )            	    
     public String listarUsuario() 
     {   	  
    	  ActionContext contexto = ActionContext.getContext();
    	 
    	  ResultSet result=null;
          PreparedStatement ps=null;
          ResultSet result3=null;
          PreparedStatement ps3=null;
          try 
          {
          	  getConection();
              //Se consulta a la tabla usuario segun su tipo user,para discriminar los ADMIN	                   
              String consulta = ("SELECT * FROM USUARIO WHERE tipo='USER';");
          		           
              ps = con.prepareStatement(consulta);
              result=ps.executeQuery();
              
              while(result.next())
              {
            	  //Se incluyen en un array todos los usuarios para enviarlos a la vista
                  user = new Usuario(result.getString("nick"),result.getString("nombre"),result.getString("apellido"),result.getString("passwd"),result.getString("mail"), result.getString("tipo"));
                  usuarios.add(user);
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
              return ERROR;
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
                
     //Metodo para borrar usuarios de la BD;recibira de la vista el nick seleccionado a borrar            
     @Action( value="borrarUsuarioAction", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
     @Result(name=ERROR, type="dispatcher", location="/jsp/borrarUsuario.jsp") } )     
     public String borrarUsuario() 
     {
    	  ActionContext contexto = ActionContext.getContext();
    	  ResultSet result1=null;
          PreparedStatement ps=null;
          PreparedStatement ps1=null;
          ResultSet result3=null;
          PreparedStatement ps3=null;
          	
          try 
          {
          		getConection();
       
          		String consulta = ("DELETE FROM USUARIO WHERE nick= '"+ getNick()+"'");
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
          		        if(result1 !=null) result1.close();
          		    } catch (Exception e) 
          		       {
          		            System.err.println("Error"+e); 
          		       }
          		}
          		return SUCCESS;
		} 
           	 
     //Metodo que elimina el usuario al darse de baja en la vista al recibir su nick de la tabla usuario y borra su lista depeliculas pendientes      
     @Action( value="bajaAction", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp"), 
     @Result(name=ERROR, type="dispatcher", location="/jsp/welcome.jsp") } )     
     public String bajaUser() 
     {
    	 ActionContext contexto = ActionContext.getContext();
         Map<String, Object> session = contexto.getSession();
           
         ResultSet result1=null;
         PreparedStatement ps=null;
         PreparedStatement ps1=null;
         PreparedStatement ps2=null;
               
          try 
          {
               getConection();
                      
               if ((String) contexto.getSession().get("loginId")!= null)  
             {
            	 
            				
            	    //Para borrar el usuario segun su nick
                    String consulta = ("DELETE FROM USUARIO WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"'");
                    ps = con.prepareStatement(consulta);
                    ps.executeUpdate();
                    
                  //Para borrar su listado de peliculas pendientes segun su nick
                    String consulta1 = ("DELETE FROM pendientes WHERE nick= '"+ (String) contexto.getSession().get("loginId")+"'");
                    ps1 = con.prepareStatement(consulta1);
                    ps1.executeUpdate();
                    
                    session.remove("loginId");
       			    session.remove("nombreuser");
            	
                    
                } 
                
                String consulta2 = ("SELECT * FROM peliculas;");
                           
                ps2 = con.prepareStatement(consulta2);
                result1=ps2.executeQuery();
                while(result1.next())
                {
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
                           if(ps != null) ps1.close();
                           if(ps1 != null) ps1.close();
                           if(ps2 != null) ps1.close();
                           if(result1 !=null) result1.close();
                           //Cierra 
                           session.remove("loginId");
                      } catch (Exception e) 
                      {
                          System.err.println("Error"+e); 
                      }
                }
                return SUCCESS;      
       }
   
   //Metodo que se utiliza para modificar los datos del usuario
     @Action( value="editUser", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/editauser.jsp") } ) 
     public String editUser() 
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
                   nombre= result1.getString("nombre");
                   apellido=result1.getString("apellido");
                   nick= result1.getString("nick");
                   passwd=result1.getString("passwd");
                   mail=result1.getString("mail");
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
                 		
                 } catch (Exception e) 
  	       	   {
              	   System.err.println("Error"+e); 
  	       	   }
  	       }
     		   return SUCCESS;
     	}
   //Metodo para modificar usuario en la BD
     @Action( value="modificaUser", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/welcome.jsp") } )     	    
     public String modificaUser() 
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
   		     
            	 String consulta = ("UPDATE USUARIO SET apellido='"+ getApellido()+"',passwd='"+ getPasswd()+"',nombre='"+ getNombre()+"',mail='"+ getMail()+"' WHERE nick='"+ getNick()+"';");
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
   //Metodo que utiliza el administrador para modificar los datos del usuario
     @Action( value="editAdmin", results= {
     @Result(name=SUCCESS, type="dispatcher", location="/jsp/editauser.jsp") } ) 
     public String editAdmin() 
     {
  	   ActionContext contexto = ActionContext.getContext();
  	   
  	   ResultSet result1=null;
  	
  	   PreparedStatement ps1=null;
  	   try 
  	   {
  		   getConection();
     		  
  	       if ((String) contexto.getSession().get("loginId")!=null)
  	       {
                 String consulta1 =("SELECT * FROM USUARIO WHERE nick= '"+ getNick()+"';");
                 ps1 = con.prepareStatement(consulta1);
                 result1=ps1.executeQuery();
             
                 while(result1.next())
                 {
                   nombre= result1.getString("nombre");
                   apellido=result1.getString("apellido");
                   nick= result1.getString("nick");
                   passwd=result1.getString("passwd");
                   mail=result1.getString("mail");
          	      
                 }
                 rol="ADMIN";
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
     //Metodos getters y setters
     public String getRol() 
     {
    	 return rol;
     }
			
     public void setRol(String rol) 
     {
		this.rol = rol;
	 }
     
     public Map<String, Object> getSession() 
     {
    	 return session;
     }
     
     public void setSession(Map<String, Object> session) 
     {
    	 this.session = session;
     }

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
	 
	 public ArrayList<Usuario> getUsuarios() 
	 {
	    return usuarios;
	 }

	 public void setUsuarios(ArrayList<Usuario> usuarios) 
	 {
	    this.usuarios = usuarios;
	 }

	 public Usuario getUser() 
	 {
	    return user;
	 }

	 public void setUser(Usuario user) 
	 {
	    this.user = user;
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
	 
	 public String getNameuser() 
	 {
		return nameuser;
	 }

	 public void setNameuser(String nameuser) 
 	 {
		this.nameuser = nameuser;
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