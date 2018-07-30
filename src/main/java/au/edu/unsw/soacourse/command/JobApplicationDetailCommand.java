package au.edu.unsw.soacourse.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.io.IOException;
import java.util.ArrayList;

public class JobApplicationDetailCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String applicationId = request.getParameter("applicationId");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
		Applications jobApplicationDetail = client.get(Applications.class);
		session.setAttribute("jobApplicationDetail", jobApplicationDetail);
		System.out.println(jobApplicationDetail.getCapacity());
		System.out.println(jobApplicationDetail.getCoverLetter());
		
		String email = (String)session.getAttribute("email");
		uri = "http://localhost:8080/FoundITRestfulService/HiringTeam/HiringTeamSearch?companyID=" + email;
		client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<HiringTeam> availableReviewers = (ArrayList<HiringTeam>) client.getCollection(HiringTeam.class);

		session.setAttribute("availableReviewers", availableReviewers);
		return "jobApplicationDetail.jsp";

	}
}
