package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;


public class DeleteSeekerProfileCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CandidateProfile/" + email).accept(MediaType.APPLICATION_XML);
		Response res = client.delete();
		int returnCode = res.getStatus();
		if (returnCode / 100 == 2)
			return "seekerMainPage.jsp";
		else {
			String e = "Deleting profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
