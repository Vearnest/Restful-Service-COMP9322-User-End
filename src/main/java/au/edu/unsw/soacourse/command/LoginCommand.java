package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.util.*;
import au.edu.unsw.soacourse.dao.*;

public class LoginCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (request.getParameter("loginType").equals("reviewerLogin")) {
			String username = request.getParameter("email");
			String password = request.getParameter("password");
			String uri = "http://localhost:8080/FoundITRestfulService";
			WebClient client = WebClient.create(uri);
			client.path("/HiringTeam/" + username).accept(MediaType.APPLICATION_XML);

			HiringTeam reviewer = client.get(HiringTeam.class);
			if (reviewer.get_reviewerID() != null) {
				if (reviewer.getPassword().equals(password)) {
					session.setAttribute("username", username);
					return "reviewerMainPage.jsp";
				}
			}
		}
		
		if (request.getParameter("loginType").equals("seekerLogin")) {
			Seeker seeker = new Seeker();
			seeker.setEmail((String)request.getParameter("email"));
			seeker.setPassword((String)request.getParameter("password"));
			session.setAttribute("email", seeker.getEmail());
			session.setAttribute("shortKey", "app-candidate");
			LoginDAO checkDAO = new LoginDAO();
			if (checkDAO.isValidSeeker(seeker))
				return "seekerMainPage.jsp";
		}
		if (request.getParameter("loginType").equals("managerLogin")) {
			Manager manager = new Manager();
			manager.setEmail((String)request.getParameter("email"));
			manager.setPassword((String)request.getParameter("password"));
			session.setAttribute("email", manager.getEmail());
			session.setAttribute("shortKey", "app-manager");
			LoginDAO checkDAO = new LoginDAO();
			if (checkDAO.isValidManager(manager))
				return "managerMainPage.jsp";
		}
		
		String e = "Login Failed";
		session.setAttribute("errMsg", e);
		return "Error.jsp";
	}

}
