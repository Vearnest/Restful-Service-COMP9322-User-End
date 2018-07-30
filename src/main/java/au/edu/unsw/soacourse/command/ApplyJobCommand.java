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

public class ApplyJobCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		JobPosting jobPosting = (JobPosting) session.getAttribute("jobPosting");
		String jobId = jobPosting.get_jobID();
		Applications application = new Applications();
		application.set_candidateProfileID(email);
		application.set_jobID(jobId);
		application.setStatus("created");
		session.setAttribute("application", application);
		return "inputCoverLetter.jsp";
	}

}
