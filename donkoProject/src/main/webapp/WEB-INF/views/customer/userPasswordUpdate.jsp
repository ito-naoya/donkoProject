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
<body class="container">
	<main>
		<form action="updateUserPassword" method="post"
			style="display: flex; justify-content: center; margin: 30px;">
			<% CustomerUser customerUser = (CustomerUser) request.getAttribute("users"); %>
			<div class="col-lg-5 m-5"
				style="border: 1px solid #333333; padding: 65px;">
				<div class="cancelButton"
					style="display: flex; justify-content: space-between; margin-bottom: 20px;">
					<h2>
						<strong>パスワード変更</strong>
					</h2>
					<div>
						<a href="home"
							style="text-decoration: none; text-align: center;"><button
								type="button" class="btn-close border" aria-label="Close"></button>️</a>
					</div>
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputUserId">ユーザーID</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputUserId"
						aria-describedby="userId" name="user_login_id" value="">
				</div>
				<div class="d-flex flex-wrap"
					style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
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
				<div style="margin-bottom: 10px;">
					<label for="exampleInputPassword">パスワード</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputPassword"
						aria-describedby="password" name="password"
						value="">
				</div>
        <div class="d-flex flex-wrap"
          style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
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
					style="display: flex; justify-content: center; margin-bottom: 20px;">
					<button type="submit" class="btn btn-lg w-100"
						style="border: 1px solid #000000; background: #E5CCFF; padding: 10px;">更新</button
				</div>
		</form>
		</div>
	</main>
	 <script src="./js/editUserInfoScript.js"></script>
</body>
</html>