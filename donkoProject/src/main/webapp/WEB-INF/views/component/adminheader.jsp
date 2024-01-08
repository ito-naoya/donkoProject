<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.PurchaseBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/button.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<title>donko</title>
</head>
<body>
	<% 
	ArrayList<PurchaseBean> unshippingnum = (ArrayList<PurchaseBean>)request.getAttribute("unshippingnum");
	%>
	<header class="fixed-top" style="background-color:white;">
    	<div style="height:54px; background-color:navy; position:relative; top: 0; left: 0; z-index:2; display:flex; align-items:center;">
    		<ul class="d-flex mb-0 p-0 w-100">
    			<li class="mx-3" style="list-style:none; display: flex; align-items: center;">
	    			<a href="adminTopPage" class="link" style="text-decoration:none;">
	    				<img src="./images/donkoLogo.png" style="height:50px;">
	    			</a>
    			</li>
    			<li class="ms-auto me-2" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<div class="dropdown">
					  <button class="btn dropdown-toggle text-light link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					    <small>一覧</small>
					  </button>
					  <ul class="dropdown-menu">
					  	<li><a class="dropdown-item" href="adminTopPage"><small>未発送商品</small>
					  			<% 
					  			if (unshippingnum.size() > 0) { 
					  			%>
					  				<span class="badge ms-2" style="background-color:red; border-radius:40px;"><%= unshippingnum.size() %></span>
					  			<% 
					  			}
					  			%>
					  		</a>
					  	</li>
					    <li><a class="dropdown-item" href="purchaseHistory"><small>受注履歴</small></a></li>
					    <li><a class="dropdown-item" href="deleteItemIndex?itemCategoryName=全ての商品&itemDelFlg=1&order=asc"><small>削除商品</small></a></li>
					    <li><a class="dropdown-item" href="deleteUserInfoIndex"><small>削除ユーザー</small></a></li>
					  </ul>
					</div>
    			</li>
    			<li class="mx-2" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<div class="dropdown">
					  <button class="btn dropdown-toggle text-light link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					    <small>登録</small>
					  </button>
					  <ul class="dropdown-menu">
					    <li><a class="dropdown-item" href="registItem1"><small>商品</small></a></li>
					    <li><a class="dropdown-item" href="userSignup"><small>ユーザー</small></a></li>
					  </ul>
					</div>
    			</li>
    			<li class="mx-2" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<div class="dropdown">
					  <button class="btn dropdown-toggle text-light link" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					    <small>編集</small>
					  </button>
					  <ul class="dropdown-menu">
					    <li><a class="dropdown-item" href="categoryIndex"><small>カテゴリ</small></a></li>
					    <li><a class="dropdown-item" href="optionIndex"><small>オプション</small></a></li>
					  </ul>
					</div>
    			</li>
    			<li class="ms-4 me-3" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<a href="logout" class="text-light link border px-3 py-1" style="text-decoration:none; border-radius:10px;"><small>ログアウト</small></a>
    			</li>
    		</ul>
    	</div>
    </header>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>