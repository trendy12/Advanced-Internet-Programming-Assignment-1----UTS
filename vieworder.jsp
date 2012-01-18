<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View your order  -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.vieworderform.orderid.value=="")
{
    alert("Please enter the order id!");
    return false;
}
var numberRegod=new RegExp('^[1-9]+$');   
var valiod=numberRegod.test(document.vieworderform.orderid.value);   
if (valiod==false)
{  
 alert("Please enter correct order id!"); 
 return false;
}
if (document.vieworderform.surname.value=="")
{
    alert("Please enter the surname!");
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
	<h3>Please enter the order id and your surname to check the order. Thank you!</h3>
<form name="vieworderform" method="post" action="VO"  onsubmit="return validate()">
<table><tr><td>Order ID:</td><td><input type="text" name="orderid"></td></tr>
	   <tr><td>Surname:</td><td><input type="text" name="surname"></td></tr>
	   <tr><td></td>
	   					   <td><input type="submit" name="submit" value="Submit"></td>
	   </tr>
</table>
</form>
</body>
</html>