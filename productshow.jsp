<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product  -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.shoppingform.quantity.value=="")
{
    alert("Please enter the quantity!");
    return false;
}
var numberReg=new RegExp('^[1-9]+$');   
var valiquan=numberReg.test(document.shoppingform.quantity.value);   
if (valiquan==false)
{  
 alert("Please enter correct quantity!"); 
 return false;
}
 return true;
 
}
</script>
</head>
<body>
	<h1>Powerhouse Museum</h1>
	<a href="index.html">Index</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="LC">Brower Products List</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="vieworder.jsp">Checking order</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="admin/index.jsp">Admin Login</a>
	<br>
	<br>
	<br><div align=center>
<% Product product=(Product) request.getAttribute("product");
   Categories productCategory=(Categories) request.getAttribute("categories");
%>
<form name="shoppingform" action="SC?productId=<%=product.getCode() %>" method="post"  onsubmit="return validate()">
<table border="1">
<tr><th>Code</th><th>Title</th><th>Description</th><th>Price</th><th>Quantity</th></tr>
<tr><td><%=product.getCode() %></td>
	<td><%=product.getTitle() %></td>
	<td><%=product.getDescription() %></td>
	<td>$<%=product.getPrice() %></td>
	<td><input type="text" name="quantity" size="3" value="1"></td>
</tr>
</table><br><br>
 <input type="hidden" name="action" value="add">
 <input type="submit" name="Submit" value="Add to Cart"></div>

</form>
</body>
</html>