<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.link:hover{opacity: 0.7;}
.link2:hover{opacity: 0.8;}
</style>
<link rel="stylesheet" href="./css/home.css">
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
					<img src="./images/広告1.png" class="d-block w-100" alt="..." style="height: calc(100vh - 94px); object-fit:cover;">
				</div>
				<div class="carousel-item">
					<img src="./images/広告2.png" class="d-block w-100" alt="..." style="height: calc(100vh - 94px); object-fit:cover;">
				</div>
				<div class="carousel-item">
					<img src="./images/広告3.jpeg" class="d-block w-100" alt="..." style="height: calc(100vh - 94px); object-fit:cover;">
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
		<div class="row text-center item" style="margin: 100px 0;">
			<h5 class="mb-4"><strong>DONKOのおすすめ商品</strong></h5>
			<div class="d-flex flex-wrap justify-content-center">
				<% 
				if (itemList != null) {
				%>
					<%
					for (ItemBean item : itemList) {
					%>
					<div class="mb-2" style="width:calc(25% - 24px);">
						<a href="itemDetail?itemId=<%= item.getItemId() %>&source=home"
							style="color: #385a37; display: block; text-decoration: none;"
							class="mx-1 link"> 
							<span> 
								<img
									src="./images/<%= item.getImageFileName() %>.jpg"
									class="img-fluid border" alt="<%= item.getImageFileName() %>"
									style="object-fit: cover; width: 100%; aspect-ratio: 1 / 1; border-radius:5px;">
							</span>
						</a>
					</div>
					<%
					}
					%>
				<%
				} 
				%>
			</div>
		</div>
		<div class="row d-flex justify-content-center item2" style="margin:100px 65px 60px;">
			<div class="col-lg-4 p-0">
				<img src="./images/donkohonsya.png"
					class="card-img-top"
					style="object-fit: cover; height: 100%;">
			</div>
			<div class="col-lg-8 border p-5" style="display: flex; align-items: center;">
				<div>
				<h5 class="mb-3"><strong>私たちについて</strong></h5>
				<small>
					山形県で誕生したECサイト<img src="./images/donkoLogo2.png" class="mb-1" style="height:30px;">へようこそ！<br>
					私たちは地元の魅力を全国に発信し、暖かい心でつながるコミュニティを築いています。<br><br>
					<img src="./images/donkoLogo2.png" class="mb-1" style="height:30px;">は、山形の豊かな自然と伝統を感じながら、新しい価値観を提案し続けるプラットフォームです。
				</small>
				</div>
			</div>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>