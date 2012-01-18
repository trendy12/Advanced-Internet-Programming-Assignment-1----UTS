package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zk.uts.aip.ass1.beans.*;

/**
 * Servlet implementation class PurchaseEnterInfo
 */
public class PurchaseEnterInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseEnterInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Dataconn o = new Dataconn();
		// get parameter
		String action = request.getParameter("action");
		// get session shopping cart
		HttpSession session = request.getSession();
		Vector<Product> cartlist = (Vector<Product>) session
				.getAttribute("cartlist");
		// do things
		//first cancel orders
		if (action.equals("cancel")) {
			cartlist.clear();
			String url = "/LC";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
		//if proceed
		} else if (action.equals("Proceed")) {
			String surname = request.getParameter("surname");
			String givenName = request.getParameter("givenName");
			String emailAddress = request.getParameter("emailAddress");
			String unitNumber = request.getParameter("unitNumber");
			String street = request.getParameter("street");
			String state = request.getParameter("state");
			String suburb = request.getParameter("suburb");
			int postCode = Integer.parseInt(request.getParameter("postcode"));
			String country = request.getParameter("country");
			
			//generate random receipt no
			Random rr = new Random();
			int recno = Math.abs(rr.nextInt() % 65535) + 1;
			//set status default PAID
			String status = "PAID";
			//check if there is a credit card no
			String ccno = request.getParameter("ccno");
			//if credit card no does not exist, change to ORDERED
			if (null == ccno || "".equals(ccno)) {
				status = "ORDERED";
			}
			try {
				o.connect();
				// insert address
				String insertaddress = "insert into address (unhuno, street, states, suburb, postcode, country) values('"
						+ unitNumber
						+ "','"
						+ street
						+ "','"
						+ state
						+ "','"
						+ suburb + "'," + postCode + ",'" + country + "')";
				o.update(insertaddress);
				// get addid from address
				ResultSet addidrs = o
						.query("select max(addid)addid from address");
				addidrs.next();
				int addidd = addidrs.getInt("addid");
				// insert customer
				String insertcus = "insert into customer (sn, gn, email, addid) values('"
						+ surname
						+ "','"
						+ givenName
						+ "','"
						+ emailAddress
						+ "'," + addidd + ")";
				o.update(insertcus);
				// get cusid from customer
				ResultSet cusidrs = o
						.query("select max(cusid)cusid from customer");
				cusidrs.next();
				int cusidd = cusidrs.getInt("cusid");
				// insert ordermain
				String insertordm = "insert into ordermain (cusid, status, receipt) values("
						+ cusidd + ",'" + status + "'," + recno + ")";
				o.update(insertordm);
				// get orderid from ordermain
				ResultSet ordidrs = o
						.query("select max(ordid)ordid from ordermain");
				ordidrs.next();
				int ordidd = ordidrs.getInt("ordid");
				// insert orderpro
				for (int count1 = 0; count1 < cartlist.size(); count1++) {
					Product productOrder = (Product) cartlist.elementAt(count1);
					String insertordp = "insert into orderpro (ordid, code, proqty) values("
							+ ordidd
							+ ","
							+ productOrder.getCode()
							+ ","
							+ productOrder.getQty() + ")";
					o.update(insertordp);
				}
				//jump
				String url = "/successpurchase.jsp?orderid=" + ordidd;
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher(url);
				rd.forward(request, response);
				o.close();
				cartlist.clear();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
