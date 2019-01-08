<!-- Vista principal que muestra menu segun el rol, busqueda rapida de peliculas,enlace directo a la ficha de todas las peliculas y un enlace 
	 directo a la videoteca del usuario con sus peliculas pendientes para ver. Ademas incluye un script para que al cerrar la ventana del navegador
	 se cierre la sesion del usuario -->

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% String nombreuser = (String) session.getAttribute("nombreuser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
   <title>Welcome</title>
   <link href= "jsp/css/welcome.css"  rel="stylesheet" type="text/css" />
 </head>

<body>
  
  <div id="body">
    
    <h2 align="center">Las mejores peliculas de la historia... Quizas no esten todas las que son, ni sean todas las que estan,pero a nosotros nos gustan</h2>
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

    <s:form id="busqueda" method="post" action="busquedaAction">
       <table width="280" border="0" align="left">
         <tr valign="baseline">
           <td width="10" height="15" align="center"><b>Elige</b> </td>
           <td>
              <select name="eleccion" size="1">
                 <option value="titulo">TITULO</option>
                  <option value="genero">GENERO</option>
                  <option value="director">DIRECTOR</option>
                  <option value="actorX">ACTOR</option>
                  <option value="anio">AÑO</option>
             </select>
          </td>
          <td align="right"><b>Texto:</b></td> 
          <td ><input name="parametro" type="text"  size="15" placeholder="Parametro Busqueda"/> </td> 
          <td> <input type="submit" name="Submit" value="Buscar" ></input></td>     
        </tr>
      </table>
    </s:form>
 
    <s:form id="busqueda" method="post" action="busquedaAction">
        <table width="280" border="0"  align="left">
           <tr valign="baseline">
              <td width="10" height="15"align="center"><b>Elige</b></td>
              <td> 
                 <select name="eleccion" size="1">
                    <option value="val">VALORACION(0-5)</option>
                 </select>
              </td> 
              <td align="right"><b>Valor:</b></td> 
              <td>
                  <select name="parametro"   size="1">
                     <option value="0">0</option>
                     <option value="1">1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                     <option value="5">5</option>
                  </select>
             </td> 
             <td> <input type="submit" name="Submit" value="Buscar"/></td>  
          </tr>
       </table>
    </s:form>

    <table width="10%" align="center">
       <tr>
         <td width="100%" bgcolor="#E4D0AF" >
             <a title="<s:property value="nick"/>" href="http://localhost:8080/42187180M22749185T/verVideotecaAction.action?title="<s:property value="titulo"/>><img src="jsp/images/videoteca.jpg" width="90px" height="90px" alt="imagen"/></a>
         </td>
      </tr>
    </table>

  </div>
 
  <div id="big_pic" class="red">
      <img src="jsp/images/luviflix.jpg" alt="mainphoto" name="mainphoto" width="950" height="400" id="mainphoto" />
  </div>
  
  <div id="pelis"> 
    <center> 
     <s:iterator value="listaPelis">
        <tr>
     	  <td align="center">
             <a title="<s:property value="titulo"/>" href="http://localhost:8080/42187180M22749185T/mostrarFichaAction.action?title=<s:property value="titulo"/>"><img src="<s:property value="fotopeli" />" width="150px" height="150px" alt="imagen" /></a><br/>
          </td>
        </tr>
     </s:iterator>
    </center> 
 </div>
 
 <script language="JavaScript" type="text/javascript">
    window.onbeforeunload = Salir;
     
    function Salir()
    {
    	location.href ="logoutAction";	
    }
 </script>


 </body>

</html>
