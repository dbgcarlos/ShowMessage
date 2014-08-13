<%@ page language="java"
	import="java.util.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%
	String new_string = (String)request.getAttribute("newString");
	String etag = (String)session.getAttribute("Etag");
	if(null != etag)
		request.setAttribute("etag",etag);
	if(null == new_string)
		new_string = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
        #finger 
        { 
        	margin-left: 550px; 
        	margin-top:150px;
        }
        textarea
        {
        	width: 500px;
        	height: 100px;
        }
     </style>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You data will be shown here</title>
</head>

<body>
	<div id="finger">
		<img src="finger.jpg" alt="nope" /><br>
		<br>
		<form name="" method="POST" action="show.jsp">
			<textarea name="newString"  rows="4"><%= new_string %></textarea>
			<br /> (max. 350 characters)<br /> <input type="submit" value="store" />
		</form>
	</div>
</body>
</html>