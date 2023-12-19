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
				<div class="col-6">
					<a href="registItem1" class="mb-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<br>
					<h2>商品登録</h2>
					<br>
					<!-- 　枠の中でnewItemを展開 -->
					<%
					ItemBean newItem = (ItemBean) request.getAttribute("newItem");
					if(newItem != null) {
					%>
						    <div class="col p-5" style="border:1px solid black" >
						    	<table class="table table-borderless ">
									  <tbody>
									    <tr>
									      <th scope="row">カテゴリー</th>
									      <td><%= newItem.getItemCategoryName() %></td>
									    </tr>
									    <tr>
									      <th scope="row">商品名</th>
									      <td><%= newItem.getItemName() %></td>
									    </tr>
									    <tr>
									      <th scope="row">商品説明</th>
									      <td><%= newItem.getItemDescription() %></td>
									    </tr>
									    <tr>
									      <th scope="row">金額</th>
									      <td><%= NumberFormat.getNumberInstance().format(newItem.getItemPrice())%></td>
									    </tr>
									    <tr>
									      <th scope="row">在庫数</th>
									      <td><%= newItem.getItemStock() %></td>
									    </tr>
									  </tbody>
								</table>
						    </div>

						<br>
						<!-- 入力エラーがある場合、ここにエラーメッセージを出力 -->
						<div id="error-message-container2" class="alert alert-danger d-none"></div>
						<br>
						<!-- 　フォーム入力 -->
						<form action="registItem2"  id="registItem2"  method="post" enctype="multipart/form-data">
						    <input type="hidden" name="itemCategoryName" value="<%= newItem.getItemCategoryName() %>">
						    <input type="hidden" name="itemName" value="<%= newItem.getItemName() %>">
						    <input type="hidden" name="itemDescription" value="<%= newItem.getItemDescription() %>">
						    <input type="hidden" name="itemPrice" value="<%= newItem.getItemPrice() %>">
						    <input type="hidden" name="itemStock" value="<%= newItem.getItemStock() %>">
						    <br>
						    <div class="mb-3">
							    <label for="registFormFile" class="form-label">商品写真を登録</label>
							    <input type="file" class="form-control" id="registFormFile" name="img" accept=".jpg" onchange="previewImage(event);" />
							</div>
						    <br>
						    <div class="upload card mb-3 mx-2" style="width: 300px; height: 300px; display: inline-block;">
								<span id="default-text" style="position: absolute; width: 100%; height: 100%;">ここに画像が表示されます</span>
							    <img id="image-preview" style="width: 100%; height: 100%; object-fit: cover; display: none;"/>
							</div>
							<br>
						    <br>
						    <!-- オプション登録 -->
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
									    		<input type="hidden" name="optionCategoryName_<%= counter %>" value="<%= optionCategoryName %>">
									            <label for="optionSelect_<%= counter %>" class="form-label mb-3"></label>
									            <!-- 一つ目のオプションは必ずセレクトボックス（画像名と一意に紐づけるため） -->
									            <select class="form-select mb-3" id="optionSelect_<%= counter %>" name="optionValueS_<%= counter %>">
									                <option selected>オプション選択： <%= optionCategoryName %></option>
									                <% for (OptionCategoryBean option : optionCategoryList) { %>
									                    <option value="<%=option.getOptionCategoryId()%>"><%=option.getOptionCategoryValue()%></option>
									                <% } %>
									            </select>
									            <br>
									    <%
									        } else if (counter == 2) {
									            // 2つ目のカテゴリ（例：サイズ）の表示タイプを選択するラジオボタン
									    %>
									            <div class="form-check">
									                <input class="form-check-input" type="radio" name="sizeDisplayType" value="select" onclick="formSwitch()" checked>
									                <label class="form-check-label mb-1">一つのアイテムを登録</label>
									            </div>
									            <div class="form-check">
									                <input class="form-check-input" type="radio" name="sizeDisplayType" value="checkbox" onclick="formSwitch()">
									                <label class="form-check-label">複数のアイテムを一度に登録</label>
									            </div>
									            <input type="hidden" name="optionCategoryName_<%= counter %>" value="<%= optionCategoryName %>">

									            <!-- セレクトボックス -->
									            <div id="sizeSelect">
									                <label for="optionSelect_<%= counter %>" class="form-label mb-3"></label>
									                <select class="form-select mb-3" id="optionSelect_<%= counter %>" name="optionValueS_<%= counter %>">
									                    <option selected>オプション選択： <%= optionCategoryName %></option>
									                    <% for (OptionCategoryBean option : optionCategoryList) { %>
									                        <option value="<%=option.getOptionCategoryId()%>"><%=option.getOptionCategoryValue()%></option>
									                    <% } %>
									                </select>
									                <br>
									            </div>

									            <!-- チェックボックス -->
									            <div id="sizeCheck" style="display: none;">
									                <label for="optionBox_<%= counter %>" class="form-label mb-3 mt-3">オプション選択 : <%= optionCategoryName %></label>
									                <br>
									                <% for (OptionCategoryBean option : optionCategoryList) { %>
									                    <input class="form-check-input me-2" type="checkbox" id="optionBox_<%= counter %>" name="optionValueC_<%= counter %>" value="<%=option.getOptionCategoryId()%>">
									                    <label class="form-check-label me-3" for="optionBox_<%= counter %>">
									                        <%=option.getOptionCategoryValue()%>
									                    </label>
									                <% } %>
									                <br><br>
									            </div>
									<%
									        }
									        counter++;
								    	}
								    %>
								</div>
								<input type="hidden" name="selectBoxCount" value="<%= itemCategoryListAll.size() %>">
								<%
								    }
								%>
						    <br>
						    <br>
						    <button type=submit class="btn px-5 py-3" style="background-color: #9933FF; color: white; border-radius: 0.5rem;">登録</button>
						</form>
					<%
						}
					%>
				</div>
			</div>
		</div>
</main>
<script src="./js/registItemScript.js"></script>
</body>
</html>