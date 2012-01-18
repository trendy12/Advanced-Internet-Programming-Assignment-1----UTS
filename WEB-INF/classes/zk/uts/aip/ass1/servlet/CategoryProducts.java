package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk.uts.aip.ass1.beans.*;

/**
 * Servlet implementation class CategoryProducts
 */
public class CategoryProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryProducts() {
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
		String catid = request.getParameter("categoryId");
		int catIDD = Integer.parseInt(catid);
		Dataconn o = new Dataconn();
		try {
			o.connect();
			//get products information
			ResultSet rs = o
					.query("select code, title, description, price from catalog natural join catalog_categories where category="
							+ catIDD);
			//get category name
			ResultSet rs2 = o
					.query("select name from category where category ="
							+ catIDD);
			List<Product> temp = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				product.setCode(Integer.parseInt(rs.getString("code")));
				product.setDescription(rs.getString("description"));
				product.setTitle(rs.getString("title"));
				product.setPrice(Double.parseDouble(rs.getString("price")));
				temp.add(product);

			}
			rs2.next();
			String catname = rs2.getString("name");
			request.setAttribute("catname", catname);
			request.setAttribute("CategoryProducts", temp);
			String url = "/categoryproducts.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
