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
			<div class="row" style="padding: 30px 0">
				<div class="col-lg-8 mx-auto">
					<% 
					String source = (String)request.getParameter("source");
					%>
					<a href="<%= source %>" class="my-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<h4 class="mb-4 p-3 border-bottom">
						<strong>受注詳細</strong>
					</h4>
					<%
					PurchaseBean purchaseInfo = (PurchaseBean) request.getAttribute("purchaseInfo");
					ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request.getAttribute("purchaseDetailList");
					NumberFormat nf = NumberFormat.getNumberInstance();
					Timestamp ts = purchaseInfo.getPurchaseDate();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd　HH:mm");
				    String formattedTime = sdf.format(ts);
				    int shippingId = purchaseInfo.getShippingId();
					%>
					<% 
					if(purchaseInfo != null) { 
					%>
						<div class="m-3">
							<small>合計金額</small>
							<h4><strong>￥ <%= nf.format(purchaseInfo.getTotalAmount()) %></strong></h4>
						</div>
						<table class="table table-borderless ms-2">
							<tbody>
								<tr>
									<td style="width: 120px;">購入ID　：　</td>
									<th>#<%= purchaseInfo.getPurchaseId() %></th>
								</tr>
								<tr>
									<td style="width: 120px;">購入者　：　</td>
									<th><%= purchaseInfo.getUserName() %></th>
								</tr>
								<tr>
									<td style="width: 120px;">購入日　：　</td>
									<th><%= formattedTime %></th>
								</tr>
							</tbody>
						</table>
						<div class="border-top" style="height: 50px;"></div>
						<table class="table table-borderless text-center">
							<tbody>
								<%
								for (PurchaseDetailBean purchaseDetail : purchaseDetailList) {
								%>
								<tr>
									<td class="td" style="width: 10%;"><%= purchaseDetail.getPurchaseDetailId() %></td>
									<td class="td" style="width: 100px;">
										<span class="card" style="width: 100px; height: 100px;">
											<img src="./images/<%= purchaseDetail.getImageFileName() %>.jpg"
												 class="card-img-top" alt="<%= purchaseDetail.getImageFileName() %>"
												 style="object-fit: cover; height: 100%; display: block;">
										</span>
									</td>
									<td class="td"><%= purchaseDetail.getItemName() %> ( <%= purchaseDetail.getOptionCategoryValue() %> )</td>
									<td class="td">￥ <%= nf.format(purchaseDetail.getPurchaseAmount()) %></td>
									<td class="td"><%= purchaseDetail.getQuantity() %> 個</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
						<% 
						
						%>
						<% 
						if (shippingId == 1) { 
						%>
						<div class="d-flex justify-content-center mb-5">
							<!-- モーダルボタン -->
							<button type="button" class="button-light-purple px-5 py-2 text-nowrap text-center" 
									data-bs-toggle="modal" data-bs-target="#staticBackdrop"
									style="border-radius:5px; margin-top: 70px;">
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