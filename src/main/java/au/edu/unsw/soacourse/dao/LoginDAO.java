package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.io.PrintWriter;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import au.edu.unsw.soacourse.bean.*;

public class LoginDAO {
	private String path = "";
	
	public LoginDAO() {
		path = System.getProperty("catalina.home") + "/webapps/ROOT/LoginInfo";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	public boolean isValidSeeker (Seeker seeker) {
		String inputEmail = seeker.getEmail();
		String inputPassword = seeker.getPassword();
		
		try {
			File file = new File(path + "/SeekerLogin.xml");
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<Login></Login>");
				out.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);

			NodeList entries = doc.getElementsByTagName("Entry");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				String validEmail = curElement.getFirstChild().getTextContent();
				if (validEmail.equals(inputEmail)) {
					String validPassword = curElement.getLastChild().getTextContent();
					if (validPassword.equals(inputPassword))
						return true;
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isValidManager (Manager manager) {
		String inputEmail = manager.getEmail();
		String inputPassword = manager.getPassword();
		
		try {
			File file = new File(path + "/ManagerLogin.xml");
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<Login></Login>");
				out.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);

			NodeList entries = doc.getElementsByTagName("Entry");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				String validEmail = curElement.getFirstChild().getTextContent();
				if (validEmail.equals(inputEmail)) {
					String validPassword = curElement.getLastChild().getTextContent();
					if (validPassword.equals(inputPassword))
						return true;
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
