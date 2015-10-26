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
					    	<% for (Utilisateur user : lesCandidats) { %>
					        <tr>	        
								<td> <%=user.getNom().toUpperCase()%> </td>
								<td> <%=user.getPrenom()%> </td>
								<td style="text-align:center;"> <input type="checkbox" name="<%=user.getId()%>"> </td>
					        </tr>
					        <% } %>
						</tbody>
					</table>
					<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
					<script type="text/javascript" charset="utf8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
					<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
					<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.9/api/fnReloadAjax.js"></script>
					<script>
					$(document).ready(function() {
					    $('#tabCandidats').DataTable( {
					    	info: false,
					    	lengthChange : false,
					        "language": {
					            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json"
					        }
					    } );
					} );
					</script>
				</fieldset></br>
				<fieldset>
					<legend> Tests </legend>
						<label> Tri par Thème : </label>
						<select id="selectTheme" name="selectTheme" onchange="SelectionTheme()">
							<% for (Theme t : lesThemes) { %>
							<option value="<%=t.getId()%>"> <%=t.getLibelle()%> </option>
							<% } %>
						</select>
						<table>
							<tr>
								<td>
									<table cellpadding="0" cellspacing="0" border="0" id="tabTests" class="display" style="width:500px;">
									<thead>
							            <tr>
							                <th>ID</th>
							                <th>Test</th>
							            </tr>
							        </thead>
									<tbody>
									</tbody>
									</table>									
								</td>
								<td>
									<img src="formateur/IMG/add.png" alt="ajouter" style="width:20px; height:20px;"/>
								</td>
								<td>
									<script>
									function RechargerPlages()
									{	
										var $id = document.getElementById("ajouterTest").name;										
										tablePlagesHoraires.fnReloadAjax("./InscriptionCandidat?action=getPlages&id="+$id);
									};
									</script>
									<a href="#divPlages"><input type="button" name="0" id="ajouterTest" value="Ajouter" onclick="RechargerPlages()"></a> 
								</td>
							</tr>
						</table>
						<script>
							// Création de la datatable contenant les tests dans le thème sélectionné
							tableTests = $("#tabTests").dataTable({
								sort : false,
								filter : false,
								lengthChange : false,
								paging: false,
								scrollY: 100,
								info : false,
								"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
								"ajax" : "./InscriptionCandidat?action=getTests&id="+$("#selectTheme option:selected")[0].value,
								"columns" : [
						    		 {
						    			 "data" : "id",
						    			 visible: false
						    		 },
						    		 {
						    			 "data" : "libelle"
						    		 }
						         ]								         
							});	
							
							SelectionTheme = function(){
								tableTests.fnReloadAjax("./InscriptionCandidat?action=getTests&id="+$("#selectTheme option:selected")[0].value);			
							};	
							
							$(document).ready(function() {
							    var table = $('#tabTests').DataTable(); 
							    $('#tabTests tbody').on( 'click', 'tr', function () {
							        if ( $(this).hasClass('selected') ) {
							            $(this).removeClass('selected');
							        }else {
							            table.$('tr.selected').removeClass('selected');
							            $(this).addClass('selected');
							        }
							        var element = document.getElementById("ajouterTest");
							        element.setAttribute("name", table.cell('.selected', 0).data());
								    });
							});						
						
						</script>						
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
		<div id="divPlages" class="modalDialog">
			<div>
				<a href="#ajoutTest" title="Close" class="close">X</a>
				<p> Sélectionnez une date de test </p>
				<table cellpadding="0" cellspacing="0" border="0" id="tabPlagesHoraires" class="display" style="width:400px;">
					<thead>
			            <tr>
			            	<th>ID Plage</th>
			                <th>Date de Début</th>
			                <th>Date de Fin</th>
			            </tr>
			        </thead>
					<tbody>
					</tbody>
				</table>
				<br></br>
				<input type="hidden" id="idPlage" name="idPlage"/>
				<input type="button" name="ajouterTest" value="Valider">
				<script>					
				// Création de la datatable contenant les plages horaires du test sélectionnés	
				var $idTest = document.getElementById("ajouterTest").name;
				tablePlagesHoraires = $("#tabPlagesHoraires").dataTable({
					sort : false,
					filter : false,
					lengthChange : false,
					paging: false,
					info : false,
					"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
					"ajax" : "./InscriptionCandidat?action=getPlages&id="+$idTest,
					"columns" : [
			    		{
			   				"data" : "id",
				   			visible : false
			   		 	},
		   				{
				   			"data" : "dateDebut"
				   		},
				   		{
				   			"data" : "dateFin"
				   		}
			         ]								         
				});	
				
				$(document).ready(function() {
				    var table = $('#tabPlagesHoraires').DataTable(); 
				    $('#tabPlagesHoraires tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        }else {
				            table.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				        }
				        var plageSelect = document.getElementById("idPlage");
				        plageSelect.setAttribute("value", table.cell('.selected', 0).data());
					    });
				});
				</script>				
			</div>
		</div>
	</body>
</html>