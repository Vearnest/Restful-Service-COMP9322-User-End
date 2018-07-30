package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;


public class DeleteMyApplicationCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		Applications myApplicationDetail = (Applications)session.getAttribute("myApplicationDetail");
		String applicationId = myApplicationDetail.get_appID();
		System.out.println(applicationId);
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
		Response res = client.delete();
		int returnCode = res.getStatus();
		if (returnCode / 100 == 2) {
			uri = "http://localhost:8080/FoundITRestfulService/Applications/candidate_applications?candidateID=" + email;
			client = WebClient.create(uri);
			client.accept(MediaType.APPLICATION_XML);
			ArrayList<ApplicationSearch> myApplications = (ArrayList<ApplicationSearch>) client.getCollection(ApplicationSearch.class);

			session.setAttribute("myApplications", myApplications);
			return "myApplicationsPage.jsp";
		}
		else {
			String e = "Deleting profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
