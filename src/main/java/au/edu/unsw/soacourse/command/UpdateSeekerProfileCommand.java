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

public class UpdateSeekerProfileCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CandidateProfile/" + email).accept(MediaType.APPLICATION_XML);
		CandidateProfile updatedCandidateProfile = (CandidateProfile)session.getAttribute("candidateProfile");
		CandidateProfile originalCandidateProfile = (CandidateProfile)session.getAttribute("candidateProfile");
		if (!request.getParameter("firstName").equals(""))
			updatedCandidateProfile.setFirstName(request.getParameter("firstName"));
		if (!request.getParameter("lastName").equals(""))
			updatedCandidateProfile.setLastName(request.getParameter("lastName"));
		if (!request.getParameter("nickName").equals(""))
			updatedCandidateProfile.setNickName(request.getParameter("nickName"));
		if (!request.getParameter("age").equals(""))
			updatedCandidateProfile.setAge(request.getParameter("age"));
		if (!request.getParameter("address").equals(""))
			updatedCandidateProfile.setAddress(request.getParameter("address"));
		
		updatedCandidateProfile.setEmail(email);
		if (!request.getParameter("contactNumber").equals(""))
			updatedCandidateProfile.setContactNumber(request.getParameter("contactNumber"));
		if (!request.getParameter("currentPosition").equals(""))
			updatedCandidateProfile.setCurrentPosition(request.getParameter("currentPosition"));
		if (!request.getParameter("education").equals(""))
			updatedCandidateProfile.setEducation(request.getParameter("education"));
		if (!request.getParameter("professionalSkill").equals(""))
			updatedCandidateProfile.setProfessionalSkill(request.getParameter("professionalSkill"));
		if (!request.getParameter("experience").equals(""))
			updatedCandidateProfile.setExperience(request.getParameter("experience"));
		updatedCandidateProfile.setShortKey("app-candidate");
		updatedCandidateProfile.setSecurityKey("i-am-foundit");
		String res = client.put(updatedCandidateProfile, String.class);
		if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
			String e = res;
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (res.equals("SUCCESS")) {
			session.setAttribute("candidateProfile", updatedCandidateProfile);
			return "seekerMyProfile.jsp";
		}
		else {
			session.setAttribute("candidateProfile", originalCandidateProfile);
			String e = "Updating profile failed!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
	}
}
