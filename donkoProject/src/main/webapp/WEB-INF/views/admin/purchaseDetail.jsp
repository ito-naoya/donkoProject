<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, bean.PurchaseDetailBean, java.text.NumberFormat, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="./css/button.css">
<style type="text/css">
.td{
	vertical-align: middle;
}
</style>
</head>
<body>
	<main>
		<div class="container">
			<div class="row" style="height:100vh;">
				<div class="col-lg-8 m-auto p-5 border" style="border-radius:10px; box-shadow:10px 10px 10px lightgray;">
					<% 
					String source = (String)request.getParameter("source");
					PurchaseBean purchaseInfo = (PurchaseBean) request.getAttribute("purchaseInfo");
					ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request.getAttribute("purchaseDetailList");
					NumberFormat nf = NumberFormat.getNumberInstance();
					Timestamp ts = purchaseInfo.getPurchaseDate();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd　HH:mm");
				    String formattedTime = sdf.format(ts);
				    int shippingId = purchaseInfo.getShippingId();
					%>
					<div class="d-flex flex-wrap align-items-center mb-3 p-3 border-bottom">
						<a href="<%= source %>" class="arrow link" style="display: inline-block; color:navy;">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
							  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
							</svg>
						</a>
						<h5 class="mb-0 ms-3">
							<strong>受注詳細</strong>
						</h5>
						<% 
						if (shippingId == 1) { 
						%>
						<div class="ms-auto">
							<!-- モーダルボタン -->
							<button type="button" class="button-light-purple px-4 py-1 text-nowrap text-center" 
									data-bs-toggle="modal" data-bs-target="#staticBackdrop"
									style="border-radius:5px;">
								発送する
							</button>
							<!-- モーダルウィンドウ -->
							<div class="modal fade" id="staticBackdrop"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered w-100">
									<div class="modal-content" id="modalWindow" style="width:100%;">
										<div class="modal-header">
											<small class="ms-2">発送手続き</small>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body p-5 d-flex flex-wrap">
											<p>#<%= purchaseInfo.getPurchaseId() %> の受注商品を発送しますか？</p>
											<small>
												発送ステータスは元に戻すことはできません。<br>
												ステータスの変更は商品を発送した後変更してください。
											</small>
										</div>
										<!-- 発送するボタン -->
										<div class="modal-footer">
											<form action="shipping" method="post">
												<input type="hidden" name="purchaseId" value="<%= purchaseInfo.getPurchaseId() %>">
												<input type="hidden" name="shippingId" value="2">
												<button type="submit" class="button-purple px-3 py-2"
													style="border-radius: 5px;">
													発送する
												</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<% 
						} 
						%>
					</div>
					<% 
					if(purchaseInfo != null) { 
					%>
						<div class="row mb-3">
							<div class="col-lg-6 me-auto">
								<div class="m-3">
									<small class="border p-1" style="border-radius:5px; background-color:cornflowerblue; color:white;">合計金額</small>
									<h4 class="my-2"><strong>￥ <%= nf.format(purchaseInfo.getTotalAmount()) %></strong></h4>
								</div>
								<div class="m-3">
									<small class="border p-1" style="border-radius:5px; background-color:cornflowerblue; color:white;">受注情報</small>
									<p class="my-2">#<%= purchaseInfo.getPurchaseId() %></p>
									<p class="my-2"><%= formattedTime %></p>
								</div>
								<div class="m-3">
									<small class="border p-1" style="border-radius:5px; background-color:cornflowerblue; color:white;">購入者</small>
									<p class="my-2"><%= purchaseInfo.getUserName() %></p>
								</div>
							</div>
							<div class="col-lg-5" style="border-radius:5px;">
								<div class="my-3 border p-3" style="border-radius:5px;">
									<small class="border p-1" style="border-radius:5px; background-color:cornflowerblue; color:white;">配送先</small><br>
									<p class="my-2">〒 <%= purchaseInfo.getPostalCode() %></p>
									<p class="mb-2"><%= purchaseInfo.getAddress() %></p>
									<p class="mb-0"><%= purchaseInfo.getAddressee() %></p>
								</div>
							</div>
						</div>
						<div class="border-top" style="height: 30px;"></div>
						<table class="table table-borderless text-center">
							<tbody>
								<%
								for (PurchaseDetailBean purchaseDetail : purchaseDetailList) {
								%>
								<tr>
									<td class="td" style="width: 120px;">
										<span style="width: 100px; height: 100px;">
											<img src="./images/<%= purchaseDetail.getImageFileName() %>.jpg"
												 class="object-fit-cover border" alt="<%= purchaseDetail.getImageFileName() %>"
												 style="width: 100%; aspect-ratio: 1 / 1; border-radius:0px;">
										</span>
									</td>
									<td class="td"><%= purchaseDetail.getItemName() %> ( <%= purchaseDetail.getOptionCategoryValue() %> )</td>
									<td class="td text-nowrap">￥ <%= nf.format(purchaseDetail.getPurchaseAmount()) %></td>
									<td class="td text-nowrap"><%= purchaseDetail.getQuantity() %> 個</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					<%
					} 
					%>
				</div>
			</div>
		</div>
	</main>
	<script src="./js/purchaseDetailScript.js"></script>
</body>
</html>