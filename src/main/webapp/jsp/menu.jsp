<!-- JSP que muestra el menu de usuario registrado y no registrado -->
<%@taglib uri="/struts-tags" prefix="s"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <c:set var="url">${pageContext.request.requestURL}</c:set>
   <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
   <link href= "jsp/css/welcome.css"  rel="stylesheet" />
   <s:set name="tipo" value="rol"/> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  </head>
  <body>
     <br />
     <div id="header">
			<ul class="nav">
				<li><a href="http://localhost:8080/42187180M22749185T/welcomeaction.action">Inicio</a></li>
				<li><a>Servicios</a>
					<ul>
						<li><a href="http://localhost:8080/42187180M22749185T/loginAction.action">Iniciar Sesion</a></li>
						<s:if test="%{#tipo=='USER'}">
						<li><a href="http://localhost:8080/42187180M22749185T/logoutAction.action" onclick="return confirm('¿Seguro que desea CERRAR SESION?')">Cerrar Sesion</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/verVideotecaAction.action">Peliculas Videoteca</a></li>
						 </s:if>
						<li><a href="http://localhost:8080/42187180M22749185T/buscarpelisjsp.action">Buscar Pelicula</a></li>
						<s:if test="%{#tipo!='USER'}">
						<li><a href="http://localhost:8080/42187180M22749185T/registroAction.action">Registro</a></li>
						</s:if>
						<s:if test="%{#tipo=='USER'}">
						<li><a href="http://localhost:8080/42187180M22749185T/bajaAction.action" onclick="return confirm('¿Confirma que desea darse de Baja en el Servicio?')">Baja Servicio</a></li>
					    
					    <li><a href="http://localhost:8080/42187180M22749185T/editUser.action">Editar Usuario</a></li>
					    </s:if>
					</ul>
				</li>
				<li><a>Acerca de</a>
					<ul>
						<li><a href="http://localhost:8080/42187180M22749185T/ayuda.action">Ayuda</a></li>
						<li><a href="#" onclick="abrirVentana('jsp/files/javadoc/index.html')">Javadoc</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/memoria.action">Memoria Practica</a></li>
					</ul>
				</li>
				<li><a href="http://localhost:8080/42187180M22749185T/contactoAction.action">Contacto</a></li>
			</ul>
      </div>
      <script>
         function abrirVentana(url) {
            window.open(url, "nuevo", "directories=yes, location=yes, menubar=yes, scrollbars=yes, statusbar=yes, tittlebar=yes, width=400, height=400");
         }
      </script>
 </body>
</html>		