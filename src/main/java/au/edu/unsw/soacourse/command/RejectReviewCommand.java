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

public class RejectReviewCommand extends Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		Applications reviewingApplicationDetail = (Applications) session.getAttribute("reviewingApplicationDetail");

		String uri = "http://localhost:8080/FoundITRestfulService";
		WebClient client = WebClient.create(uri);
		
		String applicationId = reviewingApplicationDetail.get_appID();
		String username = (String)session.getAttribute("username");
		String comment = request.getParameter("comment");
		Reviews review = new Reviews();
		review.set_appID(applicationId);
		
		if (reviewingApplicationDetail.get_reviewID().equals("null")) {
			review.setFirst_reviewerID(username);
			review.setFirstComment(comment);
			review.setFirstDecision("reject");
			
			client = WebClient.create(uri);
			client.path("/Reviews").accept(MediaType.APPLICATION_XML);
			review.setShortKey("app-reviewer");
			review.setSecurityKey("i-am-foundit");
			Reviews respReview = client.post(review, Reviews.class);
			if (respReview.getSecurityKey() != null) {
				String e = respReview.getSecurityKey();
				session.setAttribute("errMsg", e);
				return "Error.jsp";
			}
			String e = "Review Completed!";
			session.setAttribute("sucMsg", e);
			return "reviewerMainPage.jsp";
		}
		else {
			String reviewId = reviewingApplicationDetail.get_reviewID();
			review.set_reviewID(reviewId);
			review.setSecond_reviewerID(username);
			review.setSecondComment(comment);
			review.setSecondDecision("reject");
			
			client = WebClient.create(uri);
			client.path("/Reviews/" + reviewId).accept(MediaType.APPLICATION_XML);
			review.setShortKey("app-reviewer");
			review.setSecurityKey("i-am-foundit");
			String res = client.put(review, String.class);
			if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
				String e = res;
				session.setAttribute("errMsg", e);
				return "Error.jsp";
			}
			if (res.equals("SUCCESS")) {
				String e = "Review Completed!";
				session.setAttribute("sucMsg", e);
				return "reviewerMainPage.jsp";
			}
			else {
				String e = "Review failed!";
				session.setAttribute("errMsg", e);
				return "Error.jsp";
			}
		}
	}
}
