package com.shreeganesh.hibernate.Hibernate003.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String city;
	
	private String country;
	
	private String addressLine1;
	
	private String addressLine2;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + "]";
	}

	
	
	
	
}
