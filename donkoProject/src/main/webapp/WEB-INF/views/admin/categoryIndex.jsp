<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="javax.management.monitor.CounterMonitor"%>
<%@page import="java.util.ArrayList, bean.ItemCategoryBean, bean.OptionCategoryBean, java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
		<link rel="stylesheet" href="./css/button.css">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
		<link rel="stylesheet"  href="./css/optionIndex.css">
	</head>
	<body>
	<main>
		<div class="container" style="">
			<div class="row" style="height:100vh;">
				<div class="border m-auto p-5" style="border-radius:5px; box-shadow:10px 10px 10px lightgray;">
					<div class="row d-flex justify-content-center mb-3">
						
						<div class="col-lg-12" style="display: flex; align-items: center;">
							<!-- 戻るボタン -->
							<a href="adminTopPage" class="arrow my-3 link" style="display: inline-block; color:navy;">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
								  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
								</svg>
							</a>
							
							<h5 class="mb-0 mx-3"><strong>カテゴリ編集</strong></h5>
						    <small class="mx-3" style="vertical-align: middle;">
						  		登録中の商品に紐づくカテゴリは削除できません
						    </small>
						
							<!-- カテゴリ追加 -->
							<!-- モーダルボタン -->
							<div class="ms-auto">
								<button type="button" class="button-light-purple px-3 py-1 mx-2 my-4" data-bs-toggle="modal"
									data-bs-target="#modalOptionAdd"
									style="border-radius: 2rem;">
									カテゴリを追加
								</button>
							</div>
						</div>
					</div>
					
					<!-- メッセージ表示 -->
					<%
					String message = (String)request.getAttribute("message");
					if (message != null){
					%>
						<div class="alert alert-warning" role="alert">
							<%= message %>
						</div>
					<% } %>
					
					<!-- 一覧表示 -->
					<div class="row d-flex justify-content-center">
						<div class="col-lg-8">
							<%
							String itemCategoryName1 = (String) request.getAttribute("itemCategoryName1");
							ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>)request.getAttribute("categoryList");
							if(categoryList != null || categoryList.size() > 0){
							%>
							<div class="border" style="height:55vh; overflow-x: scroll; overflow:scroll; border-radius:5px;">
								<table class="table table-borderless st-tbl1" id="itemTable">
									<thead style="background-color: cornflowerblue;">
										<tr>
											<th scope="col" class="px-4 py-3" style="width:16%;"><small>ID</small></th>
											<th scope="col" class="py-3"><small>カテゴリ名</small></th>
											<th scope="col" class="text-center" style="width: 15%;"><small></small></th>
										</tr>
									</thead>
									<tbody>
										<%
										int counter = 1;
										for (ItemCategoryBean category : categoryList) {
										%>
										<tr style="cursor: pointer;"
											onclick="location.href='categoryIndex?itemCategoryName=<%= category.getItemCategoryName() %>'">
											<!-- ID -->
											<td class="p-4" style="vertical-align:middle;"><%= counter %></td>
											<!-- カテゴリ名 -->
											<td style="vertical-align:middle;"><%= category.getItemCategoryName() %></td>
											<!-- 削除ボタン -->
											<td class="text-center" style="vertical-align:middle;">
												<button type="button" class="btn btn-outline-danger"
													onclick="event.stopPropagation(); location.href='editCategory?itemCategoryName=<%= category.getItemCategoryName() %>'">
													削除</button>
											</td>
										</tr>
										<%
										counter++;
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<% } %>
						<div class="col-lg-4 border" style="height:55vh; overflow-x: scroll; overflow:scroll; border-radius:5px;">
							<%
								ArrayList<ItemCategoryBean> optionList = (ArrayList<ItemCategoryBean>)request.getAttribute("optionList");
								if(itemCategoryName1 == null){
								%>
							<div class="m-auto py-3 px-2 w-100 d-inline-block d-inline-flex align-items-center" style="border-radius:5px; height:100%;">
								<small style="color:lightgray;">カテゴリをクリックすると登録済みのオプションを確認できます</small>
							</div>
							<%}else{ %>
							<div class="overflow-auto py-4 px-2 w-100" style="height: 100%; border-radius:5px;">
								<table class="table table-borderless">
									<thead>
										<small class="border p-1"
											style="border-radius: 5px; background-color: cornflowerblue; color: white;">カテゴリ名：<%= itemCategoryName1 %></small>
										<tr>
											<th scope="col" class="px-3"><small>ID</small></th>
											<th scope="col"><small>オプション名</small></th>
										</tr>
									</thead>
									<tbody>
										<%
												int counter2 = 1;
													for(ItemCategoryBean option : optionList){
												%>
										<tr>
											<td class="p-3"><%= counter2  %></td>
											<td class="p-3"><%= option.getOptionCategoryName() %></td>
										</tr>
										<%
												counter2++;
												}
												%>
									</tbody>
								</table>
							</div>
							<% 
								} 
								%>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- モーダルウィンドウ -->
		<%
	    String showModalString = (String) request.getAttribute("showModal");
	    Boolean showModal = Boolean.parseBoolean(showModalString);
	    ItemCategoryBean modal = (ItemCategoryBean) request.getAttribute("modal");
	    String modalCategoryName = null;
	    if(modal != null){
	    	modalCategoryName = (String) modal.getItemCategoryName();
	    }
		%>
			<div id="modalTrigger" data-show-modal="<%= showModal %>"></div>
		<%
		ArrayList<OptionCategoryBean> allOptionList = (ArrayList<OptionCategoryBean>)request.getAttribute("allOptionList");
		if(allOptionList != null && allOptionList.size() > 0){
		%>
			<!-- ポップアップ１：オプションを追加 -->
			<div class="modal fade" id="modalOptionAdd" tabindex="-1"
				aria-labelledby="modalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered"
					>
					<div class="modal-content">
						<div class="modal-header">
							<small class="modal-title" id="modalLabel">カテゴリを追加</small>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<!-- フォーム内容 -->
						<form action="editCategory" method="post">
							<div class="modal-body">
								<!-- <label for="categoryName" class="form-label"
									style="font-size: small; color: grey;">20文字以内</label>  -->
								<label for="categoryName">
									<small>カテゴリ：</small>
								</label> 
								<div class="mb-3">
									<input
										type="text" class="form-control" id="newCategoryName"
										name="newCategoryName" maxlength="20"
										value="<%= modalCategoryName != null ? modalCategoryName : "" %>">
									<%
						        	String itemCategoryName = (String) request.getAttribute("itemCategoryName");
									if (itemCategoryName != null) {
								 	 %>
										<div class="d-flex flex-wrap"
											style="display: flex; justify-content: start; color: #FF0000;">
											<%= itemCategoryName %>
										</div>
									<% 
									} 
									%>
								</div>

								<!-- セレクトボックス1 -->
								<label for="optionSelect1">
									<small>オプション1</small><small style="color:red;">（必須）</small><small>：</small>
								</label> 
								<div class="mb-3">
									<select
										class="form-control" id="optionSelect1"
										name="optionSelect1">
										<option selected hidden disabled value="">選択する</option>
										<% for (OptionCategoryBean option : allOptionList) { %>
										<option value="<%= option.getOptionCategoryName() %>"><%= option.getOptionCategoryName() %></option>
										<% } %>
									</select>
									<%
							        String optionCategoryName = (String)request.getAttribute("optionCategoryName");
							        if (optionCategoryName != null) {
								    %>
										<div class="d-flex flex-wrap"
											 style="display: flex; justify-content: start; color: #FF0000;">
											<%= optionCategoryName %>
										</div>
									<% } %>
								</div>
								
								<!-- セレクトボックス2 -->
								<label for="optionSelect2">
									<small>オプション2（任意）：</small>
								</label>
								<select class="form-control mb-3" id="optionSelect2"
									name="optionSelect2">
									<option value="">未設定</option>
									<% for (OptionCategoryBean option : allOptionList) { %>
										<option value="<%= option.getOptionCategoryName() %>"><%= option.getOptionCategoryName() %></option>
									<% } %>
								</select>
								<div class="mt-5 mb-3 d-flex justify-content-center">
									<label>
										<small class="me-4">該当するオプションがない場合</small>
									</label>
									<a href="optionIndex"
								  	   style="color: #385A37; text-decoration: none;">
										<small>
											<svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" fill="currentColor" class="bi bi-caret-right-square mb-1" viewBox="0 0 16 16">
												<path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
												<path d="M5.795 12.456A.5.5 0 0 1 5.5 12V4a.5.5 0 0 1 .832-.374l4.5 4a.5.5 0 0 1 0 .748l-4.5 4a.5.5 0 0 1-.537.082z"/>
											</svg>
											オプションを新規登録する
										</small>
									</a>
							   </div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-success">追加</button>
							</div>
						</form>
						<!-- フォームここまで -->
					</div>
				</div>
			</div>
		<% 
		} 
		%>
		<!-- モーダルウィンドウここまで -->
	</main>
	<script src="./js/optionScript.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	</body>
</html>