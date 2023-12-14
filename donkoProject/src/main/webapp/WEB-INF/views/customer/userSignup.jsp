<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>ユーザー新規登録画面</h1>

	//userSignupサーブレットで処理
    <form method="POST" action="userSignup">
    
    	//ユーザーID入力欄
        <label for="user_id">ユーザーID:</label>
        <input type="text" id="user_id" name="user_id"><br>
        
        //ユーザー名入力欄
        <label for="user_name">ユーザー名:</label>
        <input type="text" id="user_name" name="user_name"><br>
        
        //パスワード入力欄
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password"><br>
        
        //作成する
         <p><form action="UserSignupServlet" method="post">
							<input type="hidden" value="<%= signup %>" name="signup">
							<input type="hidden" value="<%= sa.getShippingAddressId() %>" name="shippingAddressId">
							<button type=submit class="btn px-5 py-2" style="background-color: #9933ff; color: white;">
			</form></p>
        
        //ホーム画面へ戻る
     	<p><form method="POST" action="userSignup">
      		<input type="submit" value="ホーム画面へ戻る">
    		</form></p>
</body>
</html>