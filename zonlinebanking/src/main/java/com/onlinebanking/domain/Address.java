package com.onlinebanking.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

@Embeddable
@Validated
public class Address {

	@NotEmpty
	private String addressline1;
	
	
	private String addressline2;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String zip;

	public Address() {
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	
}
