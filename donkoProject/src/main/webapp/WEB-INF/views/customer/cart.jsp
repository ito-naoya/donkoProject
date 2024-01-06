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
			String userName = (String)request.getAttribute("userName");
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
					<div class="col-lg-8 mx-3 mb-5">
						<%
						if(cartBeanList.size() > 0){
						%>
							<div class="d-flex align-items-center justify-content-between">
								<h6 class="mb-0"><%= userName %> さんのカート</h6>
							</div>
						<%
						}
						%>
						<hr>
						<table class="table table-borderless">
							<tbody>
								<%
								for(CartBean cb : cartBeanList) {
								%>
									<tr>
										<td class="text-center" >
											<a href="itemDetail?itemId=<%= cb.getItemId() %>&source=cart"
											style="text-decoration: none; display: inline-block;">
												<div style="height: 100px; width: 100px;">
													<img class="object-fit-cover w-100 h-100 itemImage border"
														src="./images/<%= cb.getImageFileName() %>.jpg"
														style="border-radius:5px;">
												</div>
											</a>
										</td>
										<td style="vertical-align: middle;">
											<%= cb.getItemName() %>
											(
											<%= cb.getItemOptionDetail() %>
												)
											<br>
											¥ 
											<%= String.format("%,d", cb.getItemPrice()) %>
										</td>
										<td style="vertical-align: middle; width:35%;">
											<form action="cart" method="POST" class="d-flex quantityForm">											
												<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
												<div class="d-flex align-items-center mx-3">
													<span class="me-2 text-nowrap">
														数量:
													</span>
													<span class="quantityDecrementBtn" style="cursor: pointer;">
														<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
														  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z"/>
														</svg>
													</span>
													<input type="number" name="quantity" max="<%= cb.getItemStock() %>" min="1" step="1" class="mx-2 quantity form-control text-center" style="width:auto;" value="<%= cb.getQuantity() %>" required>
													<span class="quantityIncrementBtn" style="cursor: pointer;">
														<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
														  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
														</svg>
													</span>
													<button type="submit" class="quantityUpdateBtn ms-2 btn p-1" style="padding: 0; font-size: 13px; background-color: #E5CCFF; display: none;">
														更新
													</button>
												</div>
			             					</form>
										</td>
										<td style="vertical-align: middle;">
											<small class="">小計</small><br>
											<strong class="text-nowrap">￥ <%= String.format("%,d", (cb.getItemPrice())*(cb.getQuantity())) %></strong>
										</td>
										<td class="text-center" style="vertical-align: middle; width:10%;">
											<form action="deleteCart" method="GET">
												<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
												<button type="submit" class="btn p-0 link" style="color:#385A37;">
													<!-- <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
													  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
													  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
													</svg> -->
													<small class="text-nowrap">削除</small>
												</button>
											</form>
										</td>
									<tr>
								<%
								} 
								%>
							</tbody>
						</table>
					</div>
					<div class="col-lg-3 mx-3 mb-5">
						<%
						if(cartBeanList.size() > 0) {
						%>
							<div class="border p-4 align-items-center" style="width: auto; height:auto; border-radius:5px;">
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
									<button type=submit class="button-light-purple py-2 w-100 text-nowrap" style="border-radius:5px; width: auto;">
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