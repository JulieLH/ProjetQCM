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
					            <th>Pr�nom</th>
					            <th>S�lection</th>
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
						<label> Tri par Th�me : </label>
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
									<input type="button" name="ajouterTest" id="ajouterTest" value="Ajouter" onclick="AfficherAjouterTest();"> 
								</td>
							</tr>
						</table>
						<script>
							// Cr�ation de la datatable contenant les tests dans le th�me s�lectionn�
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
							
							$(document).ready(function() {
							    var table = $('#tabTests').DataTable(); 
							    $('#tabTests tbody').on( 'click', 'tr', function () {
							        if ( $(this).hasClass('selected') ) {
							            $(this).removeClass('selected');
							        }else {
							            table.$('tr.selected').removeClass('selected');
							            $(this).addClass('selected');
							        }
							        alert(table.cell('.selected', 0).data());
								    });
							});
							
							/**
							 * Gestion du changement du th�me
							 */
							SelectionTheme = function(){
								tableTests.fnReloadAjax("./InscriptionCandidat?action=getTests&id="+$("#selectTheme option:selected")[0].value);			
							}
							
							dialogAjoutTest = $("#ajouterTest").dialog({
								autoOpen: false,
						        height: 230,
						        resizable : false,
						        width: 350,
						        modal: true,
						        position : {
						        	my: "left top",
						        	at: "left bottom",
						        	of: $("#ajouterTest") 
						    	},
						    	buttons : {
						    		"Valider" : function(){
						    			$("#formAjoutTheme").submit();
						    		},
						    		"Annuler" : function(){
						    			$("#formAjoutTheme")[0].reset();
						    		}
						        },
						        close: function() {
						          $("#formAjoutCandidatToTheme")[0].reset();
						        }
						    });
							
							AfficherAjouterTest = function(){								
								if(dialogAjoutTest.dialog( "isOpen" ))
									dialogAjoutTest.dialog( "close" );
								else
									dialogAjoutTest.dialog( "open" );
							};
						</script>						
				</fieldset></br>
				<fieldset> 
					<legend> Tests s�lectionn�s </legend>
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