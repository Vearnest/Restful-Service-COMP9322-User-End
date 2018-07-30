package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.util.ArrayList;

public class UpdateCoverLetterCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		Applications myApplicationDetail = (Applications)session.getAttribute("myApplicationDetail");
		String applicationId = myApplicationDetail.get_appID();
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
		if (!request.getParameter("coverLetter").equals(""))
			myApplicationDetail.setCoverLetter(request.getParameter("coverLetter"));
		myApplicationDetail.setShortKey("app-candidate");
		myApplicationDetail.setSecurityKey("i-am-foundit");
		String res = client.put(myApplicationDetail, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (res.equals("SUCCESS")) {
			session.setAttribute("myApplicationDetail", myApplicationDetail);
			return "myApplicationDetail.jsp";
		}
		else {
			String e = "Updating cover letter failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
