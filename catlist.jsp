<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categroies  -- Powerhouse Museum</title>
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
	<tr>
		<th><b>Categories</b>
		</th></tr>
		<%
			List ls = (List) request.getAttribute("categories");
			Iterator it = ls.iterator();
			while (it.hasNext()) {
				Categories ca = (Categories) it.next();
		%>

		<tr>
			<td><a href="CP?categoryId=<%=ca.getCatID()%>"><%=ca.getCatName()%></a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>