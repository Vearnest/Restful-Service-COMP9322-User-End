package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.bean.*;


public class CreateSeekerProfileCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		client.path("/CandidateProfile").accept(MediaType.APPLICATION_XML);
		CandidateProfile candidateProfile = new CandidateProfile();
		if (!request.getParameter("firstName").equals(""))
			candidateProfile.setFirstName(request.getParameter("firstName"));
		if (!request.getParameter("lastName").equals(""))
			candidateProfile.setLastName(request.getParameter("lastName"));
		if (!request.getParameter("nickName").equals(""))
			candidateProfile.setNickName(request.getParameter("nickName"));
		if (!request.getParameter("age").equals(""))
			candidateProfile.setAge(request.getParameter("age"));
		if (!request.getParameter("address").equals(""))
			candidateProfile.setAddress(request.getParameter("address"));
		
		candidateProfile.setEmail(email);
		if (!request.getParameter("contactNumber").equals(""))
			candidateProfile.setContactNumber(request.getParameter("contactNumber"));
		if (!request.getParameter("currentPosition").equals(""))
			candidateProfile.setCurrentPosition(request.getParameter("currentPosition"));
		if (!request.getParameter("education").equals(""))
			candidateProfile.setEducation(request.getParameter("education"));
		if (!request.getParameter("professionalSkill").equals(""))
			candidateProfile.setProfessionalSkill(request.getParameter("professionalSkill"));
		if (!request.getParameter("experience").equals(""))
			candidateProfile.setExperience(request.getParameter("experience"));
		candidateProfile.setShortKey("app-candidate");
		candidateProfile.setSecurityKey("i-am-foundit");
		CandidateProfile respCandidateProfile = client.post(candidateProfile, CandidateProfile.class);
		if (respCandidateProfile.getSecurityKey() != null) {
			String e = respCandidateProfile.getSecurityKey();
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		session.setAttribute("candidateProfile", respCandidateProfile);
//		System.out.println(respCandidateProfile.get_profileID() + respCandidateProfile.getFirstName());
		return "seekerMyProfile.jsp";
		
	}
}
