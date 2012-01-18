<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Main Page  -- Powerhouse Museum</title>
<script language="javascript">
function validate()
{
if (document.adminvieworderform.orderid.value=="")
{
    alert("Please enter the order id!");
    return false;
}
var numberRegod=new RegExp('^[1-9]+$');   
var valiod=numberRegod.test(document.adminvieworderform.orderid.value);   
if (valiod==false)
{  
 alert("Please enter correct order id!"); 
 return false;
}
 return true;
 
}
</script>
</head>
<body>
	<h1>Powerhouse Museum Admin Pages!</h1>
	<h3><a href=../index.html>Index</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=index.jsp>Admin Main Page</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="AL">View all orders</a></h3>
	
<form name="adminvieworderform" method="post" action="AOS"  onsubmit="return validate()">
<table border=1>
<tr  align="center"><td colspan=2>Manually open an order</td></tr>
<tr><td>Order ID:</td><td><input type="text" name="orderid" ></td></tr>
			<tr>
				<td></td>
				
				<td><input type="submit" name="submit" value="Submit">
				</td>
			</tr>
		</table>
</form>
</body>
</html>