<!-- Vista con formulario para que el administrador agregue un nuevo usuario con los campos correspondientes -->

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
      <title>Registro Usuario</title>
   </head>

   <body>
        <center><h1>Registrar Usuario</h1></center>
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
          <s:form id="registro" method="post" action="anadirAction" onsubmit="return valida()">
               <table width="342" border="0" align="center">
                  <tr valign="baseline">
                     <td  align="right"><p><b>Nombre:</b></p></td> 
                     <td><p><input type="text" name="nombre" size="20"  placeholder="Nombre"/></p> </td>
                  </tr>
                  <tr valign="baseline">
                      <td align="right"><b> Apellidos:</b></td> 
                      <td ><input name="apellido" type="text"  size="20"  placeholder="Apellidos"/></td> 
                  </tr>      
                  <tr valign="baseline">
                      <td nowrap align="right"><b>Username(*):</b></td> 
                      <td ><input name="nick" type="text"  size="20" required  placeholder="Username"/></td> 
                  </tr>
                  <tr valign="baseline">
                       <td nowrap align="right"><b> Password(*):</b></td> 
                       <td ><input name="passwd" type="password"  size="20" required  placeholder="Password"/></td> 
                  </tr>
                  <tr valign="baseline">
                      <td nowrap align="right"><b> E-mail:</b></td> 
                      <td ><p><input name="mail" id="mail" type="text"  size="50" placeholder="Direccion e-mail" /></p></td> 
                  </tr>
                  <tr valign="baseline">
                      <td ><input  type="hidden" name="tipo" value="USER"  /></td> 
                  </tr>
                  <tr> 
                      <s:submit align="center" id="submit" value="Registrar Usuario " />
                  </tr>  
              </table>
          </s:form>
          <br />
          <br /> 
          <b>(*) Este campo se debe introducir obligatoriamente</b> 
     </center>
     <script type="text/javascript">
        function valida() {
            valor = document.getElementById("mail").value;
            if( !(/\S+@\S+\.\S+/.test(valor)) ) {
                alert("La direccion de email incorrecta");
                return false;
            }
            confirm('¿Seguro que desea registrarse como usuario?');
        }
      </script>
  </body>
</html>