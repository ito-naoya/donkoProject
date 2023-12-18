<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, java.text.NumberFormat, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<main>
		<div class="container" style="margin: 0 140px;">
			<a href="adminTopPage" class="mt-5 mb-3" style="display: inline-block">
				<div class="border text-center" style="width: 50px;">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
	  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
					</svg>
				</div>
			</a>
			<h4 class="my-3">
				<strong>受注一覧</strong>
			</h4>
			<table class="table table-borderless">
				<thead>
					<tr>
						<th>ID</th>
						<th>購入日</th>
						<th>購入ID</th>
						<th>合計金額</th>
						<th>配送先</th>
					<tr>
				</thead>
				<tbody>
					<%
					ArrayList<PurchaseBean> orderItemList = (ArrayList<PurchaseBean>) request.getAttribute("orderItemList");
					%>
					<%
					for (PurchaseBean orderItem : orderItemList) {
					%>
					<tr>
						<td><a href='#'><% %></a></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>