<!-- Vista con formulario para que el  usuario modifique los campos correspondientes -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
    <title>Modificar Usuario</title>
  </head>
  <body>
      <center><h1 align="center">Modificar Usuario</h1></center>
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
      <s:form id="modifica" method="post" action="modificaUser" onsubmit="return valida()">
        <table width="342" border="0" align="center">
           <tr valign="baseline">
               <td  align="right"><b>Nombre:</b></td> 
               <td><input type="text" name="nombre" size="20" value='<s:property value="nombre" />' /> </td>
           </tr>
           <tr valign="baseline">
               <td nowrap align="right"><b> Apellidos:</b> </td> 
               <td ><input name="apellido" type="text"  size="20" value='<s:property value="apellido" />' /></td> 
           </tr>
           <tr valign="baseline">
               <td nowrap align="right"><b>Password(*):</b>  </td> 
               <td ><input name="passwd" type="text"  size="20" value='<s:property value="passwd" />' required/></td> 
           </tr>
           <tr valign="baseline">
              <td nowrap align="right"><p><strong> E-mail:</strong></p>  </td> 
              <td ><p><input name="mail" id="mail" type="text"  size="50" value='<s:property value="mail" />' /></p></td> 
           </tr>
           <tr valign="baseline">
              <td ><p><input name="nick" type="hidden"  size="20" value='<s:property value="nick" />' /></p></td> 
           </tr>
           <tr valign="baseline">
              <td ><input  type="hidden" name="tipo" value="USER"  /></td> 
           </tr>
           <tr> 
              <s:submit align="center" id="submit" value="Modificar Usuario " />
           </tr>  
        </table>
     </s:form>
     <br />
     <br /> 
     <b>(*) Este campo se debe introducir obligatoriamente</b>
     </center>
     <script>
        function valida() {
           valor = document.getElementById("mail").value;
           if( !(/\S+@\S+\.\S+/.test(valor)) ) {
              alert("La direccion de email es incorrecta");
           return false;
           }
           confirm('¿Seguro que desea modificar al usuario " <s:property value="nombre"/>" ?');
        }
      </script>

  </body>
</html>