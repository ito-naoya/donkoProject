<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.PurchaseBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/button.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<title>donko</title>
</head>
<body>
	<% 
	ArrayList<PurchaseBean> unshippingnum = (ArrayList<PurchaseBean>)request.getAttribute("unshippingnum");
	%>
	<header class="fixed-top" style="background-color:white;">
    	<div style="height:54px; background-color:#D5E8D4; position:relative; top: 0; left: 0; z-index:2; display:flex; align-items:center; border-bottom:1px lightgray solid;">
    		<ul class="d-flex mb-0 p-0 w-100">
    			<li class="mx-3" style="list-style:none; display: flex; align-items: center;">
	    			<a href="cart" class="link" style="text-decoration: none;">
						<img src="./images/donkoLogo2.png" style="height:50px;">
					</a>
    			</li>
    		</ul>
    	</div>
    </header>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>