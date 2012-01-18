package zk.uts.aip.ass1.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import zk.uts.aip.ass1.beans.*;

/**
 * Servlet implementation class ListCat
 */
public class ListCat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListCat() {
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
		try {
			o.connect();
			//get categories
			ResultSet rs = o.query("select * from category");
			List<Categories> catelist = new ArrayList<Categories>();
			while (rs.next()) {
				Categories category = new Categories();
				category.setCatID(Integer.parseInt(rs.getString("category")));
				String name = rs.getString("name");
				category.setCatName(name);
				catelist.add(category);
			}
			request.setAttribute("categories", catelist);
			String url = "/catlist.jsp";
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
