<!--  Vista con la tabla filtrada de peliculas de la videoteca, que ofrece la posibilidad de abrir su ficha o borrarla-->

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
     <title>Busqueda peliculas Videoteca</title>
  </head>
  <body>
    <center><h1 >Peliculas Videoteca</h1></center>
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
     <table  bgcolor="black" width="100%">
        <tr style="background-color:#990000;color:#E4D0AF;text-align: center;">  
            <th>Titulo</th>
            <th>Genero</th>
            <th>Director</th>
            <th>Actor principal</th>
            <th>Actor secundario</th>
            <th>Año</th>
            <th>Valoracion</th>   
            <th>Ficha</th> 
           <th>Borrar</th>  
       </tr>
       <s:iterator value="colecta">
        <tr style="background-color:#E4D0AF;color:#990000;text-align: center;" >
            <td><s:property value="titulo"/></td>
            <td><s:property value="genero"/></td>
            <td><s:property value="director"/></td>
            <td><s:property value="actor1"/></td>
            <td><s:property value="actor2"/></td>
            <td><s:property value="anio"/></td>
            <td><s:property value="valoracion"/></td>
            <td><a title="<s:property value="titulo"/>" href="http://localhost:8080/42187180M22749185T/mostrarFichaAction.action?title=<s:property value="titulo"/>"><img src="<s:property value="fotopeli" />" width="40px" height="60px" alt="imagen"/></a></td>
            <td><a title="<s:property value="titulo"/>" href="http://localhost:8080/42187180M22749185T/borrarvideoteca.action?title=<s:property value="titulo"/>" onclick="return confirm('¿Confirma que desea Borrar Pelicula <s:property value="titulo"/> de la videoteca?')"><img src="jsp/images/x.jpg" width="18px" height="18px" alt="imagen"/></a></td>
        </tr>    
       </s:iterator>
     </table>
  </body>
</html>