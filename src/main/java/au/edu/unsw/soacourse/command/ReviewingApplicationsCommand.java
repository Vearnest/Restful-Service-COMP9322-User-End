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

public class ReviewingApplicationsCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/reviewer_applications/" + username).accept(MediaType.APPLICATION_XML);
		ArrayList<Applications> reviewingApplications = (ArrayList<Applications>) client.getCollection(Applications.class);
		session.setAttribute("reviewingApplications", reviewingApplications);
		return "reviewingApplicationsPage.jsp";
		
	}

}
