<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<!-- ここにdonko画像を表示 -->
				<span class="d-block d-flex justify-content-center my-3">
					<a href="home" class="link" style="text-decoration: none;"> 
						<img src="./images/donkoLogo2.png" style="height: 80px;">
					</a>
				</span>
				<div class="col-lg-4 p-5 border" style="border-radius:10px;">
					<h4 class="mt-3"><strong>ログイン</strong></h4>
					<br>
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

					<!-- ここからフォーム -->
					<form action="userSignin" method="post">
						<div class="row mb-3 mt-3">
							<label for="adminLoginId" class="form-label">ユーザーID</label>
							<div class="col-12">
								<input type="text" class="form-control" id="userLoginId"
									name="userLoginId" required>
							</div>
						</div>
						<div class="row mb-3">
							<label for="adminLoginPass" class="form-label">
								パスワード
								<p id="passShowToggleIcon" class="d-inline-block" style="cursor: pointer; margin: 0;">
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-eye-slash-fill" viewBox="0 0 16 16">
									  <path d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z"/>
									  <path d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z"/>
									</svg>
								</p>
							</label>
							<div class="col-12">
								<input type="password" class="form-control" id="userLoginPass"
									name="userLoginPass" required>

							</div>
						</div>

						<div class="row">
							<div class="col-12 my-4 d-flex justify-content-center">
								<input type="submit" value="ログイン" class="btn border"
									style="background-color: #9933FF; color: white; width:50%;">
							</div>
						</div>
					</form>
					<hr>
					<div class="row">
						<div class="col-12 mt-3 d-flex justify-content-center">
							<div style="width:50%;">
							<div class="d-flex justify-content-center">
							<small>パスワードをお忘れですか？</small><br>
							</div>
							<a href="updateUserPassword">
							<input type="submit" value="パスワード変更" class="btn border w-100"
								style="background-color: #9933FF; color: white;">
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center my-3">
				<div class="col-lg-4">
					<a href="userSignup" class="link" style="color:#385A37; text-decoration:none;">新規登録はこちら</a>
				</div>
			</div>
		</div>
	</main>
	<script src="./js/customerUserSigninScript.js"></script>
</body>
</html>
