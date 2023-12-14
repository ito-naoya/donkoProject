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
	</head>
	<body>
		<%@include file="../component/header.jsp"%>
		<%@include file="../component/headerTopSpace.jsp"%>
		<main class="m-5">
			<%
				ArrayList<CartBean> cartBeanList = (ArrayList<CartBean>)request.getAttribute("cartBeanList");
			%>
			<%
			if(cartBeanList.size() > 0) {
			%>
				<div>
					<form action="purchaseConfirm" method="get" class="d-flex justify-content-end mb-3">
						<button type=submit class="btn px-5 py-3" style="background-color: #9933ff; color: white; width: 210px;">
							レジに進む
						</button>
					</form>
				</div>
			<%
			}
			%>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-11">
						<%
						if(cartBeanList.size() == 0 ) {
						%>
							<h1 class="text-center">現在カートの中に商品はありません</h1>
						<%
						}
						%>
						<table class="table table-borderless">
							<%
							for(CartBean cb : cartBeanList) {
							%>
								<tr>
									<td>
										<a href="itemDetail?itemId=<%= cb.getItemId() %>" class="me-3"
										style="text-decoration: none; display: inline-block;">
											<div style="height: 150px; width: 150px;">
												<img class="object-fit-cover w-100 h-100"
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
										¥ <%= String.format("%,d", cb.getItemPrice() * cb.getQuantity()) %>
									</td>
									<form action="cart" method="POST" style="width: 120px;">
										<td style="vertical-align: middle; width: 150px;">
											<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
											<p class="border p-2" style="margin: 0; width: 120px;">
												数量: 
												<select name="quantity" style="border: none;">
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
										</td>
										<td style="vertical-align: middle;">
											<button type="submit" class="btn p-2 ms-3" style="background-color: #e5ccff;">
												更新
											</button>
										</td>
		             				 </form>
									<td style="vertical-align: middle;">
										<form action="deleteCart" method="GET">
											<input type="hidden" value="<%= cb.getItemId() %>" name="itemId">
											<button type="submit" class="btn p-2 ms-3" style="background-color: red; color: white;">
												削除
											</button>
										</form>
									</td>
								<tr>
							<%
							} 
							%>
						</table>
					</div>
					<%
					if(cartBeanList.size() > 0){
					%>
						<div class="d-flex justify-content-center">
							<form action="deleteCart" method="POST" class="d-flex justify-content-end mt-3">
								<button type=submit class="btn px-5 py-3 rounded-pill border border-danger" style="background-color: white; color: red;">
									カートの中身を全て削除する
								</button>
							</form>
						</div>
					<%
					}
					%>
				</div>
			</div>
		</main>
		<%@include file="../component/footer.jsp"%>
	</body>
</html>