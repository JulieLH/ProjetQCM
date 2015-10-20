<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Inscription d'un Candidat </title>
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> Inscription d'un Candidat </h1>
			<form id="inscription" method="get" action="/InscriptionCandidat">
				<fieldset>
					<legend> Recherche </legend>
						<table cellpadding="0px" cellspacing="0px">
							<tr>
								<td>
									<input type="text" name="caseCandidat" style="width:250px;"> 
								</td>
								<td>
									<input type="submit" value="Rechercher">
								</td>
							</tr>
						</table>
				</fieldset></br>
				<fieldset>
					<legend> Tests </legend>
						<label> Tri par Thème : </label> </td>
						<select name="selectTest">
							<option value="laValeur"> Test 1 </option>
							<option value="laValeur"> Test 2 </option>
							<option value="laValeur"> Test 3 </option>
							<option value="laValeur"> Test 4 </option>
						</select>
						<table>
							<tr>
								<td>
									<table style="width:500px;">
										<tr>
											<th>Test</th>
										</tr>
										<tr>
										    <td>Le Test</td>
										</tr>
									</table>
								</td>
								<td>
									<img src="formateur/IMG/add.png" alt="ajouter" style="width:20px; height:20px;"/>
								</td>
								<td>
									<input type="submit" value="Ajouter">
								</td>
							</tr>
						</table>
				</fieldset></br>
				<fieldset> 
					<legend> Tests sélectionnés </legend>
						<table>
							<tr>
								<td>
									<table style="width:500px;">
										<tr>
											<th>Test</th>
										</tr>
										<tr>
										    <td>Le Test</td>
										</tr>
									</table>
								</td>
								<td>
									<img src="formateur/IMG/remove.png" alt="retirer" style="width:20px; height:20px;"/>
								</td>
								<td>
									<input type="submit" value="Retirer">
								</td>
							</tr>
						</table>
				</fieldset></br>
				<input type="submit" value="Enregistrer l'Inscription">
				<input type="button" name="cancel" value="Annuler l'Inscription">				
			</form>
		</div>
	</body>
</html>