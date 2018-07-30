<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
Applications reviewingApplicationDetail = (Applications) session.getAttribute("reviewingApplicationDetail");
CandidateProfile candidateProfile = (CandidateProfile) session.getAttribute("candidateProfile");
%>
<head>
<link href="CreativeButtons/css/component.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reviewing Application detail</title>
<%@ include file="reviewerHeader.html"%>
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
.searchbox {
	width: 750px;
	height: 100px;
	margin: 0px auto 0 auto;
}

.searchbox ul {
	height: 35px;
	width: 600px;
	list-style: none;
	margin-left: 15px
}

.searchbox ul li {
	float: left
}

.searchbox ul li a {
	float: left;
	line-height: 35px;
	padding: 0 20px;
	text-decoration: none;
	color: #000;
	font-size: 14px;
	font-weight: bold;
}

.searchbox ul li .style1 {
	background-color: #729fcf;
	color: #fff
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
		
		<p>
	<p><hr></p>
		
		<table><tr>
		<br />
		<br />
		<br /> <font size=16>Application details!</font> <br />
		<br />
		<table style="text-align: center;" border="0">
			<%
				if (reviewingApplicationDetail.getCoverLetter() != null) {
			%>
			<tr>
				<td align="left"><label>CoverLetter</label></td>
				<td><%=reviewingApplicationDetail.getCoverLetter()%></td>
			</tr>
			<%
				}
			%>
			<%
				if (reviewingApplicationDetail.getStatus() != null) {
			%>
			<tr>
				<td align="left"><label>Status</label></td>
				<td><%=reviewingApplicationDetail.getStatus()%></td>
			</tr>
			<%
				}
			%>
		</table>
		</tr>
		<tr>
		<br />
		<br />
		<br /> <font size=16>Resume details!</font> <br />
		<br />
		<table style="text-align: center;" border="0">
			<%
						if (candidateProfile.getFirstName() != null) {
					%>
					<tr>
						<td align="center"><label>FirstName</label></td>
						<td><%=candidateProfile.getFirstName()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (candidateProfile.getLastName() != null) {
					%>
					<tr>
						<td align="center"><label>LastName</label></td>
						<td><%=candidateProfile.getLastName()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (candidateProfile.getNickName() != null) {
					%>
					<tr>
						<td align="center"><label>NickName</label></td>
						<td><%=candidateProfile.getNickName()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getAge() != null) {
					%>
					<tr>
						<td align="center"><label>Age</label></td>
						<td><%=candidateProfile.getAge()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getAddress() != null) {
					%>
					<tr>
						<td align="center"><label>Address</label></td>
						<td><%=candidateProfile.getAddress()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (candidateProfile.getEmail() != null) {
					%>
					<tr>
						<td align="center"><label>Email</label></td>
						<td><%=candidateProfile.getEmail()%></td>
					</tr>
					<%
						}
					%>
					
					<%
						if (candidateProfile.getContactNumber() != null) {
					%>
					<tr>
						<td align="center"><label>ContactNumber</label></td>
						<td><%=candidateProfile.getContactNumber()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getCurrentPosition() != null) {
					%>
					<tr>
						<td align="center"><label>CurrentPosition</label></td>
						<td><%=candidateProfile.getCurrentPosition()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getEducation() != null) {
					%>
					<tr>
						<td align="center"><label>Education</label></td>
						<td><%=candidateProfile.getEducation()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getProfessionalSkill() != null) {
					%>
					<tr>
						<td align="center"><label>ProfessionalSkill</label></td>
						<td><%=candidateProfile.getProfessionalSkill()%></td>
					</tr>
					<%
						}
					%>
					<%
						if (candidateProfile.getExperience() != null) {
					%>
					<tr>
						<td align="center"><label>Experience</label></td>
						<td><%=candidateProfile.getExperience()%></td>
					</tr>
					<%
						}
					%>
		</table>
		</tr>
		<br/><br/>
		<table>
		<form action='FoundITServlet' method='POST'>
		<table style="height: 200px">
				<tr>
					<td width="35%"><input type="text" name="comment"
					style="width: 100%; height: 100%"></td>
				</tr>
			</table>
	<tr>
	<td>
		<button name='action' value='reject'>
				<span>Reject</span></button>
	</td>
	<td>
		<button name='action' value='pass' >
				<span>Pass</span></button>
	</td>
	</tr>
	<tr>
	<td></td><td></td>
	<td align="center">
		<input type="button" name="back" value="back" onclick="javascript:history.go(-1);" />
	</td>
	</tr>
	
	</form>
	</table>
	</center>
	<br/><br/><br/><br/>
</body>
</html>