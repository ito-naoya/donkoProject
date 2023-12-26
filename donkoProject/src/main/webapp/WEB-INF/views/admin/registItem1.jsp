<%@page import="bean.ItemCategoryBean"%>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemCategoryBean, bean.ItemBean"%>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>donko</title>
</head>
<body>
<%
	ItemBean item = (ItemBean) request.getAttribute("item");
%>
<main class="m-5">
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<div class="col-6">
					<a href="adminTopPage" class="mb-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<br>
					<h2>商品登録</h2>
					<br>
					<br>
					<%
						ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
						if(categoryList != null && categoryList.size() > 0 ){
					 %>
						<!-- ここから入力フォーム  -->
						<form action="registItem1" id="registItem1" method="post" class="needs-validation">
							<div class="mb-3">
							    <select class="form-select category-select" name="itemCategoryName">
							        <option selected hidden disabled value="">カテゴリーを選択</option>
							        <%
							            for (ItemCategoryBean category : categoryList){
							                // item.getItemCategoryName()がnullの場合も考慮
							                boolean isSelected = item.getItemCategoryName() != null && item.getItemCategoryName().equals(category.getItemCategoryName());
							        %>
							            <option value="<%= category.getItemCategoryName() %>" <%= isSelected ? "selected" : "" %>><%= category.getItemCategoryName() %></option>
							        <%
							            }
							        %>
							    </select>
							    <div class="invalid-feedback">カテゴリーを選択してください</div>
							    <%
							        String itemCategoryName = (String)request.getAttribute("itemCategoryName");
							        if (itemCategoryName != null) {
							    %>
							        <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
							            <%= itemCategoryName %>
							        </div>
							    <% } %>
							</div>


							<div class="mb-3">
							    <label for="itemName" class="form-label">商品名</label>
							    <label for="itemName" class="form-label"  style="font-size: small; color: grey;">30文字以内</label>
							    <input type="text" class="form-control" id="itemName" name="itemName" maxlength="30" required  value="<%= item.getItemName() != null ? item.getItemName() : "" %>">
							    <% String itemName = (String)request.getAttribute("itemName");
									 if (itemName != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
											<%= itemName %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="mb-3">
							    <label for="itemDescription" class="form-label">商品説明</label>
							    <label for="itemDescription" class="form-label" style="font-size: small; color: grey;">100文字以内</label>
							    <textarea class="form-control" id="itemDescription" name="itemDescription" rows="3" maxlength="100" required ><%= item.getItemDescription() != null ? item.getItemDescription() : "" %></textarea>
							     <% String itemDescription = (String)request.getAttribute("itemDescription");
									 if (itemDescription != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
											<%= itemDescription %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="col-4 mb-3">
							    <label for="price" class="form-label">金額</label>
							    <label for="price" class="form-label"  style="font-size: small; color: grey;">¥1~入力可※半角数字以外は入力不可</label>
							    <% boolean isPriceSelected = item.getItemPrice() >= 0; %>
							    <input type="text" class="form-control" id="price" name="price"  style="text-align: right" maxlength="10" value="<%= isPriceSelected ? NumberFormat.getNumberInstance().format(item.getItemPrice()) : "" %>">
							    <%
								String itemPrice = (String) request.getAttribute("itemPrice");
								if (itemPrice != null && !itemPrice.isEmpty()) {
								%>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
											<%= itemPrice %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="col-2 mb-3">
							    <label for="stock" class="form-label">在庫</label>
							    <label for="stock" class="form-label"  style="font-size: small; color: grey;">0~9で設定可</label>
							    <% boolean isStockSelected = item.getItemStock() >= 0; %>
							    <input type="number" class="form-control" id="stock" name="stock" style="text-align: center" min="0" max="9" value="<%= isStockSelected ? item.getItemStock() : "" %>">
							    <% String itemStock = (String)request.getAttribute("itemStock");
									 if (itemStock != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
											<%= itemStock %>
									  </div>
								  <% } %>
						 	</div>
						 	<br>
							<button type=submit class="btn px-5 py-3" style="background-color: #E5CCFF; color: black; border-radius: 0.5rem;">オプションの追加をする</button>
						</form>
						<%
						}
						%>
				</div>
			</div>
		</div>
</main>
<script src="./js/registItemScript.js"></script>
<script src="./js/nullValidationScript.js"></script>
</body>
</html>