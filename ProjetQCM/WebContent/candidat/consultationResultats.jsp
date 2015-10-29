<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>
<%
	Resultat leResultat = (Resultat)request.getSession().getAttribute("resultat");
	Test leTest = (Test)request.getSession().getAttribute("test");
	ArrayList<QuestionReponses> lesQuestionsReponses = (ArrayList<QuestionReponses>)request.getSession().getAttribute("questionReponses");
 	String tempsPasse = request.getSession().getAttribute("tempsPasse").toString();
 	String seuil = request.getSession().getAttribute("seuil").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Résultats du Test </title>
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="http://code.highcharts.com/highcharts.js"></script>	
		<script src="http://code.highcharts.com/highcharts-more.js"></script>		
		<script src="http://code.highcharts.com/modules/exporting.js"></script>
		<script type="text/javascript" charset="ISO-8859-1" src="<%=request.getContextPath()%>/js/PieChart.js"></script>
		<script type="text/javascript" charset="ISO-8859-1" src="<%=request.getContextPath()%>/js/Jauge.js"></script>
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> Résultats du Test </h1>
			<br></br>			
			<div id="left">
				<div style="overflow-y: scroll; height:650px;">
					<fieldset>
						<legend> Questions & Réponses </legend>
						<% for (QuestionReponses qr : lesQuestionsReponses) {%>
							<h5> <%=qr.getLaQuestion().getEnonce()%> </h5>
							<% for (Reponse r : qr.getLesReponses()) {
								if (r.getBonneReponse())
								{%>
									<p style="color: #009900; margin-top:-10px;"> <%=r.getLibelle()%> <img src="candidat/IMG/tick.png" alt="good" style="width:12px; height:12px;"/></p> 
								<%}
								else
								{%>
									<p style="color: #CC0000; margin-top:-10px;"> <%=r.getLibelle()%> <img src="candidat/IMG/none.png" alt="good" style="width:12px; height:12px;"/></p>				
							<%}}%>
						<%}%>				
					</fieldset>
				</div>
			</div>			
			<div id="right">
				<input type="hidden" value="<%=leTest.getSeuilMin()%>" id="seuilMin"> 
				<input type="hidden" value="<%=leTest.getSeuilMax()%>" id="seuilMax"> 
				<input type="hidden" value="<%=leResultat.getSeuilAtteint()%>" id="seuilAtteint">  
				<input type="hidden" value="<%=leResultat.getPourcentage()%>" id="pourcentage">  
				<fieldset>
					<legend> Résultat Général </legend>					
					<div id="chart" style="min-width: 100px; height: 200px; max-width: 200px; margin: 0 auto"></div>
					<div id="seuil" style="min-width: 310px; max-width: 400px; height: 250px; margin: 0 auto"></div>					
					<ul type="circle">
						<li> Nombre de bonnes réponses : <b><%=leResultat.getNbBonnesReponses()%></b> </li>
						<li> <b><%=leResultat.getPourcentage()%></b>% de bonnes réponses </li>
						<li> Note : <b><%=leResultat.getSeuilAtteint()%></b>/20</li>
						<li> Seuil atteint : <b><%=seuil%></b></li>
						<li> Temps passé : <b><%=tempsPasse%></b> minutes</li>
					</ul>
					<br>
					<a href="./Accueil.jsp" style="float:right;"> Retourner à la Page d'Accueil </a>
				</fieldset>
			</div>	
		</div>
	</body>
</html>