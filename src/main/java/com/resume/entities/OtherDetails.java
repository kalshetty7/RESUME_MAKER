package com.resume.entities;

import static com.resume.utils.Utils.TO_LOCALDATE_FN;
import static com.resume.utils.Utils.calculateAge;
import static com.resume.utils.Utils.formatPeriod;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.resume.utils.Utils;

public class OtherDetails {

	SimpleDateFormat sdf = new SimpleDateFormat(Utils.PATTERN);

	Employee e;

	String age;

	String dob;

	int switches;

	String totalExperience;

	String relevantExperience;

	List<JobDetails> jobDetails;

	public Employee getE() {
		return e;
	}

	public void setE(Employee e) {
		this.e = e;
	}

	public String getDob() {
		return sdf.format(e.getDob());
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getSwitches() {
		return switches;
	}

	public void setSwitches(int switches) {
		this.switches = switches;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(String relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public List<JobDetails> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(List<JobDetails> jobDetails) {
		this.jobDetails = jobDetails;
	}

	public class JobDetails {
		String name;
		LocalDate startDate;
		String startDateString;
		LocalDate endDate;
		String endDateString;
		String duration;

		public JobDetails(String name, String startDateString, String endDateString) {
			this.name = name;
			this.startDateString = startDateString;
			this.endDateString = endDateString;
			startDate = TO_LOCALDATE_FN.apply(startDateString);
			endDate = TO_LOCALDATE_FN.apply(endDateString);
			duration = formatPeriod(Period.between(startDate, endDate));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public String getStartDateString() {
			return startDateString;
		}

		public void setStartDateString(String startDateString) {
			this.startDateString = startDateString;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public String getEndDateString() {
			return endDateString;
		}

		public void setEndDateString(String endDateString) {
			this.endDateString = endDateString;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String toString() {
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
			return gson.toJson(this);
		}
	}

	public OtherDetails(Employee e) {
		this.e = e;
		age = calculateAge(e.getDob());
		Period p = Period.ZERO;
		if (!CollectionUtils.isEmpty(e.getCompanies())) {
			List<JobDetails> jdList = new ArrayList<JobDetails>();
			List<Company> companies = e.getCompanies();
			Utils.sortCompanies(companies);
			int size = companies.size();
			e.setStartDate(companies.get(size-1).getStartDate());
			e.setStartDateString(companies.get(size-1).getStartDateString());
			e.setEndDate(companies.get(0).getEndDate());
			e.setEndDateString(companies.get(0).getEndDateString());
			totalExperience = formatPeriod(Period.between(TO_LOCALDATE_FN.apply(e.getStartDateString()),
					TO_LOCALDATE_FN.apply(e.getEndDateString())));
			for (Company c : companies) {
				JobDetails jd = new JobDetails(c.getName(), c.getStartDateString(), c.getEndDateString());
				jdList.add(jd);
				p = p.plus(Period.between(jd.startDate, jd.endDate));
			}
			jobDetails = jdList;
			switches = jdList.size();
		}
		relevantExperience = formatPeriod(p);
	}

	public String toString() {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		return gson.toJson(this);
	}

}
