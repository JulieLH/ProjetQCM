<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
				<table cellpadding="0" cellspacing="0" border="0" class="display"
					id="tabTest">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Test t : listeTest) {
						%>
						<tr>
							<td id=<%=t.getId()%>><%=t.getId()+"_"+t.getLibelle()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
				<script type="text/javascript" charset="utf8"
					src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
				<script type="text/javascript" charset="utf8"
					src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
				<script>
					$(document).ready(function() {
					    $('#tabTest').DataTable( {
					    	paging: false,
					    	searching: false,
					    	ordering: false,
					    	info: false,
					    	language: {
					            select: {
					                cells: "%d cells selected"
					            }
					        },
					        select: true,
					        "language": {
					            "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json"
						         
					        }
					    } );
					} );

					$(document).ready(function() {
					    var table = $('#tabTest').DataTable(); 
					    $('#tabTest tbody').on( 'click', 'td', function () {
					        if ( $(this).hasClass('selected') ) {
					            $(this).removeClass('selected');
					        }else {
					            table.$('td.selected').removeClass('selected');
					            $(this).addClass('selected');
					        }
					        alert(table.cell('#'+table.cell(this).index().row).data());
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
				<div style="text-align: center">
					<%@ include file="/formateur/detailTest.jspf"%>
				</div>
				<hr width="95%" color="black">
				<label>Plages disponibles</label>
				<fieldset></fieldset>
			</fieldset>

		</div>

	</div>
</body>
</html>