<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, java.text.NumberFormat, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style>
.th{
 	position: sticky;
    top: 0;
}
.td{
	vertical-align: middle;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<main>
		<%
		ArrayList<PurchaseBean> unshippingedItemList = (ArrayList<PurchaseBean>) request.getAttribute("unshippingedItemList");
		String message = (String) request.getAttribute("message");
		%>
		<div class="d-flex justify-content-between mx-5">
			<h4 class="mt-5 mb-3">
				<strong>未発送一覧　
					<% if (unshippingedItemList != null) { %>
						(<%= unshippingedItemList.size() %>件)
					<% } %>
				</strong>
			</h4>
			<div class="logout d-flex justify-content-end" >
				<a href="logout" class="btn mt-auto mb-3 border px-5 py-2">ログアウト</a>
			</div>
		</div>
		<% if (unshippingedItemList == null || unshippingedItemList.size() == 0) { %>
			<div class="border mx-5 p-5 d-flex justify-content-center align-items-center" style="margin-bottom:40px; overflow-x: scroll; overflow:scroll; height:400px;">
				<p class="mb-0" style="color: #385A37;"><%= message %></p>
			</div>
		<% } else { %>
			<div style="margin-bottom:40px; border: 1px solid #000; overflow-x: scroll; overflow:scroll; height:400px;" class="border mx-5 px-3">
				<table class="table table-borderless text-center my-5">
					<thead>
						<tr>
							<th class="th">注文ID</th>
							<th class="th" style="width:20%;">購入日</th>
							<th class="th" style="width:10%;">購入者ID</th>
							<th class="th">購入者</th>
							<th class="th" style="width:15%;">合計金額</th>
							<th class="th">配送先</th>
						<tr>
					</thead>
					<tbody>
						<% if (unshippingedItemList != null) { %>
							<%
							for (PurchaseBean unshippingedItem : unshippingedItemList) {
							%>
							<%
							NumberFormat nf = NumberFormat.getNumberInstance();
							Timestamp ts = unshippingedItem.getPurchaseDate();
						    SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd　HH:mm");
						    String formattedTime = sdf.format(ts);
							%>
							<tr>
								<td class="td"><a href='purchaseDetail?purchaseId=<%= unshippingedItem.getPurchaseId() %>&source=adminTopPage'><%= unshippingedItem.getPurchaseId() %></a></td>
								<td class="td"><%= formattedTime %></td>
								<td class="td"><%= unshippingedItem.getUserId() %></td>
								<td class="td"><%= unshippingedItem.getUserName() %></td>
								<td class="td">￥ <%= nf.format(unshippingedItem.getTotalAmount()) %></td>
								<td class="td">
									<small>
									〒 <%= unshippingedItem.getPostalCode() %><br>
									<%= unshippingedItem.getAddress() %><br>
									<%= unshippingedItem.getAddressee() %>
									</small>
								</td>
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
		<div class="row d-flex justify-content-center">
			<a href="purchaseHistory" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">受注一覧</a>
			<a href="registItem1" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">商品登録</a>
			<a href="deleteItemIndex?itemCategoryName=全ての商品&itemDelFlg=1&order=asc" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除商品一覧</a>
			<a href="deleteUserInfoIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除ユーザ情報一覧</a>
			<a href="userSignup" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">ユーザ新規登録</a>
			<a href="optionIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">オプションを編集</a>
		</div>
	</main>
</body>
</html>