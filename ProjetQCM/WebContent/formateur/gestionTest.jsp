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
<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
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
							<th style="display: none;"></th>
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
				<input type="hidden" value="0" id="idTest">
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
				 $(window).load(function(){
					 var id;
					$('tr').trigger('click'); 
		        });
				
				$(document).ready(function() {
				    var table = $('#tabTests').DataTable(); 
				    var lib_test = $('#lib_test');
				    var test_duree = $('#test_duree');
				    var nb_section = $('#nb_section');  
					var seuil1 = $('#seuil1');
					var	seuil2 = $('#seuil2');
					var info_section = $('#info_section');
					
				    $('#tabTests tbody').on( 'click', 'tr', function () 
				    {
				    	var idTest = document.getElementById("idTest");
				    	
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
				        }
				        id = table.cell('.selected', 0).data();
						$.getJSON( "GestionTest", {"idTest" : id, "action" : "getTests" }).done( function(data){
							lib_test.text(" "+data.data.libelle);
							test_duree.text(" "+data.data.duree);
							seuil1.text(" "+data.data.seuilMin);
							seuil2.text(" "+data.data.seuilMax);
							
						}),
					
						$.getJSON( "GestionTest", {"idTest" : id, "action" : "getCount" }).done( function(data){
							nb_section.text(" "+data.data);
						}),
						$.ajaxSetup({
							async: false
							}),
						$.getJSON( "GestionTest", {"idTest" : id, "action" : "getSection"}).done( function(data){
							$('#info_section').html("");
							for(key in data.data){
								var numSection = data.data[key].numSection;
								var nb_question = data.data[key].nbQuestions;
								var libTheme;
								$.getJSON( "GestionTest", {"idTheme" :data.data[key].idTheme, "action" : "getTheme" }).done( function(data){
									libTheme = data.theme.libelle;
									console.log("interieur :" + libTheme);
								}),
								console.log("exterieur : "+libTheme);
								$('<label>',{
									id:"num_section",
									text:"Section "+numSection+" : "+libTheme
								}).appendTo('#info_section');
								$('<br/>',{}).appendTo('#info_section');
								$('<label>',{
									id:"nb_question",
									text:"Nombre de questions : "+nb_question
									}).appendTo('#info_section');
								$('<br/>',{}).appendTo('#info_section');
								$('<br/>',{}).appendTo('#info_section');
								$.getJSON( "GestionTest", {"idTheme" :data.data[key].idTheme, "action" : "getTheme" }).done( function(data){
									
										})
							}
						}),
						tablePlages = $("#tabPlages").dataTable({
							paging : false,
							searching : false,
							ordering : false,
							info : false,
							destroy: true,
							"language" : { "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/French.json" },
							"ajax" : "./GestionTest?action=getPlagesTest&idTest="+id,
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
						})					
					});
				});
				</script>
			</fieldset>
			<br>
			<div id="btn_crud" style="text-align: center;">
				<a href="<%=request.getContextPath()%>/GestionPlages"><input type="button" id="btn_add_test" value="Ajouter" /></a> 
				<input type="button" id="deleteButton" value="Supprimer" />
			</div>
		</div>
		<div id="right">
			<h2>D�tail du test</h2>
			<fieldset style="padding: 20px">
				<h4 id="lib_test"></h4>
				<div style="text-align: center"></div>
				<hr width="95%" color="black">
				<label>Plages disponibles</label>
				
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="tabPlages">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<br/>
				<label>Dur�e : </label><label id="test_duree"></label><br/><br/>
				<label>Nombre de sections :</label><label id="nb_section"></label><br/><br/>
				<label>Seuil n�1 :</label><label id="seuil1"></label><br/><br/>
				<label>Seuil n�2 :</label><label id="seuil2"></label><br/><br/>
				<hr width="100%" color="black">
				<br/>
				<p id="info_section">
				</p>
				
			</fieldset>

		</div>

	</div>
</body>
</html>