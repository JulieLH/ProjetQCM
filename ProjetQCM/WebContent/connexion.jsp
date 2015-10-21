<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
<link type="text/css" rel="stylesheet" href="CSS/style.css" />

</head>
<body>

	<div id="contenu">
		<form method="post" action="GestionConnexion">
			<legend>Connexion</legend>
			<p>Vous pouvez vous connecter via ce formulaire.</p>

			<table>
				<TR>
					<TD><label for="nom">Login <span class="requis">*</span></label></TD>
					<TD><input type="login" id="login" name="login" size="20" maxlength="60" /></TD>
				</TR>
				<TR>
					<TD><label for="motdepasse">Mot de passe <span class="requis">*</span></label></TD>
					<TD><input type="password" id="motdepasse" name="password" value="" size="20" maxlength="20" /></TD>
				</TR>

			</table>
			<input type="submit" value="Connexion" class="sansLabel" /> 
		</form>
	</div>
</body>
</html>