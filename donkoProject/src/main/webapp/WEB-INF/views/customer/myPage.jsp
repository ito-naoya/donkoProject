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
<!--  メニューボタンの表示 -->
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
		<!-- 中間発表時は発送ステータスのそうと機能は非表示 -->
    <!--
     <div class="logout d-flex justify-content-end">
		  <button class="btn" id="hideShippedButton" name="status_sort" value="hidden"
        style="color: ＃000000; background-color: #E5CCFF; border-radius: 40px; margin-right: 40px;">発送済みを非表示</button>
    </div>
    -->
		<div
			style="padding: 20px; margin: 40px 40px 40px; border: 1px solid #333333; overflow-x: scroll;"
			class="border">
			<table class="table table-borderless">
				<thead align="center">
					<tr>
						<th><strong style="white-space:nowrap;">注文番号</strong></th>
						<th><strong>合計金額</strong></th>
						<th><strong>購入日</strong></th>
						<th><strong>配送先</strong></th>
						<th><strong style="white-space:nowrap;">配送ステータス</strong></th>
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
					<tr>
						<!-- 注文番号 -->
						<td align="middle"><a
							href='orderDetail?purchase_id=<%=purchaseBean.getPurchaseId()%>' style="width: 10px; color:#385A37;"><%=purchaseBean.getPurchaseId()%></a>
							</button></td>
						<!-- 合計金額 -->
						<td align="middle" style="white-space:nowrap; ">¥ <%=String.format("%,d", purchaseBean.getTotalAmount())%></td>
						<!-- 購入日 -->
						<td align="middle" style="white-space:nowrap;">
          	<%=new SimpleDateFormat("yyyy/MM/dd hh:mm").format(purchaseBean.getPurchaseDate())%></td>
						<!-- 配送先 -->
						<td align="middle" style="white-space:nowrap;"><%=purchaseBean.getShippingAddress()%></td>
						<!-- 配送ステータス -->
						<td align="middle"><%=purchaseBean.getShippingStatus()%></td>
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
