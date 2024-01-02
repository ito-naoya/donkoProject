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
		<style>
		.link:hover{opacity: 0.7;}
		.link2:hover{opacity: 0.8;}
		</style>
	</head>
	<body>
		<main>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 mx-auto" style="margin-bottom: 40px;">
					<a href="cart" class="mt-5 mb-4" style="text-decoration: none; display: inline-block">
						<img src="./images/donkoLogo2.png" style="height:70px;">
					</a>
					<br>
						<%
						ShippingAddressBean sa = (ShippingAddressBean)request.getAttribute("shippingAddress");
						%>
						<div class="d-flex border p-4 align-items-center" style="width: auto; height: auto; border-radius:5px;">
							<% 
							Integer totalPrice = (Integer)request.getAttribute("totalPrice");
							%>
							<div class="">
								<small>合計金額</small>
								<h4 class="mb-0">
									￥ <%= String.format("%,d", totalPrice) %>
								</h4>
							</div>
							<%
							if (sa != null){
							%>
								<form action="purchaseConfirm" method="post" class="ms-auto">
									<input type="hidden" value="<%= totalPrice %>" name="totalPrice">
									<input type="hidden" value="<%= sa.getAddress() %>" name="address">
									<input type="hidden" value="<%= sa.getPostalCode() %>" name="postalCode">
									<input type="hidden" value="<%= sa.getAddressee() %>" name="addressee">
									<button type=submit class="btn px-5 py-2" style="border: 1px solid gray; background-color: #9933ff; color: white;">
										注文確定
									</button>
								</form>
							<%	
							}
							%>
						</div>
						<p class="mt-5">
							1. 配送先
						</p>
						<hr>
						<div class="p-3">
							<%
							if(sa != null){
							%>
								<p class="mb-0">
									〒 
									<%= new StringBuilder(sa.getPostalCode()).insert(3, "-") %>
								</p>
								<p>
									<%= sa.getAddress() %>
								</p>
								<p>
									<%= sa.getAddressee() %>
								</p>
								<div class="d-flex justify-content-end">
									<a href="shippingAddressIndex" style="color: #385a37">
										メインの配送先を変更
									</a>
								</div>
							<%
							} else {
							%>
								<div class="mb-3">
									<a href="shippingAddressIndex" class="link" style="color: #385a37; text-decoration: none;">
										<small class="px-3 py-2" style="border:0.5px #385a37 solid; border-radius:40px;">配送先を選択</small>
									</a>
								</div>
							<%
							} 
							%>
						</div>
						<p>
							2. 商品情報
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
										<a href="itemDetail?itemId=<%= cb.getItemId() %>&source=purchaseConfirm" class="me-3"
										style="text-decoration: none; display: inline-block;">
											<div style="height: 100px; width: 100px;">
												<img class="object-fit-cover w-100 h-100 itemImage"
													src="./images/<%= cb.getImageFileName() %>.jpg">
											</div>
										</a>
									</td>
									<td style="vertical-align: middle;">
										<%= cb.getItemName() %>
										(
											<%= cb.getItemOptionDetail() %>
										)<br>
										¥ <%= String.format("%,d", cb.getItemPrice()) %>
									</td>
									<td class="text-center" style="vertical-align: middle;">
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
				</div>
			</div>
		</main>
		<script src="./js/cartScript.js"></script>
	</body>
</html>