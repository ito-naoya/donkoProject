<%@page import="controller.user.UserSigninServlet"%>
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
 <style>
	.link:hover{opacity: 0.7;}
	.link2:hover{opacity: 0.8;}
	</style>
    <title>donko</title>
</head>
<body class="container">
	<main>
		<%
		UserSigninServlet user = (UserSigninServlet) request.getAttribute("user");
		%>
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<%
					String admin = (String) request.getAttribute("admin");
					if (admin == null){
				%>
				<!-- ここにdonko画像を表示 -->
				<span class="d-block d-flex justify-content-center my-3">
					<a href="home" class="link" style="text-decoration: none;"> 
						<img src="./images/donkoLogo2.png" style="height: 80px;">
					</a>
				</span>
				<%
					}
				%>
				<div class="col-5 p-5 border" style="border-radius:10px;">
			            <div class="cancelButton"
							style="display: flex; justify-content: space-between;">
							<h4 class="mt-3">
								<strong>ログイン画面</strong>
							</h4>
					<!-- アドミン側のみ表示 -->
							<%if (admin != null){ %>
							<div>
								<a href="adminTopPage" style="text-decoration: none; text-align: center;"><button type="button" class="btn-close border" aria-label="Close"></button>️</a>
							</div>
							<%} %>
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
				<!-- //ユーザーID入力欄 -->

				<div style="margin-bottom: 10px;">
					<label for="exampleInputUserId">ユーザーID</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputUserId"
						aria-describedby="userId" >
				</div>

				<!-- //パスワード入力欄 -->
				<div style="margin-bottom: 10px;">
					<label for="exampleInputPassword">パスワード</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="password" class="form-control" id="exampleInputPassword"
						maxlength="8" value="">
				</div>
				<div class="cancelButton"
					style="display: flex; justify-content: space-between; margin-bottom: 20px;">
					</div>

				<div style="text-align: center; margin-bottom: 20px;">
				    <!-- ログインボタン -->
				    <div>
				        <a href="login" style="text-decoration: none; text-align: center;">
				            <button type="button" class="btn btn-primary" aria-label="Login" style="background-color:#9933FF;">ログイン</button>
				        </a>
				    </div>

				    <!-- 新規登録画面に戻るボタン -->
				    <div style="margin-top: 20px;">
				        <a href="registration" style="text-decoration: none; text-align: center;">
				            <button type="button" class="btn btn-primary" aria-label="Back" style="background-color:#9933FF;">新規登録画面へ戻る</button>
				        </a>
				    </div>
				</div>
			</div>
		</form>
	</main>
</body>
</html>