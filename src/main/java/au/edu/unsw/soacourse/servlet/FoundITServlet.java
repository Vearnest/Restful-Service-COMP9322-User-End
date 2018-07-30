package au.edu.unsw.soacourse.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.cxf.jaxrs.client.WebClient;

import au.edu.unsw.soacourse.autochecks.*;
import au.edu.unsw.soacourse.bean.ApplicationSearch;
import au.edu.unsw.soacourse.bean.Applications;
import au.edu.unsw.soacourse.bean.CandidateProfile;
import au.edu.unsw.soacourse.bean.JobPosting;
import au.edu.unsw.soacourse.command.*;

/**
 * Servlet implementation class FoundITServlet
 */
public class FoundITServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoundITServlet() {
        super();
        // TODO Auto-generated constructor stub
        map = new HashMap<String, Command>();
        setCommand();
    }
    
    private void setCommand() {
    	map.put("registSubmit", new RegisterCommand());
    	map.put("login", new LoginCommand());
    	map.put("searchJob", new SearchJobCommand());
    	map.put("seekerProfile", new SeekerProfileCommand());
    	map.put("companyProfile", new CompanyProfileCommand());
    	map.put("seekerUpdateProfile", new UpdateSeekerProfileCommand());
    	map.put("seekerCreateProfile", new CreateSeekerProfileCommand());
    	map.put("companyUpdateProfile", new UpdateCompanyProfileCommand());
    	map.put("companyCreateProfile", new CreateCompanyProfileCommand());
    	map.put("jobDetail", new JobDetailCommand());
    	map.put("managerJobDetail", new ManagerJobDetailCommand());
    	map.put("addCart", new AddCartCommand());
    	map.put("jobPostings", new JobPostingsCommand());
    	map.put("deleteSeekerProfile", new DeleteSeekerProfileCommand());
    	map.put("logout", new LogoutCommand());
    	map.put("apply", new ApplyJobCommand());
    	map.put("sendApplication", new SendApplicationCommand());
    	map.put("toCart", new JobCartCommand());
    	map.put("removeCart", new RemoveCartCommand());
    	map.put("postJob", new PostJobCommand());
    	map.put("updateJob", new UpdateJobCommand());
    	map.put("deleteJob", new DeleteJobCommand());
    	map.put("closeJob", new CloseJobCommand());
    	map.put("myApplications", new MyApplicationsCommand());
    	map.put("reviewingApplications", new ReviewingApplicationsCommand());
    	map.put("myApplicationDetail", new MyApplicationDetailCommand());
    	map.put("reviewingApplicationDetail", new ReviewingApplicationDetailCommand());
    	map.put("jobApplicationDetail", new JobApplicationDetailCommand());
    	map.put("updateCoverLetter", new UpdateCoverLetterCommand());
    	map.put("deleteMyApplication", new DeleteMyApplicationCommand());
    	map.put("hireTeam", new ShowAvailableReviewersCommand());
    	map.put("showJobApplications", new ShowJobApplicationsCommand());
    	map.put("applicationAssign", new ApplicationAssignCommand());
    	map.put("assign", new AssignTeamCommand());
    	map.put("reject", new RejectReviewCommand());
    	map.put("pass", new PassReviewCommand());
    	map.put("accept", new AcceptCommand());
    	map.put("rejectOffer", new RejectCommand());
    	map.put("jobAlert", new JobAlertCommand());
    	
    	map.put("editCoverLetter", new ForwardCommand());
    	map.put("editSeekerProfile", new ForwardCommand());
    	map.put("editCompanyProfile", new ForwardCommand());
    	map.put("editJob", new ForwardCommand());
    	map.put("toRegister", new ForwardCommand());
    	map.put("toLogin", new ForwardCommand());
    	map.put("toWelcome", new ForwardCommand());
    	map.put("toSeekerMain", new ForwardCommand());
    	map.put("toManagerMain", new ForwardCommand());
    	map.put("toReviewerMain", new ForwardCommand());
    	map.put("toSearchResult", new ForwardCommand());
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectFactory factory = new ObjectFactory();
		
		String action = request.getParameter("action");
//		System.out.println(action);
		// auto check
		if (action.equals("closeJob")) {
			HttpSession session = request.getSession();
			JobPosting jobPosting = (JobPosting)session.getAttribute("jobPosting");
			String jobId = jobPosting.get_jobID();
			String uri = "http://localhost:8080/FoundITRestfulService/Applications/job_applications?jobID=" + jobId;
			WebClient client = WebClient.create(uri);
			client.accept(MediaType.APPLICATION_XML);
			ArrayList<ApplicationSearch> jobApplications = (ArrayList<ApplicationSearch>) client.getCollection(ApplicationSearch.class);
			
			for (int i = 0; i < jobApplications.size(); ++i) {
				ApplicationSearch jobApplication = jobApplications.get(i);
				String applicationId = jobApplication.get_applicationID();
				uri = "http://localhost:8080/FoundITRestfulService";
				client = WebClient.create(uri);
				client.path("/Applications/candidateProfile/" + applicationId).accept(MediaType.APPLICATION_XML);

				CandidateProfile candidateProfile = client.get(CandidateProfile.class);
				String address = candidateProfile.getAddress();
				String fullName = candidateProfile.getFirstName() + candidateProfile.getLastName();
				String inputString = "";
				inputString = "#A#" + address;
				String outputMessage = "";
				try {
					SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
					SOAPConnection soapConnection = soapConnectionFactory.createConnection();
					String url = "http://localhost:6060/ode/processes/AutoChecksServiceProcess";
					SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(inputString,fullName), url);
					
					outputMessage = getSOAPResponse(soapResponse);
					soapConnection.close();
					System.out.println(outputMessage);
					Applications application = new Applications();
					application.set_appID(applicationId);
					if (outputMessage.equals("found")) {
						client = WebClient.create(uri);
						client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
						application.setStatus("autoChecked");
						application.setShortKey("app-manager");
						application.setSecurityKey("i-am-foundit");
						String res = client.put(application, String.class);
						if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
							String e = res;
							session.setAttribute("errMsg", e);
							RequestDispatcher rd = request.getRequestDispatcher("/" + "Error.jsp");
							rd.forward(request, response);
							return;
						}
					}
					else {
						client = WebClient.create(uri);
						client.path("/Applications/" + applicationId).accept(MediaType.APPLICATION_XML);
						application.setStatus("reject");
						application.setShortKey("app-manager");
						application.setSecurityKey("i-am-foundit");
						String res = client.put(application, String.class);
						if (!res.equals("SUCCESS") && !res.equals("FAIL")) {
							String e = res;
							session.setAttribute("errMsg", e);
							RequestDispatcher rd = request.getRequestDispatcher("/" + "Error.jsp");
							rd.forward(request, response);
							return;
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

		Command cmd = map.get(action);
		String nextPage = cmd.doExecute(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("/" + nextPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static SOAPMessage createSOAPRequest(String inputString, String fullName) throws SOAPException {	
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		String serverURL = "http://autochecks.soacourse.unsw.edu.au";
		
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		soapEnvelope.addNamespaceDeclaration("q0", serverURL);
		
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody.addChildElement("selectorRequest", "q0");
		SOAPElement soapChildElement = soapElement.addChildElement("inputString");
		soapChildElement.addTextNode(inputString);
		SOAPElement soapChilElement2 = soapElement.addChildElement("fullName");
		soapChilElement2.addTextNode(fullName);
		MimeHeaders mimeHeaders = soapMessage.getMimeHeaders();
		mimeHeaders.addHeader("SOAPAction", serverURL);
		soapMessage.saveChanges();
		return soapMessage;
	}
	
	public static String getSOAPResponse(SOAPMessage soapResp) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source source = soapResp.getSOAPPart().getContent();
		String string = soapResp.getSOAPPart().getElementsByTagName("outputMessage").item(0).getTextContent();
		return string;
	}

}
