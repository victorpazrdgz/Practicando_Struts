<!-- Vista de la ficha con caratula de pelicula y datos;ofrece la posibilidad de visulaizarla o añadirla a la videoteca -->

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
      <title>Ficha Pelicula</title>
   </head>
   <body>
       <center><h1>Ficha Pelicula</h1></center>
       <br /> 
       <c:if test = "${nombreuser!=null}">
          <p class="bienve">Bienvenida/o a Luviflix : <b> <c:out value = "${nombreuser}"/></b></p>
      </c:if>
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
      <center> 
      <br /> 
      <table width="900" border="0" align="center">
         <tr>
            <td  align="left" ><img src="<s:property value="fotopeli" />" width="300px" height="400px" alt="imagen"/></td> 
            <td  align="left"><p><strong>Titulo :</strong><s:property value="titulo" /></p>
                            <p>&nbsp;</p>
                            <p><strong>GENERO :</strong> <s:property value="genero" /></p>
                            <p>&nbsp;</p>
                            <p><strong>DIRECTOR : </strong> <s:property value="director" /></p>
                            <p>&nbsp;</p>
                            <p><strong>ACTOR PRINCIPAL :</strong>  <s:property value="actor1" /></p>
                            <p>&nbsp;</p>
                            <p><strong>ACTOR SECUNDARIO : </strong> <s:property value="actor2" /></p>
                            <p>&nbsp;</p>
                            <p><strong>AÑO : </strong> <s:property value="anio" /></p>
                            <p>&nbsp;</p>
                            <p><strong>VALORACIÓN : </strong> <s:property value="valoracion" /></p>
         
            </td>   
            <td  align="right"> 
                      <a title="<s:property value="titulo"/>" href="http://localhost:8080/42187180M22749185T/verPeliAction.action?title=<s:property value="titulo"/>"><img src="jsp/images/play.jpg" width="220px" height="180px" alt="imagen"/> </a>              
                      <a title="<s:property value="titulo"/>" href="http://localhost:8080/42187180M22749185T/videotecaAction.action?title=<s:property value="titulo"/>" onclick="return confirm('¿Confirma que desea Agregar Pelicula <s:property value="titulo"/> a la videoteca?')"><img src="jsp/images/anadirvideoteca.jpg" width="220px" height="180px" alt="imagen" /></a>
            </td> 
        </tr>  
	
       </table>
      </center>
  </body>
</html>