<!-- Vista de la tabla de usuarios que ofrece la posibilidad de borrar uno -->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="org.apache.commons.io.FileUtils" %>
<% String nombreuser = (String) session.getAttribute("nombreuser"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
     <title>Borrar Usuario</title>
  </head>

  <body>
    <center> <h1>Lista Usuarios A Borrar</h1></center>
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
    <table  bgcolor="black" width="100%">
        <tr style="background-color: #990000;color:#E4D0AF;text-align: center;">
           <th>Username</th>
           <th>Nombre</th>
           <th>Apellido</th>
           <th>Password</th>
           <th>Mail</th>
           <th>Editar</th>
           <th>Borrar</th>           
       </tr>
       <s:iterator value="usuarios">
        <tr style="background-color:#E4D0AF;color:#990000;text-align: center;" >
            <td><s:property value="nick"/></td>
            <td><s:property value="nombre"/></td>
            <td><s:property value="apellido"/></td>
            <td><s:property value="passwd"/></td>
            <td><s:property value="mail"/></td>
            <td><a nick="<s:property value="nick"/>" href="http://localhost:8080/42187180M22749185T/editAdmin.action?nick=<s:property value="nick"/>">Edit</a></td>
            <td><a nick="<s:property value="nick"/>" href="http://localhost:8080/42187180M22749185T/borrarUsuarioAction.action?nick=<s:property value="nick"/>"><img src="jsp/images/x.jpg" width="14px" height="14px" alt="imagen" onclick="return confirm('¿Seguro que desea Borrar Usuario <s:property value="nick"/> ?')"/></a></td>
         </tr>    
       </s:iterator>
     </table>
  </body>
</html>