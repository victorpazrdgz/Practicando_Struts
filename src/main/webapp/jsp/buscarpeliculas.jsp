<!-- Vista para el formulario que ofrece la posibilidad de buscar una pelicula por campo -->

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
     <title>Busqueda pelicula</title>
  </head>
  <body >
     <center><h1>Elija pelicula</h1></center>
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
     <center><img src="jsp/images/luviflix.jpg" width="500px" height="250px"  alt="imagen"/></center> 
     <br /> 
     <br />
     <center>
     <s:form id="busqueda" method="post" action="busquedaAction">
        <table  >
           <tr >
              <td  align="right"><b>Elige</b></td>
              <td> <select name="eleccion" size="1">
                      <option value="titulo" >TITULO</option>
                      <option value="genero">GENERO</option>
                      <option value="director">DIRECTOR</option>
                      <option value="actorX">ACTOR</option>
                      <option value="anio">AÑO</option>
                   </select>
              </td>
              <td align="right"><b>Texto:</b> </td> 
              <td ><input name="parametro" type="text"  size="20"  placeholder="Parametro Busqueda" /></td> 
              <td bgcolor="#E4D0AF"><input type="submit" name="Submit" value="Buscar" /></td>  
           </tr>
       </table>
     </s:form>
     <s:form id="busqueda" method="post" action="busquedaAction">
        <table >
           <tr >
              <td  align="right"><b>Elige</b></td>
              <td> <select name="eleccion" size="1">  
                       <option value="val">VALORACION(0-5)</option>
                   </select>
              </td>
             <td align="right"><b>Valor:</b></td> 
             <td ><select name="parametro" size="1">
                     <option value="0">0</option>
                     <option value="1">1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                     <option value="5">5</option>
                  </select>
             </td> 
             <td bgcolor="#E4D0AF"><input type="submit" name="Submit" value="Buscar"/></td>  
           </tr>
        </table>
      </s:form>
      </center>
  </body>

</html>