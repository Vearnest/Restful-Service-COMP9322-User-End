package au.edu.unsw.soacourse.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "";
	}

	public boolean forward() {
		return false;
	}

}
