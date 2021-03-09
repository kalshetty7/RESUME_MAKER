package com.resume.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.resume.entities.Base;
import com.resume.entities.Company;
import com.resume.entities.Employee;
import com.resume.entities.Skill;

public class Utils {
	final static String COMMA_DELIM = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	public static final String PATTERN = "dd MMM yyyy";
	public static final SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern(PATTERN);
	public static final String TILL_DATE = "Till Date";
	public static final Function<String, Date> TO_DATE_FN = t -> {
		if (t == null)
			t = TILL_DATE;
		t = t.trim();
		if (t.equals(""))
			t = TILL_DATE;
		if (t.equals(TILL_DATE))
			return today();
		else
			try {
				return sdf.parse(t);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return today();
	};

	public static final Function<String, LocalDate> TO_LOCALDATE_FN = t -> {
		if (t == null)
			t = TILL_DATE;
		t = t.trim();
		if (t.equals(""))
			t = TILL_DATE;
		if (t.equals(TILL_DATE))
			return LocalDate.now();
		else
			return LocalDate.parse(t, df);
	};
	
	
	static final String EXTENSION = ".properties";
	static final String FILE_NAME = "DefaultData" + EXTENSION;

	static enum Defaults {
		NAME, ADDRESS, MOBILE, EMAIL, SUMMARY, SKILLS, HOBBIES, LANGUAGES, COMPANIES;
		private static Properties p = new Properties();

		private static Properties loadProperties() {
			try {
				String userDir=System.getProperty("user.dir");
				p("\nuserDir : "+userDir);
				String path = userDir+"/classes/DefaultData.properties";
				p("\npath : "+path);
				p("getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll(\"!\", \"\") : "+Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("!", ""));
				//InputStream is = new FileInputStream(path);
				InputStream is = new FileInputStream(Utils.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("!", "")+FILE_NAME);
				p.load(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return p;
		}

		static {
			p = loadProperties();
		}

		public String getValue() {
			return p.getProperty(this.toString().toLowerCase());
		}
	}
	
	public static Employee getDefaultData() {
		Employee e = new Employee();
		e.setName(Defaults.NAME.getValue());
		e.setAddress(Defaults.ADDRESS.getValue());
		e.setEmail(Defaults.EMAIL.getValue());
		e.setHobbies(Defaults.HOBBIES.getValue());
		e.setSummary(Defaults.SUMMARY.getValue());
		e.setMobile(Defaults.MOBILE.getValue());
		e.setLanguagesKnown(Defaults.LANGUAGES.getValue());
		String skillsArray[] = Defaults.SKILLS.getValue().split("\n");
		List<Skill> skills = new ArrayList<>();
		Arrays.stream(skillsArray).forEach(s -> {
			String str[] = s.split("\\|");
			skills.add(new Skill(str[0], str[1]));
		});
		e.setSkills(skills);
		List<Company> companies = new ArrayList<>();
		String companyArray[] = Defaults.COMPANIES.getValue().split("\\|");
		Arrays.stream(companyArray).forEach(c -> {
			String comps[] = c.split(COMMA_DELIM, -1);
			Company company = new Company();
			company.setName(comps[0]);
			company.setStartDateString(comps[1]);
			company.setEndDateString(comps[2]);
			company.setJobProfile(comps[3]);
			String address = comps[4].replaceAll("\"", "");
			company.setAddress(address);
			companies.add(company);
		});
		e.setCompanies(companies);
		return e;
	}
	
	@SuppressWarnings("unchecked")
	public static final Function<List<? extends Base>, List<? extends Base>> COPY_LIST = l -> {
		return (List)l.stream().map(n->n.clone()).collect(Collectors.toList());
	};

	public static String formatPeriod(Period p) {
		int years = p.getYears();
		int months = p.getMonths();
		int days = p.getDays();
		String s = years + " Years, " + months + " months, " + days + " days";
		if(days>=30) {
			months=months+(days/30);
			days=days%30;
		}
		if(months>=12) {
			years=years+(months/12);
			months=months%12;
		}
		//s +="<br><br>"+years + " Years, " + months + " months, " + days + " days";
		s = years + " Years, " + months + " months, " + days + " days";
		return s;
	}

	public static Date today() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public static Date defaultDOB() {
		Calendar c = Calendar.getInstance();
		c.set(1989, 4, 31);
		return c.getTime();
	}

	public static LocalDate convertToLocalDate(Date d) {
		String date = sdf.format(d);
		return LocalDate.parse(date, df);
	}

	public static String calculateAge(Date d) {
		LocalDate now = LocalDate.now();
		LocalDate date = convertToLocalDate(d);
		Period p = Period.between(date, now);
		return formatPeriod(p);
	}

	public static <T> T clone(T t) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			os = new ObjectOutputStream(bos);
			os.writeObject(t);
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			return (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void removeElements(List items,List<Integer> indices) {
		Iterator it = items.iterator();
		int counter=0;
		while(it.hasNext()) {
			it.next();
			if(indices.contains(counter))
				it.remove();
			counter++;
		}
	}
	
	public static void sortCompanies(List<Company> companies) {
		companies.sort((c1,c2)->c2.getStartDate().compareTo(c2.getStartDate()));
	}
	
	public static void sortCompanies(Employee e) {
		if(!CollectionUtils.isEmpty(e.getCompanies())) {
			sortCompanies(e.getCompanies());
		}
	}

	static void p(Object o) {
		System.out.print("\n" + o + "\n");
	}

}
