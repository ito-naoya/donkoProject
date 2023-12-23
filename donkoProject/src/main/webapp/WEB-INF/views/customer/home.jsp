<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		<div class="row text-center" style="margin: 100px 0;">
			<h6 class="mb-4"><strong>DONKOのおすすめ商品</strong></h6>
			<div class="d-flex flex-wrap justify-content-center">
				<% 
				if (itemList != null) {
				%>
					<%
					for (ItemBean item : itemList) {
					%>
					<a href="itemDetail?itemId=<%= item.getItemId() %>&source=home"
						style="color: #385a37; display: block; text-decoration: none;"
						class="mx-1"> 
						<span class="card mb-2"
						style="width: 330px; height: 330px;"> 
							<img
								src="./images/<%= item.getImageFileName() %>.jpg"
								class="card-img-top" alt="<%= item.getImageFileName() %>"
								style="object-fit: cover; height: 100%;">
						</span>
					</a>
					<%
					}
					%>
				<%
				} 
				%>
			</div>
		</div>
		<div class="row d-flex justify-content-center" style="margin:100px 0;">
			<div class="d-flex p-0">
				<img class=""
					src="./images/donkohonsya.png"
					class="card-img-top"
					style="object-fit: cover; height: auto; width:30%;">
				<div class="border p-5" style="width:70%;">
				<h5 class="mb-3"><strong>私たちについて</strong></h5>
				<p>
					山形県で誕生したECサイト「donko」へようこそ！<br>
					私たちは地元の魅力を全国に発信し、暖かい心でつながるコミュニティを築いています。<br>
					donkoは、山形の豊かな自然と伝統を感じながら、新しい価値観を提案し続けるプラットフォームです。<br><br>

					山形の心、donkoのこころ。<br><br>

					山形の四季折々の美しい風景や伝統文化にインスパイアされ、私たちの商品は地元の職人やアーティストとの協力によって生み出されています。<br>
					donkoでは、その土地土地の温もりと共に、現代の洗練されたデザインや機能性も大切にし、お客様に特別な体験を提供しています。<br><br>

					地域と共に、未来へ。<br><br>

					donkoは地元社会との連携を重視し、持続可能な未来を目指しています。<br>
					私たちの商品は環境への配慮を大切にし、地元の小売業者やアーティストを支援しながら、地域全体の発展に貢献しています。<br><br>

					ご来店いただきありがとうございます。<br>

					donkoはあなたが心地よくショッピングを楽しめる場所であり、山形の魅力を感じながら、新しい発見ができる場でもあります。<br>
					どうぞゆっくりとお楽しみください。<br>
					私たちは皆さまとの繋がりを大切にし、笑顔と幸せが広がる場所を目指しています。
				</p>
				</div>
			</div>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>