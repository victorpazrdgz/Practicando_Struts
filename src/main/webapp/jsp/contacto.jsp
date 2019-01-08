<!--  Vista de la pestaña contacto con los datos de contacto -->

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
    <title>Contacto</title>
  </head>
  <body>
     <center><h1>Contacto</h1></center>
     <br /> 
     <c:if test = "${nombreuser!=null}">
          <p class="bienve">Bienvenida/o a Luviflix : <b> <c:out value = "${nombreuser}"/></b></p>
     </c:if>
     <%@include file="menu.jsp" %>
     <br /> 
     <br />
     <br /> 
     <center><img src="jsp/images/luviflix.jpg" width="500px" height="250px" alt="imagen"/> </center>
     <br />
     <br /> 
     <center>
       <table width="600px"  border="0" align="center"> 
        <tr style="background-color: #990000;color:#E4D0AF;text-align: center;">
          <th>Alumno</th>
          <th>E-Mail</th>
        </tr>
        <tr style="background-color:#E4D0AF;color:#990000;text-align: center;"  >
            <td>Luis Miguel García Santurtun</td>
            <td><a href="mailto:lgarcia1775@alumno.uned.es">lgarcia1775@alumno.uned.es</a></td>
        </tr>  
        <tr style="background-color:#E4D0AF;color:#990000;text-align:center;" >
            <td>Victor Paz Rodríguez</td>
            <td><a href="mailto:vpaz14@alumno.uned.es">vpaz14@alumno.uned.es</a></td>
        </tr>    
       </table>
    </center>
 </body>
</html>