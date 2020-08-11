package com.infodart.entity;

import java.util.Date;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "employee")
public class Employee {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	private String Name;
	private int  Activeflag;
	private double ContactNumber;
	private String Email, Designation, CreatedBy, ModifiedBy;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date CreatedTimestamp;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date ModifiedTimestamp;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "loginid")
	private Login loginid;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(double contactNumber) {
		ContactNumber = contactNumber;
	}

	public int getActiveflag() {
		return Activeflag;
	}

	public void setActiveflag(int activeflag) {
		Activeflag = activeflag;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public java.util.Date getCreatedTimestamp() {
		return CreatedTimestamp;
	}

	public void setCreatedTimestamp(java.util.Date createdTimestamp) {
		CreatedTimestamp = createdTimestamp;
	}

	public java.util.Date getModifiedTimestamp() {
		return ModifiedTimestamp;
	}

	public void setModifiedTimestamp(java.util.Date modifiedTimestamp) {
		ModifiedTimestamp = modifiedTimestamp;
	}

	public Login getLoginid() {
		return loginid;
	}

	public void setLoginid(Login loginid) {
		this.loginid = loginid;
	}

	public Employee(String name, double contactNumber, int activeflag, String email, String designation, String createdBy,
			String modifiedBy, Date createdTimestamp, Date modifiedTimestamp, Login loginid) {
		super();
		Name = name;
		ContactNumber = contactNumber;
		Activeflag = activeflag;
		Email = email;
		Designation = designation;
		CreatedBy = createdBy;
		ModifiedBy = modifiedBy;
		CreatedTimestamp = createdTimestamp;
		ModifiedTimestamp = modifiedTimestamp;
		this.loginid = loginid;
	}

	public Employee() {
		super();
	}

	
}
