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
    private Integer hours;
    private Integer price;
    private Integer credit;
    private String course;	
    private String paymentType;	
    private String memo;
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
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

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Register(Date date, String firstName, String lastName, Integer hours, Integer price, Integer credit,
			String course, String paymentType, String memo) {
		super();
		this.date = date;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hours = hours;
		this.price = price;
		this.credit = credit;
		this.course = course;
		this.paymentType = paymentType;
		this.memo = memo;
	}
	
}
