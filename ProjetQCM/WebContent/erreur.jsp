<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	  <meta content="text/html; charset=ISO-8859-1" http-equiv="content-type"/>
	  <title>Oups !</title>
	  <link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
	</head>
	<body>
		<%@ include file="/menu.jspf" %>
		<div id="contenu">
			<h1 class="erreur">Et bim ! L'erreur !</h1>
			
			<div>
				<h2>Erreur</h2>
				<jsp:useBean id="erreur" class="java.lang.Exception" type="java.lang.Exception" scope="request" />
				<p>Une erreur s'est produite : <jsp:getProperty name="erreur" property="message" /></p>
			</div>
	 	</div>
	</body>
</html>