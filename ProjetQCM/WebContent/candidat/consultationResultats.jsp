<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Résultats du Test </title>
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="http://code.highcharts.com/highcharts.js"></script>			
		<script src="http://code.highcharts.com/modules/exporting.js"></script>
		<script type="text/javascript" charset="ISO-8859-1" src="<%=request.getContextPath()%>/js/PieChart.js"></script>
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> Résultats du Test </h1>
			<br></br>			
			<div id="left">
				<fieldset>
					<legend> Questions & Réponses </legend>

				</fieldset>
			</div>			
			<div id="right">
				<fieldset>
					<legend> Résultat Général </legend>					
					<div id="chart" style="min-width: 100px; height: 200px; max-width: 200px; margin: 0 auto">				
					</div>
					<ul type="circle" style="padding: 10px;">
						<li> Nombre de bonnes réponses : </li>
						<li> % de bonnes réponses </li>
						<li> Seuil atteint : </li>
						<li> Temps passé : </li>
						<li> Nombre d'incident(s) survenu(s) : </li>
					</ul>
					<a href="./Accueil.jsp" style="float:right;"> Retourner à la Page d'Accueil </a>
				</fieldset>
			</div>	
		</div>
	</body>
</html>