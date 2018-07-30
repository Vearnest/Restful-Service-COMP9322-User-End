<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	ArrayList<JobSearch> jobPostings = (ArrayList<JobSearch>) session.getAttribute("jobPostings");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Postings</title>
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
	<table align="center" style="text-align: center;" width="60%" border="0"><p>
	<form action="FoundITServlet" method="post">
		
			<table align="center" style="text-align: center;" width="60%" border="0">
				<tr>
					<td colspan="4"><h3>Job Postings</h3></td>
				</tr>
				<tr>
					<td width="10%">Title:</td>
					<td width="35%"><input type="text" name="title"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">SalaryRate:</td>
					<td width="35%"><input type="text" name="salaryRate"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">PositionType:</td>
					<td width="35%"><input type="text" name="positionType"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Location:</td>
					<td width="35%"><input type="text" name="location"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">JobDescription:</td>
					<td width="35%"><input type="text" name="jobDescription"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Requirement:</td>
					<td width="35%"><input type="text" name="requirement"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Status:</td>
					<td width="35%"><input type="text" name="status"
					style="width: 100%"></td>
				</tr>
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="postJob">Post The Job</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="reset">Reset</button>
					</td>
				</tr>
			</table>
	</form>
	</table>
		<p><hr></p>
	<p><center>
	<table align="center" style="text-align: center;" width="80%" border="0">
		<table align="center" style="text-align: center;" width="80%" border="0">
		<form action="FoundITServlet" method="post">
					<%
				int number;
				for (int i = 0; i < jobPostings.size(); i++) {
					JobSearch job = jobPostings.get(i);
					number = i + 1;
			%>
			<table align="center" style="text-align: center;" width="20%" border="0">
 			<tr>
				<td><%=number%></td>
				<td><%="Title: "%></td>
				<td><a href="FoundITServlet?action=managerJobDetail&jobId=<%=job.get_jobID()%>"><%=job.getTitle()%></a>
					
				</td>
			</tr>
			<tr>
				<td></td><td><%="Requirement: "%></td><td><%=job.getRequirement()%></td>
			</tr>
			<tr>
				<td></td><td><%="Status: "%></td><td><%=job.getStatus()%></td>
			</tr>
			<tr>
				<td></td><td>
				
				</td>
			</tr>
			</table>
			<%
				}
			%>
		</form>
		</table>
		
		</td>
		</tr>
		
		</table>
		</center>
</body>
</html>