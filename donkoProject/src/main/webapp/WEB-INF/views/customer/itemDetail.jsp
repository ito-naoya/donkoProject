<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.ItemBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<link rel="stylesheet" href="./css/itemDetailStyle.css">
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main class="m-5">
		<div class="container">
			<%
			ItemBean item = (ItemBean) request.getAttribute("item");
			%>
			<a href="category?categoryName=<%= item.getItemCategoryName() %>" class="mb-3" style="display: inline-block">
				<div class="border text-center" style="width: 50px;">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
	  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
					</svg>
				</div>
			</a>
			<div class="row">
				<div class="col">
					<div class="m-auto" style="height: 400px; width: 400px;">
						<img class="object-fit-cover w-100 h-100" id="mainImage" src="./images/<%=item.getImageFileName()%>.jpg">
					</div>
				</div>
				<div class="col">
					<h2>
					<%
					ItemBean itemOptionDetail = (ItemBean)request.getAttribute("itemOptionDetail");
					%>
						<strong>
							<%=item.getItemName()%>
							(
						
								<span><%= itemOptionDetail.getItemFirstOptionValue() %></span>
							)
						</strong>
					</h2>
					<h3>
						¥
						<%=String.format("%,d", item.getItemPrice())%>
					</h3>
					<hr>
					<%
					ArrayList<ItemBean> itemImageList = (ArrayList<ItemBean>) request.getAttribute("itemImageList");
					%>
					<div class="d-flex justify-content-start mb-3">
						<%
						if (itemImageList.size() > 1) {
						%>
							<%
							for (ItemBean itemBean : itemImageList) {
							%>
							<a href="itemDetail?itemId=<%=itemBean.getItemId()%>" class="me-3" style="text-decoration: none;">
								<div style="height: 150px; width: 150px;">
									<img class="object-fit-cover w-100 h-100 subImage" src="./images/<%=itemBean.getImageFileName()%>.jpg">
								</div>
							</a>
							<%
							}
							%>
						<%
						}
						%>
					</div>
					<%
					ArrayList<ItemBean> itemOptionList = (ArrayList<ItemBean>)request.getAttribute("itemOptionList");
					%>
					
					<% 
					if(itemOptionList.size() > 1){
					%>
						<div class="d-flex">
							<%
							for(ItemBean ib : itemOptionList) {
							%>
								<a href="itemDetail?itemId=<%= ib.getItemId() %>" class="d-inline-block" style="margin-right: 9px; text-decoration: none; margin-bottom: 16px; color: black;">
									<div class="border text-center size" style=" width: 50px; hegiht: 50px">
										<%= ib.getItemFirstOptionValue() %>
									</div>
								</a>
							<%
							}
							%>
						</div>
					<%
					} 
					%>
					
					<h5>
						<strong>
							この商品について
						</strong>
					</h5>
					<p>
						<%=item.getItemDescription()%>
					</p>
					<div class="d-flex justify-content-between align-items-center">
						<% 
						if (item.getItemStock() <= 3) {
						%>
							<p class="border p-3 mt-4 text-center" style=" width: 100px; color: red">
								残り<%=item.getItemStock()%>点
							</p>
						<%		
						} else {
						%>
							<p class="border p-3 mt-4 text-center" style=" width: 100px;">
								残り<%=item.getItemStock()%>点
							</p>
						<%
						} 
						%>
						<%
						if (item.getItemStock() != 0) { 
						%>
							<form action="itemDetail?itemId=<%= item.getItemId() %>" method="post">
								<button type=submit class="btn px-5 py-3" style="background-color: #9933ff; color: white;">
									カートに入れる
								</button>
							</form>
						<%
						} 
						%>

					</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
	<script src="./js/itemDetailScript.js"></script>
</body>
</html>