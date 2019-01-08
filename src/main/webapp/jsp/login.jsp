<!-- Vista del formulario para loguearse en la aplicacion mediante nick y password -->

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
  <head >
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Inicio Sesion</title>
  </head>
  <body> 
    <center><h1>Iniciar Sesion</h1> </center>
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
    <br /> 
    <center> 
      <s:form id="login" method="post" action="verificarUsuarioAccion" >
       <table width="342" border="0">
         <tr><td width="18"nowrap><B> UserName: </B></td><td><input type="text" name="nick" id="nick" placeholder="Username"/></td></tr>
         <tr><td width="18" nowrap><B> Password: </B></td><td><input type="password" name="passwd" id="passwd"  placeholder="Password" /><td></tr>
         <tr><td> <s:submit  align="center" id="submit" value="Login Usuario" /><td></tr>
      </table>
     </s:form>
    </center>
  </body>
</html>