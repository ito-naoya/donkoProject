<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="bean.ItemCategoryBean"%>
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
						<%
						}
						%>
						<br>
						<div id="error-message-container2" class="alert alert-danger d-none"></div>
						<br>
					<!-- 　フォーム入力 -->
					<form action="registItem2"  id="registItem2"  method="post" enctype="multipart/form-data">
						<input type="hidden" name="getparam" value="取れてる?">
					    <input type="hidden" name="itemCategoryName" value="<%= newItem.getItemCategoryName() %>">
					    <input type="hidden" name="itemName" value="<%= newItem.getItemName() %>">
					    <input type="hidden" name="itemDescription" value="<%= newItem.getItemDescription() %>">
					    <input type="hidden" name="itemPrice" value="<%= newItem.getItemPrice() %>">
					    <input type="hidden" name="itemStock" value="<%= newItem.getItemStock() %>">
					    <br>
					    <div class="mb-3">
						    <label for="formFile" class="form-label">商品写真を登録</label>
						    <input type="file" class="form-control" id="formFile" name="img" accept=".jpg" onchange="previewImage(event);" />
						</div>
					    <br>
					    <div class="upload card mb-3 mx-2" style="width: 300px; height: 300px; display: inline-block;">
							<span id="default-text" style="position: absolute; width: 100%; height: 100%;">ここに画像が表示されます</span>
						    <img id="image-preview" style="width: 100%; height: 100%; object-fit: cover; display: none;"/>
						</div>

					    <br>
					    <div class="mb-3">
					    	<label for="options" class="form-label">オプションを登録</label>
						    <!-- categoryListをSeleectで選択。（TODOチャレンジ:色に関して、既存の商品名で登録済みのものは表示しない -->
						    <%
						    ArrayList<ItemCategoryBean> itemCategoryList = (ArrayList<ItemCategoryBean>) request.getAttribute("itemCategoryList");
				            int counter = 1;
				            //カテゴリーから取得したオプションの数分、セレクトボックスを作成（衣類なら、「色」と「衣類サイズ」）
				            for (ItemCategoryBean itemCategory : itemCategoryList) {
				            	ArrayList<OptionCategoryBean> options = (ArrayList<OptionCategoryBean>) request.getAttribute("optionCategory" + counter);
				            %>
				            <input type="hidden" name="optionCategoryName_<%= counter %>" value="<%= itemCategory.getOptionCategoryName() %>">
				            <!-- itemCategoryを元に得られたoptionCategoryの中身を選択画面に表示 -->
			            	<select class="form-select option-select mb-3" id="options" name="optionValue_<%= counter %>">
							  <option selected>オプション選択： <%=itemCategory.getOptionCategoryName() %></option>
						        <%
						        for (OptionCategoryBean option : options) {
						        %>
						            <option value="<%=option.getOptionCategoryId()%>"><%=option.getOptionCategoryValue()%></option>
						        <%
						        }
						        %>
						    </select>
						    <%
						    counter++;
					        }
					        %>
					        <input type="hidden" name="selectBoxCount" value="<%= counter - 1 %>">
				        </div>
					    <br>
					    <br>
					    <button type=submit class="btn px-5 py-3" style="background-color: #9933FF; color: white; border-radius: 0.5rem;">登録</button>
					</form>
				</div>
			</div>
		</div>
</main>
<script>
	function previewImage(event) {
	    let preview = document.getElementById('image-preview');
	    let defaultText = document.getElementById('default-text');
	    let file = event.target.files[0];
	    let reader = new FileReader();

	    reader.onloadend = function() {
	        if (file) {
	            preview.src = reader.result;
	            preview.style.display = 'block'; // 画像を表示
	            defaultText.style.display = 'none'; // デフォルトテキストを非表示にする
	        } else {
	            preview.src = "";
	            preview.style.display = 'none'; // 画像がない場合は画像を非表示
	            defaultText.style.display = 'block'; // デフォルトテキストを表示する
	        }
	    }

	    if (file) {
	        reader.readAsDataURL(file);
	    } else {
	        preview.src = "";
	        preview.style.display = 'none';
	        defaultText.style.display = 'block';
	    }
	}

	//各種入力チェック
	// submit押下時にnull値及び文字数をチェック
	document.getElementById('registItem2').addEventListener('submit', function(event) {
	    let selectCategoryElement = document.querySelector('.option-select');
	    let selectedCategoryValue = selectCategoryElement.value;
	    let errorMessageContainer = document.getElementById('error-message-container2');
	    // カテゴリー選択のチェック
	    if(selectedCategoryValue.includes("オプション選択")) {
		    event.preventDefault(); // フォームの送信を阻止
		    errorMessageContainer.textContent = 'オプションを選択してください';
		    errorMessageContainer.classList.remove('d-none'); // エラーメッセージを表示
		}
	    // 商品名、価格、在庫の空白チェック
	    else if(document.getElementById('formFile').files.length === 0) {
		    event.preventDefault(); // フォームの送信を阻止
		    errorMessageContainer.textContent = '写真をアップロードしてください';
		    errorMessageContainer.classList.remove('d-none'); // エラーメッセージを表示
		}
	});
	function validateFile(file) {
	    const allowedExtensions = 'jpg';
	    const fileSizeLimit = 2 * 1024 * 1024; // 2MB in bytes
	    const dimensionLimit = 3000;
	    // 拡張子のチェック
	    let extension = file.name.split('.').pop().toLowerCase();
	    if (!allowedExtensions.includes(extension)) {
	        return "ファイル形式はjpgのみ許可されています。";
	    }
	     // ファイルサイズのチェック
	    if (file.size > fileSizeLimit) {
	        return "ファイルサイズは2MB以下にしてください。";
	    }
	    // 画像サイズのチェック (非同期)
	    return new Promise((resolve, reject) => {
	        let img = new Image();
	        img.src = URL.createObjectURL(file);
	        img.onload = () => {
	            if (img.width > dimensionLimit || img.height > dimensionLimit) {
	                resolve("画像サイズは3000px * 3000px以下にしてください。");
	            } else {
	                resolve(null);
	            }
	        };
	        img.onerror = reject;
	    });
	}
</script>
</body>
</html>