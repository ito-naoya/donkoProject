<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, bean.PurchaseDetailBean, java.text.NumberFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<main>
		<div class="container">
			<div class="row" style="padding: 30px 70px">
				<a href="adminTopPage" class="mt-5 mb-3" style="display: inline-block">
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
				%>
				<div></div>
				<table class="table table-borderless">
					<tbody>
						<tr>
							<td style="width: 10%;">購入ID　：　</td>
							<th><%= purchaseInfo.getPurchaseId() %></th>
						</tr>
						<tr>
							<td style="width: 10%;">購入者　：　</td>
							<th><%= purchaseInfo.getUserName() %></th>
						</tr>
						<tr>
							<td style="width: 10%;">購入日　：　</td>
							<th><%= purchaseInfo.getPurchaseDate() %></th>
						</tr>
					</tbody>
				</table>
				<div class="border-top" style="height: 50px;"></div>
				<table class="table table-borderless text-center">
					<tbody>
						<%
						ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request.getAttribute("purchaseDetailList");
						NumberFormat nf = NumberFormat.getNumberInstance();
						%>
						<%
						for (PurchaseDetailBean purchaseDetail : purchaseDetailList) {
						%>
						<tr>
							<td style="width: 10%;"><%= purchaseDetail.getPurchaseDetailId() %></td>
							<td><%= purchaseDetail.getItemName() %></td>
							<td>￥ <%= nf.format(purchaseDetail.getPurchaseAmount()) %></td>
							<td><%= purchaseDetail.getQuantity() %> 個</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<div class="d-flex justify-content-center">
					<form>
						<input type="hidden">
						<input type="submit" value="発送する" class="btn border" style="margin-top: 100px; background-color:#9933FF; color: white; padding: 12px 80px;">
					</form>
				</div>
			</div>
		</div>
	</main>
</body>
</html>