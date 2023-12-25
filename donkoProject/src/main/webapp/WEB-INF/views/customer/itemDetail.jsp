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
						<img class="object-fit-cover" style=" width: 100%; aspect-ratio: 1 / 1;" id="mainImage" src="./images/<%=item.getImageFileName()%>.jpg">
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
						if (itemImageList.size() > 1) {
						%>
							<%
							for (ItemBean itemBean : itemImageList) {
							%>
							<a href="itemDetail?itemId=<%=itemBean.getItemId()%>" class="me-3" style="text-decoration: none;">
								<div style="height: 120px; width: 120px;">
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
							<p class="mt-4 p-3" style=" width: 100px; color: red; border: 1px red solid;">
								入荷待ち
							</p>
						<% 
						} else if (item.getItemStock() <= 3) {
						%>
							<p class="p-3 mt-4 text-center" style=" width: 100px; color: red; border: 1px red solid;">
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
						if (item.getItemStock() > 0) { 
						%>
							<form action="itemDetail?itemId=<%= item.getItemId() %>" method="post" class="d-flex align-items-center">
								<div>
									<small class="mx-4">数量</small>
									<input type="number" name="quantity" min="1" step="1" max="<%=item.getItemStock()%>" class="form-control mx-4" style="width:80px;" value="1">
								</div>
								<button type=submit class="btn btn-sm px-5 py-3" style="background-color: #9933ff; color: white;">
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