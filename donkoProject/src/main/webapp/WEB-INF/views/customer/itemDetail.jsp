<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.ItemBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style>
.arrow:hover{opacity: 0.7;}
</style>
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
			<%
			String url = (String)request.getAttribute("url");
			%>
			<div class="row d-flex justify-content-center">
				<div class="col-lg-10">
					<a href="<%= url %>" class="arrow mb-5" style="display: inline-block; color:#385A37;">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
						</svg>
					</a>
				</div>
			</div>
			<div class="row d-flex justify-content-center">
				<div class="col-lg-3 mb-4">
					<div class="m-auto" style="width: 100%;">
						<img class="object-fit-cover border" 
							 style=" width: 100%; aspect-ratio: 1 / 1; border-radius:0px;" 
							 id="mainImage" 
							 src="./images/<%=item.getImageFileName()%>.jpg">
					</div>
				</div>
				<div class="col-lg-7">
					<h4>
						<strong>
							<%=item.getItemName()%>
							(
						
								<span><%= item.getItemFirstOptionValue() %></span>
							)
						</strong>
					</h4>
					<h5>
						¥
						<%=String.format("%,d", item.getItemPrice())%>
					</h5>
					<hr>
					<%
					ArrayList<ItemBean> itemImageList = (ArrayList<ItemBean>) request.getAttribute("itemImageList");
					%>
					<div class="d-flex justify-content-start mb-3">
						<%
						String source = (String)request.getAttribute("source");
						%>
						<%
						String categoryName = (String)request.getAttribute("categoryName");
						%>
						<%
						if (itemImageList.size() > 1) {
						%>
							<%
							for (ItemBean itemBean : itemImageList) {
							%>
							<a href="itemDetail?itemId=<%=itemBean.getItemId()%>&source=<%= source %><%= categoryName != null ? "&categoryName=" + categoryName : "" %>" class="me-3" style="text-decoration: none;">
								<div style="height: 120px; width: 120px;">
									<img class="object-fit-cover w-100 h-100 subImage border" 
										 src="./images/<%=itemBean.getImageFileName()%>.jpg"
										 style="border-radius:5px;">
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
								<a href="itemDetail?itemId=<%= ib.getItemId() %>&source=<%= source %><%= categoryName != null ? "&categoryName=" + categoryName : "" %>" class="d-inline-block" style="margin-right: 9px; text-decoration: none; margin-bottom: 16px; color: black;">
									<div class="border px-3 py-2 text-center size" style=" width: auto; hegiht: 50px">
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
					
					<h6 class="mt-3">
						<strong>
							この商品について
						</strong>
					</h6>
					<p class="mb-5">
						<%=item.getItemDescription()%>
					</p>
					<div class="d-flex justify-content-between align-items-center">
						<% 
						if (item.getItemStock() == 0) {
						%>
							<p class="mb-0 px-3 py-2 text-center" style=" width: 120px; color: red; border: 1px red solid; border-radius:5px;">
								入荷待ち
							</p>
						<% 
						} else if (item.getItemStock() <= 3) {
						%>
							<p class="mb-0 px-3 py-2 text-center" style=" width: 120px; color: red; border: 1px red solid; border-radius:5px;">
								残り<%=item.getItemStock()%>点
							</p>
						<%		
						} else {
						%>
							<p class="border mb-0 px-3 py-2 text-center" style=" width: 120px; border-radius:5px;">
								残り<%=item.getItemStock()%>点
							</p>
						<%
						} 
						%>
						
						<%
						if (item.getItemStock() > 0) { 
						%>
							<form action="itemDetail?itemId=<%= item.getItemId() %>" method="post" class="d-flex align-items-center">
									<div class="d-flex align-items-center mx-3">
										<span class="me-2">数量:</span>
										<span id="quantityDecrementBtn" style="cursor: pointer;">
											<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
											  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1h-7z"/>
											</svg>
										</span>
										<input type="number" id="quantity" name="quantity" max="<%= item.getItemStock() %>" min="1" step="1" class="form-control mx-2 text-center" style="width:60px;" value="1" required>
										<span id="quantityIncrementBtn" style="cursor: pointer;">
											<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
											  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
											</svg>
										</span>
									</div>
								<button type=submit class="button-purple px-3 py-2" style="border-radius:5px;">
									カートに追加
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