<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.ItemCategoryBean, bean.OptionCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/button.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>donko</title>
</head>
<body>
<%@include file= "../component/adminheader.jsp" %>
<%@include file= "../component/adminheaderTopSpace.jsp" %>
<%
	ItemBean item = (ItemBean) request.getAttribute("item");
%>
<main>
	<div class="container">
		<div class="row d-flex justify-content-center" style="height:100vh;">
			<div class="col-lg-10 m-auto border p-5" style="border-radius:10px; box-shadow:10px 10px 10px lightgray;">
				<div class="row d-flex justify-content-center">
					<div class="d-flex flex-wrap px-4 mb-4">
						<!-- 戻るボタン -->
						<a href="registItem1" class="arrow mb-3 link" style="display: inline-block; color:navy;">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
							  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
							</svg>
						</a>
						<h5 class="mb-0 mx-3"><strong>商品登録</strong></h5>
					</div>
				</div>
				<!-- 　枠の中でnewItemを展開 -->
				<%
				ItemBean newItem = (ItemBean) request.getAttribute("newItem");
				if(newItem != null) {
				%>
				<div class="row d-flex justify-content-center">
					<div class="col-lg-4 px-4 mb-5">
					    <div class="border p-4 w-100" style="border-radius:5px;">
					    	<small class="border p-1" style="border-radius:5px; background-color:cornflowerblue; color:white;">基本商品情報</small>
					    	<div class="my-3">
						    	<strong><small>カテゴリ名：</small></strong><br>
						    	<%=newItem.getItemCategoryName()%>
					    	</div>
					    	<div class="mb-3">
						    	<strong><small>商品名：</small></strong><br>
						    	<%=newItem.getItemName()%>
					    	</div>
					    	<div class="mb-3">
					    		<strong><small>商品説明：</small></strong><br>
					    		<%=newItem.getItemDescription()%>
					    	</div>
					    	<div class="mb-3">
					    		<strong><small>金額：</small></strong><br>
					    		￥ <%=NumberFormat.getNumberInstance().format(newItem.getItemPrice())%>
					    	</div>
					    	<div>
					    		<strong><small>在庫数：</small></strong><br>
					    		<%=newItem.getItemStock()%>
					    	</div>
					    </div>
					</div>
					<div class="col-lg-8 px-4">
						<!-- 　フォーム入力 -->
						<form action="registItem2"  id="registItem2"  method="post" enctype="multipart/form-data">
						    <input type="hidden" name="itemCategoryName" value="<%= newItem.getItemCategoryName() %>">
						    <input type="hidden" name="itemName" value="<%= newItem.getItemName() %>">
						    <input type="hidden" name="itemDescription" value="<%= newItem.getItemDescription() %>">
						    <input type="hidden" name="itemPrice" value="<%= newItem.getItemPrice() %>">
						    <input type="hidden" name="itemStock" value="<%= newItem.getItemStock() %>">

							<!-- 画像登録 -->
						    <div>
						        <label for="registFormFile" class="d-flex justify-content-between">
						        	<small>商品写真： <span class="badge bg-danger">必須</span></small>
							        <button type="button" class="btn link p-0"
										data-bs-container="body" data-bs-toggle="popover"
										data-bs-placement="top"
										data-bs-content="拡張子jpgのみ&lt;br&gt;データサイズ2MBまで" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button>
						        </label>
						        <input type="file" class="form-control" id="registFormFile" name="img" accept=".jpg" required onchange="previewImage(event);" />
						    </div>
						    <div class="upload card my-3" style="width: 200px; height: 200px; display: inline-block;">
								<span id="default-text" style="position: absolute; top:10px; width: 100%; height: 100%; color:lightgray;">
									<small class="m-3">ここに画像を表示</small>
								</span>
							    <img id="image-preview" style="width: 100%; height: 100%; object-fit: cover; display: none;"/>
							</div>
							<%
							String imageFileName = (String) request.getAttribute("imageFileName");
							if (imageFileName != null && !imageFileName.isEmpty()) {
							%>
				                <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
									<%= imageFileName %>
							    </div>
							<% } %>

						    <!-- オプション登録 -->
							<%
							ArrayList<ArrayList<OptionCategoryBean>> itemCategoryListAll = (ArrayList<ArrayList<OptionCategoryBean>>) request.getAttribute("itemCategoryListAll");
							%>
							<div>
							    <label for="options"><small>オプションを登録： <span class="badge bg-danger">必須</span></small></label>
								<%
								if(itemCategoryListAll != null && itemCategoryListAll.size() > 0 ){
								    int counter = 1;
								%>
									<%
								    for (ArrayList<OptionCategoryBean> optionCategoryList : itemCategoryListAll){
								        String optionCategoryName = optionCategoryList.get(0).getOptionCategoryName();
									%>
										<%
								        if (counter == 1) {
								            // 最初のカテゴリ（例：色）のセレクトボックスを生成
								    	%>

								    		<input type="hidden" name="optionCategoryName_1" value="<%= optionCategoryName %>">
								            <div class="mb-3">
									            <!-- 一つ目のオプションは必ずセレクトボックス（画像名と一意に紐づけるため） -->
									            <select class="form-select" id="optionSelect_1" name="optionValue_1">
									                <option selected hidden disabled value="">オプション項目： <%= optionCategoryName %></option>
									                <% for (OptionCategoryBean option : optionCategoryList) {
									                	boolean isSelected = newItem.getItemFirstOptionIncrementId() >= 0 && newItem.getItemFirstOptionIncrementId() == option.getOptionCategoryId();
									                %>
									                    <option value="<%=option.getOptionCategoryId()%>" <%= isSelected ? "selected" : "" %>><%=option.getOptionCategoryValue()%></option>
									                <% } %>
									            </select>
									            <%
												String itemFirstOptionIncrementId = (String) request.getAttribute("itemFirstOptionIncrementId");
												if (itemFirstOptionIncrementId != null && !itemFirstOptionIncrementId.isEmpty()) {
												%>
									                <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
														<%= itemFirstOptionIncrementId %>
												    </div>
												<%
												}
												%>
											</div>
										<%
								    	} else if (counter == 2) {
								            // 2つ目のカテゴリ（例：サイズ）の表示タイプを選択するラジオボタン
										%>
								            <input type="hidden" name="optionCategoryName_2" value="<%= optionCategoryName %>">
											<!-- チェックボックス -->
								            <div id="sizeCheck" class="mb-3 border p-3" style="border-radius:5px;">
								                <small class="mb-2 d-inline-block">
								                	オプション項目： <%= optionCategoryName %>
								                </small><br>
								                <% for (OptionCategoryBean option : optionCategoryList) {
								                boolean isSelected2 = newItem.getItemSecondOptionIncrementId() >= 0 && newItem.getItemSecondOptionIncrementId() == option.getOptionCategoryId();
								                %>
								                    <input class="form-check-input me-2" type="checkbox" id="optionBox_2" name="optionValue_2" value="<%=option.getOptionCategoryId()%>" <%= isSelected2 ? "checked" : "" %> >
								                    <label class="form-check-label me-3" for="optionBox_2">
								                        <%=option.getOptionCategoryValue()%>
								                    </label>
								                <%
								                }
								                %>
								                <%
												String itemSecondOptionIncrementId = (String) request.getAttribute("itemSecondOptionIncrementId");
												if (itemSecondOptionIncrementId != null && !itemSecondOptionIncrementId.isEmpty()) {
												%>
									              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; color: #FF0000;">
														<%= itemSecondOptionIncrementId %>
												  </div>
												<%
												}
												%>
								            </div>
										<%
								        }
								        counter++;
								        %>
								    <%
							    	}
									%>
									<input type="hidden" name="selectBoxCount" value="<%= itemCategoryListAll.size() %>">
								<%
								}
								%>
							</div>
							<div class="d-flex justify-content-center mt-5">
						    	<button type=submit class="button-purple px-3 py-1 w-50" style="border-radius: 5px;">登録</button>
						    </div>
						</form>
					</div>
				</div>
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
<script>
  // ポップオーバーを初期化
  var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
  var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
    return new bootstrap.Popover(popoverTriggerEl)
  });
</script>
</body>
</html>