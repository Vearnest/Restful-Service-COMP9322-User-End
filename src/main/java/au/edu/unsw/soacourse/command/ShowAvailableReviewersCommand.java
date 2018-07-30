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

public class ShowAvailableReviewersCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService/HiringTeam/HiringTeamSearch?companyID=" + email;
		WebClient client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<HiringTeam> availableReviewers = (ArrayList<HiringTeam>) client.getCollection(HiringTeam.class);

		session.setAttribute("availableReviewers", availableReviewers);
		return "showAvailableReviewers.jsp";
		
	}

}
