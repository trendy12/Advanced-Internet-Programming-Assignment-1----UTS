<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,zk.uts.aip.ass1.beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Information and Show Shopping Cart  -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.placeorder.surname.value=="")
{
    alert("Please enter the surname!");
    return false;
}
if (document.placeorder.givenName.value=="")
{
    alert("Please enter the givenName!");
    return false;
}
if (document.placeorder.unitNumber.value=="")
{
    alert("Please enter the unitNumber!");
    return false;
}
if (document.placeorder.street.value=="")
{
    alert("Please enter the street!");
    return false;
}
if (document.placeorder.state.value=="")
{
    alert("Please enter the state!");
    return false;
}
if (document.placeorder.suburb.value=="")
{
    alert("Please enter the suburb!");
    return false;
}
if (document.placeorder.postcode.value=="")
{
    alert("Please enter the postcode!");
    return false;
}
var numberRegp=new RegExp('^[1-9]+$');   
var valipostcode=numberRegp.test(document.placeorder.postcode.value);   
if (valipostcode==false)
{  
 alert("Please enter correct postcode!"); 
 return false;
}
if (document.placeorder.country.value=="")
{
    alert("Please enter the country!");
    return false;
}
if (document.placeorder.emailAddress.value=="")
{
    alert("Please enter the emailAddress!");
    return false;
}
if (document.placeorder.paymentName.value=="")
{
    alert("Please enter the paymentName!");
    return false;
}
if (document.placeorder.ccno.value=="")
{
    alert("Please enter the credit card!");
    return false;
}
var numberRegc=new RegExp('^[1-9][0-9]{15}$');   
var valiccno=numberRegc.test(document.placeorder.ccno.value);   
if (valiccno==false)
{  
 alert("Please enter correct credit number!"); 
 return false;
}
if (document.placeorder.expiry_date.value=="")
{
    alert("Please enter the expiry date!");
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
	double subTotal = 0;
%>
	<form name="placeorder" method="post" action="PEI"  onsubmit="return validate()">
		<table>
			<tr>
				<td><b>Personal details:</b>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Surname:</td>
				<td><input name="surname" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>Given name:</td>
				<td><input name="givenName" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td><b>Address:</b>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>House/Unit No:</td>
				<td><input name="unitNumber" type="text" size="10">
				</td>
			</tr>
			<tr>
				<td>Street:</td>
				<td><input name="street" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>State:</td>
				<td><input name="state" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>Suburb:</td>
				<td><input name="suburb" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>Post code:</td>
				<td><input name="postcode" type="text" size="4">
				</td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><input name="country" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>E-mail Address:</td>
				<td><input name="emailAddress" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td><b>Payment details:</b>
				</td>
				<td>Entering payment detail is optional, <br />but will be
					validated if entered</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input name="paymentName" type="text" size="30">
				</td>
			</tr>
			<tr>
				<td>Credit Card</td>
				<td><select name="paymentType" size="1">
						<option value="American_Express">American Express</option>
						<option value="Diners_Club">Diners Club</option>
						<option value="Mastercard">Master card</option>
						<option value="Visa">Visa</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>Credit Card No:</td>
				<td><input name=ccno type="text" size="16">
				</td>
			</tr>
			<tr>
				<td>Expiry Date:</td>
				<td><input name="expiry_date" type="text" size="10">
					(mm/yy)</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="action" value="Proceed">	</form>
				</td>
			</tr>

			<tr>
				<td></td>
				<td><form name="canelform" method="post" action="PEI"  ><input type="submit" name="action" value="cancel"></form>
				</td>
			</tr>

		</table>

	<h3>Products in your Shopping Cart</h3>
	<table border="1">
		<tr align="center">
			<th>Code</th>
			<th>Title</th>
			<th>Description</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Total</th>
		</tr>
		<%
			for (int index = 0; index < cartlist.size(); index++) {
				Product productOrder = (Product) cartlist.elementAt(index);
		%>
		<tr align="left">
			<td><%=productOrder.getCode()%></td>
			<td><%=productOrder.getTitle()%></td>
			<td><%=productOrder.getDescription()%></td>
			<td><%=productOrder.getPrice()%></td>
			<td><%=productOrder.getQty()%></td>
			<td><%=productOrder.getPrice() * productOrder.getQty()%></td>
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
			<td><%=subTotal%></td>
		</tr>
	</table>
</body>
</html>