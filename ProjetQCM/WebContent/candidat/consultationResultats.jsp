<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> R�f�rentiel </title>
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/style.css" type="text/css" />
		<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/CSS/dataTables.css" type="text/css" />
	</head>
	<body>
		<%@ include file="/menu.jsp"%>
		<div id="contenu">
			<h1> R�sultats du Test </h1>
			<br></br>			
			<div id="left">
				<fieldset>
					<legend> Questions & R�ponses </legend>

				</fieldset>
			</div>
			
			<div id="right">
				<fieldset>
					<legend> R�sultat G�n�ral </legend>

				</fieldset>
			</div>	
		</div>
	</body>
</html>