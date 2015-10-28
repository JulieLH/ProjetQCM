<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>	
	<div id="contenu">
		<form id="AffichageQuestion" method="get" action="#">
			
			<p>${lesQuestion}</p>
			
			<%
			// Recupere la session
			HttpSession sessionQ = request.getSession(true); 
			// Recupere l'age de l'utilisateur 
			ArrayList<String> Question = (ArrayList)session.getAttribute("lesQuestions");
			%>
			
			<p></p>
			
		
			

			<input type="submit" value=Suivant>
		</form>
	</div>
</body>
</html>