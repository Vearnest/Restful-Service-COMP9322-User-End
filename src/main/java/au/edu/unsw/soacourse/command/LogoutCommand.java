package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {

//	public LogOffCommand() {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		HttpSession session = request.getSession();
		session.setAttribute("email", null);
		session.setAttribute("username", null);
		session.setAttribute("candidateProfile", null);
		session.setAttribute("companyProfile", null);
		return "welcomePage.jsp";

	}

}
