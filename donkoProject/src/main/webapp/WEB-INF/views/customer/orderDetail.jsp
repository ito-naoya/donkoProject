<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseDetailBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
	<main class="container">
		<%@include file="../component/header.jsp"%>
		<%@include file="../component/headerTopSpace.jsp"%>
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
		<h2>
			購入ID ：
			<%=purchase_id%>
		</h2>
		<table class="table table-borderless">
			<tbody>
				<%
				ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request
						.getAttribute("purchaseDetailList");
				%>
				<%
				for (PurchaseDetailBean purchaseDetailBean : purchaseDetailList) {
				%>
				<tr>
					<td><img class="object-fit-cover subImage"
						style="height: 150px; width: 150px;"
						src="./images/<%= purchaseDetailBean.getImageFileName()%>.jpg">
					</td>
					<!-- 商品タイトル -->
					<td valign="middle"><%= purchaseDetailBean.getItemName()%></td>
					<!-- 合計金額 -->
					<td valign="middle">¥<%= String.format("%,d", purchaseDetailBean.getPurchaseAmount())%></td>
					<!-- 個数 -->
					<td valign="middle" style="width: 10px;"><%= purchaseDetailBean.getQuantity()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>