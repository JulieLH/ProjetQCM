<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Inscription d'un Candidat </title>
	</head>
	<body>
		<div id="contenu">
			<h1> Inscription d'un Candidat </h1>
			<fieldset> 
				<legend> Recherche </legend> 
				<form id="rechercheCandidat" method="get" action="/RechercheCandidat"> 
					<table cellpadding="0px" cellspacing="0px"> 
						<tr> 
							<td style="border-style:solid none solid solid; border-color:#4B7B9F; border-width:1px;">
								<input type="text" name="caseCandidat" style="width:250px; border:0px solid; height:17px; padding:0px 3px; position:relative;"> 
							</td>
							<td style="border-style:solid; border-color:#4B7B9F; border-width:1px;"> 
								<input type="submit" value="Rechercher" style="border-style: none; height: 20px;">
							</td>
						</tr>
					</table>
				</form>
			</fieldset>
			<fieldset> 
				<legend> Tests </legend> 
				<form id="ajoutTest" method="get" action="/RechercheCandidat"> 
					<table cellpadding="0px" cellspacing="0px"> 
						<tr>
							<td> <label> Tri par Thème : </label> </td>
							<td>
								<select name="selectTest">
									<option value="laValeur"> Test 1 </option>
									<option value="laValeur"> Test 2 </option>
									<option value="laValeur"> Test 3 </option>
									<option value="laValeur"> Test 4 </option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<table>
									<tr>
										<th>Test</th>
									</tr>
									<tr>
									    <td>Le Test</td>
									</tr>
								</table>
							</td>
							<td> 
								<input type="submit" value="Ajouter">
							</td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
	</body>
</html>