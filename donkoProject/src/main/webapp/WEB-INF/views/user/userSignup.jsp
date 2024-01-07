<%@page import="controller.user.UserSigninServlet"%>
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
</style>
<title>donko</title>
</head>
<body>
	<main>
		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%>
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<%
				String admin = (String) request.getAttribute("admin");
				if (admin == null){
			    %>
				<!-- ここにdonko画像を表示 -->
				<span class="d-block d-flex justify-content-center my-3"> <a
					href="home" class="link" style="text-decoration: none;"> <img
						src="./images/donkoLogo2.png" style="height: 80px;">
				</a>
				</span>
				<%
				}
				%>
				<div class="col-lg-4 m-auto p-5 border" style="border-radius: 10px;">
					<div class="cancelButton"
						style="display: flex; justify-content: space-between;">
						<h5 class="mt-3">
							<strong>ユーザー新規登録</strong>
						</h5>
						<!-- アドミン側のみ表示 -->
						<%
						if (admin != null){ 
						%>
						<div>
							<a href="adminTopPage"
								style="text-decoration: none; text-align: center;"><button
									type="button" class="btn-close border" aria-label="Close"></button>️</a>
						</div>
						<%
						} 
						%>
					</div>
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
					<br>
					<!-- ここからフォーム -->
					<form action="userSignup" method="post"
						onsubmit="return validatePassword()">
						<div class="row my-3">
							<label for="userLoginId" class="form-label">ユーザーID</label> <label
								for="itemName" class="form-label"
								style="font-size: small; color: grey;"> 5文字以上10文字以内 </label>
							<div class="col-12">
								<input type="text" class="form-control" id="userLoginId"
									name="userLoginId" maxlength="10"
									value="<%= users.getUserLoginId() != null ? users.getUserLoginId() : "" %>"
									required>
							</div>
							<% String userLoginId = (String)request.getAttribute("userLoginId");
							if (userLoginId != null) {
							%>
							<div class="d-flex flex-wrap"
								style="display: flex; justify-content: start; margin-bottom: 30px; color: FF0000_1;">
								<%= userLoginId %>
							</div>
							<% } %>
						</div>

						<div class="row mb-4">
							<label for="userLoginUser" class="form-label">ユーザー名</label> <label
								for="itemName" class="form-label"
								style="font-size: small; color: grey;"> 1文字以上25文字以内 </label>
							<div class="col-12">
								<input type="text" class="form-control" id="userLoginName"
									name="userLoginName" maxlength="25"
									value="<%= users.getUserName() != null ? users.getUserName() : "" %>"
									required>
							</div>
							<% String userName = (String)request.getAttribute("userName");
							if (userName != null) {
							%>
							<div class="d-flex flex-wrap"
								style="display: flex; justify-content: start; margin-bottom: 30px; color: FF0000_1;">
								<%= userName %>
							</div>
							<% } %>
						</div>

						<div class="row mb-3">
							<label for="userLoginPass" class="form-label"> パスワード
								<p id="passShowToggleIcon" class="d-inline-block"
									style="cursor: pointer; margin: 0;">
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="currentColor" class="bi bi-eye-slash-fill"
										viewBox="0 0 16 16">
										<path
											d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z" />
										<path
											d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z" />
									</svg>
								</p>
							</label> <label for="itemName" class="form-label"
								style="font-size: small; color: grey;"> 半角英数字8文字以上16文字以内
							</label> <label for="itemName" class="form-label"
								style="font-size: small; color: grey;"> 数字を1つ以上含む </label>
							<div class="col-12">
								<input type="text" class="form-control" id="userLoginPass"
									name="userLoginPass" maxlength="16"
									value="<%= users.getPassword() != null ? users.getPassword() : "" %>"
									required>
							</div>
							<% String password = (String)request.getAttribute("password");
							if (password != null) {
							%>
							<div class="d-flex flex-wrap"
								style="display: flex; justify-content: start; margin-bottom: 30px; color: FF0000_1;">
								<%= password %>
							</div>
							<% } %>
						</div>

						<div class="row">
							<div class="col-12 mt-5 d-flex justify-content-center">
								<input type="submit" value="登録する" class="button-purple py-1"
									style="border-radius: 5px; width: 50%;">
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- ユーザー側のみ表示 -->
			<%if (admin == null){ %>
			<div class="row justify-content-center my-3">
				<div class="col-lg-4">
					<a href="userSignin" class="link"
						style="color: #385A37; text-decoration: none;">ログインはこちら</a>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</main>
	<script src="./js/userSignup.js"></script>
</body>
</html>