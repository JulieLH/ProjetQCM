<%@ page  import ="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% ArrayList<Test> lesTests = (ArrayList<Test>)request.getAttribute("tests");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<div id="contenu">
		<h1>Choix du test</h1>
		<form id="ChoixTest" method="get" action="/GestionPasserTest">
			<select id="selectTest" name="selectTest">
				<%
					for (Test t : lesTests) {
				%>
				<option value="<%=t.getId()%>">
					<%=t.getLibelle()%>
				</option>
				<%
					}
				%>
			</select> <input type="submit" value="Lancer le test">
		</form>
	</div>
</body>
</html>