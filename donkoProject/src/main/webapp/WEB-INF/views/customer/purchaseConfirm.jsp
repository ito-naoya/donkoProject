<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
	<%@ page import="bean.ShippingAddressBean" %>
	<%@ page import="bean.CartBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
		<link rel="stylesheet" href="./css/cartStyle.css">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
	</head>
	<body>
		<main>
			<a href="cart" class="m-5 mb-4" style="text-decoration: none; display: inline-block">
				<h6 class="py-2 px-5"style="color: #385a37; border: 1px solid #385a37; text-align: center; width: 200px;">
					DONKO
				</h6>
			</a>
			<br>
			<div class="container">
				<div class="row">
					<div class="col-8">
					<%
					ShippingAddressBean sa = (ShippingAddressBean)request.getAttribute("shippingAddress");
					%>
						<p>
							1. 配送先
						</p>
						<hr>
						<div class="p-3">
							<p class="mb-0">
								〒 
								<%= sa.getPostalCode() %>
							</p>
							<p>
								<%= sa.getAddress() %>
							</p>
							<p>
								<%= sa.getAddressee() %>
							</p>
							<div class="d-flex justify-content-end">
								<a href="shippingAddressIndex" style="color: #385a37">
									デフォルトの配送先を変更
								</a>
							</div>
						</div>
						<p>
							1. 商品情報
						</p>
						<hr>
						<table class="table table-borderless">
							<%
							ArrayList<CartBean> cartBeanList = (ArrayList<CartBean>) request.getAttribute("cartBeanList");
							%>
							<%
							for(CartBean cb : cartBeanList) {
							%>
								<tr>
									<td style="width: 200px">
										<a href="itemDetail?itemId=<%= cb.getItemId() %>" class="me-3"
										style="text-decoration: none; display: inline-block;">
											<div style="height: 150px; width: 150px;">
												<img class="object-fit-cover w-100 h-100 itemImage"
													src="./images/<%= cb.getImageFileName() %>.jpg">
											</div>
										</a>
									</td>
									<td style="vertical-align: middle;">
										<strong>
											<%= cb.getItemName() %>
											(
												<%= cb.getItemOptionDetail() %>
											)
										</strong>
									</td>
									<td style="vertical-align: middle;">
										価格 ¥ 
										<%= String.format("%,d", cb.getItemPrice()) %>
									</td>
									<td style="vertical-align: middle;">
										<p style="margin: 0">
											<%= cb.getQuantity() %>
											個 
										</p>
									</td>
								<tr>
							<%
							} 
							%>
						</table>
					</div>
					<div class="col-4">
						<div class="d-flex flex-column border p-5 align-items-center justify-content-center" style="width: 280px; height: 200px;">
							<% 
							Integer totalPrice = (Integer)request.getAttribute("totalPrice");
							%>
							<p>
								合計金額 ¥ 
								<%= String.format("%,d", totalPrice) %>
							</p>
							<form action="purchaseConfirm" method="post">
								<input type="hidden" value="<%= totalPrice %>" name="totalPrice">
								<input type="hidden" value="<%= sa.getShippingAddressId() %>" name="shippingAddressId">
								<button type=submit class="btn px-5 py-2" style="background-color: #9933ff; color: white;">
									注文確定
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</main>
		<script src="./js/cartScript.js"></script>
	</body>
</html>