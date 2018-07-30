package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;


public class CreateCompanyProfileCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CompanyProfile").accept(MediaType.APPLICATION_XML);
		CompanyProfile companyProfile = new CompanyProfile();
		if (!request.getParameter("companyName").equals(""))
			companyProfile.setCompanyName(request.getParameter("companyName"));
		if (!request.getParameter("ABN").equals(""))
			companyProfile.setABN(request.getParameter("ABN"));
		if (!request.getParameter("companyAddress").equals(""))
			companyProfile.setCompanyAddress(request.getParameter("companyAddress"));
		
		companyProfile.setCompanyEmail(email);
		if (!request.getParameter("contactNumber").equals(""))
			companyProfile.setContactNumber(request.getParameter("contactNumber"));
		if (!request.getParameter("introduction").equals(""))
			companyProfile.setIntroduction(request.getParameter("introduction"));
		if (!request.getParameter("mainPageURL").equals(""))
			companyProfile.setMainPageURL(request.getParameter("mainPageURL"));
		companyProfile.setShortKey("app-manager");
		companyProfile.setSecurityKey("i-am-foundit");
		CompanyProfile respCompanyProfile = client.post(companyProfile, CompanyProfile.class);
		if (respCompanyProfile.getSecurityKey() != null) {
			String e = respCompanyProfile.getSecurityKey();
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		session.setAttribute("companyProfile", respCompanyProfile);
		return "companyProfile.jsp";
		
	}
}
