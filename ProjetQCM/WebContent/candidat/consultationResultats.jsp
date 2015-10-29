<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> R�sultats du Test </title>
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
			<h1> R�sultats du Test </h1>
			<br></br>			
			<div id="left">
				<fieldset>
					<legend> Questions & R�ponses </legend>

				</fieldset>
			</div>			
			<div id="right">
				<input type="hidden" value="8" id="seuilMin"> 
				<input type="hidden" value="15" id="seuilMax"> 
				<input type="hidden" value="20" id="seuilAtteint">  
				<input type="hidden" value="100" id="pourcentage">  
				<fieldset>
					<legend> R�sultat G�n�ral </legend>					
					<div id="chart" style="min-width: 100px; height: 200px; max-width: 200px; margin: 0 auto"></div>
					<div id="seuil" style="min-width: 310px; max-width: 400px; height: 250px; margin: 0 auto"></div>					
					<ul type="circle">
						<li> Nombre de bonnes r�ponses : </li>
						<li> % de bonnes r�ponses </li>
						<li> Seuil atteint : </li>
						<li> Temps pass� : </li>
						<li> Nombre d'incident(s) survenu(s) : </li>
					</ul>
					<br>
					<a href="./Accueil.jsp" style="float:right;"> Retourner � la Page d'Accueil </a>
				</fieldset>
			</div>	
		</div>
	</body>
</html>