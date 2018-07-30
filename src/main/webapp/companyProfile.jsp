<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
CompanyProfile companyProfile = (CompanyProfile)session.getAttribute("companyProfile");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Company Profile</title>
<%@ include file="managerHeader.html"%>
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
		<tr>
			<td>
				<table align="center" style="text-align: center;" width="80%" border="0">
					
					<%
						if (companyProfile.getCompanyName() != null) {
					%>
					<tr>
						<td align="center"><label>CompanyName</label></td>
						<td><%=companyProfile.getCompanyName()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (companyProfile.getABN() != null) {
					%>
					<tr>
						<td align="center"><label>ABN</label></td>
						<td><%=companyProfile.getABN()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (companyProfile.getCompanyAddress() != null) {
					%>
					<tr>
						<td align="center"><label>CompanyAddress</label></td>
						<td><%=companyProfile.getCompanyAddress()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (companyProfile.getCompanyEmail() != null) {
					%>
					<tr>
						<td align="center"><label>CompanyEmail</label></td>
						<td><%=companyProfile.getCompanyEmail()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (companyProfile.getContactNumber() != null) {
					%>
					<tr>
						<td align="center"><label>ContactNumber</label></td>
						<td><%=companyProfile.getContactNumber()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (companyProfile.getIntroduction() != null) {
					%>
					<tr>
						<td align="center"><label>Introduction</label></td>
						<td><%=companyProfile.getIntroduction()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (companyProfile.getMainPageURL() != null) {
					%>
					<tr>
						<td align="center"><label>MainPageURL</label></td>
						<td><%=companyProfile.getMainPageURL()%></td>
					</tr>
					<%
						}
					%>
		</table>
		</center>
		<center><table>
		<form action='FoundITServlet' method='POST'>
			<button style="width: 150px; height: 30px" name='action' value='editCompanyProfile'>Edit Company Profile</button>
		</form>
<!-- 		<button style="width: 150px; height: 30px" name="back" -->
<!-- 			value="back" onclick="javascript:history.go(-1);" />back</button> -->
		</table></center>
		</td>
		</tr>
		</table>
</body>
</html>