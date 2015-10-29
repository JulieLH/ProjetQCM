<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="fr.eni_ecole.jee.bean.Utilisateur"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title></title>
	<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
</head>
<body>
	<div id="page">
		<div id="entete">
			<% Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");%>
			Bonjour, <%= u.getNom()%> <%=u.getPrenom()%>
			<a href="GestionDeconnexion" style="float:right;"> Déconnexion </a>
		</div>
		</br>
		<%@ include file="/menu.jspf"%>
	</div>
</body>
</html>