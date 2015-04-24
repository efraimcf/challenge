package br.com.cowtysys.challenge.repository;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DateModel implements Serializable {

	private static final long serialVersionUID = -2129549599058757463L;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedAt;

	public DateModel(){
		
	}
	
	public DateModel(Calendar createdAt, Calendar updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public DateModel(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}