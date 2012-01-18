package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk.uts.aip.ass1.beans.*;

/**
 * Servlet implementation class Product
 */
public class ProductShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductShow() {
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
		String proid = request.getParameter("productId");
		int proIDD = Integer.parseInt(proid);
		Dataconn o = new Dataconn();
		try {
			o.connect();
			//get product informarion
			ResultSet rs = o
					.query("select * from catalog where code=" + proIDD);
			rs.next();
			Product product = new Product();
			product.setCode(proIDD);
			product.setDescription(rs.getString("description"));
			product.setPrice(Double.parseDouble(rs.getString("price")));
			product.setTitle(rs.getString("title"));

			request.setAttribute("product", product);
			String url = "/productshow.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
