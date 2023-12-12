<%@page import="bean.ItemCategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemCategoryBean"%>
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
			<div class="row justify-content-center">
				<div class="col-6">
					<a href="adminTopPage" class="mb-3" style="display: inline-block">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
			  					<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<br>
					<h2>商品登録</h2>
					<br>
						<%
						String errorMessage = (String) request.getAttribute("errorMessage");
						if(errorMessage != null && !errorMessage.isEmpty()) {
						%>
						    <div class="alert alert-danger alert-message" role="alert">
						        <%= errorMessage %>
						    </div>
						<%
						}
						%>
						<div id="error-message-container" class="alert alert-danger d-none"></div>
						<!-- ここから入力フォーム  -->
						<form action="registItem1" id="registItem1" method="post">
							<div class="mb-3">
								<select class="form-select category-select" name="itemCategoryName">
								  <option selected>カテゴリーを選択</option>
							            <%
							            ArrayList<ItemCategoryBean> categoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("categoryList");
							            for (ItemCategoryBean category : categoryList){
							            %>
							            <option value="<%=category.getItemCategoryName()%>"><%=category.getItemCategoryName()%></option>
							            <%
							            }
							            %>
								</select>
							</div>
							<div class="mb-3">
							    <label for="itemName" class="form-label">商品名</label>
							    <input type="text" class="form-control" id="itemName" name="itemName" maxlength="30" required>
						 	</div>
						 	<div class="mb-3">
							    <label for="itemDescription" class="form-label">商品説明</label>
							    <textarea class="form-control" id="itemDescription" name="itemDescription" rows="3" required maxlength="100" ></textarea>
						 	</div>
						 	<div class="col-4 mb-3">
							    <label for="price" class="form-label">金額</label>
							    <input type="text" class="form-control" id="price" name="price" maxlength="11" required style="text-align: right">
						 	</div>
						 	<div class="col-2 mb-3">
							    <label for="stock" class="form-label">在庫</label>
							    <input type="number" class="form-control" id="stock" name="stock" min="1" max="100" required style="text-align: center">
						 	</div>
						 	<br>
							<button type=submit class="btn px-5 py-3" style="background-color: #E5CCFF; color: black; border-radius: 0.5rem;">オプションの追加をする</button>
						</form>
				</div>
			</div>
		</div>
</main>
<script>
	// ubmit押下時にnull値及び文字数をチェック
	document.getElementById('registItem1').addEventListener('submit', function(event) {
	    let selectCategoryElement = document.querySelector('.category-select');
	    let selectedCategoryValue = selectCategoryElement.value;
	    let errorMessageContainer = document.getElementById('error-message-container');
	    // カテゴリー選択のチェック
	    if(selectedCategoryValue === "カテゴリーを選択") {
	        event.preventDefault(); // フォームの送信を阻止
	        errorMessageContainer.textContent = 'カテゴリーを選択してください';
	        errorMessageContainer.classList.remove('d-none'); // エラーメッセージを表示
	    }
	    // 商品名、価格、在庫の空白チェック
	    else if(document.getElementById('itemName').value === '' ||
	            document.getElementById('itemDescription').value === '' ||
	            document.getElementById('price').value === '' ||
	            document.getElementById('stock').value === '') {
	        event.preventDefault(); // フォームの送信を阻止
	        errorMessageContainer.textContent = '入力漏れがあります';
	        errorMessageContainer.classList.remove('d-none'); // エラーメッセージを表示
	    }
	});
	//数字をカンマ区切りにする
	document.getElementById('price').addEventListener('input', function (event) {
      let value = event.target.value;
      value = value.replace(/[^\d]/g, ''); //正規表現で数字以外の文字を除去
      value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ','); //3桁ごとにカンマを挿入
      event.target.value = value;
  });
</script>
</body>
</html>