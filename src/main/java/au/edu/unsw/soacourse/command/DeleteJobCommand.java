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


public class DeleteJobCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		JobPosting jobPosting = (JobPosting)session.getAttribute("jobPosting");
		String jobId = jobPosting.get_jobID();
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/JobPosting/" + jobId).accept(MediaType.APPLICATION_XML);
		Response res = client.delete();
		int returnCode = res.getStatus();
		if (returnCode / 100 == 2) {
			uri = "http://localhost:8080/FoundITRestfulService/JobPosting?companyEmail=" + email;
			client = WebClient.create(uri);
			client.accept(MediaType.APPLICATION_XML);
			ArrayList<JobSearch> jobPostings = (ArrayList<JobSearch>) client.getCollection(JobSearch.class);

			session.setAttribute("jobPostings", jobPostings);
			return "jobPostings.jsp";
		}
		else {
			String e = "Deleting profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
	}
}
