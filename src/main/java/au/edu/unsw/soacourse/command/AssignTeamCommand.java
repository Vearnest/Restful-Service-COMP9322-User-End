package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.dao.*;

public class AssignTeamCommand extends Command {
	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		HiringTeam reviewer = new HiringTeam();
		reviewer.set_companyProfileID(email);
		reviewer.setUserName(request.getParameter("username"));
		reviewer.setPassword(request.getParameter("password"));
		reviewer.setSkills(request.getParameter("skills"));
		reviewer.setStatus("available");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/HiringTeam/").accept(MediaType.APPLICATION_XML);
		reviewer.setShortKey("app-manager");
		reviewer.setSecurityKey("i-am-foundit");
		HiringTeam respReviewer = client.post(reviewer, HiringTeam.class);
		if (respReviewer.getSecurityKey() != null) {
			String e = respReviewer.getSecurityKey();
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
		
		uri = "http://localhost:8080/FoundITRestfulService/HiringTeam/HiringTeamSearch?companyID=" + email;
		client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<HiringTeam> availableReviewers = (ArrayList<HiringTeam>) client.getCollection(HiringTeam.class);

		session.setAttribute("availableReviewers", availableReviewers);
		return "showAvailableReviewers.jsp";
	}
}
