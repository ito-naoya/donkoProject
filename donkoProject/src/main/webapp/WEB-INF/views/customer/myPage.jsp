<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="stylesheet">
<title>donko</title>
<style>
.border::-webkit-scrollbar {
	display: none;
}
</style>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
<main class="container">
		<div class="d-flex justify-content-between">
			<a href="userInfoEdit"
				class="d-inline-block border mx-2 mb-4 p-3 text-center"
				style="width: 30%; border: #385A37; background-color: white; color: #385A37; text-decoration:none;">個人情報の編集<a>
					<a href="createShippingAddress"
					class="d-inline-block border mx-2 mb-4 p-3 text-center"
					style="width: 30%; border: #385A37; background-color: white; color: #385A37; text-decoration:none;">配送先の登録<a>
							<a href="shippingAddressIndex"
							class="d-inline-block border mx-2 mb-4 p-3 text-center"
							style="width: 30%; border: #385A37; background-color: white; color: #385A37; text-decoration:none;">配送先一覧<a>
		</div>
		<h2 style="margin-left: 40px; margin-top: 30px;">
			<strong>購入履歴</strong>
		</h2>
		<div
			style="padding: 20px; margin: 20px 40px 40px; border: 1px solid #333333; overflow-x: scroll;"
			class="border">
			<table class="table table-borderless">
				<thead align="center">
					<tr>
						<th style="width: 10px;"><strong>No.</strong></th>
						<th><strong>合計金額</strong></th>
						<th><strong>購入日</strong></th>
						<th><strong>配送先</strong></th>
						<th><strong>配送ステータス</strong></th>
					<tr>
				</thead>
				<tbody>
					<%
						ArrayList<PurchaseBean> purchaseList = (ArrayList<PurchaseBean>) request.getAttribute("purchaseList");
						%>
					<%
						for (PurchaseBean purchaseBean : purchaseList) {
						%>
					<tr>
						<!-- 購入履歴ID -->
						<td align="middle" style="width: 10px;"><a
							href='orderDetail?purchase_id=<%=purchaseBean.getPurchaseId()%>'><%=purchaseBean.getPurchaseId()%></a>
							</button></td>
						<!-- 合計金額 -->
						<td align="middle" style="width: 150px;">¥ <%=String.format("%,d", purchaseBean.getTotalAmount())%></td>
						<!-- 購入日 -->
						<td align="middle" style="width: 200px;">
          	<%=new SimpleDateFormat("yyyy-MM-dd hh:mm").format(purchaseBean.getPurchaseDate())%></td>
						<!-- 配送先 -->
						<td align="middle"><%=purchaseBean.getShippingAddress()%></td>
						<!-- 配送ステータス -->
						<td align="middle" style="width: 200px;"><%=purchaseBean.getShippingStatus()%></td>
					</tr>
					<%
						}
						%>
				</tbody>
			</table>
		</div>
		<div class="logout d-flex justify-content-end">
			<a href="logout" class="btn px-4"
				style="color: white; background-color: #385A37; border-radius: 40px; margin-bottom: 40px; margin-right: 40px;">ログアウト</a>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>