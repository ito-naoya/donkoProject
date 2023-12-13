<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean"%>
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
	<main>
		<div class="row d-flex justify-content-center pt-2">
			<div
				style="margin: 0 15px; width: 30%; text-decoration: none; padding: 10px; padding: 1rem 4rem; font-weight: bold; border: 2px solid #385A37; text-align: center;">
				<a href="editUserInfo"
					style="color: #385A37; vertical-align: middle; text-decoration: none;">個人情報の編集</a>
			</div>
			<div
				style="margin: 0 15px; width: 30%; text-decoration: none; padding: 10px; padding: 1rem 4rem; font-weight: bold; border: 2px solid #385A37; text-align: center;">
				<a href="createShippingAddress"
					style="color: #385A37; vertical-align: middle; text-decoration: none;">配送先の登録</a>
			</div>
			<div
				style="margin: 0 15px; width: 30%; text-decoration: none; padding: 10px; padding: 1rem 4rem; font-weight: bold; border: 2px solid #385A37; text-align: center;">
				<a href="shippingAddressIndex"
					style="color: #385A37; vertical-align: middle; text-decoration: none;">配送先一覧</a>
			</div>
		</div>
		<h2 style="margin-left: 40px; margin-top: 30px;">
			<strong>購入履歴</strong>
		</h2>
		<div
			style="padding-top: 20px; padding-bottom: 20px; margin: 20px 40px 40px; border: 1px solid #333333; overflow-x: scroll;"
			class="border">
			<table class="table table-borderless">
				<thead align="center"
					style="display: flex; justify-content: space-between;">
					<th width="20" style="padding-left: 100px";><strong>No.</strong></th>
					<th width="200"><strong>合計金額</strong></th>
					<th width="200"><strong>購入日</strong></th>
					<th width="300"><strong>配送先</strong></th>
					<th width="300"><strong>配送ステータス</strong></th>
					<br>
				</thead>
				<tbody>
					<%
					ArrayList<PurchaseBean> purchaseList = (ArrayList<PurchaseBean>) request.getAttribute("purchaseList");
					%>
					<%
					for (PurchaseBean purchaseBean : purchaseList) {
					%>
					<tr align="center"
						style="display: flex; justify-content: space-between; height: auto; margin-right: 100px;">
						<!-- 購入履歴ID -->
						<td width="20" style="padding-left: 100px";><a
							href='orderDetail?purchase_id=<%=purchaseBean.getPurchaseId()%>'><%=purchaseBean.getPurchaseId()%></a>
							</button></td>
						<!-- 合計金額 -->
						<td width="200" style="padding-left: 100px";>¥ <%=String.format("%,d", purchaseBean.getTotalAmount())%></td>
						<!-- 購入日 -->
						<td width="200" style="padding-left: 100px";><%=purchaseBean.getPurchaseDate()%></td>
						<!-- 配送先 -->
						<td width="200" style="padding-left: 100px";><%=purchaseBean.getShippingAddress()%></td>
						<!-- 配送ステータス -->
						<td width="200" style="padding-left: 100px";><%=purchaseBean.getShippingStatus()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="logout d-flex justify-content-end" >
			<a href="logout" class="btn px-4"
				style="color: white; background-color: #385A37; border-radius: 40px; margin-bottom:40px; margin-right: 40px;">ログアウト</a>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>