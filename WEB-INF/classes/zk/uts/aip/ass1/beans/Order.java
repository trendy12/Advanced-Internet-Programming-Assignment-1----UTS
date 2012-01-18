package zk.uts.aip.ass1.beans;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getOrdid() {
		return ordid;
	}

	public void setOrdid(int ordid) {
		this.ordid = ordid;
	}

	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getReceipt() {
		return receipt;
	}

	public void setReceipt(int receipt) {
		this.receipt = receipt;
	}

	private int ordid;
	private int cusid;
	private String status;
	private int receipt;
}
