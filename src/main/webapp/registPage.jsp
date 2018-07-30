<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="Header.html"%>
<title>Registration</title>
</head>
<body>
	<center><h1>Registration</h1><hr></center>
	<form action="FoundITServlet" method="post">
		<center>
			<table>
				<tr>
					<td	colspan="2"><font size=3; color=red>* means this blank must be filled.</font></td>
				</tr>
				<tr>
					<td colspan="4"><h3>Personal Informations</h3></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Identity:</td>
					<td width="35%"><select name="identity" style="width: 100%">
						<option value="seekerRegist">Seeker</option>
						<option value="managerRegist">Manager</option>
				</select></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Email:</td>
					<td width="35%"><input type="text" name="email"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Password:</td>
					<td width="35%"><input type="password" name="password"
						style="width: 100%"></td>
				</tr>
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="registSubmit">Submit</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="registReset">Reset</button>
					</td>
					<td width="45%" colspan="1.5">
					<input type="button" name="action" value="Back" onclick="window.location.href='welcomePage.jsp';"/>
					</td>
				</tr>
			</table>
		</center>
	</form>
	</body>
</html>