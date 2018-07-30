package au.edu.unsw.soacourse.dao;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import au.edu.unsw.soacourse.bean.*;

public class RegisterDAO {
	private String path = "";
	
	public RegisterDAO() {
		path = System.getProperty("catalina.home") + "/webapps/ROOT/LoginInfo";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
	
	public boolean hasRegistered(String Email, String identity) {
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Element curElement = null;
		String fileName = "";
		if (identity.equals("seeker"))
			fileName = "/SeekerLogin.xml";
		else if (identity.equals("manager"))
			fileName = "/ManagerLogin.xml";
		
		try {
			File file = new File(path + fileName);
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<Login></Login>");
				out.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			curElement = (Element)xpath.evaluate("/Login/Entry[Email='" + Email + "']", root, XPathConstants.NODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == curElement) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void InsertSeeker(Seeker seeker) {
		String fileName = "/SeekerLogin.xml";
		
		try {
			File file = new File(path + fileName);
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<Login></Login>");
				out.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			Element entryElement = doc.createElement("Entry");
			root.appendChild(entryElement);
			Element emailElement = doc.createElement("Email");
			emailElement.setTextContent(seeker.getEmail());
			Element passwordElement = doc.createElement("Password");
			passwordElement.setTextContent(seeker.getPassword());
			entryElement.appendChild(emailElement);
			entryElement.appendChild(passwordElement);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(doc);
			PrintWriter pw = new PrintWriter(file);
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void InsertManager(Manager manager) {
		String fileName = "/ManagerLogin.xml";
		
		try {
			File file = new File(path + fileName);
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<Login></Login>");
				out.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			Element entryElement = doc.createElement("Entry");
			root.appendChild(entryElement);
			Element emailElement = doc.createElement("Email");
			emailElement.setTextContent(manager.getEmail());
			Element passwordElement = doc.createElement("Password");
			passwordElement.setTextContent(manager.getPassword());
			entryElement.appendChild(emailElement);
			entryElement.appendChild(passwordElement);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(doc);
			PrintWriter pw = new PrintWriter(file);
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
