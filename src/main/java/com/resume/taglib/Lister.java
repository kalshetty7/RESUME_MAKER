package com.resume.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

public class Lister extends TagSupport {
	private String content;
	private String relevantExperience;
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(String relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (content != null) {
				content = populateRelevantExperience(content,relevantExperience);
				String str[] = content.split("\n");
				if (str.length <= 1) {
					out.print(content);
				} else {
					StringBuilder sb = new StringBuilder("<ul>");
					for (String s : str)
						sb.append("<li>" + s + "</li>");
					sb.append("</ul>");
					out.print(sb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	static String populateRelevantExperience(String c,String r) {
		if(!StringUtils.isEmpty(r)) {
			String formatExpArr[] = r.split(",");
			String formatExp = "";
			if(formatExpArr!=null && formatExpArr.length>0)
				for(String f:formatExpArr) {
					if(!f.contains("days"))
						formatExp+=f+", ";
			formatExp=formatExp.substring(0, formatExp.length()-1);
			}
			c = c.replaceAll("\\{exp\\}", formatExp);
		}
		return c;
	}

	static void p(Object o) {
		System.out.print("\n" + o + "\n");
	}
}