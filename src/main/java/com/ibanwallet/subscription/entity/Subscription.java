package com.ibanwallet.subscription.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Subscription  {
	
	//private static final long serialVersionUID = 4130985178082136408L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long newsletterID;
	
    private String email;
    private String firstName;
    private String gender;
    private Date dateOfBirth;
    private boolean consentFlag;

    public enum Gender{
        MALE,
        FEMALE
    }

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

    public Long getNewsletterID() {
		return newsletterID;
	}
    
    
}
