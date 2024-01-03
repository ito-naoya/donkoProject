<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, classes.user.CustomerUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
						<div class="form-group">
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
							<label for="exampleInputPassword">パスワード</label>
							<input type="text" class="form-control" id="exampleInputPassword"
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
						<div class="cancelButton mt-5"
							 style="display: flex; justify-content: center;">
							<button type="submit" class="btn w-100"
								style="border: 1px solid #000000; background: #E5CCFF;">更新</button
						</div>
				</form>
			</div>
		</div>
	</main>
	<script src="./js/editUserInfoScript.js"></script>
</body>
</html>