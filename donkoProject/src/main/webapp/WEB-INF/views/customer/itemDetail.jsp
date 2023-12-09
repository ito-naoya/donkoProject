<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="bean.ItemBean" %>
	<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main>
		<div class="container text-center">
			<div class="row">
				<div class="col">
					<%
						ItemBean item = (ItemBean)request.getAttribute("item");
					%>
					<div class="m-auto h-75 w-75" style="height: 200px; width: 200px;">
						<img class="object-fit-cover w-100 h-100" src="./images/<%= item.getImageFileName() %>.jpg">
					</div>
				</div>
				
				<div class="col">
					<h2> <%= item.getItemName() %> </h2>
					<h3> <%= item.getItemPrice() %> </h3>
					<hr>
					<%
						ArrayList<ItemBean> itemImageList = (ArrayList<ItemBean>)request.getAttribute("itemImageList"); 
					%>
					<div class="d-flex justify-content-around">
					<%
					for(ItemBean itemBean : itemImageList){ 
					%>
						<a style="text-decoration: none;"href="itemDetail?itemId=<%= itemBean.getItemId() %>">
							<div style="height: 150px; width: 150px;">							
								<img class="object-fit-cover w-100 h-100" src="./images/<%= itemBean.getImageFileName() %>.jpg">
							</div>
						</a>
					<%
					} 
					%>
					</div>
					
					<h4>
						この商品について
					</h4>
					<p>
						<%= item.getItemDescription() %>
					</p>
				</div>
			</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>