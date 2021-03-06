package au.edu.unsw.soacourse.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.edu.unsw.soacourse.bean.*;
import au.edu.unsw.soacourse.dao.*;

public class RemoveCartCommand extends Command {
	@Override
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String jobId = request.getParameter("jobId");
		JobCartDAO jobCartDAO = new JobCartDAO();
		jobCartDAO.DeleteJob(jobId);
		ArrayList<JobSearch> jobsInCart = jobCartDAO.JobsInCart();
		session.setAttribute("jobsInCart", jobsInCart);
		
		return "jobCart.jsp";
	}
}
