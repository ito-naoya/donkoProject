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
			<%
			CustomerUser users = (CustomerUser) request.getAttribute("users");
			%>
			<div class="row" style="height:100vh;">
				<div class="col-lg-5 border m-auto p-5" style="display: flex; justify-content: center; border-radius:10px; box-shadow:10px 10px 10px lightgray;">
					<form action="userInfoEdit" method="post"
					class="needs-validation w-100" novalidate>
						<div class="cancelButton"
							style="display: flex; justify-content: space-between;">
							<h4>
								<strong>ユーザ情報編集</strong>
							</h4>
							<div>
								<a href="myPage"
									style="text-decoration: none; text-align: center;"><button
									type="button" class="btn-close border" aria-label="Close"></button>️</a>
							</div>
						</div>
						<br>
						<div>
						<div class="form-group d-flex flex-wrap mb-3">
							<label for="exampleInputUserId">ユーザーID</label>
							<input type="text" class="form-control" id="exampleInputUserId"
								aria-describedby="userId" name="user_login_id"
								value="<%=users.getUserLoginId()%>">
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String userLoginId = (String)request.getAttribute("userLoginId"); %>
							<% if (userLoginId != null) { %>
							<%= userLoginId %>
							<% } %>
						</div>
						<div class="form-group d-flex flex-wrap mb-3">
							<label for="exampleInputUserName">ユーザー名</label>
							<input type="text" class="form-control" id="exampleInputUserName"
								aria-describedby="userName" name="user_name"
								value="<%=users.getUserName()%>">
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String userName = (String)request.getAttribute("userName"); %>
							<% if (userName != null) { %>
							<%= userName %>
							<% } %>
						</div>
						<div class="form-group d-flex flex-wrap mb-3">
							<label for="exampleInputGender">性別</label>
							<select class="form-control" name="gender" id="exampleInputGender">
								<%
								String selected = users.getGender();
								String men = (selected != null && selected.equals("男性") ? "selected" : "");
								String woman = (selected != null && selected.equals("女性") ? "selected" : "");
								%>
								<option hidden>選択してください</option>
								<option value="男性" <%=men%>>男性</option>
								<option value="女性" <%=woman%>>女性</option>
							</select>
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String gender = (String)request.getAttribute("gender"); %>
							<% if (gender != null) { %>
							<%= gender %>
							<% } %>
						</div>
						<div class="form-group d-flex flex-wrap">
							<label for="exampleInputbirthday">誕生日</label><br>
							<input type="date" class="form-control" id="exampleInputbirthday"
								name="birthday" value="<%=users.getBirthday()%>">
						</div>
						<div class="d-flex flex-wrap"
							style="display: flex; justify-content: start; color: #FF0000;">
							<% String birthday = (String)request.getAttribute("birthday"); %>
							<% if (birthday != null) { %>
							<%= birthday %>
							<% } %>
						</div>
						<div class="cancelButton mt-5 mb-3"
							style="display: flex; justify-content: center;">
							<button type="submit" class="btn w-100"
								style="border: 1px solid gray; background: #E5CCFF;">更新</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<script src="./js/nullValidationScript.js"></script>
	<script src="./js/userInfoEditScript.js"></script>
</body>
</html>