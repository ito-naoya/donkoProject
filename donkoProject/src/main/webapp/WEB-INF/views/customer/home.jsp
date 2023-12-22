<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/popUpWindow.css" type="text/css">
<title>donko</title>
</head>
<body>
	<%@include file= "../component/header.jsp" %>
	<%@include file= "../component/headerTopSpace.jsp" %>
	<main>
		<%
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList");
		%>
		<div id="carouselExampleAutoplaying" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="./images/広告1.png" class="d-block w-100" alt="..." style="height:550px; object-fit:cover;">
				</div>
				<div class="carousel-item">
					<img src="./images/広告2.png" class="d-block w-100" alt="..." style="height:550px; object-fit:cover;">
				</div>
				<div class="carousel-item">
					<img src="./images/広告3.jpeg" class="d-block w-100" alt="..." style="height:550px; object-fit:cover;">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		<div class="row text-center" style="margin: 60px 0;">
			<h6 class="mb-4"><strong>DONKOのおすすめ商品</strong></h6>
			<div class="d-flex flex-wrap justify-content-center">
				<% 
				if (itemList != null) {
				%>
					<%
					for (ItemBean item : itemList) {
					%>
					<%-- <a href="itemDetail?itemId=<%= item.getItemId() %>&source=home"
						style="color: #385a37; display: block; text-decoration: none;"
						class="mx-2"> 
						<span class="card mb-3"
						style="width: 300px; height: 300px;"> 
							<img
								src="./images/<%= item.getImageFileName() %>.jpg"
								class="card-img-top" alt="<%= item.getImageFileName() %>"
								style="object-fit: cover; height: 100%;">
						</span>
					</a>  --%>
					<label class="open mx-1" for="pop-up"> 
						<span class="card mb-2"
						style="width: 300px; height: 300px;"> 
							<img
								src="./images/<%= item.getImageFileName() %>.jpg"
								class="card-img-top" alt="<%= item.getImageFileName() %>"
								style="object-fit: cover; height: 100%;">
						</span>
					</label>
					<input type="checkbox" id="pop-up">
					<div class="overlay">
						<div class="window">
							<label class="close" for="pop-up">閉じる</label>
							<h4>ポップアップウィンドウ</h4>
							<p>この窓が表示されている間は窓の外の操作はできません。ウィンドウを閉じて操作して下さい。</p>
						</div>
					</div>
				<%
					}
					%>
				<%
				} 
				%>
			</div>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>