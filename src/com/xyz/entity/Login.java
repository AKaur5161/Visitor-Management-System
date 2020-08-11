package com.infodart.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	private String Name, Username, Password, CreatedBy, ModifiedBy;

	private int Activeflag;
	private String Role;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date CreatedTimestamp;
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date ModifiedTimestamp;
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
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
	public int getActiveflag() {
		return Activeflag;
	}
	public void setActiveflag(int activeflag) {
		Activeflag = activeflag;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
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
	public Login(String name, String username, String password, String createdBy, String modifiedBy, int activeflag,
			String role, Date createdTimestamp, Date modifiedTimestamp) {
		super();
		Name = name;
		Username = username;
		Password = password;
		CreatedBy = createdBy;
		ModifiedBy = modifiedBy;
		Activeflag = activeflag;
		Role = role;
		CreatedTimestamp = createdTimestamp;
		ModifiedTimestamp = modifiedTimestamp;
	}
	public Login() {
		super();
	}
	public Login(String name, String uname, String pass) {
		super();
		Name = name;
		Username = uname;
		Password = pass;
		
	}
	public Object loginid(int id) {
		ID = id;
	return ID;
}

	
	
}
