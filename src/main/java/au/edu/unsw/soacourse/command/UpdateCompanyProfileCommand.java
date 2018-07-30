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

public class UpdateCompanyProfileCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CompanyProfile/" + email).accept(MediaType.APPLICATION_XML);
		CompanyProfile updatedCompanyProfile = (CompanyProfile)session.getAttribute("companyProfile");
		CompanyProfile originalCompanyProfile = (CompanyProfile)session.getAttribute("companyProfile");
		if (!request.getParameter("companyName").equals(""))
			updatedCompanyProfile.setCompanyName(request.getParameter("companyName"));
		if (!request.getParameter("ABN").equals(""))
			updatedCompanyProfile.setABN(request.getParameter("ABN"));
		if (!request.getParameter("companyAddress").equals(""))
			updatedCompanyProfile.setCompanyAddress(request.getParameter("companyAddress"));
		
		updatedCompanyProfile.setCompanyEmail(email);
		if (!request.getParameter("contactNumber").equals(""))
			updatedCompanyProfile.setContactNumber(request.getParameter("contactNumber"));
		if (!request.getParameter("introduction").equals(""))
			updatedCompanyProfile.setIntroduction(request.getParameter("introduction"));
		if (!request.getParameter("mainPageURL").equals(""))
			updatedCompanyProfile.setMainPageURL(request.getParameter("mainPageURL"));
		updatedCompanyProfile.setShortKey("app-manager");
		updatedCompanyProfile.setSecurityKey("i-am-foundit");
		String res = client.put(updatedCompanyProfile, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (res.equals("SUCCESS")) {
			session.setAttribute("companyProfile", updatedCompanyProfile);
			return "companyProfile.jsp";
		}
		else {
			session.setAttribute("companyProfile", originalCompanyProfile);
			String e = "Updating profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
