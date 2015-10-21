<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<div id="contenu">
		<h1>Gestion des tests</h1>

		<div id="DivListeTest">
			<h2>Listes des tests</h2>

			<%@ include file="/formateur/listeTest.jspf"%>

		</div>

		<input type="button" id="btn_add_test" value="Ajouter" /> 
		<input type="button" id="btn_update_test" value="Modifier" /> 
		<input type="button" id="btn_remove_test" value="Supprimer" />

		<div id="detailTest">
			<h2>Détail du test</h2>
		</div>
	</div>
</body>
</html>