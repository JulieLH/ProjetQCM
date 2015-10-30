<%@ page import="fr.eni_ecole.jee.bean.*, java.util.*, java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">
var compte = 10;
function decompte()
{
        if(compte <= 1) {
        pluriel = "";
        } else {
        pluriel = "s";
        }
 
    document.getElementById("compt").innerHTML = compte + " minute" + pluriel;
 
        if(compte == 0 || compte < 0) {
        compte = 0;
 
        clearInterval(timer);

        }
 
    compte--;
}
var timer = setInterval('decompte()',60000);

</script>
</head>
<body onload="decompte();">
	<%@ include file="/menu.jsp"%>
	
	
	<div id="contenu">
		<p>Temps restant : <b><span id="compt"></span></b> </p>

		<jsp:include page="./AffichageTestReload.jsp"/>
		
	</div>
</body>
</html>