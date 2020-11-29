package com.ibanwallet.subscription.dto;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SubscriptionDTO {

	
	@Email(message="Please provide a valid email address")
    private String email;
	
    private String firstName;
    private String gender;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message="The name is required")
    private Date dateOfBirth;
    
    @AssertTrue(message = "You must accept the terms before.")
	private boolean consentFlag;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public boolean isConsentFlag() {
		return consentFlag;
	}
	public void setConsentFlag(boolean consentFlag) {
		this.consentFlag = consentFlag;
	}

    
    
}
