package au.edu.unsw.soacourse.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobSearch {

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	private String shortKey;
	private String securityKey;
	private String _jobID;
	private String title;
	private String requirement;
	private String status;
	
	public JobSearch() {
		
	}
	
	public JobSearch(String _jobID) {
		this._jobID = _jobID;
	}

	/**
	 * @return the _jobID
	 */
	public String get_jobID() {
		return _jobID;
	}

	/**
	 * @param _jobID the _jobID to set
	 */
	public void set_jobID(String _jobID) {
		this._jobID = _jobID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
