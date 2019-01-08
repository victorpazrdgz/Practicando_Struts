<!-- Vista de visualizacion de una pelicula; con un video demo de prueba y con formulario para valorar la pelicula de 0 a 5-->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import = "com.tecweb.action.PeliAction"%> 
<%@ page import = "java.util.LinkedList"%> 
<%@ page import = "com.tecweb.action.PeliAction"%> 
<% String nombreuser = (String) session.getAttribute("nombreuser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
     <title>Fin pelicula</title>
  </head>
 
  <body>
       <center><h1>Disfruta de la Pelicula</h1></center>
       <br /> 
       <c:if test = "${nombreuser!=null}">
           <p class="bienve">Bienvenida/o a Luviflix : <b> <c:out value = "${nombreuser}"/></b></p>
       </c:if>
       <!--Selecciona menu a mostrar en funcion de si es administrador o usuario-->
       <s:set name="tipo" value="rol"/>
       <s:if test="%{#tipo=='ADMIN'}">
	      <%@include file="menuadmin.jsp" %>
       </s:if>
       <s:else >
         <%@include file="menu.jsp" %>
       </s:else>
       <br /> 
       <br />
       <br /> 
       <center><img src="jsp/images/luviflix.jpg" width="500px" height="250px" alt="imagen"/> </center>
       <br />
       <br /> 
       <!--Muestra video demostracion por pantalla-->
       <center> 
           <object data="jsp/video/video.ogv" width="640" height="360" type="video/ogg" >
             <param name="src" value="jsp/video/video.ogv"/>
             <param name="allowFullScreen" value="true" />  
             <param name="controller" value="false" />
	         <param name="autostart" value="1"/>  
          </object>
       </center>
       <br />
       <br /> 
       <center>
          <!--Formulario para recoger valoración de la película-->
          <s:form id="valorar" method="post" action="valorarPeliAction">
             <table width="342" >
                <tr>
                    <td><h4>Valore la pelicula</h4></td>
                    <td><select name="valoracion" size="1">
                           <option value="0">0</option>
                           <option value="1">1</option>
                           <option value="2">2</option>
                           <option value="3">3</option>
                           <option value="4">4</option>
                           <option value="5">5</option>
                        </select>
                    </td>
                    <td> <input type="hidden" name="titulo" value="<s:property value="titulo" />"/> </td>
                    <td> <input type="submit" name="Submit" value="Valorar" /></td>
                </tr>
             </table>
           </s:form>
         </center>
     </body>
</html>