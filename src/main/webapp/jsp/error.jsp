<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<% String nombreuser = (String) session.getAttribute("nombreuser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
     <title>Error Insercion</title>
   </head>
   <body>
     <center><h1>Se ha Producido un Error</h1></center>
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
     <s:set name="err" value="error"/>
     <s:if test="%{#err=='PELI'}">
       <center>
          <h1> Se ha producido un error en el registro de la película por favor intentelo de nuevo</h1>
          <br /> 
          <b> El titulo introducido no es Valido o ya existe </b>
          <br /> 
          <a  href="http://localhost:8080/42187180M22749185T/anadePeliAction.action" >Intentar de Nuevo</a>
      </center>
     </s:if>
     <s:else >
        <center>
          <h1> Se ha producido un error en el registro por favor intentelo de nuevo</h1>
          <br /> 
          <b> El nombre de usuario introducido no es Valido o ya existe</b>
          <br /> 
          <a  href="http://localhost:8080/42187180M22749185T/registroAction.action" >Intentar de Nuevo</a>
        </center>
     </s:else>
  </body>
</html>