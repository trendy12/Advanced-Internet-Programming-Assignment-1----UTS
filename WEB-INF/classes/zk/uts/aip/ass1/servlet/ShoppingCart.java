package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk.uts.aip.ass1.beans.*;

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShoppingCart
 */
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Dataconn o = new Dataconn();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		Vector<Product> cartlist = (Vector<Product>) session
				.getAttribute("cartlist");
		//check what do you want to do
		//check confirms
		if (!action.equals("confirm")) {
			//if delete
			if (action.equals("delete")) {
				String del = request.getParameter("deleteIndex");
				int d = (new Integer(del)).intValue();
				cartlist.removeElementAt(d);
				//if add
			} else if (action.equals("add")) {
				boolean match = false;
				int productID = Integer.parseInt(request
						.getParameter("productId"));
				Product product = new Product();
				try {
					o.connect();
					ResultSet rs = o.query("SELECT * FROM catalog where code="
							+ productID);
					while (rs.next()) {
						product.setCode(productID);
						product.setDescription(rs.getString("description"));
						product.setPrice(Double.parseDouble(rs
								.getString("price")));
						product.setTitle(rs.getString("title"));
					}
					o.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				int productQTY = Integer.parseInt(request
						.getParameter("quantity"));
				product.setQty(productQTY);
				//if empty, add the first product to the shopping cart
				if (cartlist == null) {
					cartlist = new Vector<Product>();
					cartlist.addElement(product);
				} else {
					//otherwise add to the end of array
					for (int i = 0; i < cartlist.size(); i++) {
						Product testProduct = (Product) cartlist.elementAt(i);
						if (testProduct.getCode() == (product.getCode())) {
							testProduct.setQty(testProduct.getQty()
									+ product.getQty());
							cartlist.setElementAt(testProduct, i);
							match = true;
							break;
						}
					}
					//check same product, if new, just add
					if (!match)
						cartlist.addElement(product);
				}
				//change quantity
			} else if (action.equals("modify")) {
				String modify = request.getParameter("modifyIndex");
				String quantity = request.getParameter("quantity");
				int modifyIndex = Integer.parseInt(modify);
				int intQuantity = Integer.parseInt(quantity);
				Product tempProduct = (Product) cartlist.elementAt(modifyIndex);
				tempProduct.setQty(intQuantity);
				cartlist.setElementAt(tempProduct, modifyIndex);
			} else if (action.equals("cancel")) {
				cartlist.clear();
			}

			session.setAttribute("cartlist", cartlist);
			String url = "/shoppingcart.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);

		} else if (action.equals("confirm")) {
			session.setAttribute("cartlist", cartlist);
			String url = "/orderconfirm.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
		}

	}
}
