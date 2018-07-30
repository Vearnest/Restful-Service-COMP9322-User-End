package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForwardCommand extends Command {

	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action.equals("toRegister"))
			return "registPage.jsp";
		else if (action.equals("toLogin"))
			return "loginPage.jsp";
		else if (action.equals("toWelcome"))
			return "welcomePage.jsp";
		else if (action.equals("toSeekerMain"))
			return "seekerMainPage.jsp";
		else if (action.equals("toReviewerMain"))
			return "reviewerMainPage.jsp";
		else if (action.equals("editSeekerProfile"))
			return "seekerEditProfile.jsp";
		else if (action.equals("editCompanyProfile"))
			return "companyEditProfile.jsp";
		else if (action.equals("toManagerMain"))
			return "managerMainPage.jsp";
		else if (action.equals("toSearchResult"))
			return "jobSearchResultPage.jsp";
		else if (action.equals("editJob"))
			return "editJobPage.jsp";
		else if (action.equals("editCoverLetter"))
			return "editCoverLetter.jsp";
		//No need to add logout forward
		return null;
	}

}
