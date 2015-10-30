<%@page import="sun.org.mozilla.javascript.internal.ast.WhileLoop"%>
<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<%
		// Recupere la session
		HttpSession sessionQ = request.getSession(true);
		// Recupere l'age de l'utilisateur 
		ArrayList<QuestionReponses> LesQuestionRep = (ArrayList) session.getAttribute("lesQuestionsReponses");
		int nbQuestion = LesQuestionRep.size();
	%>
	<%!int i = 0;%>
	<h1>Question n°<%=i+1%></h1>
	<form id="AffichageQuestion" method="get" action="./GestionReponseTest"	style="width: 600px">
		<script>
			
			function incremement(){ 
			 	
				<% 
					
					System.out.println("i : "+i+" nbQuestion : "+nbQuestion);
					i++;
			  	%>
			  	
				} 
			

		</script>

		<%
			//Recuperation de la question X
			String StringQuestion = LesQuestionRep.get(i).getLaQuestion().getEnonce().toString();

			//Recuperation du type de la réponse 
			Boolean typeRep = LesQuestionRep.get(i).getLaQuestion().getTypeReponse();

			//Recuperation des réponses X
			ArrayList<Reponse> LesReponses = LesQuestionRep.get(i).getLesReponses();
		%>


		<%
			if (typeRep == true) {
		%>
		<label><%=StringQuestion%></label> <label><i> (Une réponse
				possible)</i></label></br>
		<%
			} else {
		%>

		<label><%=StringQuestion%></label><label><i> (Plusieurs
				réponses possibles)</i></label></br>
		<%
			}
		%>

		<%
			for (Reponse r : LesReponses) {
		%>
		<%
			if (typeRep == true) {
		%>

		</br>
		<input type="radio" name="CheckRep" value="<%=r.getId()%>"><%=r.getLibelle()%></input></br>
		<%
			} else {
		%>

		</br>
		<input type="checkbox" name="CheckRep" value="<%=r.getId()%>"><%=r.getLibelle()%></input></br>
		<%
			}
			}
		%>
		<% 
			if(i==nbQuestion-1)
			{	
				%>
					</br><a href="<%=request.getContextPath()%>/GestionReponseTest?action=affRes"><input id="envoyer" type="button" value="Terminer"></a>
				<%
			}else
			{
				%>
					</br><input id="envoyer" onclick="increment()" type="submit" value="Suivant">				
				<% 
			} 
		%>
		
		
	</form>

</body>
</html>