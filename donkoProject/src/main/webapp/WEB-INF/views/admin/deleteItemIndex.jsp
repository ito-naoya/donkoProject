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
<title>donko</title>
</head>
<body>
<script>

</script>

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
					<div class="col-10">
						<form action="deleteItemIndex" method="get">
							<div class="row">
								<div class="col-2">
									<%
									    ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
									    Integer itemDelFlg = (Integer) request.getAttribute("itemDelFlg");
									    String categoryName = (String) request.getAttribute("categoryName");
									    String sortOrder = (String) request.getAttribute("order");
									    String keyword = (String) request.getAttribute("keyword");
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
								<div class="col-2">
									<!-- 削除フラグでソート -->
									<label for="delFlg-select" class="form-label">現在の表示</label>
										<div id="delFlg-select">
										    <div class="form-check">
										        <input class="form-check-input" type="radio" name="itemDelFlg" id="delFlgAll" value="2" <%= itemDelFlg == 2 ? "checked" : "" %>>
										        <label class="form-check-label" for="delFlgAll">全て</label>
										    </div>
										    <div class="form-check">
										        <input class="form-check-input" type="radio" name="itemDelFlg" id="delFlgActive" value="0" <%= itemDelFlg == 0 ? "checked" : "" %>>
										        <label class="form-check-label" for="delFlgActive">掲載中</label>
										    </div>
										    <div class="form-check">
										        <input class="form-check-input" type="radio" name="itemDelFlg" id="delFlgInactive" value="1" <%= itemDelFlg == 1 ? "checked" : "" %>>
										        <label class="form-check-label" for="delFlgInactive">停止中</label>
										    </div>
										</div>
								</div>
								<div class="col-2">
									<label for="order-select" class="form-label">並び順</label>
								    <div id="order-select">
								        <div class="form-check">
								            <input class="form-check-input" type="radio" name="order" id="orderAsc" value="asc" <%= "asc".equals(sortOrder) ? "checked" : "" %>>
								            <label class="form-check-label" for="orderAsc">昇順</label>
								        </div>
								        <div class="form-check">
								            <input class="form-check-input" type="radio" name="order" id="orderDesc" value="desc" <%= "desc".equals(sortOrder) ? "checked" : "" %>>
								            <label class="form-check-label" for="orderDesc">降順</label>
								        </div>
								    </div>
							    </div>
							    <div class="col-3">
								    <!-- キーワード検索の入力ボックス -->
								    <label for="keyword-search" class="form-label">キーワード検索</label>
								    <input type="text" class="form-control" id="keyword-search" name="keyword" placeholder=<%= keyword == null ? "キーワードを入力" : keyword %>>
								</div>
								<div class="col-3 d-flex align-items-center">
									<button type="submit" class="btn p-2 ms-3" style="background-color: #e5ccff;">
										絞り込む
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br>
				<!-- 各種メッセージ -->
				<%
				    // リクエストからmessage属性を取得
				    String message = (String) request.getAttribute("message");
				    // messageがnullでない場合にのみ表示
				    if (message != null) {
				%>
				        <div class="alert alert-warning" role="alert">
				            <%= message %>
				        </div>
				<%
				    }
				%>
				<!-- 商品一覧を掲載 -->
				<div class="row justify-content-center">
					<div class="col-11 mt-4">
						<!-- ここから削除切り替えフォーム  -->
						<form action="deleteItemIndex" method="post">
							<h2>削除商品一覧</h1>
							<h6>商品をダブルクリックで編集できます</h6>
								<div class="overflow-auto" style="height: calc(85vh - 200px);">
								<table class="table table-borderless  st-tbl1" id="itemTable">
									<thead>
									    <tr>
									      <th scope="col">ID</th>
									      <th scope="col">画像</th>
									      <th scope="col">商品名</th>
									      <th scope="col">カテゴリー</th>
									      <th scope="col">オプション1</th>
									      <th scope="col">オプション2</th>
									      <th scope="col">金額</th>
									      <th scope="col">在庫数</th>
									      <th scope="col">ステータス</th>
									      <th scope="col">#</th>
									    </tr>
									</thead>
									<tbody>
											<%
											    ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList");
											   	 if(itemList != null && itemList.size() > 0){
											        for(ItemBean item : itemList){
											%>
									            <tr style="cursor: pointer;" ondblclick="location.href='editItemInfo1?itemId=<%= item.getItemId() %>'">
									                <td style="vertical-align: middle;"><!-- ID -->
									                    <p><%= item.getItemId() %></p>
									                </td>
									                <td class="td" style="width: 60px; vertical-align: middle;">
														<span class="card" style="width: 60px; height: 60px;">
															<img src="./images/<%= item.getImageFileName() %>.jpg"
																 class="card-img-top" alt="<%= item.getImageFileName() %>"
																 style="object-fit: cover; height: 100%; display: block;">
														</span>
													</td>
									                <td style="vertical-align: middle;"><!-- 商品名 -->
									                    <p><%= item.getItemName() %></p>
									                </td>
									                <td style="vertical-align: middle;"><!-- カテゴリー -->
									                    <p><%= item.getItemCategoryName() %></p>
									                </td>
									                <td style="vertical-align: middle;"><!-- オプション1 -->
									                    <p><%= item.getItemFirstOptionValue() %></p>
									                </td>
									                <td style="vertical-align: middle;"><!-- オプション2 -->
									                    <% if(item.getItemSecondOptionValue() != null && !item.getItemSecondOptionValue().isEmpty()){ %>
									                        <p><%= item.getItemSecondOptionValue() %></p>
									                    <% } else { %>
									                        <p>-</p>
									                    <% } %>
									                </td>
									                <td style="vertical-align: middle;"><!-- 金額 -->
									                    <%
														    NumberFormat formatter = NumberFormat.getInstance();
														    String formattedPrice = formatter.format(item.getItemPrice());
														%>
														<p><%= formattedPrice %></p>
									                </td>
									                <td style="vertical-align: middle;"><!-- 在庫 -->
									                    <p><%= item.getItemStock() %></p>
									                </td>
									                <td style="vertical-align: middle;"><!-- ステータス -->
									                    <% if(item.isDeleted()){ %>
														    <p style="color: #CCC;">停止中</p>
														<% } else { %>
															<p style="color: #00FF00;">販売中</p>
														<% } %>
									                </td>
									                <td><!-- チェックボックス -->
									                	<input type="checkbox" name="itemStatus" id="item_<%= item.getItemId() %>" value="<%= item.getItemId() %>">
													</td>
									            </tr>
											<%
											        }
											    }
											%>
									</tbody>
								</table>
								</div>
								<br>

							<input type=hidden name="itemCategoryName" value="<%= categoryName %>">
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