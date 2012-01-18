<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.*, zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Vector<Product> orderedPdList=(Vector<Product>) request.getAttribute("orderedPdList");  %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>See your order  -- Powerhouse Museum</title>
</head>
<body>
	<h1>Powerhouse Museum</h1>
	<a href="index.html">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="LC">Brower Products List</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="vieworder.jsp">Checking order</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="admin/index.jsp">Admin Login</a>
	<br>
	<br>
	<br>
	<%String nothing = request.getParameter("nothing");%>
	<%
		if (nothing == null || nothing =="") {
	%>
<h2>This order's status is <%=request.getParameter("status")%>.</h2>
<table border="1">
	<tr align="center">
		<th>Category</th>
		<th>Product code</th>
		<th>Description</th>
		<th>Quantity</th>
		<th>Total</th>
	</tr>
	<%  double subTotal=0;
		for(int index=0; index < orderedPdList.size(); index++) {
			Product product=(Product) orderedPdList.elementAt(index);
			double total=product.getPrice()*product.getQty();
	%>
		<tr align="left">
			<td><%=product.getCat()%></td>
			<td><%=product.getCode() %></td>
			<td><%=product.getDescription() %></td>
			<td><%=product.getQty() %></td>
			<td>$<%=total %></td>
		</tr>
		<% subTotal+=total;
		}
	 %>
	<tr>
			 <td colspan=4 align="right">SubTotal:</td>
			 <td>$<%=subTotal %></td>

    </tr>			
</table>
<%
		} else {
	%>
	<p>This order does not exist!</p>
	<%
		}
	%>
</body>
</html>