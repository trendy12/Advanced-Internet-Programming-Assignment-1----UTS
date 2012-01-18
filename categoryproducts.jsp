<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,zk.uts.aip.ass1.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProductsList Under A categroy  -- Powerhouse Museum</title>
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

<table border=1>     
<th>Product Id</th><th>Product Name</th><th>Description</th><th>Price</th><th>Link to PHM</th> 
					<% List ls  = (List)request.getAttribute("CategoryProducts");
						Iterator it = ls.iterator();
						String catname = (String)request.getAttribute("catname");
						while(it.hasNext()){
						Product pro = (Product)it.next();
						%>
<tr><td>
<%=pro.getCode()%></td><td>
<a href="PS?productId=<%=pro.getCode() %>">
<%=pro.getTitle() %></a></td><td>
<%=pro.getDescription() %></td><td>
$<%=pro.getPrice() %></td>
<td><a target=_blank href="http://www.powerhousemuseum.com/collection/database/?irn=<%=pro.getCode() %>">Jump To</a></td></tr>

<% }%>
					</table><br><br>
						<b>Category: &nbsp;<%=catname %></b>
</body>
</html>