<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, bean.PurchaseDetailBean, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.arrow:hover{opacity: 0.7;}
</style>
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
		<a href="myPage" class="mb-3" style="display: inline-block; color:#385A37;">
			<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square arrow" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
			</svg>
		</a>
		<%
		  PurchaseBean purchaseInfo = (PurchaseBean) request.getAttribute("purchaseInfo");
    %>
		<h4 class="my-3"><strong>購入詳細</strong></h4>
		<hr>
		<h5 class="ms-3 my-4">
			<strong>注文ID ：
			<%= purchaseInfo.getPurchaseId() %>
			</strong>
		</h5>
		<div class="m-3">
		  <small>合計金額</small><br>
		  <h4>¥<%= String.format("%,d", purchaseInfo.getTotalAmount()) %><br></h4>
		  </div>
		  <table class="table table-borderless ms-2">
		  <tbody>
		  <tr><td>購入日　　：　　<%=new SimpleDateFormat("yyyy/MM/dd hh:mm").format(purchaseInfo.getPurchaseDate())%></td></tr>
		  <tr><td>宛先　　　：　　<%= purchaseInfo.getAddressee() %></td></tr>
		  <tr><td>配送先　　：　　〒 
		  <% String postalCode = (String) purchaseInfo.getPostalCode();%> 
		  <% String head = postalCode.substring(0, 3);
		     String end = postalCode.substring(3);%>
		  <%=head%>-<%=end%> <%=purchaseInfo.getAddress()%></td></tr>
		  </tbody>
		  </table>
		<hr>
		<table class="table table-borderless text-center">
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
						style="height: 100px; width: 100px;"
						src="./images/<%= purchaseDetailBean.getImageFileName()%>.jpg">
					</td>
					<!-- 商品タイトル -->
					<td valign="middle"><%= purchaseDetailBean.getItemName()%> <small>(<%= purchaseDetailBean.getOptionCategoryValue()%>)</small></td>
					<!-- 単価 -->
					<td valign="middle">¥<%=String.format("%,d", purchaseDetailBean.getPrice())%></td>
					<!-- 個数 -->
					<td valign="middle"><%=purchaseDetailBean.getQuantity()%> 個</td>
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