<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.PurchaseBean, java.text.NumberFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style>
.th{
 	position: sticky;
    top: 0;
    left: 0;
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
		%>
		<div class="d-flex justify-content-between mx-5">
			<h4 class="mt-5 mb-3">
				<strong>未発送一覧　 (<%= unshippingedItemList.size() %>件)</strong>
			</h4>
			<div class="logout d-flex justify-content-end" >
				<a href="logout" class="btn mt-auto mb-3 border px-5 py-2">ログアウト</a>
			</div>
		</div>
		<div style="margin-bottom:40px; border: 1px solid #000; overflow-x: scroll; overflow:scroll; height:400px;" class="border mx-5">
			<table class="table table-borderless" style="margin: 40px;">
				<thead>
					<tr>
						<th class="th">ID</th>
						<th class="th">購入日</th>
						<th class="th">購入者ID</th>
						<th class="th">購入者</th>
						<th class="th">合計金額</th>
						<th class="th">配送先</th>
					<tr>
				</thead>
				<tbody>
					<% if (unshippingedItemList != null) { %>
						<% 
						NumberFormat nf = NumberFormat.getNumberInstance();
						%>
						<%
						for (PurchaseBean purchaseBean : unshippingedItemList) {
						%>
						<tr>
							<td class="td"><a href='purchaseDetail?id=<%= purchaseBean.getPurchaseId() %>'><%= purchaseBean.getPurchaseId() %></a></td>
							<td class="td"><%= purchaseBean.getPurchaseDate() %></td>
							<td class="td"><%= purchaseBean.getUserId() %></td>
							<td class="td"><%= purchaseBean.getUserName() %></td>
							<td class="td">￥ <%= nf.format(purchaseBean.getTotalAmount()) %></td>
							<td class="td">
								<small>
								〒 <%= purchaseBean.getPostalCode() %><br>
								<%= purchaseBean.getAddress() %><br>
								<%= purchaseBean.getAddressee() %>
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
		<div class="row d-flex justify-content-center">
			<a href="purchaseHistory" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">受注一覧</a>
			<a href="registItem1" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">商品登録</a>
			<a href="deleteItemIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除商品一覧</a>
			<a href="deleteUserInfoIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除ユーザ情報一覧</a>
			<a href="userSignup" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">ユーザ新規登録</a>
			<a href="home" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">ホーム画面に移動</a>
		</div>
	</main>
</body>
</html>