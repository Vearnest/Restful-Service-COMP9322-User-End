package au.edu.unsw.soacourse.dao;

import java.io.*;
import java.util.ArrayList;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.edu.unsw.soacourse.bean.*;

public class JobCartDAO {
	private String path = System.getProperty("catalina.home") + "/webapps/ROOT/JobCart";;
	private String fileName = "/JobCart.xml";
	private File dir = null;
	private File file = null;
	
	public JobCartDAO() {
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(path + fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter out = new PrintWriter(file);
				out.print("<JobCart></JobCart>");
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasAdded(JobPosting jobPosting) {
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Element curElement = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			curElement = (Element)xpath.evaluate("/JobCart/Entry[JobId='" + jobPosting.get_jobID() + "']", root, XPathConstants.NODE);
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
	
	public void InsertCart(JobPosting jobPosting) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			Element entryElement = doc.createElement("Entry");
			root.appendChild(entryElement);
			
			Element jobIdElement = doc.createElement("JobId");
			jobIdElement.setTextContent(jobPosting.get_jobID());
			entryElement.appendChild(jobIdElement);
			
			Element companyProfileIdElement = doc.createElement("CompanyProfileId");
			companyProfileIdElement.setTextContent(jobPosting.get_companyProfileID());
			entryElement.appendChild(companyProfileIdElement);
			
			Element titleElement = doc.createElement("Title");
			titleElement.setTextContent(jobPosting.getTitle());
			entryElement.appendChild(titleElement);
			
			Element salaryRateElement = doc.createElement("SalaryRate");
			salaryRateElement.setTextContent(jobPosting.getSalaryRate());
			entryElement.appendChild(salaryRateElement);
			
			Element positionTypeElement = doc.createElement("PositionType");
			positionTypeElement.setTextContent(jobPosting.getPositionType());
			entryElement.appendChild(positionTypeElement);
			
			Element locationElement = doc.createElement("Location");
			locationElement.setTextContent(jobPosting.getLocation());
			entryElement.appendChild(locationElement);
			
			Element jobDescriptionElement = doc.createElement("JobDescription");
			jobDescriptionElement.setTextContent(jobPosting.getJobDescription());
			entryElement.appendChild(jobDescriptionElement);
			
			Element requirementElement = doc.createElement("Requirement");
			requirementElement.setTextContent(jobPosting.getRequirement());
			entryElement.appendChild(requirementElement);
			
			Element statusElement = doc.createElement("Status");
			statusElement.setTextContent(jobPosting.getStatus());
			entryElement.appendChild(statusElement);
			
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
	
	public ArrayList<JobSearch> JobsInCart() {
		ArrayList<JobSearch> jobsInCart = new ArrayList<JobSearch>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			
			NodeList entries = doc.getElementsByTagName("Entry");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				JobSearch job = new JobSearch();
				
				Element curChild = (Element)curElement.getFirstChild();
				job.set_jobID(curChild.getTextContent());
				
				curChild = (Element)curChild.getNextSibling();
				curChild = (Element)curChild.getNextSibling();
				job.setTitle(curChild.getTextContent());
				
				curChild = (Element)curElement.getLastChild();
				job.setStatus(curChild.getTextContent());
				
				curChild = (Element)curChild.getPreviousSibling();
				job.setRequirement(curChild.getTextContent());
				
				jobsInCart.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobsInCart;
	}
	
	public void DeleteJob(String jobId) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document doc = documentBuilder.parse(file);
			Element root = doc.getDocumentElement();
			
			NodeList entries = doc.getElementsByTagName("Entry");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(jobId)) {
					root.removeChild((Node)curElement);
					TransformerFactory tFactory = TransformerFactory.newInstance();
					Transformer transformer = tFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					transformer.setOutputProperty(OutputKeys.INDENT, "no");
					DOMSource source = new DOMSource(doc);
					PrintWriter printWriter = new PrintWriter(file);
					StreamResult streamResult = new StreamResult(printWriter);
					transformer.transform(source, streamResult);
					printWriter.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
