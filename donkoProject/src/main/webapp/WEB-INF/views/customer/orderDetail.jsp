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
			<div class="col-lg-9 mx-auto my-5">
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
		    	<div class="my-3 d-flex flex-wrap justify-content-between align-items-center">
					<h5 class="mb-0"><strong>購入詳細</strong></h5>
				</div>
				<div class="d-flex justify-content-between align-items-top my-4">
					<div class="border p-4 d-flex align-items-center" style="border-radius:10px;">
						<div>
							注文ID　#<%= purchaseInfo.getPurchaseId() %></span><br>
							<small><%=new SimpleDateFormat("yyyy/MM/dd hh:mm").format(purchaseInfo.getPurchaseDate())%></small>
						</div>
					</div>
					<div class="d-flex flex-wrap align-items-center">
						<div class="mx-5">
							<small class="mt-0 mb-3" style="display: inline-block;"><strong>配送先</strong></small><br>
							<%=purchaseInfo.getAddressee()%><br>
							<%
							String postalCode = (String) purchaseInfo.getPostalCode();
							%>
							<%
							String head = postalCode.substring(0, 3);
							String end = postalCode.substring(3);
							%>
							〒 <%=head%>-<%=end%><br>
							<%=purchaseInfo.getAddress()%>
						</div>
						<div class="mx-4" style="height:100%;">
							<small class="mt-0 mb-3" style="display: inline-block;"><strong>ご請求金額</strong></small><br>
							<h4 class="mb-0">¥ <%=String.format("%,d", purchaseInfo.getTotalAmount())%></h4>
						</div>
					</div>
				</div>
				<table class="table table-borderless my-4">
					<tbody>
						<%
						ArrayList<PurchaseDetailBean> purchaseDetailList = (ArrayList<PurchaseDetailBean>) request
								.getAttribute("purchaseDetailList");
						%>
						<%
						for (PurchaseDetailBean purchaseDetailBean : purchaseDetailList) {
						%>
						<tr class="border" style="border-radius:10px important!;">
						  <!-- 画像 -->
							<td class="p-4" style="width:148px;"><img class="object-fit-cover subImage"
								style="height: 100px; width: 100px;"
								src="./images/<%= purchaseDetailBean.getImageFileName()%>.jpg">
							</td>
							<!-- 商品タイトル -->
							<!-- 単価 -->
							<td valign="middle">
								<%= purchaseDetailBean.getItemName()%> <small>(<%= purchaseDetailBean.getOptionCategoryValue()%>)</small><br>
								¥ <%=String.format("%,d", purchaseDetailBean.getPrice())%>
							</td>
							<!-- 個数 -->
							<td valign="middle" class="p-4 text-end text-nowrap"><%=purchaseDetailBean.getQuantity()%> 個</td>
							<!-- 小計金額 -->
							<td valign="middle" class="p-4 text-end text-nowrap">
								<small>小計金額</small><br>
								¥ <%= String.format("%,d", purchaseDetailBean.getPurchaseAmount())%>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<%
				if ((purchaseInfo.getShippingStatus()).equals("処理中")) {
				%>
					<div>
						<small>商品の発送状況　：　</small>
						<small class="me-3 mb-0 px-3 py-1" style="color: #CCC; border: 0.5px #CCC solid; border-radius:40px;">
							<%= purchaseInfo.getShippingStatus() %>
						</small>
					</div>
				<% } else { %>
					<div>
						<small>商品の発送状況　：　</small>
						<small class="me-3 mb-0 px-3 py-1" style="color: #63A162; border: 0.5px #63A162 solid; border-radius:40px;">
							<%= purchaseInfo.getShippingStatus() %>
						</small>
					</div>
				<% } %>
			</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>