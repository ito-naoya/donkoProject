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
<title>doko</title>
</head>
<body class="container">
	<main>
		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%>
		<form action="editUserInfo" method="post"
			style="display: flex; justify-content: center; margin: 30px;">
			<div class="col-lg-5 m-5"
				style="border: 1px solid #333333; padding: 65px;">
				<div class="cancelButton"
					style="display: flex; justify-content: space-between; margin-bottom: 20px;">
					<h2>
						<strong>ユーザ情報編集</strong>
					</h2>
					<div>
						<a href="myPage"
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
						aria-describedby="userId" name="user_login_id"
						value="<%=users.getUserLoginId()%>">
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputUserName">ユーザー名</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputUserName"
						aria-describedby="userName" name="user_name"
						value="<%=users.getUserName()%>">
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputPassword">パスワード</label><br>
				</div>
				<div class="btn-toolbar mb-3" role="toolbar"
					aria-label="Toolbar with button groups">
					<input type="checkbox" style="display:none;" name="password_checked" value="null" checked="checked">
					<input type="checkbox" class="btn-check" id="change" autocomplete="off"
						name="password_checked" value="changed">
						<label class="btn btn-secondary me-2" for="change">change</label>
					<div class="input-group">
						<input type="text" class="form-control" id="exampleInputPassword"
							aria-label="Input group example" aria-describedby="btnGroupAddon"
							maxlength="8" name="password" style="display:none;">
					</div>
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputGender">性別</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
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
				<div style="margin-bottom: 10px;">
					<label for="exampleInputbirthday">誕生日</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 70px;">
					<input type="date" class="form-control" id="exampleInputbirthday"
						name="birthday" value="<%=users.getBirthday()%>">
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