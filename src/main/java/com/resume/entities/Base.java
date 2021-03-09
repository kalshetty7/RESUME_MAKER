package com.resume.entities;


import com.resume.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Base implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
	public long id;

	Base() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Base b = (Base) obj;
		if(b.id==this.id)
			return true;
		else
			return false;
	}

	@Override
	public Object clone() {
		Base b = Utils.clone(this);
		b.setId(0);
		return b;
	}
	
	public String toString() {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	    return gson.toJson(this);
	}

}
