<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.modifyform.quantity.value=="")
{
    alert("Please enter the quantity!");
    return false;
}
var numberReg=new RegExp('^[1-9]+$');   
var valiquan=numberReg.test(document.modifyform.quantity.value);   
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
	<br>
	<%
		Vector<Product> cartlist = (Vector<Product>) session
				.getAttribute("cartlist");
	%>
	<%
		if (cartlist != null && (cartlist.size() > 0)) {
			double subTotal = 0;
	%>
	<center>
		<table border="1">
			<tr align="center">
				<th>Code</th>
				<th>Title</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
				<th>Change</th>
				<th>Delete</th>
			</tr>
			<%
				for (int index = 0; index < cartlist.size(); index++) {
						Product productOrder = (Product) cartlist.elementAt(index);
			%>
			<tr align="left">
				<td><%=productOrder.getCode()%></td>
				<td><%=productOrder.getTitle()%></td>
				<td><%=productOrder.getDescription()%></td>
				<td>$<%=productOrder.getPrice()%></td>
				<td><%=productOrder.getQty()%></td>
				<td>$<%=productOrder.getPrice() * productOrder.getQty()%></td>
				<td>
					<form name="modifyform" action="SC" method="post" onsubmit="return validate()">
						<input type="text" name="quantity" size="3" value="1"> <input
							type="hidden" name="modifyIndex" value="<%=index%>"> <input
							type="hidden" name="action" value="modify"> <input
							type="submit" value="Modify" >
					</form>
				<td>
					<form name="deleteform" action="SC" method="POST">
						<input type="submit" value="Delete"> <input type="hidden"
							name="deleteIndex" value='<%=index%>'> <input
							type="hidden" name="action" value="delete">
					</form></td>
					
			</tr>
			<%
				subTotal += productOrder.getPrice() * productOrder.getQty();
					}
					session.setAttribute("subTotal", subTotal);
			%>

			<tr align="center">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>SubTotal</td>
				<td>$<%=subTotal%></td>
				<td></td>
					<td></td>
			</tr>
		</table>
		<table>
			<tr align="center">
				<td>
					<form name="clearform" action="SC" method="post">
						<input type="hidden" name="action" value="cancel"> <input
							type="submit" name="cancel" value="Cancel">
					</form>
				</td>
				<td>
					<form name="confirmform" action="SC" method="post">
						<input type="hidden" name="action" value="confirm"> <input
							type="submit" name="Confirm" value="Confirm">
					</form>
				</td>
			</tr>
		</table>
	</center>
	<%
		} else {
	%>
	<p>Shopping cart is empty!</p>
	<%
		}
	%>

</body>
</html>