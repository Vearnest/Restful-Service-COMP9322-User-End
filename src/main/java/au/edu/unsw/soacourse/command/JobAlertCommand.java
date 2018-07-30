package au.edu.unsw.soacourse.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;

import java.io.IOException;
import java.util.ArrayList;

public class JobAlertCommand extends Command {
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String keyword = request.getParameter("keyword");
		String sort_by = request.getParameter("sort_by");
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService/JobAlerts/jobs?keyword=" + keyword + "&sort_by=" + sort_by + "&email=" + email;
		WebClient client = WebClient.create(uri);
		client.accept(MediaType.APPLICATION_XML);
		client.get();
//		System.out.println("jobalert");
		return "seekerMainPage.jsp";
	}
}
