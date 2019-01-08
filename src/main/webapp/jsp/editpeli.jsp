<!-- Esta vista muestra el formulario para que el administrador cumplimenta los datos de una nueva pelicula -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<% String nombreuser = (String) session.getAttribute("nombreuser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title> Modifica Pelicula </title>
  </head>
  <body>
     <center><h1> Modificar Pelicula</h1></center>
     <br /> 
     <c:if test = "${nombreuser!=null}">
          <p class="bienve">Bienvenida/o a Luviflix : <b> <c:out value = "${nombreuser}"/></b></p>
     </c:if>
     <%@include file="menuadmin.jsp" %>
     <br /> 
     <br />
     <br /> 
     <center><img src="jsp/images/luviflix.jpg" width="500px" height="250px" alt="imagen"/> </center>
     <br />
     <br /> 
     <center>
      <s:form id="modificapelicula" method="post" action="modificaPeli" onsubmit="return valida()">
        <table width="159" border="0" align="center">
          <tr valign="baseline">
  		    <td  align="right"><b> Genero:</b></td> 
  		    <td > <select name="genero" size="1"  >
  		            <option value='<s:property value="genero" />' ><s:property value="genero" /></option>    
                    <option value="Infantil">INFANTIL</option>
                    <option value="Western">WESTERN</option>
                    <option value="Terror">TERROR</option>
                    <option value="Comedia">COMEDIA</option>
                    <option value="Belica">BELICA</option>
                    <option value="Drama">DRAMA</option>
                    <option value="Suspense">SUSPENSE</option>
                    <option value="Accion">ACCION</option>
                    <option value="Musical">MUSICAL</option>
              	</select>
             </td> 
           </tr>
           <tr valign="baseline">
  		      <td  align="right"><b>Director:</b></td> 
  		      <td ><input type="text" name="director" value='<s:property value="director"/>' /></td> 
           </tr>
  		   <tr valign="baseline">
  		      <td  align="right"><b>Actor_Principal:</b></td> 
              <td ><input type="text" name="actor1" value='<s:property value="actor1" />' /></td> 
           </tr>
  		   <tr valign="baseline">
  		      <td  align="right" ><b>Actor_Secundario:</b></td> 
  		      <td ><input type="text" name="actor2" value='<s:property value="actor2" />' /></td> 
           </tr>
           <tr valign="baseline">
  		      <td  align="right"><b> Año:</b></td> 
  		      <td >  <select name="anio" size="1"  >
  		                <option value='<s:property value="anio" />' ><s:property value="anio" /></option>   
  		   	            <c:forEach var="x" begin="1940" end="2030">
  		   		           <option value="${x}">${x}</option>
  		   	            </c:forEach>
           	        </select>
           	 </td> 
            </tr>
            <tr valign="baseline">
                <td ><p><input name="titulo" type="hidden"  size="20" value='<s:property value="titulo" />' /></p></td> 
            </tr>
            <tr valign="baseline">
  		       <s:submit align="center" id="submit" value="Modificar Pelicula" />
  		    </tr> 
         </table>
     </s:form>
    </center>
    <script>
        function valida() {
           
           confirm('¿Seguro que desea modificar la pelicula " <s:property value="title"/>" ?');
        }
      </script>
  </body>
</html>