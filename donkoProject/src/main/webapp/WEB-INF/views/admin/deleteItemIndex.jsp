<%@page import="bean.ItemCategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.ItemCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet"  href="./css/deleteItemIndex.css">
<title>DONKO</title>
</head>
<body>
<main>
	<div class="container mt-5">
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
					<div class="col-8">
						<form action="deleteItemIndex" method="post">
							<div class="row">
								<div class="col-4">
									<%
									    ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
									    Integer itemDelFlg = (Integer) request.getAttribute("itemDelFlg");
									    String categoryName = (String) request.getAttribute("categoryName"); // 現在のカテゴリ名を取得
									%>
									<!-- カテゴリでソート -->
									<label for="category-select" class="form-label">カテゴリー選択</label>
									<select class="form-select" id="category-select" name="itemCategoryName">
									    <%
									        if(categoryList != null && itemDelFlg != null && !categoryName.isEmpty()){
									            for(ItemCategoryBean category : categoryList){
									                boolean isSelected = category.getItemCategoryName().equals(categoryName);
									    %>
									                <option value="<%= category.getItemCategoryName() %>" <%= isSelected ? "selected" : "" %>><%= category.getItemCategoryName() %></option>
									    <%
									            }
									        }
									    %>
									</select>
								</div>
								<div class="col-4">
									<!-- 削除フラグでソート -->
									<label for="delFlg-select"  class="form-label">現在の表示</label>
									<select class="form-select" id="delFlg-select"  name="itemDelFlg">
											<option value="2" <%= itemDelFlg == 2 ? "selected" : "" %>>全ての商品</option>
											<option value="0" <%= itemDelFlg == 0 ? "selected" : "" %>>掲載中の商品</option>
											<option value="1" <%= itemDelFlg == 1 ? "selected" : "" %>>削除済みの商品</option>
									</select>
								</div>
								<div class="col-4 d-flex align-items-end">
									<button type="submit" class="btn p-2 ms-3" style="background-color: #e5ccff;">
										検索
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- 商品一覧を掲載 -->
				<div class="row justify-content-center">
					<div class="col-11 mt-4">
							<h2>商品一覧</h1>
						<!-- ここから削除切り替えフォーム  -->
						<form action="swichItemDelFlg" method="get">
								<div class="overflow-auto" style="height: calc(90vh - 200px);">
								<table class="table table-borderless  st-tbl1">
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
									<tbody>
											<div class="overflow" style="overflow: auto;"＞
													<%
													    ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList");
													   	 if(itemList != null && itemList.size() > 0){
													        for(ItemBean item : itemList){
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
											                    <% if(item.getItemSecondOptionValue() != null && !item.getItemSecondOptionValue().isEmpty()){ %>
											                        <p><%= item.getItemSecondOptionValue() %></p>
											                    <% } else { %>
											                        <p>-</p>
											                    <% } %>
											                </td>
											                <td><!-- 金額 -->
											                    <p><%= item.getItemPrice() %></p>
											                </td>
											                <td><!-- 在庫 -->
											                    <p><%= item.getItemStock() %></p>
											                </td>
											                <td><!-- ステータス -->
											                    <% if(item.isDeleted()){ %>
																    <input type="checkbox" name="itemStatus" id="item_<%= item.getItemId() %>" style="display: none;" value="<%= item.getItemId() %>" onclick="changeTextColor(this, 'itemLabel_<%= item.getItemId() %>')">
																    <label for="item_<%= item.getItemId() %>" id="itemLabel_<%= item.getItemId() %>" style="color: #CCC; cursor: pointer;">削除済み</label>
																<% } else { %>
																    <input type="checkbox" name="itemStatus" id="item_<%= item.getItemId() %>" style="display: none;" value="<%= item.getItemId() %>" onclick="changeTextColor(this, 'itemLabel_<%= item.getItemId() %>')">
																    <label for="item_<%= item.getItemId() %>" id="itemLabel_<%= item.getItemId() %>" style="color: #00FF00; cursor: pointer;">販売中</label>
																<% } %>
											                </td>
											                <td><!-- 編集ボタン -->
											                    <button type="submit" style="border: 1px solid #000000; background: #385A37; color: white; text-decoration: none; border-radius: 2rem;">
																    <a href="editItemInfo1?itemId=<%= item.getItemId() %>" style="color: white; text-decoration: none;">編集</a>
																</button>
											                </td>
											            </tr>
													<%
													        }
													    }
													%>
											</div>
									</tbody>
								</table>
								</div>
								<br>
								<div class="row">
								    <div class="col-11 d-flex justify-content-end">
								    <p>チェックしたアイテムのステータスを切り替える</p>
								        <button type="submit" class="btn px-3" onclick="return confirmStatusChange();" style="background-color: #E5CCFF; color: black; border-radius: 2rem;">
										    実行
										</button>
								    </div>
								</div>
						</form>
					</div>
				</div>

	</div>
</main>
<script src="./js/deleteItemScript.js"></script>
</body>
</html>