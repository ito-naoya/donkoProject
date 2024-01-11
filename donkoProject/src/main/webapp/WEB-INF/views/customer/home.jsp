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
		<div class="row text-center item" style="margin: 100px 0px;">
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
		<% 
		if (user_id == null) {
		%>
		<div style="margin: 65px;">
			<a href="adminSignin" class="link" style="text-decoration:none; color:#385A37;">
				<small>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-gear mb-1" viewBox="0 0 16 16">
						<path d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm-9 8c0 1 1 1 1 1h5.256A4.493 4.493 0 0 1 8 12.5a4.49 4.49 0 0 1 1.544-3.393C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4Zm9.886-3.54c.18-.613 1.048-.613 1.229 0l.043.148a.64.64 0 0 0 .921.382l.136-.074c.561-.306 1.175.308.87.869l-.075.136a.64.64 0 0 0 .382.92l.149.045c.612.18.612 1.048 0 1.229l-.15.043a.64.64 0 0 0-.38.921l.074.136c.305.561-.309 1.175-.87.87l-.136-.075a.64.64 0 0 0-.92.382l-.045.149c-.18.612-1.048.612-1.229 0l-.043-.15a.64.64 0 0 0-.921-.38l-.136.074c-.561.305-1.175-.309-.87-.87l.075-.136a.64.64 0 0 0-.382-.92l-.148-.045c-.613-.18-.613-1.048 0-1.229l.148-.043a.64.64 0 0 0 .382-.921l-.074-.136c-.306-.561.308-1.175.869-.87l.136.075a.64.64 0 0 0 .92-.382l.045-.148ZM14 12.5a1.5 1.5 0 1 0-3 0 1.5 1.5 0 0 0 3 0Z"/>
					</svg>
					管理者画面に遷移
				</small>
			</a>
		</div>
		<% 
		}
		%>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>