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
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> Inscription d'un Candidat </h1>
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
						$(document).ready(function() 
						{
							var candidatsSelect = new Array();						
							tableCandidats = $('#tabCandidats').DataTable( 
							{
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
					    	});						
						    
							// Fonction de sélection
							// Lorsqu'on sélectionne un candidat, celui-ci est ajouté dans une liste
							// Lorsqu'on le déselectionne, il en est retiré
						    $('#tabCandidats tbody').on( 'click', 'tr', function () 
						    {
						    	var id = tableCandidats.cell($(this), 0).data()
						    	if ($(this).hasClass('selected')) 
						    	{					    		
						            $(this).removeClass('selected');
						            // Retrait
						            var index = candidatsSelect.indexOf(id);
						            if (index > -1) 
						            {
						            	candidatsSelect.splice(index, 1);
						            }
						        } 
						    	else 
						        {
						    		// Ajout
						            $(this).addClass('selected');
						            candidatsSelect.push(id);
						        }
						    });
						    
						    // Fonction d'enregistrement et de passage de paramètres JSON à la Servlet
						    function enregistrerInscriptions()
						    {
						    	var testsSelect = new Array();
						    	var plagesSelect = new Array();
						    	// Récupération du nombre de lignes dans la DataTable des tests à ajouter
						        var rows = $("#tabTestsSelect").dataTable().fnGetNodes();
						        for(var i=0; i<rows.length; i++)
						        {
						        	// Récupération des informations dans des tableaux JSON
						        	var lesTests = $("#tabTestsSelect").DataTable();
						        	var idTest = lesTests.cell(i, 0).data();
						        	var idPlage = lesTests.cell(i, 2).data();
						            testsSelect.push(idTest); 
						            plagesSelect.push(idPlage); 
						        }					        
						        
						    	$.ajax(
						    	{
						            url : "./InscriptionCandidat?action=addInscriptions",
						            type : "POST",
						            dataType : 'json',
						            data : {candidatsSelect:candidatsSelect, testsSelect:testsSelect, plagesSelect:plagesSelect}
						        });
					    	}
						    
						    $('#enregistrer').click( function () {
						    	enregistrerInscriptions();
						    } );
						});
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
									// Fonction de rechargement des plages en fonction du test sélectionné
									function RechargerPlages()
									{	
										var $id = document.getElementById("idTest").value;										
										tablePlagesHoraires.fnReloadAjax("./InscriptionCandidat?action=getPlages&id="+$id);
									}
									</script>									
									<a href="#choixPlage"><input type="button" id="ajouterTest" value="Ajouter" onclick="RechargerPlages()"></a> 
								</td>
							</tr>
						</table>
						<script>
							// Création de la datatable contenant les tests contenant le thème sélectionné
							tableTests = $("#tabTests").dataTable(
							{
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
							
							// Fonction de rechargement si le thème a été changé
							SelectionTheme = function()
							{
								tableTests.fnReloadAjax("./InscriptionCandidat?action=getTests&id="+$("#selectTheme option:selected")[0].value);			
							};	
							
							$(document).ready(function() 
							{
							    var table = $('#tabTests').DataTable(); 
							    var idTest = document.getElementById("idTest");
							    var libelleTest = document.getElementById("libelleTest");
							    
							    // Fonction de sélection
							    // Lorsqu'on selectionne un test, on stocke les données associées
							    $('#tabTests tbody').on( 'click', 'tr', function () 
							    {
							        if ( $(this).hasClass('selected') ) 
							        {
							            $(this).removeClass('selected');
							            idTest.setAttribute("value", "0");
							        }
							        else 
							        {
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
										// Création de la datatable contenant les tests ajoutés	
										tableTestsSelect = $("#tabTestsSelect").dataTable(
										{
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
										
										$(document).ready(function() 
										{
										    var table = $('#tabTestsSelect').DataTable(); 
										    // Fonction de sélection
										    $('#tabTestsSelect tbody').on( 'click', 'tr', function () 
										    {
										        if ( $(this).hasClass('selected') ) {
										            $(this).removeClass('selected');
										        }else {
										            table.$('tr.selected').removeClass('selected');
										            $(this).addClass('selected');
										        }
									    	});
										    
										    // Définition du bouton de retrait
										    $('#deleteButton').click( function () 
										    {
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
					$(document).ready(function() 
					{
						tableTestsSelect = $("#tabTestsSelect").DataTable();
							
						// Définition de la fonction OnClick du bouton de validation d'une plage
					    $('#validerAjoutTest').on( 'click', function () 
					    {
					    	// Récupération des valeurs
					    	var $idTestToAdd = document.getElementById("idTest").value;
					    	var $libelleTestToAdd = document.getElementById("libelleTest").value;
							var $idPlageToAdd = document.getElementById("idPlage").value;
							var $dateDebutPlageToAdd = document.getElementById("dateDebutPlage").value;
							var $dateFinPlageToAdd = document.getElementById("dateFinPlage").value;
							// On récupère les tests déjà ajoutés
							var rows = $("#tabTestsSelect").dataTable().fnGetNodes();
							var exist = false;
							var conflit = false;
							// On vérifier qu'il n'y a pas de conflits
					        for(var i=0; i<rows.length; i++)
					        {
					        	var lesTestsDejaAjoutes = $("#tabTestsSelect").DataTable();
					        	var idTest = lesTestsDejaAjoutes.cell(i, 0).data();
					        	var idPlage = lesTestsDejaAjoutes.cell(i, 2).data();
					        	var dateDebut = lesTestsDejaAjoutes.cell(i, 3).data();
					        	// Si le test a déjà été ajouté
					            if (idTest == $idTestToAdd && idPlage == $idPlageToAdd)
				            	{
				            		exist = true;
				            	}
					        	// Si un autre test avec la même date a été ajouté
					        	else if (exist == false && dateDebut == $dateDebutPlageToAdd)
				            	{
				            		conflit = true;
				            	}
					        }
						        
							// Si aucun conflit, on ajoute le test
					        if (exist == false && conflit == false)
				        	{
					        	// Ajout de la ligne dans la table
								tableTestsSelect.row.add( [
									$idTestToAdd, 
									$libelleTestToAdd,
									$idPlageToAdd,
									$dateDebutPlageToAdd,
									$dateFinPlageToAdd
						        ] ).draw(false);
				        	}
					        else
				        	{
					        	// Affichage de l'alerte en fonction du conflit rencontré
					        	if (exist == true)
				        		{
					        		alert("Ce test a déjà été ajouté !");
				        		}
					        	else if(conflit == true)
					        	{
					        		alert("Un test a déjà été ajouté à cette date-là !");
				        		}
				        	}
					    } );
					} );
				</script>
				<a href="#"><input type="button" id="validerAjoutTest" value="Valider"></a>
				<script>					
					// Création de la DataTable contenant les plages horaires ultérieures du test sélectionné	
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
					
					$(document).ready(function() 
					{
					    var table = $('#tabPlagesHoraires').DataTable(); 
					    // Fonction de sélection
					    $('#tabPlagesHoraires tbody').on( 'click', 'tr', function () {
					        if ( $(this).hasClass('selected') ) {
					            $(this).removeClass('selected');
					        }else {
					            table.$('tr.selected').removeClass('selected');
					            $(this).addClass('selected');
					        }
					        // Lorsqu'on selectionne une plage, on stocke les données associées
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