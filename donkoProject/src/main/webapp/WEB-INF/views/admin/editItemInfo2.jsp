<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.ItemCategoryBean, bean.OptionCategoryBean"%>
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
			<div class="row  justify-content-center">
			<%
			ItemBean item = (ItemBean) request.getAttribute("item");
			int OptionName_1 = (Integer) item.getItemFirstOptionIncrementId();
			int OptionName_2 = (Integer) item.getItemSecondOptionIncrementId();

			if(item != null) {
			%>
				<div class="col-6">
					<a href="editItemInfo1?itemId=<%=item.getItemId()%>" class="mb-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<br>
					<h2>商品情報登録</h2>
					<br>
					<!-- 　枠の中でnewItemを展開 -->

								    <div class="col p-5" style="border:1px solid black" >
								    	<table class="table table-borderless ">
											  <tbody>
											    <tr>
											      <th scope="row">カテゴリー</th>
											      <td><%= item.getItemCategoryName() %></td>
											    </tr>
											    <tr>
											      <th scope="row">商品名</th>
											      <td><%= item.getItemName() %></td>
											    </tr>
											    <tr>
											      <th scope="row">商品説明</th>
											      <td><%= item.getItemDescription() %></td>
											    </tr>
											    <tr>
											      <th scope="row">金額</th>
											      <td><%= NumberFormat.getNumberInstance().format(item.getItemPrice())%></td>
											    </tr>
											    <tr>
											      <th scope="row">在庫数</th>
											      <td><%= item.getItemStock() %></td>
											    </tr>
											  </tbody>
										</table>
								    </div>

								<br>
								<div id="error-message-container2" class="alert alert-danger d-none"></div>
								<br>
							<!-- 　フォーム入力 -->
							<form action="editItemInfo2"  id="registItem2"  method="post" enctype="multipart/form-data">
							    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
							    <input type="hidden" name="itemCategoryName" value="<%= item.getItemCategoryName() %>">
							    <input type="hidden" name="itemName" value="<%= item.getItemName() %>">
							    <input type="hidden" name="itemDescription" value="<%= item.getItemDescription() %>">
							    <input type="hidden" name="itemPrice" value="<%= item.getItemPrice() %>">
							    <input type="hidden" name="itemStock" value="<%= item.getItemStock() %>">
							    <input type="hidden" name="itemImgFileName" value="<%= item.getImageFileName() %>">
							    <br>

								<label for="default-text" class="form-label">現在登録済みの写真</label>
							    <br>
							    <div class="upload card mb-3 mx-2" style="width: 300px; height: 300px; display: inline-block;">
								    <img src="./images/<%= item.getImageFileName() %>.jpg" id="default-text" style="width: 100%; height: 100%; object-fit: cover; display: block;" />
								    <img id="image-preview" style="width: 100%; height: 100%; object-fit: cover; display: none;" />
								</div>
								<div class="mb-3">
								    <label for="formFile" class="form-label">商品写真を変更</label>
								    <input type="file" class="form-control" id="registFormFile" name="img" accept=".jpg" onchange="previewImage(event);" />
								</div>

							    <br>
							    <div class="mb-3">
								    <label for="options" class="form-label">オプションを登録</label>
								    <%
								    ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = (ArrayList<ArrayList<OptionCategoryBean>>) request.getAttribute("itemCategoryListAll");
								    if(itemCategoryListAll != null && itemCategoryListAll.size() > 0 ){
									    int counter = 1;
									    for (ArrayList<OptionCategoryBean> optionCategoryList : itemCategoryListAll){
									        String optionCategoryName = optionCategoryList.get(0).getOptionCategoryName();

									        if (counter == 1) {
									            // 最初のカテゴリ（例：色）のセレクトボックスを生成
									    %>
									    		<input type="hidden" name="optionCategoryName_1" value="<%= optionCategoryName %>">
									            <label for="optionSelect_<%= counter %>" class="form-label mb-3"></label>
									            <!-- 一つ目のオプションは必ずセレクトボックス（画像名と一意に紐づけるため） -->
									            <select class="form-select mb-3" id="optionSelect_1" name="optionValue_1">
									                <% for (OptionCategoryBean option : optionCategoryList) {
									                	boolean isSelected = item.getItemFirstOptionIncrementId() >= 0 && item.getItemFirstOptionIncrementId() == option.getOptionCategoryId();
									                %>
									                    <option value="<%=option.getOptionCategoryId()%>" <%= isSelected ? "selected" : "" %>><%=option.getOptionCategoryValue()%></option>
									                <% } %>
									            </select>
									            <%
													String itemFirstOptionIncrementId = (String) request.getAttribute("itemFirstOptionIncrementId");
													if (itemFirstOptionIncrementId != null && !itemFirstOptionIncrementId.isEmpty()) {
												%>
										              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
															<%= itemFirstOptionIncrementId %>
													  </div>
												<% } %>
									            <br>
									    <%
									        } else if (counter == 2) {
									            // 2つ目のカテゴリ（例：サイズ）の表示タイプを選択するラジオボタン
									    %>
									            <input type="hidden" name="optionCategoryName_2" value="<%= optionCategoryName %>">
									            <select class="form-select mb-3" id="optionSelect_2" name="optionValue_2">
									                <% for (OptionCategoryBean option : optionCategoryList) {
									                	boolean isSelected = item.getItemSecondOptionIncrementId() >= 0 && item.getItemSecondOptionIncrementId() == option.getOptionCategoryId();
									                %>
									                    <option value="<%=option.getOptionCategoryId()%>" <%= isSelected ? "selected" : "" %>><%=option.getOptionCategoryValue()%></option>
									                <% } %>
									            </select>
									            <%
													String itemSecondOptionIncrementId = (String) request.getAttribute("itemSecondOptionIncrementId");
													if (itemSecondOptionIncrementId != null && !itemSecondOptionIncrementId.isEmpty()) {
												%>
										              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
															<%= itemSecondOptionIncrementId %>
													  </div>
												<% }
									        }
									        counter++;
								    	}
								    }
								    %>
								    <input type="hidden" name="selectBoxCount" value="<%= itemCategoryListAll.size() %>">
						        </div>
							    <br>
							    <br>
							    <button type=submit class="btn px-5 py-3" style="background-color: #9933FF; color: white; border-radius: 0.5rem;">登録</button>
							</form>
						<% } %>
				</div>
			</div>
		</div>
</main>
<script src="./js/registItemScript.js"></script>
</body>
</html>
