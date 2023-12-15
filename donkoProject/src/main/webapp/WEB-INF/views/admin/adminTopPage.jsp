<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<main>
		<div class="d-flex justify-content-between mx-5">
			<h4 class="mt-5 mb-3">
				<strong>未発送一覧　 (件)</strong>
			</h4>
			<div class="logout d-flex justify-content-end" >
				<a href="logout" class="btn mt-auto mb-3 border px-5 py-2">ログアウト</a>
			</div>
		</div>
		<div style="margin: 0px 40px 40px; border: 1px solid #000; overflow-x: scroll;" class="border p-4">
			<table class="table table-borderless">
				<thead>
					<tr>
						<th></th>
						<th>購入日</th>
						<th>購入ID</th>
						<th>合計金額</th>
						<th>配送先</th>
					<tr>
				</thead>
				<tbody>
					<%-- <%
					ArrayList<PurchaseBean> purchaseList = (ArrayList<PurchaseBean>) request.getAttribute("purchaseList");
					%>
					<%
					for (PurchaseBean purchaseBean : purchaseList) {
					%> --%>
					<tr>
						<td><a href='#'></a></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<%-- <%
					}
					%> --%>
				</tbody>
			</table>
		</div>
		<div class="row d-flex justify-content-center">
			<a href="purchaseHistory" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">受注一覧</a>
			<a href="registItem1" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">商品登録</a>
			<a href="deleteItemIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除商品一覧</a>
			<a href="deleteUserInfoIndex" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">削除ユーザ情報一覧</a>
			<a href="userSignup" class="d-inline-block border mx-2 mb-4 p-3 text-center" style="width: 30%; background-color:#385A37; color: white;">ユーザ新規登録</a>
			<a href="#" class="mx-2" style="width: 30%;"></a>
		</div>
	</main>
</body>
</html>