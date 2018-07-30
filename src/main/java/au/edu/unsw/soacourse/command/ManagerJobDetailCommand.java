package au.edu.unsw.soacourse.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.io.IOException;

public class ManagerJobDetailCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String jobId = request.getParameter("jobId");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/JobPosting/" + jobId).accept(MediaType.APPLICATION_XML);
		JobPosting jobPosting = client.get(JobPosting.class);
		session.setAttribute("jobPosting", jobPosting);
		return "managerJobDetail.jsp";


	}
}
