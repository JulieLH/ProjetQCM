<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'un test</title>
<script type="text/javascript" charset="utf8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/themes/default.css" rel="stylesheet" id="theme_base">
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/themes/default.date.css" rel="stylesheet" id="theme_date">
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/themes/default.time.css" rel="stylesheet" id="theme_date">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/picker.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/picker.time.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/pickadate.js-3.5.6/lib/legacy.js"></script>
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<div id="contenu">
		<h1>Ajout/Modification d'un test</h1>
		<form id="Ajout" method="get" action="GestionTest">
			<fieldset style="text-align: center;">
				<legend> Informations Générales</legend>
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
			<fieldset style="text-align: center">
				<legend> Plage de Validité </legend>
				<div id="left">
					<table>
						<tr>
							<td><img src="formateur/IMG/add.png" alt="retirer"
								style="width: 20px; height: 20px;" /></td>
							<td><input type="button" id="ajouter" value="Ajouter"></td>
							<td><label>Début :</label></td>
							<td><input type='text' size='8' class='datepicker' id='dateDeb' /></td>
							<td><label>Heure :</label></td>
							<td><input type='text' size='4' class='timepicker' id='heureDeb' /></td>
						</tr>
						<tr>
							<td><img src="formateur/IMG/remove.png" alt="retirer"
								style="width: 20px; height: 20px;" /></td>
							<td><input type="button" value="Supprimer" id="supprimer"></td>
							<td><label>Fin :</label></td>
							<td><input type='text' size='8' class='datepicker' id='dateFin' /></td>
							<script>
								$('.datepicker').pickadate({
									onOpen: function() { $('pre').css('overflow', 'hidden') },
									onClose: function() { $('pre').css('overflow', '') },
									monthsFull: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
									weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
									today: 'aujourd\'hui',
									clear: 'effacer',
									close : 'fermer',
									format: 'dd/mm/yyyy',
  									formatSubmit: 'dd/mm/yyyy',
  									hiddenName: true
								})
							</script>
							<td><label>Heure :</label></td>
							<td><input type='text' size='4' class='timepicker' id='heureFin' /></td>
							<script>
								$('.timepicker').pickatime({
									clear: 'Effacer',
									format: 'HH:i',
									formatLabel: 'HH:i',
									formatSubmit: 'HH:i',
									min: [9,00],
									max: [17,30]
								})
							</script>
						</tr>
					</table>
				</div>
				
				<div>
					<table cellpadding="0" cellspacing="0" border="0" id="tabPlages" class="display">
								<thead>
						            <tr>
						            	<th></th>
						                <th>Début</th>
						                <th>Fin</th>
						            </tr>
						        </thead>
								<tbody>
								</tbody>
								</table>
					<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
				</div>
			</fieldset>
			<fieldset>
				<legend> Sections & Questions </legend>
				<div id="sections" style="text-align: center">
				<label>Saisir un nombre de section</label>
				</div>
			</fieldset>
			<input type="button" id="enregistrer" value="Enregistrer le test">
			<a href="<%=request.getContextPath()%>/GestionTest"><input type="button" id="cancel" name="cancel" value="Annuler"></a>
		</form>
		<script>
			$(document).ready(function() {
				var tablePlages = $('#tabPlages').DataTable();
				tablePlages = $("#tabPlages").dataTable({
					paging : false,
					searching : false,
					ordering : false,
					info : false,
					destroy: true,
					scrollY: 200,
					"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
					"ajax" : "./GestionPlages?action=getPlages",
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
					
				$("#ajouter").click(function(){
					var dateDebut = $("#dateDeb").val();
					var dateFin = $("#dateFin").val();
					var heureDeb = $("#heureDeb").val();
					var heureFin = $("#heureFin").val();
					
					$.getJSON( "GestionPlages", {"dateDebut" : dateDebut,"dateFin":dateFin,"heureDeb":heureDeb,"heureFin":heureFin,"action" : "createPlage" }).done( function(data){
					});
					$('#tabPlages').html("");
				
					tablePlages = $("#tabPlages").dataTable({
						paging : false,
						searching : false,
						ordering : false,
						info : false,
						destroy: true,
						scrollY: 200,
						"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
						"ajax" : "./GestionPlages?action=getPlages",
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
					$('#tabPlages tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        }
				        else {
				        	tablePlages.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				        }
				    } );
	 			}); 
				$("#supprimer").click(function(){
					  var table = $('#tabPlages').DataTable();
					  var id = table.cell('.selected', 0).data();
					  $.getJSON( "GestionPlages", {"idPlage" : id,"action" : "removePlage" }).done( function(data){

						});
					  $('#tabPlages').html("");
						
						tablePlages = $("#tabPlages").dataTable({
							paging : false,
							searching : false,
							ordering : false,
							info : false,
							destroy: true,
							scrollY: 200,
							"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
							"ajax" : "./GestionPlages?action=getPlages",
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
						$('#tabPlages tbody').on( 'click', 'tr', function () {
					        if ( $(this).hasClass('selected') ) {
					            $(this).removeClass('selected');
					        }
					        else {
					        	tablePlages.$('tr.selected').removeClass('selected');
					            $(this).addClass('selected');
					        }
					    } );
						
					});
				$(document).ready(function() {
					$('#tabPlages tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        }
				        else {
				        	tablePlages.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				        }
				    });
				});

				$("#nbSection_test").change(function(){
						$('#sections').html("");
						var nbSection = $("#nbSection_test").val();
						var i;
						for (i = 1; i <= nbSection; i++)
						{
							$('<select class="lib_theme">').appendTo('#sections');
							$('<label>',{
								text:"  Nombre de questions : "
							}).appendTo('#sections');
							$('<input class="nb_questions" type="text">').appendTo('#sections');
							$('<br/>',{}).appendTo('#sections');
						} 
						$.getJSON( "GestionPlages", {"action" : "getTheme" }).done( function(data){
							for(key in data.data)
							{	
								$('<option>').val(data.data[key].id).text(data.data[key].libelle).appendTo('.lib_theme');
							}
							});
					});
			});
		    $('#enregistrer').click( function () {
			    var idTest;	
		    	var nom = $("#lib_test").val();
				var duree = $("#duree_test").val();
				var nb_section = $("#nbSection_test").val();
				var seuil1 = $("#seuil1_test").val();
				var seuil2 = $("#seuil2_test").val();
				var table = $('#tabPlages').DataTable();
				var id = table.cell('.selected', 0).data();
				var listeTheme = [];
				var listeNbQuestion = [];
				
				$('.lib_theme').each(function(){
					listeTheme.push($(this).val());
				});
				$('.nb_questions').each(function(){	
					listeNbQuestion.push($(this).val());
				});
				 $.getJSON( "GestionPlages", {"nom" :nom,"duree":duree,"seuil1":seuil1,"seuil2":seuil2,"idPlage":id,"action" :"createTest"}).done(function(data){
					 alert(data.data);
				});
					
				for (i = 0; i < listeTheme.length; i++)
				{
					$.getJSON( "GestionPlages", {"idTest" : idTest,"idTheme":listeTheme[i],"nbQuestion":listeNbQuestion[i],"action" : "createSection" }).done( function(data){
					});
				}
				alert("test ajouté !");		
		    } );
			</script>
		</div>
	</body>
</html>