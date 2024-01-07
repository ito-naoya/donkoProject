<%@page import="classes.ShippingAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@ page import="java.util.ArrayList, bean.ShippingAddressBean"%>
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
	<main>
		<div class="container">
			<%
		    ShippingAddressBean shippingAddressEdit = (ShippingAddressBean) request.getAttribute("shippingAddressEdit");
		    %>
		    <div class="row" style="height:100vh;">
				<form action="editShippingAddress" method="post"
					style="display: flex; justify-content:"
					class="needs-validation" novalidate>
					<div class="col-lg-5 m-auto p-5 border" style="border-radius:10px; box-shadow:10px 10px 10px lightgray;">
						<div class="cancelButton"
							style="display: flex; justify-content: space-between;">
							<h5>
								<strong>配送先編集</strong>
							</h5>
							<div>
								<a href="shippingAddressIndex"
									style="text-decoration: none; text-align: center;"><button
										type="button" class="btn-close border" aria-label="Close"></button>
									️</a>
							</div>
						</div>
						<br>
						<div class="form-group d-flex flex-wrap">
							<label for="exampleInputAddresses"><small>宛名：</small></label>
							<input type="text" class="form-control" id="exampleInputAddresses"
								aria-describedby="addresses" name="addressee"
								value="<%=shippingAddressEdit.getAddressee() %>">
						</div>
						<div class="d-flex flex-wrap mb-4"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String addressee = (String) request.getAttribute("addressee"); %>
							<% if (addressee != null) { %>
							<%= addressee %>
							<% } %>
						</div>
						<div class="form-group d-flex flex-wrap">
							<label for="exampleInputPostalCode"><small>郵便番号：</small></label>
							<input type="text" class="form-control" id="exampleInputPostalCode"
								aria-describedby="postalcode" maxlength="8" name="postalcode"
								value="<%=shippingAddressEdit.getPostalCode() %>">
						</div>
						<div class="d-flex flex-wrap mb-4"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String postalCode = (String) request.getAttribute("postalCode"); %>
							<% if (postalCode != null) { %>
							<%= postalCode %>
							<% } %>
						</div>
						<div class="form-group d-flex flex-wrap">
							<label for="exampleInputAddress"><small>住所：</small></label>
							<input type="text" class="form-control" id="exampleInputAddress"
								aria-describedby="address" name="address"
								value="<%=shippingAddressEdit.getAddress() %>">
						</div>
						<div class="d-flex flex-wrap mb-4"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String address = (String) request.getAttribute("address"); %>
							<% if (address != null) { %>
							<%= address %>
							<% } %>
						</div>
						<div class="cancelButton mt-5"
							style="display: flex; justify-content: center;">
							<button type="submit" class="button-purple mt-3 w-50 px-2 py-1" id="editButton"
								style="border-radius:5px;">更新</button
						</div>
					</div>
				</form>
			</div>
		</div>
	</main>
	<script src="./js/editShippingAddress.js"></script>
</body>
</html>