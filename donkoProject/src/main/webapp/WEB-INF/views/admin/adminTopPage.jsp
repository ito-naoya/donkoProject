<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, bean.PurchaseBean, java.text.NumberFormat, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style>
.th {
	position: sticky;
	top: 0;
}

.td {
	vertical-align: middle;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<%
	ArrayList<PurchaseBean> unshippingedItemList = (ArrayList<PurchaseBean>) request.getAttribute("unshippingedItemList");
	String message = (String) request.getAttribute("message");
	%>
	<%@include file="../component/adminheader.jsp"%>
	<%@include file="../component/adminheaderTopSpace.jsp"%>
	<main>
		<div class="container">
			<div class="row px-5">
				<h5 class="mt-5 mb-3">
					<strong>未発送商品一覧 <%
					if (unshippingedItemList != null) {
					%> (<%=unshippingedItemList.size()%>件)
						<%
					}
					%>
					</strong>
				</h5>
				<%
				if (unshippingedItemList == null || unshippingedItemList.size() == 0) {
				%>
				<div
					class="border p-5 d-flex justify-content-center align-items-center"
					style="margin-bottom: 40px; overflow-x: scroll; overflow: scroll; height: 67vh; border-radius: 5px;">
					<p class="mb-0" style="color: lightgray;"><%=message%></p>
				</div>
				<%
				} else {
				%>
				<div class="border"
					style="margin-bottom: 40px; border: 1px solid #000; overflow-x: scroll; overflow: scroll; height: 67vh; border-radius: 5px;">
					<table class="table table-borderless table-hover text-center">
						<thead>
							<tr>
								<th class="th py-4 ps-4">ID</th>
								<th class="th py-4" style="width: 20%;">購入日</th>
								<th class="th py-4" style="width: 10%;">購入者ID</th>
								<th class="th py-4">購入者</th>
								<th class="th py-4" style="width: 15%;">合計金額</th>
								<th class="th py-4 pe-4">配送先</th>
							<tr>
						</thead>
						<tbody>
							<%
							if (unshippingedItemList != null) {
							%>
							<%
							for (PurchaseBean unshippingedItem : unshippingedItemList) {
							%>
							<%
							NumberFormat nf = NumberFormat.getNumberInstance();
							Timestamp ts = unshippingedItem.getPurchaseDate();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd　HH:mm");
							String formattedTime = sdf.format(ts);
							%>
							<tr
								onclick="location.href='purchaseDetail?purchaseId=<%=unshippingedItem.getPurchaseId()%>&source=adminTopPage'"
								style="cursor: pointer;">
								<td class="td ps-4">#<%=unshippingedItem.getPurchaseId()%></td>
								<td class="td"><%=formattedTime%></td>
								<td class="td"><%=unshippingedItem.getUserId()%></td>
								<td class="td"><%=unshippingedItem.getUserName()%></td>
								<td class="td">￥ <%=nf.format(unshippingedItem.getTotalAmount())%></td>
								<td class="td pe-4"><small> 〒 <%=unshippingedItem.getPostalCode()%><br>
										<%=unshippingedItem.getAddress()%><br> <%=unshippingedItem.getAddressee()%>
								</small></td>
							</tr>
							<%
							}
							%>
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
	<%@include file="../component/adminfooter.jsp"%>
</body>
</html>