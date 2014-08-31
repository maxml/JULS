package com.juls.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
@Table(name="user")
@Component
@Scope("session")
public class User implements Serializable{
	
	public static final int REGISTERED = 1;
	public static final int UNCONFIRMED = 0;
	
	public User(){
		
	}
	
	public User(String email, String password){
		setId(UUID.randomUUID().toString());
		setEmail(email);
		setPassword(password);
		setRegStatus(UNCONFIRMED);
	}
	
	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private int regStatus;
	
	@Column(unique = true)
	private String token;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn
	private UserDetails additionalInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDetails getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(UserDetails additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public int getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(int regStatus) {
		this.regStatus = regStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
