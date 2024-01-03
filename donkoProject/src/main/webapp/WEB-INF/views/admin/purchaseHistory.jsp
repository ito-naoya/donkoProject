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
	<main>
		<div class="container">
			<div class="row px-5">
				<a href="adminTopPage" class="mt-5 mb-3 arrow" style="display: inline-block; color:#385A37;">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
					  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
					</svg>
				</a>
				<h4 class="my-3 th">
					<strong>受注一覧</strong>
				</h4>
				<% 
				ArrayList<PurchaseBean> orderItemList = (ArrayList<PurchaseBean>) request.getAttribute("orderItemList");
				String message = (String) request.getAttribute("message");
				%>
				<% 
				if (orderItemList == null || orderItemList.size() == 0) {
				%>
					<div class="mx-5 p-5 d-flex justify-content-center align-items-center" style="overflow-x: scroll; overflow:scroll; height:400px;">
						<p class="mb-0" style="color: #385A37;"><%= message %></p>
					</div>
				<% 
				} else { 
				%>
					<div style=" overflow-x: scroll; overflow:scroll; height:70vh; border-radius:5px;" class="border px-5">
						<table class="table table-borderless table-hover text-center my-5">
							<thead>
								<tr>
									<th class="th">ID</th>
									<th class="th">購入日</th>
									<th class="th">購入者ID</th>
									<th class="th">購入者名</th>
									<th class="th">合計金額</th>
									<th class="th">配送先</th>
									<th class="th">配送ステータス</th>
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
										<td class="td">#<%= orderItem.getPurchaseId() %></td>
										<td class="td"><%= formattedTime %></td>
										<td class="td"><%= orderItem.getUserId() %></td>
										<td class="td"><%= orderItem.getUserName() %></td>
										<td class="td">￥ <%= nf.format(orderItem.getTotalAmount()) %></td>
										<td class="td">
											<small>
												〒 <%= orderItem.getPostalCode() %><br>
												<%= orderItem.getAddress() %><br>
												<%= orderItem.getAddressee() %>
											</small>
										</td>
										<td class="td"><%= orderItem.getShippingStatus() %></td>
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