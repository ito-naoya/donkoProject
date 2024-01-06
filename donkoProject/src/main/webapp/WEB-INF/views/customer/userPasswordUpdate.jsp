<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, classes.user.CustomerUser"%>
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
	<main>
		<div class="container">
			<div class="row" style="height:100vh;">
				<form action="updateUserPassword" 
					  method="post"
					  id="updateUserPassword"
					  style="display: flex; justify-content: center;">
					<% CustomerUser customerUser = (CustomerUser) request.getAttribute("users"); %>
					<div class="col-lg-5 m-auto p-5 border" style="border-radius:10px;">
						<div class="cancelButton"
							 style="display: flex; justify-content: space-between; margin-bottom: 20px;">
							<div>
								<h4><strong>パスワードの更新</strong></h4>
							</div>
							<div>
								<a href="userSignin" style="text-decoration: none; text-align: center;">
									<button type="button" class="btn-close border" aria-label="Close"></button>️
								</a>
							</div>
						</div>
						<div class="form-group my-3">
							<label for="exampleInputUserId">ユーザーID</label>
							<input type="text" class="form-control" id="exampleInputUserId"
								aria-describedby="userId" name="user_login_id">
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000; margin-bottom: 20px;">
							<%
							String userLoginId = (String) request.getAttribute("userLoginId");
							%>
							<%
							if (userLoginId != null) {
							%>
							<%=userLoginId%>
							<%
							}
							%>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword">パスワード
								<p id="passShowToggleIcon" class="d-inline-block"
									style="cursor: pointer; margin: 0;">
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="currentColor" class="bi bi-eye-slash-fill"
										viewBox="0 0 16 16">
										<path d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z" />
										<path d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z" />
									</svg>
								</p>
							</label><br>
							<label for="exampleInputPassword" class="form-label"
								style="font-size: small; color: grey;"> 半角英数字8文字以上16文字以内
							</label><br>
							<label for="exampleInputPassword" class="form-label"
								style="font-size: small; color: grey;"> 数字を1つ以上含む </label>
								<input type="password" class="form-control" id="exampleInputPassword"
								aria-describedby="password" maxlength="16" name="password" value="">
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000;">
							<%
							String password = (String) request.getAttribute("password");
							%>
							<%
							if (password != null) {
							%>
							<%=password%>
							<%
							}
							%>
						</div>
						<div class="cancelButton"
							 style="display: flex; justify-content: center; margin:60px 0 0;">
							<button type="submit" class="button-light-purple py-1 w-50"
								style="border-radius:5px;">更新</button>
						</div>
				</form>
			</div>
		</div>
	</main>
	<script src="./js/updatePassword.js"></script>
	<script src="./js/editUserInfoScript.js"></script>
</body>
</html>