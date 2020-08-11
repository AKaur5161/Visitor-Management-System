package com.infodart.entity;

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
@Table(name = "visitor")
public class Visitor {

	public Visitor() {
		super();
	}

	public Visitor(String name, long contactno, String purpose, String contactPersonName, String designation,
			String email) {
		super();
		this.Name = name;
		this.Purpose = purpose;
		this.ContactPersonName = contactPersonName;
		this.Designation = designation;
		this.Email = email;
		this.Contactno = contactno;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String Name, Purpose, ContactPersonName, Designation, Email, CreatedBy, ModifiedBy;
	private long Contactno;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date CheckIntimestamp;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date CheckOuttimestamp;
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date Date;
	private int Activeflag;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date CreatedTimestamp;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date ModifiedTimestamp;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "loginid")
	// private List<StaffSecurity> staffSecurities;
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

	public String getPurpose() {
		return Purpose;
	}

	public void setPurpose(String purpose) {
		Purpose = purpose;
	}

	public String getContactPersonName() {
		return ContactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		ContactPersonName = contactPersonName;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String contactPersonEmail) {
		Email = contactPersonEmail;
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

	public long getContactno() {
		return Contactno;
	}

	public void setContactno(long contactno) {
		Contactno = contactno;
	}

	public java.util.Date getCheckIntimestamp() {
		return CheckIntimestamp;
	}

	public void setCheckIntimestamp(java.util.Date checkIntimestamp) {
		CheckIntimestamp = checkIntimestamp;
	}

	public java.util.Date getCheckOuttimestamp() {
		return CheckOuttimestamp;
	}

	public void setCheckOuttimestamp(java.util.Date checkOuttimestamp) {
		CheckOuttimestamp = checkOuttimestamp;
	}

	public java.util.Date getDate() {
		return Date;
	}

	public void setDate(java.util.Date date) {
		Date = date;
	}

	public int getActiveflag() {
		return Activeflag;
	}

	public void setActiveflag(int activeflag) {
		Activeflag = activeflag;
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
public Visitor(String email)
{
	super();
	Email=email;
}
	public Visitor(String name, String purpose, String contactPersonName, String designation, String email,
			String createdBy, String modifiedBy, long contactno, java.util.Date checkIntimestamp,
			java.util.Date checkOuttimestamp, java.util.Date date, int activeflag, java.util.Date createdTimestamp,
			java.util.Date modifiedTimestamp, Login loginid) {
		super();
		Name = name;
		Purpose = purpose;
		ContactPersonName = contactPersonName;
		Designation = designation;
		Email = email;
		CreatedBy = createdBy;
		ModifiedBy = modifiedBy;
		Contactno = contactno;
		CheckIntimestamp = checkIntimestamp;
		CheckOuttimestamp = checkOuttimestamp;
		Date = date;
		Activeflag = activeflag;
		CreatedTimestamp = createdTimestamp;
		ModifiedTimestamp = modifiedTimestamp;
		this.loginid = loginid;
	}

}
