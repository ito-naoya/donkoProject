<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<style>
.link:hover{opacity: 0.7;}
.link2:hover{opacity: 0.8;}
</style>
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
				<div class="col-5 p-5 border" style="border-radius:10px;">
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
							<label for="adminLoginPass" class="form-label">パスワード</label>
							<div class="col-12">
								<input type="text" class="form-control" id="userLoginPass"
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
							<small class="mb-3">パスワードをお忘れですか？</small><br>
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
				<div class="col-5">
					<a href="userSignup" class="link" style="color:#385A37; text-decoration:none;">新規登録はこちら</a>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
