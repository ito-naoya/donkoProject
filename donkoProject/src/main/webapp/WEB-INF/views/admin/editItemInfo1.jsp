<%@page import="bean.ItemCategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.ItemCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>donko</title>
</head>
<body>
<main class="m-5">
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<div class="col-6">
					<%
						//渡されたidに基づく商品詳細を取得
						ItemBean item = (ItemBean) request.getAttribute("item");
						if(item != null){
							//カテゴリのデフォルト情報を取得
							String defaultItemCategoryName = item.getItemCategoryName();
							String errorMessage = (String) request.getAttribute("errorMessage");
					%>
					<a href="deleteItemIndex?itemCategoryName=<%= defaultItemCategoryName %>&itemDelFlg=2" class="mb-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<br>
					<h2>商品情報編集</h2>
					<h6 style="color: red;">※全て必須項目です</h6>
					<br>
						<%
							if(errorMessage != null && !errorMessage.isEmpty()) {
						%>
							    <div class="alert alert-danger alert-message" role="alert">
							        <%= errorMessage %>
							    </div>
							<%
							}
							%>
							<div id="error-message-container" class="alert alert-danger d-none"></div>
							<!-- ここから入力フォーム  -->
							<form action="editItemInfo1" id="registItem1" method="post" class="needs-validation" novalidate>
								<div class="mb-3">
									<label for="itemCategory" class="form-label">カテゴリ：<%= item.getItemCategoryName() %></label>
								</div>
								<div class="mb-3">
								    <label for="itemName" class="form-label">商品名</label>
								    <input type="text" class="form-control" id="itemName" name="itemName" maxlength="30" value="<%= item.getItemName() %>" required>
								    <div class="invalid-feedback">商品名を入力してください</div>
							 	</div>
							 	<div class="mb-3">
								    <label for="itemDescription" class="form-label">商品説明</label>
								    <textarea class="form-control" id="itemDescription" name="itemDescription" rows="3" required maxlength="100"><%= item.getItemDescription().trim() %></textarea>
								    <div class="invalid-feedback">商品説明を入力してください</div>
							 	</div>
							 	<div class="col-4 mb-3">
								    <label for="price" class="form-label">金額</label>
								    <input type="text" class="form-control" id="price" name="price" maxlength="11"  value="<%= NumberFormat.getNumberInstance().format(item.getItemPrice()) %>" required style="text-align: right">
								    <div class="invalid-feedback">金額を入力してください</div>
							 	</div>
							 	<div class="col-2 mb-3">
								    <label for="stock" class="form-label">在庫</label>
								    <input type="number" class="form-control" id="stock" name="stock" min="0" max="9" value="<%= item.getItemStock() %>" required style="text-align: center">
								    <div class="invalid-feedback">在庫を入力してください</div>
							 	</div>
							 	<br>
							 	<input type="hidden" name="itemId" value="<%= item.getItemId() %>">
							 	<input type="hidden" name="itemCategoryName" value="<%= item.getItemCategoryName() %>">
							 	<input type="hidden" name="fileName" value="<%= item.getImageFileName() %>">
							 	<input type="hidden" name="firstOptionId" value="<%= item.getItemFirstOptionIncrementId() %>">
							 	<input type="hidden" name="secondOptionId" value="<%= item.getItemSecondOptionIncrementId() %>">

								<button type=submit class="btn px-5 py-3" style="background-color: #E5CCFF; color: black; border-radius: 0.5rem;">オプションの追加をする</button>
							</form>
						<% } %>
				</div>
			</div>
		</div>
</main>
<script src="./js/registItemScript.js"></script>
<script src="./js/nullValidationScript.js"></script>
</body>
</html>





