package com.resume.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Project extends CommonFields {
	@Lob
	private String rolesAndResponsibilities;
	@Lob
	private String summary;
	private int teamSize;
	@Lob
	private String clientDetails;
	@Lob
	private String tools;
	private String domain = "Banking";

	public String getRolesAndResponsibilities() {
		return rolesAndResponsibilities;
	}

	public void setRolesAndResponsibilities(String rolesAndResponsibilities) {
		this.rolesAndResponsibilities = rolesAndResponsibilities;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}

	public String getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(String clientDetails) {
		this.clientDetails = clientDetails;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public Object clone() {
		return super.clone();
	}

	public Project() {
		super();
	}

}
