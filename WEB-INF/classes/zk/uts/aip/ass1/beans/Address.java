package zk.uts.aip.ass1.beans;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int addid;
	private String unhuno;
	private String street;
	private String states;
	private String suburb;
	private int postcode;
	private String country;

	public int getAddid() {
		return addid;
	}

	public void setAddid(int addid) {
		this.addid = addid;
	}

	public String getUnhuno() {
		return unhuno;
	}

	public void setUnhuno(String unhuno) {
		this.unhuno = unhuno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
