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
<link rel="stylesheet" href="./css/button.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>donko</title>
</head>
<body>
<%
	ItemBean item = (ItemBean) request.getAttribute("item");
%>
<main>
		<div class="container">
			<div class="row justify-content-center" style="height:100vh;">
				<div class="col-lg-5 m-auto border p-5" style="border-radius:5px; box-shadow:10px 10px 10px lightgray;">
					<a href="adminTopPage" class="arrow mb-3 link" style="display: inline-block; color:navy;">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
						</svg>
					</a>
					<h5 class="mb-4">
						<strong>商品登録</strong>
					</h5>
					<%
						ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
						if(categoryList != null && categoryList.size() > 0 ){
					 %>
						<!-- ここから入力フォーム  -->
						<form action="registItem1" id="registItem1" method="post">
							<div class="mb-3">
								<small>カテゴリ： <span class="badge bg-danger mb-1">必須</span></small>
							    <select class="form-select category-select" name="itemCategoryName">
							        <option selected hidden disabled value="">選択する</option>
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
							    <div class="invalid-feedback">カテゴリを選択してください</div>
							    <%
							        String itemCategoryName = (String)request.getAttribute("itemCategoryName");
							        if (itemCategoryName != null) {
							    %>
							        <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
							            <%= itemCategoryName %>
							        </div>
							    <% } %>
							</div>

							<div class="">
							    <label for="itemName" class="form-label d-flex justify-content-between align-items-center mb-0">
							    	<small>商品名： <span class="badge bg-danger">必須</span></small>
							    	<button type="button" class="btn link p-0 mb-1" 
										data-bs-container="body" data-bs-toggle="popover" 
										data-bs-placement="top" 
										data-bs-content="30 文字以内" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button> 
							    </label>
							    <input type="text" class="form-control" id="itemName" name="itemName" maxlength="30" required  value="<%= item.getItemName() != null ? item.getItemName() : "" %>">
							    <div id="itemNameCount" style="text-align: right; font-size: small; color: grey;">0/30</div>
							    <% String itemName = (String)request.getAttribute("itemName");
									 if (itemName != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
											<%= itemName %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="">
							    <label for="itemDescription" class="form-label d-flex justify-content-between align-items-center mb-0">
							    	<small>商品説明： <span class="badge bg-danger">必須</span></small>
							    	<button type="button" class="btn link p-0 mb-1" 
										data-bs-container="body" data-bs-toggle="popover" 
										data-bs-placement="top" 
										data-bs-content="100 文字以内" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button> 
							    </label>
							    <textarea class="form-control" id="itemDescription" name="itemDescription" rows="3" maxlength="100" required ><%= item.getItemDescription() != null ? item.getItemDescription() : "" %></textarea>
							    <div id="itemDescriptionCount" style="text-align: right; font-size: small; color: grey;">0/100</div>
							     <% String itemDescription = (String)request.getAttribute("itemDescription");
									 if (itemDescription != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
											<%= itemDescription %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="col-4 mb-3">
							    <label for="price" class="form-label d-flex justify-content-between align-items-center mb-0">
							    	<small>金額： <span class="badge bg-danger">必須</span></small>
							    	<button type="button" class="btn link p-0 mb-1" 
										data-bs-container="body" data-bs-toggle="popover" 
										data-bs-placement="top" 
										data-bs-content="¥1~入力可&lt;br&gt;※半角数字以外は入力不可" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button> 
							    </label>
							    <% boolean isPriceSelected = item.getItemPrice() >= 0; %>
							    <input type="text" class="form-control" id="price" name="price"  style="text-align: right" maxlength="10" value="<%= isPriceSelected ? NumberFormat.getNumberInstance().format(item.getItemPrice()) : "" %>">
							    <%
								String itemPrice = (String) request.getAttribute("itemPrice");
								if (itemPrice != null && !itemPrice.isEmpty()) {
								%>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
											<%= itemPrice %>
									  </div>
								  <% } %>
						 	</div>

						 	<div class="col-3 mb-3">
							    <label for="stock" class="form-label d-flex justify-content-between align-items-center mb-0">
							    	<small>在庫： <span class="badge bg-danger">必須</span></small>
							    	<button type="button" class="btn link p-0 mb-1" 
										data-bs-container="body" data-bs-toggle="popover" 
										data-bs-placement="top" 
										data-bs-content="0~9で設定可" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button> 
							    </label>
							    <% boolean isStockSelected = item.getItemStock() >= 0; %>
							    <input type="number" class="form-control" id="stock" name="stock" style="text-align: center" min="0" max="100" value="<%= isStockSelected ? item.getItemStock() : "" %>">
							    <% String itemStock = (String)request.getAttribute("itemStock");
									 if (itemStock != null) {
							 	 %>
						              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
											<%= itemStock %>
									  </div>
								  <% } %>
						 	</div>
						 	<div class="d-flex justify-content-center mt-5">
								<button type=submit class="button-light-purple px-3 py-1" style="border-radius: 5px;">オプションの追加をする</button>
							</div>
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
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script>
  // ポップオーバーを初期化
  var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
  var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
    return new bootstrap.Popover(popoverTriggerEl)
  });
</script>
</body>
</html>