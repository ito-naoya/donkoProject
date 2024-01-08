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
		<div class="container mx-0">
			<div class="row" style="height:100vh; width:100vw;">
			
				<div class="col-lg-5 border m-auto p-5" style="display: flex; justify-content: center; border-radius:10px; box-shadow:10px 10px 10px lightgray;">
					<!-- ここからフォーム -->
					<form action="adminSignin" method="post" class="w-100">
						<h5>
							<img src="./images/donkoLogo2.png" style="height:50px;">
							<strong>管理者ログイン</strong>
						</h5>
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
						<div class="mb-3">
							<label for="adminLoginId" class="form-label"><small>ユーザーID：</small></label>
							<div>
								<input type="text" class="form-control" id="adminLoginId"
									name="adminLoginId" required>
							</div>
						</div>

						<div class="mb-5">
							<label for="adminLoginPass" class="form-label"><small>パスワード：</small></label>
							<div>
								<input type="text" class="form-control" id="adminLoginPass"
									name="adminLoginPass" required>
							</div>
						</div>
						
						<div class="mt-5">
							<div class="d-flex justify-content-center">
								<input type="submit" value="ログイン" class="button-purple py-1 w-50"
									style="border-radius:5px; margin:20px 0;">
							</div>
						</div>
					</form>
				</div>
				
				<div class="col-lg-6 d-flex justify-content-center px-5" style="height:100%; background-image: url('./images/広告3.jpeg'); background-size: cover; background-position: center;">
					<a href="home" class="border p-3" style="margin:auto; color:white; border-radius:5px; text-decoration:none;">ユーザー側ホーム画面に移動</a>
				</div>
				
			</div>
    	</div>
  </main>
</body>
</html>
