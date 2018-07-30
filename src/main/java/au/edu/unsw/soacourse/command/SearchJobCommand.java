package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.JobSearch;

public class SearchJobCommand extends Command {

	public SearchJobCommand() {
		// TODO Auto-generated constructor stub
	}
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String keyword = request.getParameter("keywordContent");
		String skills = request.getParameter("skillsContent");
		String status = request.getParameter("statusContent");
		String uri = "http://localhost:8080/FoundITRestfulService/JobSearch/Jobs?"
				+ "keyword=" + keyword
				+ "&skills=" + skills
				+ "&status=" + status;
		WebClient client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<JobSearch> results = (ArrayList<JobSearch>) client.getCollection(JobSearch.class);
		session.setAttribute("jobSearch", results);
		
		return "jobSearchResultPage.jsp";
	}
}
