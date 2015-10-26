<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>
<%
	ArrayList<Test> listeTest = (ArrayList<Test>) request.getSession().getAttribute("listeTest");
	int index = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
</head>
<body>
	<%@ include file="/menu.jsp"%>
	<div id="contenu">
		<h1>Gestion des tests</h1>
		<div id="left">
			<h2>Listes des tests</h2>
			<fieldset>
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="tabTests">
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
							for (Test t : listeTest) {
						%>
						<tr>
							<td style="display: none;" id=<%=i%>><%=t.getId()%></td>
							<td id=<%=i%>><%=t.getLibelle()%></td>
						</tr>
						<%
							i++;
							}
						%>
					</tbody>
				</table>
				<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
				<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
				<script>
				$(document).ready(function() {
				    $('#tabTests').DataTable( {
				    	paging : false,
						searching : false,
						ordering : false,
						info : false,
				        "language": {
				            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json"
				        }
				    });
				});
				
				$(document).ready(function() {
				    var table = $('#tabTests').DataTable(); 
				    var lib_test = $('#lib_test'); 
				    $('#tabTests tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        }else {
				            table.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				        }
						var id = table.cell('.selected', 0).data();
						lib_test.text(id);

						$.get( "GestionTest", {"idTest" : id, "action" : "getTests" }).done( function(data){
							console.log(data);
							lib_test.text();
							})
							
					});
				    
				});
		
				 
				</script>
			</fieldset>
			<div id="btn_crud">
				<input type="button" id="btn_add_test" value="Ajouter" /> <input
					type="button" id="btn_update_test" value="Modifier" /> <input
					type="button" id="btn_remove_test" value="Supprimer" />
			</div>
		</div>

		<div id="right">
			<h2>Détail du test</h2>
			<fieldset style="padding: 20px">
				<h4 id="lib_test"></h4>
				<div style="text-align: center"></div>
				<hr width="95%" color="black">
				<label>Plages disponibles</label>
				<fieldset></fieldset><br/>
				<label id="test_duree">Durée : </label><br/><br/>
				<label id="test_nb_section">Nombre de sections :</label><br/><br/>
				<label id="test_seuil1">Seuil n°1 :</label><br/><br/>
				<label id="test_seuil2">Seuil n°2 :</label><br/><br/>
				<hr width="95%" color="black">
			</fieldset>

		</div>

	</div>
</body>
</html>