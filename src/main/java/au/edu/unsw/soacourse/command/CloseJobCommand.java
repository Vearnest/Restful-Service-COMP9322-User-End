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


public class CloseJobCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		JobPosting jobPosting = (JobPosting)session.getAttribute("jobPosting");
		String jobId = jobPosting.get_jobID();
		jobPosting.setStatus("close");
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
			uri = "http://localhost:8080/FoundITRestfulService";
			client = WebClient.create(uri);
			client.path("/JobPosting/" + jobId).accept(MediaType.APPLICATION_XML);
			jobPosting = client.get(JobPosting.class);
			session.setAttribute("jobPosting", jobPosting);
			return "managerJobDetail.jsp";
		}
		else {
			String e = "Closing profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
	}
}
