package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.dao.*;

public class AddCartCommand extends Command {
	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JobPosting jobPosting = (JobPosting) session.getAttribute("jobPosting");
		JobCartDAO jobCartDAO = new JobCartDAO();
		if (!jobCartDAO.hasAdded(jobPosting)){
			jobCartDAO.InsertCart(jobPosting);
		}
		ArrayList<JobSearch> jobsInCart = jobCartDAO.JobsInCart();
		session.setAttribute("jobsInCart", jobsInCart);
		
		return "jobCart.jsp";
	}
}
