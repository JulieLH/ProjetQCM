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
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="tabCandidats" style="text-align:center;">
					    <thead>
					        <tr>
					        	<th>ID</th>
					            <th>Nom</th>
					            <th>Prénom</th>
					        </tr>
					    </thead>
					    <tbody>
					    	<% for (Utilisateur user : lesCandidats) { %>
					        <tr>	  
					            <td> <%=user.getId()%> </td> 
								<td> <%=user.getNom().toUpperCase()%> </td>
								<td> <%=user.getPrenom()%> </td>
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
						var candidatsSelect = new Array();
						
						tableCandidats = $('#tabCandidats').DataTable( {
					    	info: false,
					    	lengthChange : false,
					        "language": { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
					        "columnDefs": [
				               {
				                   "targets": [0],
				                   "visible": false,
				                   "searchable": false
				               }
				           ]
					    } );
					    
					    $('#tabCandidats tbody').on( 'click', 'tr', function () {
					    	var id = tableCandidats.cell($(this), 0).data()
					    	if ($(this).hasClass('selected')) 
					    	{
					            $(this).removeClass('selected');
					            var index = candidatsSelect.indexOf(id);
					            if (index > -1) 
					            {
					            	candidatsSelect.splice(index, 1);
					            }
					        } 
					    	else 
					        {
					            $(this).addClass('selected');
					            candidatsSelect.push(id);
					        }
					    } );
					    
					    function enregistrerInscriptions()
					    {
					    	var testsSelect = new Array();
					    	var plagesSelect = new Array();
					        var rows = $("#tabTestsSelect").dataTable().fnGetNodes();
					        for(var i=0; i<rows.length; i++)
					        {
					        	var lesTests = $("#tabTestsSelect").dataTable();
					        	var idTest = lesTests.cell(i, 0).data();
					        	var idPlage = lesTests.cell(i, 2).data();
					            testsSelect.push(idTest); 
					            plagesSelect.push(idPlage); 
					        }					        
					        
					    	$.ajax({
					            url : "./InscriptionCandidat?action=addInscriptions",
					            type : "POST",
					            dataType : 'json',
					            data : {candidatsSelect:candidatsSelect, testsSelect:testsSelect, plagesSelect:plagesSelect}
					        });
					    }
					    
					    $('#enregistrer').click( function () {
					    	enregistrerInscriptions();
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
									<table cellpadding="0" cellspacing="0" border="0" id="tabTests" class="display" style="width:550px;">
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
										var $id = document.getElementById("idTest").value;										
										tablePlagesHoraires.fnReloadAjax("./InscriptionCandidat?action=getPlages&id="+$id);
									};
									</script>									
									<a href="#choixPlage"><input type="button" id="ajouterTest" value="Ajouter" onclick="RechargerPlages()"></a> 
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
							    var idTest = document.getElementById("idTest");
							    var libelleTest = document.getElementById("libelleTest");
							    
							    $('#tabTests tbody').on( 'click', 'tr', function () {
							        if ( $(this).hasClass('selected') ) {
							            $(this).removeClass('selected');
							            idTest.setAttribute("value", "0");
							        }else {
							            table.$('tr.selected').removeClass('selected');
							            $(this).addClass('selected');
							            idTest.setAttribute("value", table.cell('.selected', 0).data());
							            libelleTest.setAttribute("value", table.cell('.selected', 1).data());
							        }							        
							    });
							});						
						
						</script>						
				</fieldset></br>
				<fieldset> 
					<legend> Tests sélectionnés </legend>
						<table>
							<tr>
								<td>
									<table cellpadding="0" cellspacing="0" border="0" id="tabTestsSelect" class="display" style="text-align: center; width:550px;">
										<thead>
								            <tr>
								                <th>ID Test</th>
								                <th>Test</th>
								                <th>ID Plage</th>
								                <th>Date de Début</th>
								                <th>Date de Fin</th>
								            </tr>
								        </thead>
										<tbody>
										</tbody>
									</table>
									<script>					
										// Création de la datatable contenant les plages horaires du test sélectionnés	
										tableTestsSelect = $("#tabTestsSelect").dataTable({
											sort : false,
											filter : false,
											lengthChange : false,
											paging: false,
											info : false,
											"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
											"language" : { "emptyTable": "Aucun test n'a encore été ajouté" },
											"columnDefs": [
								               {
								                   "targets": [0],
								                   "visible": false,
								                   "searchable": false
								               },
								               {
								                   "targets": [2],
								                   "visible": false,
								                   "searchable": false
								               }
								           ]
										});	
										
										$(document).ready(function() {
										    var table = $('#tabTestsSelect').DataTable(); 
										    $('#tabTestsSelect tbody').on( 'click', 'tr', function () {
										        if ( $(this).hasClass('selected') ) {
										            $(this).removeClass('selected');
										        }else {
										            table.$('tr.selected').removeClass('selected');
										            $(this).addClass('selected');
										        }
											    });
										    
										    $('#deleteButton').click( function () {
										        table.row('.selected').remove().draw( false );
										    } );
										});
									</script>
								</td>
								<td>
									<img src="formateur/IMG/remove.png" alt="retirer" style="width:20px; height:20px;"/>
								</td>
								<td>
									<input type="button" id="deleteButton" value="Retirer">
								</td>
							</tr>
						</table>
				</fieldset></br>
				<input type="button" id="enregistrer" value="Enregistrer l'Inscription">
				<a href="./Accueil.jsp"><input type="button" name="cancel" value="Annuler l'Inscription"></a>			
			</form>
		</div>
		<div id="choixPlage" class="modalDialog">			
				<div>
					<a href="#" title="Close" class="close">X</a>
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
					<input type="hidden" id="idTest" name="idTest" value="0"/>
					<input type="hidden" id="idPlage" name="idPlage"/>
					<input type="hidden" id="libelleTest" name="libelleTest"/>
					<input type="hidden" id="dateDebutPlage" name="dateDebutPlage"/>
					<input type="hidden" id="dateFinPlage" name="dateFinPlage"/>
					<script>						
						$(document).ready(function() {
							tableTestsSelect = $("#tabTestsSelect").DataTable();
						 
						    $('#validerAjoutTest').on( 'click', function () {
						    	var $idTest = document.getElementById("idTest").value;
						    	var $libelleTest = document.getElementById("libelleTest").value;
								var $idPlage = document.getElementById("idPlage").value;
								var $dateDebutPlage = document.getElementById("dateDebutPlage").value;
								var $dateFinPlage = document.getElementById("dateFinPlage").value;
								tableTestsSelect.row.add( [
									$idTest, 
									$libelleTest,
									$idPlage,
									$dateDebutPlage,
									$dateFinPlage
						        ] ).draw(false);
						    } );
						} );
					</script>
					<a href="#"><input type="button" id="validerAjoutTest" value="Valider"></a>
					<script>					
					// Création de la datatable contenant les plages horaires du test sélectionnés	
					var $idTest = document.getElementById("idTest").value;
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
					        var dateDebutPlageSelect = document.getElementById("dateDebutPlage");
					        var dateFinPlageSelect = document.getElementById("dateFinPlage");
					        plageSelect.setAttribute("value", table.cell('.selected', 0).data());
					        dateDebutPlageSelect.setAttribute("value", table.cell('.selected', 1).data());
					        dateFinPlageSelect.setAttribute("value", table.cell('.selected', 2).data());
						    });
					});
					</script>				
			</div>
		</div>
	</body>
</html>