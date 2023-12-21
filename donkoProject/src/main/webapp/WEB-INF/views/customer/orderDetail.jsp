<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, bean.PurchaseDetailBean, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
		<%@include file="../component/header.jsp"%>
		<%@include file="../component/headerTopSpace.jsp"%>
	<main class="container">
	<div class="row">
	<div class="col-lg-6 mx-auto my-5">
		<%
		int purchase_id = (int) request.getAttribute("purchase_id");
		%>
		<a href="myPage" class="mb-3" style="display: inline-block">
			<div class="border text-center" style="width: 50px;">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
					<path fill-rule="evenodd"
						d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"></path>
						</svg>
			</div>
		</a>
		<%
		  PurchaseBean purchaseInfo = (PurchaseBean) request.getAttribute("purchaseInfo");
    %>
		<h4 class="my-3"><strong>購入詳細</strong></h4>
		<hr>
		<h4 class="my-4">
			<strong>注文ID ：
			<%= purchaseInfo.getPurchaseId() %>
			</strong>
		</h4>
		<div class="m-3">
		  <small>合計金額</small><br>
		  <h4>¥<%= String.format("%,d", purchaseInfo.getTotalAmount()) %><br></h4>
		  </div>
		  <table class="table table-borderless ms-2">
		  <tbody>
		  <tr><td>購入日　：　</td><th><%=new SimpleDateFormat("yyyy/MM/dd hh:mm").format(purchaseInfo.getPurchaseDate())%></th></tr>
		  <tr><td>宛先　：　</td><th><%= purchaseInfo.getAddressee() %></th></tr>
		  <tr><td>配送先　：　</td><th>〒<%= purchaseInfo.getPostalCode() %> <%= purchaseInfo.getAddress() %></th></tr>
		  </tbody>
		  </table>
		<hr>
		<table class="table table-borderless">
			<tbody>
			<tr>
			<!-- １行目は画像部分のタイトルを表示させたくないので空でセット -->
			<th></th>
			<th>商品名</th>
			<th>単価</th>
			<th>個数</th>
			<th>小計</th>
			</tr>
				<%
				ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request
						.getAttribute("purchaseDetailList");
				%>
				<%
				for (PurchaseDetailBean purchaseDetailBean : purchaseDetailList) {
				%>
				<tr>
				  <!-- 画像 -->
					<td><img class="object-fit-cover subImage"
						style="height: 150px; width: 150px;"
						src="./images/<%= purchaseDetailBean.getImageFileName()%>.jpg">
					</td>
					<!-- 商品タイトル -->
					<td valign="middle"><%= purchaseDetailBean.getItemName()%> <small>(<%= purchaseDetailBean.getOptionCategoryValue()%>)</small></td>
          <!-- 単価 -->
          <td valign="middle">¥<%= String.format("%,d",purchaseDetailBean.getPrice())%></td>
					<!-- 個数 -->
					<td valign="middle"><%= purchaseDetailBean.getQuantity()%> 個</td>
					<!-- 合計金額 -->
          <td valign="middle">¥<%= String.format("%,d", purchaseDetailBean.getPurchaseAmount())%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>