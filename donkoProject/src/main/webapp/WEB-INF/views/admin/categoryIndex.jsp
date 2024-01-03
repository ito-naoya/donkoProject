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
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
		<link rel="stylesheet"  href="./css/optionIndex.css">
	</head>
	<body>
		<main>
			<div class="container">
				<div class="d-flex mt-5 mb-3">
					<a href="adminTopPage" style="display: inline-block;">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
  								<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
				</div>

				<div style="display: flex; align-items: end;">
				    <h2>カテゴリ一覧　</h2>
				    <h6>　登録中の商品に紐づくカテゴリは削除できません</h6>
				</div>
				<br>
				<%
				String message = (String)request.getAttribute("message");
				if (message != null){
				%>
				<div class="alert alert-warning" role="alert"> <%= message %></div>
				<% } %>
				<button type="button" class="btn px-3 m-3" data-bs-toggle="modal" data-bs-target="#modalOptionAdd" style="background-color: #E5CCFF; color: black; border-radius: 2rem;">カテゴリを追加</button>
				<div class="row">
					<div class="col-6">
					<%
					String itemCategoryName1 = (String) request.getAttribute("itemCategoryName1");
					ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>)request.getAttribute("categoryList");
					if(categoryList != null || categoryList.size() > 0){
					%>
						<div class="overflow-auto" style="height: calc(80vh - 200px);">
						<table class="table table-borderless st-tbl1" id="itemTable" style="padding: 10px">
							<thead>
							    <tr>
							      <th scope="col">
							      	#
							      </th>
							      <th scope="col">
							      	カテゴリ
							      </th>
							      <th scope="col">
							      	削除
							      </th>
							    </tr>
							</thead>
							<tbody>
								<%
								int counter = 1;
								for (ItemCategoryBean category : categoryList) {
								%>
								    <tr style="cursor: pointer;" onclick="location.href='categoryIndex?itemCategoryName=<%= category.getItemCategoryName() %>'">
								        <td><!-- ID -->
								            <p><%= counter  %></p>
								        </td>
								        <td><!-- カテゴリ名 -->
								            <p><%= category.getItemCategoryName() %></p>
								        </td>
								        <td><!-- 削除ボタン -->
								            <p>
								                <button type="button" class="btn btn-outline-danger"
								                        onclick="event.stopPropagation(); location.href='editCategory?itemCategoryName=<%= category.getItemCategoryName() %>'">
								                    削除
								                </button>
								            </p>
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
					<div class="col-5">
					<%
					ArrayList<ItemCategoryBean> optionList = (ArrayList<ItemCategoryBean>)request.getAttribute("optionList");
					if(itemCategoryName1 == null){
					%>
						<div class="" style="display:block;">カテゴリをクリックすると登録済みのオプションを確認できます</div>
					<%}else{ %>
						<div class="overflow-auto" style="height: calc(80vh - 200px);">
						<table class="table table-borderless st-tbl1" id="itemTable" style="padding: 10px">
							<thead>
							    <tr>
							      <th scope="col">
							      	#
							      </th>
							      <th scope="col">
							      	カテゴリ詳細：<%= itemCategoryName1 %>
							      </th>
							    </tr>
							</thead>
							<tbody>
								<%
								int counter2 = 1;
									for(ItemCategoryBean option : optionList){
								%>
								<tr>
									<td>
											<%= counter2  %>
									</td>
									<td>
											<%= option.getOptionCategoryName() %>
									</td>
								</tr>
								<%
								counter2++;
								}
								%>
							</tbody>
						</table>
							<% } %>
						</div>
					</div>
				</div>
			</div>
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
			<div class="modal fade" id="modalOptionAdd" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered" style="max-width: 25%;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="modalLabel">カテゴリを追加</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
				      <div class="modal-body">
					    <!-- フォーム内容 -->
					    <form action="editCategory" method="post">
					        <label for="categoryName" class="form-label" style="font-size: small; color: grey;">20文字以内</label>
					        <input type="text" class="form-control mb-3" id="newCategoryName" name="newCategoryName" maxlength="20" value="<%= modalCategoryName != null ? modalCategoryName : "" %>">
					        <%
					        	String itemCategoryName = (String) request.getAttribute("itemCategoryName");
								 if (itemCategoryName != null) {
						 	 %>
					              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
										<%= itemCategoryName %>
								  </div>
							  <% } %>
							  <br>


					        <!-- セレクトボックス1 -->
					        <label for="optionSelect1" class="form-label">オプション1</label>
					        <select class="form-control mb-3" id="optionSelect1" name="optionSelect1">
					        		<option selected hidden disabled value="">必ず選択してください</option>
					            <% for (OptionCategoryBean option : allOptionList) { %>
					                <option value="<%= option.getOptionCategoryName() %>"><%= option.getOptionCategoryName() %></option>
					            <% } %>
					        </select>
					        	<%
							        String optionCategoryName = (String)request.getAttribute("optionCategoryName");
							        if (optionCategoryName != null) {
							    %>
							        <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
							            <%= optionCategoryName %>
							        </div>
							    <% } %>
							    <br>

					        <!-- セレクトボックス2 -->
					        <label for="optionSelect2" class="form-label">オプション2（任意）</label>
					        <select class="form-control mb-3" id="optionSelect2" name="optionSelect2">
					            <option value="">選択しない</option>
					            <% for (OptionCategoryBean option : allOptionList) { %>
					                <option value="<%= option.getOptionCategoryName() %>"><%= option.getOptionCategoryName() %></option>
					            <% } %>
					        </select>
					        <br>
							<label>該当するオプションがない場合</label><br>
							<a href="optionIndex" style="color: #385A37; text-decoration: none;">オプションを新規登録する</a>
					        <br>
					        <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">キャンセル</button>
						        <button type="submit" class="btn btn-success">追加</button>
					        </form>
					      </div>
					</div>
			    </div>
			  </div>
			</div>
			<% } %>
		</main>
	<script src="./js/optionScript.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	</body>
</html>