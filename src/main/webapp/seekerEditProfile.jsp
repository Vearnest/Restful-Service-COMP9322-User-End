<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String email = (String) session.getAttribute("email");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seeker profile edit</title>
<script language="javascript">
</script>
<%@ include file="seekerHeader.html"%>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".bodys p").not(":first").hide();
		$(".searchbox ul li").mouseover(function() {
			var index = $(this).index();
			if (index == 0) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 1) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 2) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 3) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 4) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
			}
			var index = $(this).index();
			$(".bodys p").eq(index).show().siblings().hide();
		});
	});
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a, img {
	border: 0;
}

/* searchbox */
ul#navlist{
	margin: 0 0 0 .1px;
	padding: 0;
	width: 300px;
	}
#navlist li{
	list-style-type: none;
	background-color: #000;
	color: #ffffff;
	border: .01em solid #ffffff;
	font-weight: 600;
	text-align: center;
	padding: .5em;
	margin-bottom: .01em;
	}
#navlist li a{
	color: #ffffff;
	text-decoration: none;
	display: block;
	}
#navlist li a:hover{
	padding: .1em;
	background-color: #faebd7;
	color: #191970;
	}

.bodys input {
	height: 30px;
	line-height: 30px;
	width: 600px;
	padding: 0 10px;
	float: left;
}

.bodys .one {
	border: #000 3px solid
}

.bodys .one1 {
	background-color: #000;
}

.bodys button {
	float: left;
	border: 0;
	height: 36px;
	width: 100px;
	color: #FFF;
	line-height: 36px;
	text-align: center;
	overflow: hidden;
}
</style>
</head>
<body>
	<center>
	<table>
		<form action='FoundITServlet' method='POST'>
			<td>
			<center><table>
				<tr>
					<td colspan="4"><h3>Personal Profile</h3></td>
				</tr>
				<tr align="left">
					<td width="10%"><font color=red size=2></font>Email:</td>
					<%
						if (email != null) {
					%>
					<td><%=email%></td>
					<%
						}
					%>
				</tr>
				<tr>
					<td width="10%">FirstName:</td>
					<td width="35%"><input type="text" name="firstName"
						style="width: 100%" placeholder="FirstName"></td>
				</tr>
				<tr>
					<td width="10%">LastName:</td>
					<td width="35%"><input type="text" name="lastName"
						style="width: 100%" placeholder="LastName"></td>
				</tr>
				<tr>
					<td width="10%">NickName:</td>
					<td width="35%"><input type="text" name="nickName"
						style="width: 100%" placeholder="NickName"></td>
				</tr>
				<tr>
					<td width="10%">Age:</td>
					<td width="35%"><input type="text" name="age"
						style="width: 100%" placeholder="Age"></td>
				</tr>
				<tr>
					<td width="10%">Address:</td>
					<td width="35%"><input type="text" name="address"
						style="width: 100%" placeholder="Address"></td>
				</tr>
				<tr>
					<td width="10%">ContactNumber:</td>
					<td width="35%"><input type="text" name="contactNumber"
						style="width: 100%" placeholder="ContactNumber"></td>
				</tr> 
				<tr>
					<td width="10%">CurrentPosition:</td>
					<td width="35%"><input type="text" name="currentPosition"
						style="width: 100%" placeholder="CurrentPosition"></td>
				</tr>
				<tr>
					<td width="10%">Education:</td>
					<td width="35%"><input type="text" name="education"
						style="width: 100%" placeholder="Education"></td>
				</tr>
				<tr>
					<td width="10%">ProfessionalSkill:</td>
					<td width="35%"><input type="text" name="professionalSkill"
						style="width: 100%" placeholder="ProfessionalSkill"></td>
				</tr>
				<tr>
					<td width="10%">Experience:</td>
					<td width="35%"><input type="text" name="experience"
						style="width: 100%" placeholder="Experience"></td>
				</tr>
			</table>
		</center>
		</td>
		</table>
		</center>
		<center><table>
		
			<input type='hidden' name="ID" value='<%=email%>'>
			<button style="width: 150px; height: 30px" name='action' value='seekerUpdateProfile'>Update Profile</button>
<!-- 		<input type='hidden' name="ID" herf="buyerMyProfile.jsp"> -->
			<button style="width: 150px; height: 30px" name="back"
								value="back" onclick="javascript:history.go(-1);" />Cancel</buton></td>
		
		</table></center>
		</td>
		</form>
		</tr>
		</table>
</body>
</html>