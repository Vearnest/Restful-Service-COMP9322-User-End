package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.dao.*;

import java.util.ArrayList;

public class SendApplicationCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Applications application = (Applications)session.getAttribute("application");
		application.setCoverLetter(request.getParameter("coverLetter"));
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications").accept(MediaType.APPLICATION_XML);
		application.setShortKey("app-candidate");
		application.setSecurityKey("i-am-foundit");
		Applications respApplication = client.post(application, Applications.class);
		if (respApplication.getSecurityKey() != null) {
			String e = respApplication.getSecurityKey();
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		session.setAttribute("application", respApplication);
		String jobId = respApplication.get_jobID();
		JobCartDAO jobCartDAO = new JobCartDAO();
		jobCartDAO.DeleteJob(jobId);
		
		String email = (String)session.getAttribute("email");
		uri = "http://localhost:8080/FoundITRestfulService/Applications/candidate_applications?candidateID=" + email;
		client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<ApplicationSearch> myApplications = (ArrayList<ApplicationSearch>) client.getCollection(ApplicationSearch.class);
		session.setAttribute("myApplications", myApplications);
		return "myApplicationsPage.jsp";
	}

}
