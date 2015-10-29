<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout d'un test</title>
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/themes/default.css" rel="stylesheet" id="theme_base">
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/themes/default.date.css" rel="stylesheet" id="theme_date">
<link href="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/themes/default.time.css" rel="stylesheet" id="theme_time">
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/picker.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/legacy.js"></script>
<script src="<%=request.getContextPath()%>/js/pickadate.js-3.5.6/lib/picker.time.js"></script>
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
							<td><input type="button" id="ajouter" value="Ajouter"></td>
							<td><label>Début :</label></td>
							<td><input type='text' size='8' class='datepicker' id='dateDeb' /></td>
							<td><label>Heure :</label></td>
							<td><input type='text' size='4' class='timepicker' id='heureDeb' /></td>
						</tr>
						<tr>
							<td><img src="formateur/IMG/remove.png" alt="retirer"
								style="width: 20px; height: 20px;" /></td>
							<td><input type="button" value="Supprimer"></td>
							<td><label>Fin :</label></td>
							<td><input type='text' size='8' class='datepicker' id='dateFin' /></td>
							<script>
								$( '.datepicker' ).pickadate({
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
					<script type="text/javascript" charset="utf8"
						src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
				</div>
			</fieldset>
			<fieldset>
				<legend> Sections & Questions </legend>
			</fieldset>
			<input type="submit" value="Enregistrer"> <input
				type="button" name="cancel" value="Annuler">
		</form>
		<script>
		$(document).ready(function() {
			$("#ajouter").click(function(){
				var dateDebut = $("#dateDeb").val();
				var dateFin = $("#dateFin").val();
				var heureDeb = $("#heureDeb").val();
				var heureFin = $("#heureFin").val();
				
				$.getJSON( "GestionPlages", {"dateDebut" : dateDebut,"dateFin":dateFin,"heureDeb":heureDeb,"heureFin":heureFin,"action" : "createPlage" }).done( function(data){
					console.log(data.dateDeb);
					console.log(data.dateFin);
					console.log(data.heureDeb);
					console.log(data.heureFin);
				});
			
			});
			
		    $('#tabPlages').DataTable( {
		    	paging : false,
				searching : false,
				ordering : false,
				info : false,
		        "language": {
		            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json"
		        }
		    });
		});
		</script>
	</div>
</body>
</html>