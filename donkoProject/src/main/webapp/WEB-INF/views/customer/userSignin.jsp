<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>ログイン画面</h1>
    //userSigninサーブレットで処理
    <form method="POST" action="userSignin">
    	//ユーザーID入力欄
        <label for="user_login_id">ユーザーID:</label>
        <input type="text" id="user_login_id" name="user_login_id"><br>
        //パスワード入力欄
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password"><br>
        //ログインボタン
       	<form action="UserSigninServlet" method="post">
							<input type="hidden" value="<%= signin %>" name="signin">
							<input type="hidden" value="<%= sa.getShippingAddressId() %>" name="shippingAddressId">
							<button type=submit class="btn px-5 py-2" style="background-color: #9933ff; color: white;">
			</form>
		//新規登録画面へ戻る
    	<p><a href="userSignup">新規登録画面へ戻る</a></p>
</body>
</html>