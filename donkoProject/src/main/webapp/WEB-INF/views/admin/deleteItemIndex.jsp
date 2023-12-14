<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="bean.ItemCategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DONKO</title>
</head>
<body>
<main>
	<div class="container">
				<div class="row justify-content-center">
					<!-- 戻るボタン -->
					<div class="col-2">
						<a href="adminTopPage" class="mb-3" style="display: inline-block">
							<div class="border text-center" style="width: 50px;">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
				  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
								</svg>
							</div>
						</a>
					</div>
					<!-- ここから入力フォーム  -->
					<form action="deleteItemIndex" method="post">
						<div class="col-8">
							<%
							ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
							String itemDelFlg = (String) getAttribute("itemDelFlg")
							%>
							<!-- カテゴリでソート(配列に「全商品」を追加する？) -->
							<label for="category-select"  class="form-label">カテゴリー選択：</label>
							<select class="form-select" id="category-select"  name="itemCategoryName">
									<%
									if(categoryList != null){
										categoryList.forEachc(category) -> {
											boolean isSelected1 = categoryList.getItemCategoryName() == categoryName;
									%>
											<option value="<%= categoryList.getItemCategoryName() %>" <%= isSelected1 ? "selected" : "" %>><%= categoryList.getgetItemCategoryName() %></option>
									<%
										}
									}
									%>
							</select>

							<!-- 削除フラグでソート -->
							<label for="delFlg-select"  class="form-label">現在の表示：</label>
							<select class="form-select" id="delFlg-select"  name="delFlg">
									<option value="2" <%= itemDelFlg == 2 ? "selected" : "" %>>全ての商品</option>
									<option value="0" <%= itemDelFlg == 0 ? "selected" : "" %>>掲載中の商品</option>
									<option value="1" <%= itemDelFlg == 1 ? "selected" : "" %>>削除済みの商品</option>
							</select>
						</div>
						<div class="col-2">
							<button type="submit" class="btn p-2 ms-3" style="background-color: #e5ccff;">
								検索
							</button>
						</div>
					</form>
				</div>
				<!-- 商品一覧を掲載 -->
				<div class="row justify-content-center">
					<div class="col-11">
							<h2>商品一覧</h1>
						<table class="table table-borderless">
							<thead>
							    <tr>
							      <th scope="col">ID</th>
							      <th scope="col">商品名</th>
							      <th scope="col">カテゴリー</th>
							      <th scope="col">オプション1</th>
							      <th scope="col">オプション2</th>
							      <th scope="col">金額</th>
							      <th scope="col">在庫数</th>
							      <th scope="col">ステータス</th>
							      <th scope="col">編集</th>
							    </tr>
							</thead>
								<%
								ArrayList<ItemCategoryBean> itemList = (ArrayList<ItemCategoryBean>) request.getAttribute("itemList");
								if(itemList != null){
									itemList.forEachc(item) -> {
								%>
								<tr>
									<td><!-- ID -->
										<p><%= item.getItemId() %></p>
									</td>
									<td><!-- 商品名 -->
										<p><%= item.getItemName() %></p>
									</td>
									<td><!-- カテゴリー -->
										<p><%= item.getItemCategoryName() %></p>
									</td>
									<td><!-- オプション1 -->
										<p><%= item.getItemFirstOptionValue() %></p>
									</td>
									<td><!-- オプション2 -->
										<% if(item.getItemSecondOptionValue()){ %>
											<p><%= item.getItemFirstOptionValue() %></p>
										<% } else { %>
											<p>-<p>
										<% } %>
									</td>
									<td><!-- 金額 -->
										<p><%= item.getItemPrice() %></p>
									</td>
									<td><!-- 在庫 -->
										<p><%= item.getItemStock() %></p>
									</td>
									<td><!-- ステータス -->
										<p><%= item.getItemStock() %></p>
									</td>
									<td><!-- 編集ボタン -->
										<button type="submit" style="border: 1px solid #000000; background: #385A37; text-decoration: none; border-radius: 0.5rem;">
												 <a href="editShippingAddress?=<%= item.getItemId() %>"
							            </button>
									</td>
								<tr>
								<%
									}
								}
								%>
						</table>
					</div>
					<%
					if(cartBeanList.size() > 0){
					%>
						<div class="d-flex justify-content-center">
							<form action="deleteCart" method="POST" class="d-flex justify-content-end mt-3">
								<button type=submit class="btn px-5 py-3 rounded-pill border border-danger" style="background-color: white; color: red;">
									カートの中身を全て削除する
								</button>
							</form>
						</div>
					<%
					}
					%>
				</div>
		</div>
</main>
</body>
</html>