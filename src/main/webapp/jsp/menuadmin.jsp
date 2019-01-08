<!-- JSP que muestra el menu de usuario administrador -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
     <c:set var="url">${pageContext.request.requestURL}</c:set>
     <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
     <link href= "jsp/css/welcome.css"  rel="stylesheet" />
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  </head>

  <body>
      <br />
      <div id="header">
			<ul class="nav">
				<li><a href="http://localhost:8080/42187180M22749185T/welcomeaction.action">Inicio</a></li>
				<li><a>Servicios</a>
					<ul>
						<li><a href="http://localhost:8080/42187180M22749185T/anadePeliAction.action">Añadir Pelicula</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/borrarPeliAction.action">Eliminar/Editar Pelicula</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/logoutAction.action" onclick="return confirm('¿Seguro que desea CERRAR SESION?')">Cerrar Sesion</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/registroAction.action">Registro Usuario</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/listarUsuariosAction.action">Eliminar/Editar Usuario</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/verVideotecaAction.action?title=">Peliculas Videoteca</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/buscarpelisjsp.action">Buscar Pelicula</a></li>
					</ul>
				</li>
				<li><a>Acerca de</a>
					<ul>
						<li><a href="http://localhost:8080/42187180M22749185T/ayuda.action">Ayuda</a></li>
						<li><a href="#" onclick="abrirVentana('jsp/files/javadoc/index.html')">Javadoc</a></li>
						<li><a href="http://localhost:8080/42187180M22749185T/memoria.action">Memoria Practica</a></li>
						
					</ul>
				</li>
				
		  </ul>
      </div>
      <script>
         function abrirVentana(url) {
            window.open(url, "nuevo", "directories=yes, location=yes, menubar=yes, scrollbars=yes, statusbar=yes, tittlebar=yes, width=400, height=400");
         }
      </script>
  </body>
</html>	