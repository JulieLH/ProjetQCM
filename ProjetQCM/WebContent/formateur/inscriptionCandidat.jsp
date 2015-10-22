<%@ page  import ="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% ArrayList<Theme> lesThemes = (ArrayList<Theme>)request.getAttribute("themes"); 
   ArrayList<Utilisateur> lesCandidats = (ArrayList<Utilisateur>)request.getAttribute("candidats"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Inscription d'un Candidat </title>
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> Inscription d'un Candidat </h1>
			<form id="inscription" method="get" action="/InscriptionCandidat">
				<fieldset>
					<legend> Recherche </legend>
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="tabCandidats"> 
					    <thead> 
					        <tr> 
					            <th>Nom</th> 
					            <th>Prénom</th> 
					            <th>Sélection</th> 
					        </tr> 
					    </thead> 
					    <tbody> 
					    	<% for (Utilisateur u : lesCandidats) { %>
					        <tr>						        
								<td> <%=u.getNom().toUpperCase()%> </td>
								<td> <%=u.getPrenom()%> </td>
								<td style="text-align:center;"> <input type="checkbox" name="<%=u.getId()%>"> </td>
					        </tr> 
					        <% } %>
						</tbody>
					</table>
					<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
					<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
					<script>
					$(document).ready(function() {
					    $('#tabCandidats').DataTable( {
					        "language": {
					            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json"
					        }
					    } );
					} );
					</script>
				</fieldset></br>
				<fieldset>
					<legend> Tests </legend>
						<label> Tri par Thème : </label> </td>
						<select name="selectTest">
							<% for (Theme t : lesThemes) { %>
							<option value="theme_<%=t.getId()%>"> <%=t.getLibelle()%> </option>
							<% } %>
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