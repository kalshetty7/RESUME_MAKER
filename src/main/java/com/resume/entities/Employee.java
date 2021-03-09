package com.resume.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.resume.utils.Utils;

@Entity
public class Employee extends CommonFields {
	
	//4624343653611295474
	
	private static final long serialVersionUID = -1101334265176855929L;

    private Date dob = Utils.defaultDOB();
    private String resumeName;
    private String email;
    private String address;
    private String languagesKnown="English";
    private String mobile;
    @Lob
    private String summary;
    @Lob
    private String hobbies;
    
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Company> companies;
    
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Skill> skills;

    public Employee() {
    }
 
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguagesKnown() {
        return languagesKnown;
    }

    public void setLanguagesKnown(String languagesKnown) {
        this.languagesKnown = languagesKnown;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    
    
	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public String getResumeName() {
		return StringUtils.isEmpty(resumeName)?getName():resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	@Override
	public Object clone() {
		Employee e = (Employee) super.clone();
		e.setResumeName(e.getResumeName()+" - Copied");
		if(!CollectionUtils.isEmpty(companies)) {
			List<Company> companies1 = new ArrayList<Company>();
			for(Company c:companies) {
				companies1.add((Company) c.clone());
				e.setCompanies(companies1);
			}
		}
		if(!CollectionUtils.isEmpty(skills)) {
			List<Skill> skills1 = new ArrayList<>();
			for(Skill s:skills) {
				skills1.add((Skill) s.clone());
				e.setSkills(skills1);
			}
		}
		return e;
	}
    

}
