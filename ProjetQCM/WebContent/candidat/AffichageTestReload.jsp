<%@page import="sun.org.mozilla.javascript.internal.ast.WhileLoop"%>
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
	<%
		// Recupere la session
		HttpSession sessionQ = request.getSession(true);
		// Recupere l'age de l'utilisateur 
		ArrayList<Question> LesQuestions = (ArrayList) session
				.getAttribute("lesQuestions");

		
	%>
	
	<h1>Question n°</h1>
	<form id="AffichageQuestion" method="get" action="#"
		style="width: 600px">


<%
					for (Question q : LesQuestions) {
				%>
				<label><%=q.getEnonce()%></label>
				<%
					}
				%>
		 
		
		<input type="submit" value=Suivant>
	</form>

</body>
</html>