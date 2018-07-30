<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="au.edu.unsw.soacourse.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="CreativeButtons/css/component.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job cart</title>
<%@ include file="seekerHeader.html"%>
</head>
<body>

	<%
		ArrayList<JobSearch> jobsInCart = (ArrayList<JobSearch>) session.getAttribute("jobsInCart");
		int size = jobsInCart.size();
	%>
	<%
		if (size == 0) {
	%>
	<center>
		<br /> <br /> <br /> <font size=12>Job Cart is Empty!</font> <br />
		<br />
	</center>
	<%
		}
	%>
	<center>
		<form action='FoundITServlet' method='POST'>
			<table align="center" style="text-align: center;" width="80%"
				border="0">
				<%
				int number;
				for (int i = 0; i < jobsInCart.size(); i++) {
					JobSearch job = jobsInCart.get(i);
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
		<tr>
		<td>
		<input type='hidden' name='jobId' value='<%=job.get_jobID()%>'/>
		<button name='action' value='removeCart'><span>Remove</span></button>
		<td>
		
		</tr>
			<%
				}
			%>

		</table>
		<br /><br /><br />
		<table>
		<tr>
		<td>
			<button name='action' value='toSearchResult'><span>Back to Search Results</span></button>
		</td>
		<td>
			<input type="button" name="back" value="back" onclick="javascript:history.go(-1);" />
		</td>
		</tr>
		</table>
			
		</form>
	</center>

<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>