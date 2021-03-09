package com.resume.entities;

import javax.persistence.Entity;

@Entity
public class Skill extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Skill(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Skill() {
	}
	
	@Override
	public Object clone() {
		return super.clone();
	}
	
	
}
