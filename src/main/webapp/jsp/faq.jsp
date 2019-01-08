<!-- Vista que muestra el pdf con la memoria del proyecto -->
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
     <title>Ayuda</title>
   </head>

   <body>
     <center> <h1>FAQ</h1></center>
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
     <br /> 
    <br /> 
     <center><object data="jsp/files/Faq.pdf" width="900" height="1200" type="application/pdf"></object></center>
  </body>
</html>