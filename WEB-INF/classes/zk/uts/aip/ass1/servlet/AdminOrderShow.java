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
 * Servlet implementation class AdminOrderShow
 */
public class AdminOrderShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOrderShow() {
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
		int orderId = Integer.parseInt(request.getParameter("orderid"));
		Vector<Product> products = new Vector<Product>();
		try {
			o.connect();
			String exist = "select * from ordermain where ordid =" + orderId;
			ResultSet rs = o.query(exist);
			if (rs.next()) {
				// set orderid status and receipt
				Order order = new Order();
				order.setOrdid(orderId);
				order.setReceipt(Integer.parseInt(rs.getString("receipt")));
				order.setStatus(rs.getString("status"));
				// get customer id
				int cusid = Integer.parseInt(rs.getString("cusid"));
				// get customer info
				String getcus = "select * from customer where cusid = " + cusid;
				ResultSet rs2 = o.query(getcus);
				rs2.next();
				Customer customer = new Customer();
				customer.setSn(rs2.getString("sn"));
				customer.setGn(rs2.getString("gn"));
				customer.setEmail(rs2.getString("email"));
				// get addid
				int addid = Integer.parseInt(rs2.getString("addid"));
				// get address info
				String getadd = "select * from address where addid = " + addid;
				ResultSet rs3 = o.query(getadd);
				rs3.next();
				Address address = new Address();
				address.setUnhuno(rs3.getString("unhuno"));
				address.setStreet(rs3.getString("street"));
				address.setStates(rs3.getString("states"));
				address.setSuburb(rs3.getString("suburb"));
				address.setPostcode(Integer.parseInt(rs3.getString("postcode")));
				address.setCountry(rs3.getString("country"));
				// get order product detail
				String getProducts = "select * from orderpro where ordid="
						+ orderId;
				ResultSet rs4 = o.query(getProducts);
				while (rs4.next()) { // for each product in order list
					String ProCode = rs4.getString("code");
					String productQTY = rs4.getString("proqty");
					String getProSql = "select * from catalog where code='"
							+ ProCode + "'";
					ResultSet rs5 = o.query(getProSql);
					rs5.next(); // for a product
					String getcate = "select name from catalog_categories natural join category where code ="
							+ ProCode;
					ResultSet rs6 = o.query(getcate);
					Product ordPro = new Product();
					ordPro.setTitle(rs5.getString("title"));
					ordPro.setPrice(Double.parseDouble(rs5.getString("price")));
					ordPro.setDescription(rs5.getString("description"));
					ordPro.setQty(Integer.parseInt(productQTY));
					ordPro.setCode(Integer.parseInt(ProCode));
					rs6.next();
					ordPro.setCat(rs6.getString("name"));
					products.addElement(ordPro);

				}
				request.setAttribute("products", products);
				request.setAttribute("customer", customer);
				request.setAttribute("address", address);
				request.setAttribute("order", order);
				String url = "/admin/adminordershow.jsp";
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher(url);
				rd.forward(request, response);

			} else {
				String nothing = "nothing";
				String urlno = "/admin/adminordershow.jsp?nothing=" + nothing;
				ServletContext scno = getServletContext();
				RequestDispatcher rdno = scno.getRequestDispatcher(urlno);
				rdno.forward(request, response);
			}
			o.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
