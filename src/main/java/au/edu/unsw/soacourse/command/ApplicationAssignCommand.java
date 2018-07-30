package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.util.ArrayList;

public class ApplicationAssignCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String reviewerUsername = (String) request.getParameter("reviewerUsername");
		Applications jobApplicationDetail = (Applications) session.getAttribute("jobApplicationDetail");
		String applicationId = jobApplicationDetail.get_appID();
		Applications assignApplication = new Applications();
		assignApplication.set_appID(applicationId);
		if (jobApplicationDetail.getCapacity().equals("0"))
			assignApplication.setFirst_ReviewerUserName(reviewerUsername);
		if (jobApplicationDetail.getCapacity().equals("1"))
			assignApplication.setSecond_ReviewerUserName(reviewerUsername);
		String uri = "http://localhost:8080/FoundITRestfulService/";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/updateReviewer").accept(MediaType.APPLICATION_XML);
		assignApplication.setShortKey("app-manager");
		assignApplication.setSecurityKey("i-am-foundit");
		String res = client.put(assignApplication, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
		if (res.equals("SUCCESS")) {
			uri = "http://localhost:8080/FoundITRestfulService";
			client = WebClient.create(uri);
			client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
			jobApplicationDetail = client.get(Applications.class);
			session.setAttribute("jobApplicationDetail", jobApplicationDetail);
			
			uri = "http://localhost:8080/FoundITRestfulService/HiringTeam/HiringTeamSearch?companyID=" + email;
			client = WebClient.create(uri);
			client.accept(MediaType.APPLICATION_XML);
			ArrayList<HiringTeam> availableReviewers = (ArrayList<HiringTeam>) client.getCollection(HiringTeam.class);

			session.setAttribute("availableReviewers", availableReviewers);

			return "jobApplicationDetail.jsp";
		}
		else {
			session.setAttribute("errMsg", res);
			return "Error.jsp";
		}
	}

}
