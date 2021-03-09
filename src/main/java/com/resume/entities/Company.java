package com.resume.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.util.CollectionUtils;

@Entity
public class Company extends CommonFields {
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Project> projects;

    private String address;
    
    private String jobProfile;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}

	public Company() {
		super();
	}
    
    public Object clone() {
    	List<Project> copiedProjects = new ArrayList<Project>();
    	if(!CollectionUtils.isEmpty(projects))
    		for(Project p:projects)
    			copiedProjects.add((Project) p.clone());
    	Company c = (Company) super.clone();
    	c.setProjects(copiedProjects);
    	return c;
    }

}
