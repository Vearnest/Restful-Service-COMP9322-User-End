<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	ArrayList<JobSearch> results = (ArrayList<JobSearch>) session.getAttribute("jobSearch");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Result</title>
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
	<p>
	<form action="FoundITServlet" method="post">
		<center>
			<table>
				<tr>
					<td colspan="4"><h3>Search Jobs</h3></td>
				</tr>
				<tr>
					<td width="10%">Keyword:</td>
					<td width="35%"><input type="text" name="keywordContent"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Skills:</td>
					<td width="35%"><input type="text" name="skillsContent"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Status:</td>
					<td width="35%"><input type="text" name="statusContent"
					style="width: 100%"></td>
				</tr>
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="searchJob">Search</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="reset">Reset</button>
					</td>
				</tr>
			</table>
		</center>
	</form>
	<p><hr></p>
	<p><center>
		<table align="center" style="text-align: center;" width="70%"
			border="0">
			<%
				int number;
				for (int i = 0; i < results.size(); i++) {
					JobSearch job = results.get(i);
					number = i + 1;
			%>
			<table align="center" style="text-align: center;" width="20%" border="0">
 			<tr>
				<td><%=number%></td>
				<td><%="Title: "%></td>
				<td><a href="FoundITServlet?action=jobDetail&jobId=<%=job.get_jobID()%>"><%=job.getTitle()%></a></td>
			</tr>
			<tr>
				<td></td><td><%="Requirement: "%></td><td><%=job.getRequirement()%></td>
			</tr>
			<tr>
				<td></td><td><%="Status: "%></td><td><%=job.getStatus()%></td>
			</tr>
			</table>
			<%
				}
			%>
			
		</table></center>
		<br/><br/>
		
<br/><br/><br/><br/><br/><br/>
</body>
</html>