package com.spidersmart.register.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="register")
public class Register {
	@Id
	@GeneratedValue
	private Long id;	
	
    @JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;	
    
    private String firstName;	
    private String lastName;
    private Double amount;
    private String course;	
    private String paymentType;	
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Register(Date date, String firstName, String lastName, Double amount, String course, String paymentType) {
		super();
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.amount = amount;
		this.course = course;
		this.paymentType = paymentType;
	}

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
