package au.edu.unsw.soacourse.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.io.IOException;
import java.util.ArrayList;

public class RejectCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Applications myApplicationDetail = (Applications) session.getAttribute("myApplicationDetail");
		myApplicationDetail.setStatus("reject");
		String applicationId = myApplicationDetail.get_appID();
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
		myApplicationDetail.setShortKey("app-candidate");
		myApplicationDetail.setSecurityKey("i-am-foundit");
		String res = client.put(myApplicationDetail, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		String email = (String)session.getAttribute("email");
		uri = "http://localhost:8080/FoundITRestfulService/Applications/candidate_applications?candidateID=" + email;
		client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		ArrayList<ApplicationSearch> myApplications = (ArrayList<ApplicationSearch>) client.getCollection(ApplicationSearch.class);

		session.setAttribute("myApplications", myApplications);
		return "myApplicationsPage.jsp";


	}
}
