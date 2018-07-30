<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	JobPosting jobPosting = (JobPosting) session.getAttribute("jobPosting");
%>
<head>
<link href="CreativeButtons/css/component.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job detail</title>
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
		<td width="70%">
		<br />
		<br />
		<br /> <font size=16>Job details!</font> <br />
		<br />
		<table style="text-align: center;" border="0">
			<%
				if (jobPosting.getTitle() != null) {
			%>
			<tr>
				<td align="left"><label>Title</label></td>
				<td><%=jobPosting.getTitle()%></td>
			</tr>
			<%
				}
			%>
			<%
				if (jobPosting.getSalaryRate() != null) {
			%>
			<tr>
				<td align="left"><label>SalaryRate</label></td>
				<td><%=jobPosting.getSalaryRate()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (jobPosting.getPositionType() != null) {
			%>
			<tr>
				<td align="left"><label>PositionType</label></td>
				<td><%=jobPosting.getPositionType()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (jobPosting.getLocation() != null) {
			%>
			<tr>
				<td align="left"><label>Location</label></td>
				<td><%=jobPosting.getLocation()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (jobPosting.getJobDescription() != null) {
			%>
			<tr>
				<td align="left"><label>JobDescription</label></td>
				<td><%=jobPosting.getJobDescription()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (jobPosting.getRequirement() != null) {
			%>
			<tr>
				<td align="left"><label>Requirement</label></td>
				<td><%=jobPosting.getRequirement()%></td>
			</tr>
			<%
				}
			%>

			<%
				if (jobPosting.getStatus() != null) {
			%>
			<tr>
				<td align="left"><label>Status</label></td>
				<td><%=jobPosting.getStatus()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br/><br/>
		<table>
		<form action='FoundITServlet' method='POST'>

	<tr>
	<td>
		<button type="submit" name="action" value="showJobApplications">Show Applications</button>
	</td>
	<td>
		<button name='action' value='editJob'>
				<span>Edit The Job</span></button>
	</td>
	<td>
		<button name='action' value='deleteJob' >
				<span>Delete The Job</span></button>
	</td>
	<td>
		<button name='action' value='closeJob' >
				<span>Close The Job</span></button>
	</td>
	<td align="center">
		<input type="button" name="back" value="back" onclick="javascript:history.go(-1);" />
	</td>
	</tr>
	</table>
	</form>
	</center>
	<br/><br/><br/><br/>
</body>
</html>