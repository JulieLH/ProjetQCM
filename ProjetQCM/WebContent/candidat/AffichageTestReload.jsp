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
		ArrayList<QuestionReponses> LesQuestionRep = (ArrayList) session
				.getAttribute("lesQuestionsReponses");
	%>

	<h1>Question n°</h1>
	<form id="AffichageQuestion" method="get" action="#"
		style="width: 600px">

		<%
			//Recuperation de la question X
			String StringQuestion = LesQuestionRep.get(0).getLaQuestion()
					.getEnonce().toString();

			//Recuperation du type de la réponse 
			Boolean typeRep = LesQuestionRep.get(0).getLaQuestion()
					.getTypeReponse();

			//Recuperation des réponses X
			ArrayList<Reponse> LesReponses = LesQuestionRep.get(0)
					.getLesReponses();
			
			
		%>
	
		
			<%
		if (typeRep ==true){
		%>
		<label><%=StringQuestion%></label> <label><i> (Une réponse possible)</i></label></br>
		<%
		}else{
		%>
		
		<label><%=StringQuestion%></label><label><i> (Plusieurs réponses possibles)</i></label></br>
		<%
		}
		%>
		
		<%
			for (Reponse r : LesReponses) {
		%>
		<%
				if (typeRep == true) {
		%>
					
					</br><input type="radio"><%=r.getLibelle()%></input></br>
		<%
				} else {
		%>			
					
					</br><input type="checkbox"><%=r.getLibelle()%></input></br>
		<%
				}
			}
		%>

		</br> <input type="submit" value=Suivant>
	</form>

</body>
</html>