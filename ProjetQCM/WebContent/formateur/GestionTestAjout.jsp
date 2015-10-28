<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'un test</title>
<link media="all" rel="stylesheet"
	href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
<link media="all" rel="stylesheet"
	href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<div id="contenu">
		<h1>Ajout/Modification d'un test</h1>
		<form id="Ajout" method="get" action="/GestionTest">
			<fieldset style="text-align: center;">
				<legend> Informations Général </legend>
				<label>Nom :</label> <input id="lib_test"> <br /> <br />
				<div>
					<table>
						<tr>
							<td><label>Durée :</label></td>
							<td><input id="duree_test"></td>
							<td><label>Nombre de section :</label></td>
							<td><input id="nbSection_test"></td>
						</tr>
						<tr>
							<td><label>1e seuil :</label></td>
							<td><input id="seuil1_test"></td>
							<td><label>2e seuil :</label></td>
							<td><input id="seuil2_test"></td>
						</tr>
					</table>
				</div>
			</fieldset>
			<fieldset>
				<legend> Plage de Validité </legend>
				<div id="left">
					<table>
						<tr>
							<td><img src="formateur/IMG/add.png" alt="retirer"
								style="width: 20px; height: 20px;" /></td>
							<td><input type="button" value="Ajouter plage"></td>
							<td>
								<input type="text">
							</td>
						</tr>
						<tr>
							<td>
								<img src="formateur/IMG/remove.png" alt="retirer" style="width: 20px; height: 20px;" />
							</td>
							<td>
								<input type="button" value="Supprimer plage">
							</td>
							<td>
								<input type="date"/>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table cellpadding="0" cellspacing="0" border="0" class="display"
						id="tabPlages">
						<thead>
							<tr>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
				</div>
			</fieldset>
			<fieldset>
				<legend> Sections & Questions </legend>
			</fieldset>
			<input type="submit" value="Enregistrer"> 
			<input type="button" name="cancel" value="Annuler">
		</form>
	</div>
</body>
</html>