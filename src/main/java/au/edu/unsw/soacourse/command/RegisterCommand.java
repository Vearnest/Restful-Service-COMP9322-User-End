package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.edu.unsw.soacourse.util.*;
import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.dao.*;

public class RegisterCommand extends Command {
	
	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("email").equals("")) {
			String e = "Email can not be empty!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (!Utils.isEmail(request.getParameter("email"))) {
			String e = "Wrong format of Email!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (request.getParameter("password").equals("")) {
			String e = "Password can not be empty!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		if (request.getParameter("password").contains(" ")) {
			String e = "Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			return "Error.jsp";
		}
		
		if (request.getParameter("identity").equals("seekerRegist")) {
			Seeker seeker = new Seeker();
			seeker.setEmail((String)request.getParameter("email"));
			seeker.setPassword((String)request.getParameter("password"));

			RegisterDAO checkDAO = new RegisterDAO();
			if (checkDAO.hasRegistered(seeker.getEmail(), "seeker")) {
				String e = "The email has already been registered!";
				session.setAttribute("errMsg", e);
				return "Error.jsp";
			}
			
			RegisterDAO registerDAO = new RegisterDAO();
			try {
				registerDAO.InsertSeeker(seeker);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			session.setAttribute("email", seeker.getEmail());
/*			
			BuyerEmailSender buyeremailsender = new BuyerEmailSender(buyer.getEmail());
			buyeremailsender.start();
			
			String e = "Register Successful!\n" + "Please confirm your email!\n";
			session.setAttribute("errMsg", e);
*/
			return "seekerMainPage.jsp";
		}
		
		if (request.getParameter("identity").equals("managerRegist")) {
			Manager manager = new Manager();
			manager.setEmail((String)request.getParameter("email"));
			manager.setPassword((String)request.getParameter("password"));

			RegisterDAO checkDAO = new RegisterDAO();
			if (checkDAO.hasRegistered(manager.getEmail(), "manager")) {
				String e = "The email has already been registered!";
				session.setAttribute("errMsg", e);
				return "Error.jsp";
			}
			
			RegisterDAO registerDAO = new RegisterDAO();
			try {
				registerDAO.InsertManager(manager);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			session.setAttribute("email", manager.getEmail());
			return "managerMainPage.jsp";
		}
		return "Error.jsp";
	}
	public boolean forward() {
		return true;
	}
}

class SellerEmailSender extends Thread {
	private String email;
	public SellerEmailSender(String email) {
		this.email = email;
	}
	public void run() {
		try {
			String subject = "Registration Successful";
			String url = "http://localhost:8080/final/sellerMainPage.jsp";
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";

			System.out.println(email);
			MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", email, subject, message);
			
		} catch (Exception e) {}
	}
}

class BuyerEmailSender extends Thread {
	private String email;
	public BuyerEmailSender(String email) {
		this.email = email;
	}
	public void run() {
		try {
			String subject = "Registration Successful";
			String url = "http://localhost:8080/final/buyerMainPage.jsp";
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";

			System.out.println(email);
			MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", email, subject, message);
			
		} catch (Exception e) {}
	}
}