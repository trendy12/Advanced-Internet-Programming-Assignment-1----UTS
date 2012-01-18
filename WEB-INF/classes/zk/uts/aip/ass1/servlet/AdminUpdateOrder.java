package zk.uts.aip.ass1.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zk.uts.aip.ass1.beans.Dataconn;

/**
 * Servlet implementation class AdminUpdateOrder
 */
public class AdminUpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdateOrder() {
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
		int receiptno = Integer.parseInt(request.getParameter("receiptno"));
		String status = request.getParameter("status");
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		Dataconn o = new Dataconn();
		try {
			o.connect();
			String update = "update ordermain set status='" + status
					+ "', receipt='" + receiptno + "' where ordid=" + orderid;
			o.query(update);
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("orderid", orderid);
		String url = "/admin/AOS";
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
