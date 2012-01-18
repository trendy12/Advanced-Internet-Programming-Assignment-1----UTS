package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk.uts.aip.ass1.beans.*;

/**
 * Servlet implementation class AdminList
 */
public class AdminList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminList() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Dataconn o = new Dataconn();
		// Prepare for order list
		Vector<AdminOrderListInfo> adminOrderList = new Vector<AdminOrderListInfo>();
		try {
			o.connect();
			String getallorders = "select * from ordermain where status <> 'SENT'";
			ResultSet rs = o.query(getallorders);
			while (rs.next()) { // for each order
				// new adminorderlistinfo
				AdminOrderListInfo adminorderinfo = new AdminOrderListInfo();
				// get order info
				adminorderinfo
						.setOrdid(Integer.parseInt(rs.getString("ordid")));
				adminorderinfo.setStatus(rs.getString("status"));
				int ordid = Integer.parseInt(rs.getString("ordid"));
				// get customer id
				int cusid = Integer.parseInt(rs.getString("cusid"));
				// get customer info
				String getcus = "select * from customer where cusid = " + cusid;
				ResultSet rs2 = o.query(getcus);
				rs2.next();
				adminorderinfo.setSurname(rs2.getString("sn"));
				// get addid
				int addid = Integer.parseInt(rs2.getString("addid"));
				// get address info
				String getadd = "select * from address where addid = " + addid;
				ResultSet rs3 = o.query(getadd);
				rs3.next();
				adminorderinfo.setCountry(rs3.getString("country"));
				adminorderinfo.setPostcode(Integer.parseInt(rs3
						.getString("postcode")));
				// get grandtotal of an order
				String getProducts = "select * from orderpro natural join catalog where ordid="
						+ ordid;
				ResultSet rs4 = o.query(getProducts);
				double subTotal = 0;
				while (rs4.next()) { // for each product in order list
					double price = Double.parseDouble(rs4.getString("price"));
					int qty = Integer.parseInt(rs4.getString("proqty"));
					double total = price * qty;
					subTotal += total;
				}
				// set total
				adminorderinfo.setGrandtotal(subTotal);
				adminOrderList.addElement(adminorderinfo);
			}
			request.setAttribute("adminOrderList", adminOrderList);
			String url = "/admin/adminlist.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);

			o.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
