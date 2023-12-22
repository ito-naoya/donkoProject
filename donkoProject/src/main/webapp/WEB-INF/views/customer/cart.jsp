<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="bean.CartBean" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
		<link rel="stylesheet" href="./css/cartStyle.css">
	</head>
	<body>
		<%@include file="../component/header.jsp"%>
		<%@include file="../component/headerTopSpace.jsp"%>
		<main>
			<%
			ArrayList<CartBean> cartBeanList = (ArrayList<CartBean>)request.getAttribute("cartBeanList");
			%>
			<div class="container">
				<div class="row justify-content-center" style="margin-top:40px;">
					<%	
					if(cartBeanList.size() == 0 ) {
					%>
					<div class="col-lg-8 d-flex justify-content-center align-items-center p-2" style="height:60vh;">
						<div>
							<h6 class="border text-center px-5 py-3" style="border-radius:40px; color:lightgray;">現在カートの中に商品はありません</h6>
						</div>
					</div>
					<%
					} else {
					%>
					<div class="col-lg-7 mx-3">
						<table class="table table-borderless">
							<%
							for(CartBean cb : cartBeanList) {
							%>
								<tr>
									<td>
										<a href="itemDetail?itemId=<%= cb.getItemId() %>&source=cart" class="me-3"
										style="text-decoration: none; display: inline-block;">
											<div style="height: 100px; width: 100px;">
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
										¥ 
										<%= String.format("%,d", cb.getItemPrice()) %>
									</td>
									<td style="vertical-align: middle; width:30%;">
										<form action="cart" method="POST" class="d-flex">
											<p class="mb-0">
												<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
												<small>数量:</small>
												<select name="quantity" style="display: inline-block" class="form-control">
													<%
													for(int i = 1; i <= cb.getItemStock(); i ++) {
													%>
														<%
														if (i == cb.getQuantity()) {
														%>
															<option value="<%= i %>" selected>
																<%= i %>
															</option>
														<%
														}else {
														%>
															<option value="<%= i %>">
																<%= i %>
															</option>
														<%
														} 
														%>
													<%
													}
													%>
												</select>
											</p>
											<button type="submit" class="btn p-2 ms-4 mt-auto text-nowrap" style="background-color: #e5ccff;">
												更新
											</button>
		             					</form>
									</td>
									<td style="vertical-align: middle; width:8%;">
										<form action="deleteCart" method="GET">
											<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
											<button type="submit" class="btn p-2" style="color:red; border: 1px red solid; border-radius:50%;">
												<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
												  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
												  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
												</svg>
											</button>
										</form>
									</td>
								<tr>
							<%
							} 
							%>
						</table>
						<%
						if(cartBeanList.size() > 0){
						%>
							<div class="d-flex justify-content-center">
								<form action="deleteCart" method="POST" class="d-flex justify-content-end mt-3">
									<button type=submit class="btn btn-sm px-5 py-2 rounded-pill border border-danger" style="background-color: white; color: red;">
										カートの中身を全て削除する
									</button>
								</form>
							</div>
						<%
						}
						%>
					</div>
					<div class="col-lg-3 mx-3">
						<%
						if(cartBeanList.size() > 0) {
						%>
							<div class="border p-4 align-items-center" style="width: auto; height:auto;">
								<% 
								Integer totalPrice = (Integer)request.getAttribute("totalPrice");
								%>
								<div class="mb-4">
									<small>合計金額</small>
									<h4>
										￥ <%= String.format("%,d", totalPrice) %>
									</h4>
								</div>
								<form action="purchaseConfirm" method="get" class="d-flex justify-content-end mb-3">
									<button type=submit class="btn btn-sm px-5 py-2 w-100" style="background-color: #9933ff; color: white; width: auto;">
										レジに進む
									</button>
								</form>
							</div>
						<%
						}
						%>
					</div>
					<% 
					} 
					%>
				</div>
			</div>
		</main>
		<%@include file="../component/footer.jsp"%><a>
		<script src="./js/cartScript.js"></script>
	</body>
</html>