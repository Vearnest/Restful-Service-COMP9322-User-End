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

public class SeekerProfileCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CandidateProfile/" + email).accept(MediaType.APPLICATION_XML);
		CandidateProfile candidateProfile = client.get(CandidateProfile.class);
		if (candidateProfile.get_profileID() != null) {
			session.setAttribute("candidateProfile", candidateProfile);
			return "seekerMyProfile.jsp";
		}
		else
			return "seekerCreateProfile.jsp";
		
	}

}
