<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, java.text.NumberFormat, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style>
.th{position: sticky; top: 0;}
.td{vertical-align: middle;}
.arrow:hover{opacity: 0.7;}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<%@include file= "../component/adminheader.jsp" %>
	<%@include file= "../component/adminheaderTopSpace.jsp" %>
	<main>
		<div class="container">
			<div class="row px-5">
				<h5 class="mt-5 mb-3">
					<strong>受注履歴一覧</strong>
				</h5>
				<% 
				ArrayList<PurchaseBean> orderItemList = (ArrayList<PurchaseBean>) request.getAttribute("orderItemList");
				String message = (String) request.getAttribute("message");
				%>
				<% 
				if (orderItemList == null || orderItemList.size() == 0) {
				%>
					<div class="d-flex justify-content-center align-items-center" style="overflow-x: scroll; overflow:scroll; height:74vh;">
						<p class="mb-0" style="color: #385A37;"><%= message %></p>
					</div>
				<% 
				} else { 
				%>
					<div class="border" style=" overflow-x: scroll; overflow:scroll; height:74vh; border-radius:5px;">
						<table class="table table-borderless table-hover text-center">
							<thead>
								<tr>
									<th class="th py-4 ps-4">ID</th>
									<th class="th py-4">購入日</th>
									<th class="th py-4">購入者ID</th>
									<th class="th py-4">購入者名</th>
									<th class="th py-4">合計金額</th>
									<th class="th py-4">配送先</th>
									<th class="th py-4 pe-4">配送ステータス</th>
								<tr>
							</thead>
							<tbody>
								<%
								for (PurchaseBean orderItem : orderItemList) {
								%>
									<% 
									NumberFormat nf = NumberFormat.getNumberInstance();
									Timestamp ts = orderItem.getPurchaseDate();
								    SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd　HH:mm");
								    String formattedTime = sdf.format(ts); 
								    %>
									<tr onclick="location.href='purchaseDetail?purchaseId=<%= orderItem.getPurchaseId() %>&source=purchaseHistory'"
										style="cursor: pointer;">
										<td class="td ps-4">#<%= orderItem.getPurchaseId() %></td>
										<td class="td"><%= formattedTime %></td>
										<td class="td"><%= orderItem.getUserId() %></td>
										<td class="td"><%= orderItem.getUserName() %></td>
										<td class="td">￥ <%= nf.format(orderItem.getTotalAmount()) %></td>
										<td class="td pe-4">
											<small>
												〒 <%= orderItem.getPostalCode() %><br>
												<%= orderItem.getAddress() %><br>
												<%= orderItem.getAddressee() %>
											</small>
										</td>
										<%
										if ((orderItem.getShippingStatus()).equals("処理中")) {
										%>
											<td class="td pe-4" style="color: royalblue;"><%= orderItem.getShippingStatus() %></td>
										<%
										} else {
										%>
											<td class="td pe-4" style="color: #CCC;"><%= orderItem.getShippingStatus() %></td>
										<%
										}
										%>
									</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				<% 
				}
				%>
			</div>
		</div>
	</main>
</body>
</html>