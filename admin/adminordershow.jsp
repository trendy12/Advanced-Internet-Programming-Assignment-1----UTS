<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.util.*, zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% Vector<Product> products=(Vector<Product>) request.getAttribute("products");  
	Customer customer = (Customer) request.getAttribute("customer");
	Order order = (Order) request.getAttribute("order");
	Address address = (Address) request.getAttribute("address");
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin See your order  -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.adminupdate.receiptno.value=="")
{
    alert("Please enter the receipt no!");
    return false;
}
var numberRegrc=new RegExp('^[1-9][0-9]{5}$');   
var valirc=numberRegrc.test(document.adminupdate.receiptno.value);   
if (valirc==false)
{  
 alert("Please enter correct receipt no!"); 
 return false;
}
 return true;
 
}
</script>
</head>
<body>
	<h1>Powerhouse Museum Admin Pages!</h1>
	<h3><a href=../index.html>Index</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=index.jsp>Admin Main Page</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="AL">View all orders</a></h3>
	<%String nothing = request.getParameter("nothing");%>
	<%
		if (nothing == null || nothing =="") {
	%>
<form action="AUO" name="adminupdate" method="post" onsubmit="return validate()">
<b>Order ID: </b><%=order.getOrdid() %>&nbsp;&nbsp;&nbsp;<b>Receipt No: </b><input type="text" name="receiptno" value="<%=order.getReceipt() %>">&nbsp;&nbsp;
<b>Status: </b><select name="status">
			<option <%= "ORDERED".equals(order.getStatus()) ? "selected=\"selected\"" : ""  %> value="ORDERED">ORDERED</option>
			<option <%= "PAID".equals(order.getStatus()) ? "selected=\"selected\"" : ""  %> value="PAID">PAID</option>
			<option <%= "SENT".equals(order.getStatus()) ? "selected=\"selected\"" : ""  %> value="SENT">SENT</option>
			</select>&nbsp;&nbsp;
			<input type="submit" name="confirm" value="Update">
<input type="hidden" name="orderid" value="<%=order.getOrdid() %>"><br/><br/>
<b>Name: </b><%=customer.getSn() %> <%=customer.getGn() %>&nbsp;&nbsp;
<b>Email: </b><%=customer.getEmail() %>&nbsp;&nbsp;
<b>Address: </b><%=address.getUnhuno() %> <%=address.getStreet() %> <%=address.getSuburb() %> <%=address.getStates() %> <%=address.getPostcode() %> <%=address.getCountry() %>
</form><br/>

<table border="1">
	<tr  align="center">
		<th>Category</th>
		<th>Product code</th>
		<th>Description</th>
		<th>Quantity</th>
		<th>Total</th>
	</tr>
	<%  double subTotal=0;
		for(int index=0; index < products.size(); index++) {
			Product product=(Product) products.elementAt(index);
			double total=product.getPrice()*product.getQty();
	%>
	<tr>
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