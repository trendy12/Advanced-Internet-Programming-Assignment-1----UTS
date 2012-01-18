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

import zk.uts.aip.ass1.beans.Dataconn;
import zk.uts.aip.ass1.beans.Product;

/**
 * Servlet implementation class ViewOrder
 */
public class ViewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewOrder() {
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
		String surname = request.getParameter("surname");

		Vector<Product> orderedPdList = new Vector<Product>();
		String status = null;


		try {
			o.connect();
			//see if it exist
			String exist = "select * from ordermain natural join customer where ordid ="
					+ orderId + " and sn='" + surname + "'";
			ResultSet rs = o.query(exist);
			if (rs.next()) {
				//get order information
				String getProductSql = "select * from orderpro where ordid="
						+ orderId;
				ResultSet rs2 = o.query(getProductSql);
				while (rs2.next()) {
					String PdCode = rs2.getString("code");
					String productQTY = rs2.getString("proqty");
					//get products information
					String getPDSql = "select * from catalog where code='"
							+ PdCode + "'";
					ResultSet rs3 = o.query(getPDSql);
					if (rs3.next()) {
						//for each product, get information
						String getcate = "select name from catalog_categories natural join category where code ="
								+ PdCode;
						ResultSet rs4 = o.query(getcate);
						Product orderedPD = new Product();
						orderedPD.setTitle(rs3.getString("title"));
						orderedPD.setPrice(Double.parseDouble(rs3
								.getString("price")));
						orderedPD.setDescription(rs3.getString("description"));
						orderedPD.setQty(Integer.parseInt(productQTY));
						orderedPD.setCode(Integer.parseInt(PdCode));
						rs4.next();
						orderedPD.setCat(rs4.getString("name"));
						orderedPdList.addElement(orderedPD);
					}
				}
				status = rs.getString("status");
				request.setAttribute("orderedPdList", orderedPdList);
				String url = "/orderpage.jsp?status=" + status;
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher(url);
				rd.forward(request, response);
			} else {
				String nothing = "nothing";
				String url = "/orderpage.jsp?nothing=" + nothing;
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher(url);
				rd.forward(request, response);
			}

			o.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
