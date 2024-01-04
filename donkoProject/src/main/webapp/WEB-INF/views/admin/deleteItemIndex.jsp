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
<style>
.arrow:hover{opacity: 0.7;}
</style>
<title>donko</title>
</head>
<body>
<script>

</script>

<main>
	<div class="container mt-5">
		<div class="row">
				<div class="col-3 d-flex flex-wrap mt-4">
					<!-- ここから入力フォーム  -->
					<form action="deleteItemIndex" method="get">
							<!-- 戻るボタン -->
							<div class="m-1">
								<a href="adminTopPage" class="arrow mb-3" style="display: inline-block; color:#385A37;">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
									  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
									</svg>
								</a>
							</div>
							<div class="mx-1 mb-4">
								<%
								    ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
								    Integer itemDelFlg = (Integer) request.getAttribute("itemDelFlg");
								    String categoryName = (String) request.getAttribute("categoryName");
								    String sortOrder = (String) request.getAttribute("order");
								    String keyword = (String) request.getAttribute("keyword");
								%>
								<!-- カテゴリでソート -->
								<label for="category-select" class="form-label"><small>カテゴリー選択：</small></label>
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
							<div class="mx-1 mb-4">
								<!-- 削除フラグでソート -->
								<label for="delFlg-select" class="form-label"><small>現在の表示：</small></label>
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
							<div class="mx-1 mb-4">
								<label for="order-select" class="form-label"><small>並び順：</small></label>
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
						    <div class="mx-1 mb-4">
							    <!-- キーワード検索の入力ボックス -->
							    <label for="keyword-search" class="form-label">キーワード検索：</label>
							    <input type="text" class="form-control" id="keyword-search" name="keyword" placeholder=<%= keyword == null ? "キーワードを入力" : keyword %>>
							</div>
							<div class="m-1 d-flex align-items-center">
								<button type="submit" class="btn mt-5 p-2 w-100" style="background-color: #9933ff; color:white;">
									絞り込む
								</button>
							</div>
					</form>
				</div>
				
				<div class="col-9">
					<!-- 各種メッセージ -->
					<%
					    // リクエストからmessage属性を取得
					    String message = (String) request.getAttribute("message");
					    // messageがnullでない場合にのみ表示
					    if (message != null) {
					%>
					        <div class="alert alert-success" role="alert">
					            <%= message %>
					        </div>
					<%
					    }
					%>
					<!-- 商品一覧を掲載 -->
					<div class="justify-content-center">
						<div class="mt-4">
							<!-- ここから削除切り替えフォーム  -->
							<form action="deleteItemIndex" method="post">
								<div class="mb-4" style="display: flex; align-items: end;">
								    <h4 class="mb-0 me-3"><strong>削除商品一覧</strong></h4>
								    <small class="mx-3" style="vertical-align: middle;">
								  		ダブルクリックで商品を編集
								    </small>
								</div>
	
									<div class="overflow-auto border px-4" style="height: 70vh; border-radius:5px;">
									<table class="table table-borderless st-tbl1 my-4 text-center" id="itemTable">
										<thead>
										    <tr>
										      <th scope="col" class="text-nowrap"><small>ID</small></th>
										      <th scope="col" class="text-nowrap"><small>画像</small></th>
										      <th scope="col" class="text-nowrap"><small>商品名</small></th>
										      <th scope="col" class="text-nowrap"><small>カテゴリー</small></th>
										      <th scope="col" class="text-nowrap"><small>オプション1</small></th>
										      <th scope="col" class="text-nowrap"><small>オプション2</small></th>
										      <th scope="col" class="text-nowrap"><small>金額</small></th>
										      <th scope="col" class="text-nowrap"><small>在庫数</small></th>
										      <th scope="col" class="text-nowrap"><small>ステータス</small></th>
										      <th scope="col" class="text-nowrap"></th>
										    </tr>
										</thead>
										<tbody>
												<%
												    ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList");
												   	 if(itemList != null && itemList.size() > 0){
												        for(ItemBean item : itemList){
												%>
										          	 <tr style="cursor: pointer;" onclick="changeColor(this)" ondblclick="location.href='editItemInfo1?itemId=<%= item.getItemId() %>'">
										                <td class="p-3" style="vertical-align: middle;"><!-- ID -->
										                    <small><%= item.getItemId() %></small>
										                </td>
										                <td class="td" style="width: 60px; vertical-align: middle;">
															<span class="card" style="width: 60px; height: 60px;">
																<img src="./images/<%= item.getImageFileName() %>.jpg"
																	 class="card-img-top" alt="<%= item.getImageFileName() %>"
																	 style="object-fit: cover; height: 100%; display: block;">
															</span>
														</td>
										                <td style="vertical-align: middle;"><!-- 商品名 -->
										                    <small><%= item.getItemName() %></small>
										                </td>
										                <td style="vertical-align: middle;"><!-- カテゴリー -->
										                    <small><%= item.getItemCategoryName() %></small>
										                </td>
										                <td style="vertical-align: middle;"><!-- オプション1 -->
										                    <small><%= item.getItemFirstOptionValue() %></small>
										                </td>
										                <td style="vertical-align: middle;"><!-- オプション2 -->
										                    <% if(item.getItemSecondOptionValue() != null && !item.getItemSecondOptionValue().isEmpty()){ %>
										                        <small><%= item.getItemSecondOptionValue() %></small>
										                    <% } else { %>
										                        -
										                    <% } %>
										                </td>
										                <td style="vertical-align: middle;" class="text-nowrap"><!-- 金額 -->
										                    <%
															    NumberFormat formatter = NumberFormat.getInstance();
															    String formattedPrice = formatter.format(item.getItemPrice());
															%>
															<small>￥ <%= formattedPrice %></small>
										                </td>
										                <td style="vertical-align: middle;"><!-- 在庫 -->
										                    <small><%= item.getItemStock() %></small>
										                </td>
										                <td style="vertical-align: middle;"><!-- ステータス -->
										                    <% if(item.isDeleted()){ %>
															    <small class="mb-0" style="color: #CCC;">停止中</small>
															<% } else { %>
																<small class="mb-0" style="color: #00FF00;">販売中</small>
															<% } %>
										                </td>
										                <td class="p-3" style="vertical-align: middle;"><!-- チェックボックス -->
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
									    <div class="d-flex flex-wrap justify-content-end text-center">
									    	<small class="mb-0 me-3">ステータス<br>切り替え</small>
									        <button type="submit" class="btn px-3" onclick="return confirmStatusChange();" style="background-color: #E5CCFF; color: black; border-radius: 2rem;">
											    実行
											</button>
									    </div>
									</div>
							</form>
						</div>
				</div>
		</div>
	</div>
</main>

<script src="./js/deleteItemScript.js"></script>
</body>
</html>