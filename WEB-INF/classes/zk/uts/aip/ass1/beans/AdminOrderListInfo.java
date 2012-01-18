package zk.uts.aip.ass1.beans;

import java.io.Serializable;

public class AdminOrderListInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ordid;
	private String surname;
	private String country;
	private int postcode;
	private double grandtotal;
	private String status;

	public int getOrdid() {
		return ordid;
	}

	public void setOrdid(int ordid) {
		this.ordid = ordid;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public double getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(double grandtotal) {
		this.grandtotal = grandtotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
