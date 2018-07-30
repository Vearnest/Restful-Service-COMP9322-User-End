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


public class UpdateJobCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		JobPosting jobPosting = (JobPosting)session.getAttribute("jobPosting");
		String jobId = jobPosting.get_jobID();
		jobPosting.set_companyProfileID(email);
		if (!request.getParameter("title").equals(""))
			jobPosting.setTitle(request.getParameter("title"));
		if (!request.getParameter("salaryRate").equals(""))
			jobPosting.setSalaryRate(request.getParameter("salaryRate"));
		if (!request.getParameter("positionType").equals(""))
			jobPosting.setPositionType(request.getParameter("positionType"));
		if (!request.getParameter("location").equals(""))
			jobPosting.setLocation(request.getParameter("location"));
		if (!request.getParameter("jobDescription").equals(""))
			jobPosting.setJobDescription(request.getParameter("jobDescription"));
		if (!request.getParameter("requirement").equals(""))
			jobPosting.setRequirement(request.getParameter("requirement"));
		if (!request.getParameter("status").equals(""))
			jobPosting.setStatus(request.getParameter("status"));

		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/JobPosting/" + jobId).accept(MediaType.APPLICATION_XML);
		jobPosting.setShortKey("app-manager");
		jobPosting.setSecurityKey("i-am-foundit");
		String res = client.put(jobPosting, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (res.equals("SUCCESS")) {
			uri = "http://localhost:8080/FoundITRestfulService/JobPosting?companyEmail=" + email;
			client = WebClient.create(uri);
			client.accept(MediaType.APPLICATION_XML);
			ArrayList<JobSearch> jobPostings = (ArrayList<JobSearch>) client.getCollection(JobSearch.class);

			session.setAttribute("jobPostings", jobPostings);
			return "jobPostings.jsp";
		}
		else {
			String e = "Updating job failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
