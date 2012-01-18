<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.*, zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Vector<AdminOrderListInfo> adminOrderList=(Vector<AdminOrderListInfo>) request.getAttribute("adminOrderList");  %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin check order list  -- Powerhouse Museum</title>
</head>
<body>
	<h1>Powerhouse Museum Admin Pages!</h1>
	<h3><a href=../index.html>Index</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=index.jsp>Admin Main Page</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="AL">View all orders</a></h3>
<table border="1">
	<tr align="center">
		<th>Orderid</th>
		<th>Surname</th>
		<th>Country</th>
		<th>Postcode</th>
		<th>GrandTotal</th>
		<th>Status</th>
		<th>Link</th>
	</tr>
	<%  for(int index=0; index < adminOrderList.size(); index++) {
			AdminOrderListInfo adminorderlistinfo = (AdminOrderListInfo) adminOrderList.elementAt(index);
	%>
	<tr>
		<td><%=adminorderlistinfo.getOrdid()%></td>
		<td><%=adminorderlistinfo.getSurname() %></td>
		<td><%=adminorderlistinfo.getCountry() %></td>
		<td><%=adminorderlistinfo.getPostcode() %></td>
		<td>$<%=adminorderlistinfo.getGrandtotal() %></td>
		<td><%=adminorderlistinfo.getStatus() %></td>
		<td><a href="AOS?orderid=<%=adminorderlistinfo.getOrdid()%>">jump to the order</a></td>
	</tr>	
	<%} %>		
</table>
</body>
</html>