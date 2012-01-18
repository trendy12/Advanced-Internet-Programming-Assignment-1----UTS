package zk.uts.aip.ass1.beans;

import java.io.Serializable;

public class Categories implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int catID;
	private String catName;

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
}
