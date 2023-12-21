<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, bean.PurchaseBean, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" rel="stylesheet">
<title>donko</title>
<style>
.border::-webkit-scrollbar {
	display: none;
}
.th{
 	position: sticky;
    top: 0;
}
</style>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main class="container">
		<!--  メニューボタンの表示 -->
		<div class="d-flex justify-content-between mx-5 mt-4 mb-2">
			<a href="userInfoPage" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; border: 1px #385A37 solid; color: #385A37; text-decoration: none;">
				ユーザ情報を確認
			</a>
			<a href="createShippingAddress" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; border: 1px #385A37 solid; color: #385A37; text-decoration: none;">
				配送先の登録
			</a>
			<a href="shippingAddressIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; border: 1px #385A37 solid; color: #385A37; text-decoration: none;">
				配送先一覧
			</a>
		</div>
		<div class="logout d-flex justify-content-between mx-5">
			<h4 style="margin-bottom: 0;">
				<strong>購入履歴</strong>
			</h4>
			<button class="btn btn-sm" id="hideShippedButton" name="status_sort"
				value="hidden"
				style="color: ＃000000; background-color: #E5CCFF; border-radius: 40px;">発送済みを非表示</button>
		</div>
		<div
			style="overflow-x: scroll; height:40vh;"
			class="border mx-5 my-3">
			<table class="table table-hover table-borderless m-3">
				<thead align="center">
					<tr>
						<th class="th"><strong style="white-space: nowrap;">注文ID</strong></th>
						<th class="th"><strong>合計金額</strong></th>
						<th class="th"><strong>購入日</strong></th>
						<th class="th"><strong>配送先</strong></th>
						<th class="th"><strong style="white-space: nowrap;">配送ステータス</strong></th>
					<tr>
				</thead>
				<tbody>
					<!-- PurchaseBeanから順次値を取得して表示 -->
					<%
					ArrayList<PurchaseBean> purchaseList = (ArrayList<PurchaseBean>) request.getAttribute("purchaseList");
					%>
					<%
					for (PurchaseBean purchaseBean : purchaseList) {
					%>

					<tr onclick="location.href='orderDetail?purchase_id=<%=purchaseBean.getPurchaseId()%>'"
						style="cursor: pointer;">
						<!-- 注文番号 -->
						<td align="middle" style="color: #385A37;"><%=purchaseBean.getPurchaseId()%></td>
						<!-- 単価 -->
						<td align="middle" style="white-space: nowrap;">¥ <%=String.format("%,d", purchaseBean.getTotalAmount())%></td>
						<!-- 購入日 -->
						<td align="middle" style="white-space: nowrap;"><%=new SimpleDateFormat("yyyy/MM/dd hh:mm").format(purchaseBean.getPurchaseDate())%></td>
						<!-- 配送先 -->
						<td align="middle" style="white-space: nowrap;">〒 <%=purchaseBean.getPostalCode()%>
							<%=purchaseBean.getAddress()%> <%=purchaseBean.getAddressee()%></td>
						<!-- 配送ステータス -->
						<% if ((purchaseBean.getShippingStatus()).equals("処理中")) { %>
							<td align="middle" style="color:#CCC;"><%=purchaseBean.getShippingStatus()%></td>
						<% } else { %>
							<td align="middle" style="color:#00FF00;"><%=purchaseBean.getShippingStatus()%></td>
						<% } %>
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
	<script src="./js/myPageScript.js"></script>
</body>
</html>
