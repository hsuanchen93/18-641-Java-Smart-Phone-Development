<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Configuration Proof of Concept</title>
</head>
<body>

<p>Here is what you selected:</p>

<%! //declaration
	String model = "";
	ArrayList<String> optset = new ArrayList<String>();
	String option = "";
	int totalPrice = 0;
%>

<% //get user selection
Enumeration enParams = request.getParameterNames(); 
while(enParams.hasMoreElements()) {
	String paramName = (String)enParams.nextElement();
	if(paramName.equals("model")) {
		model = request.getParameter("model");
	}
	else {
		optset.add(request.getParameter(paramName));
	}
}
%>

<table border="1" style="width:50%">	
	<tr>
		<td><%= model.split(":")[0] %></td>            
   		<td>Base Price</td>
   		<td><%= Double.parseDouble(model.split(":")[1]) %></td>
   		<% totalPrice += Double.parseDouble(model.split(":")[1]); %>
	</tr>
	
<% //fill out the table with options
	while(optset.size()>0) {
		option = optset.get(0);
%>
	<tr>
		<td><%= option.split(":")[0] %></td>            
   		<td><%= option.split(":")[1] %></td>
   		<td><%= Double.parseDouble(option.split(":")[2]) %></td>
   		<% totalPrice += Double.parseDouble(option.split(":")[2]); %>
	</tr>
<%
		optset.remove(0);
	}
%>

	<tr>
		<td><b>Total Cost</b></td>            
   		<td></td>
   		<td><b>$<%= totalPrice %></b></td>
	</tr>
	
<% totalPrice = 0; %>
	
</table>


</body>
</html>