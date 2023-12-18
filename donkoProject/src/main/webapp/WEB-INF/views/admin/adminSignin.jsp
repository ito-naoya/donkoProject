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
<title>donko</title>
</head>
<body>
  <main>
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<div class="col-5 m-5 p-5" style="border: 1px solid #333333;">

			          <h2>
			            <strong>管理者ログイン</strong>
			          </h2>
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
					    <form action="adminSignin" method="post">
				            <div class="row mb-5 mt-3">
				              <label for="adminLoginId" class="form-label">ユーザーID</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="adminLoginId" name="adminLoginId" required>
				              </div>
				            </div>

				            <div class="row mb-3">
				              <label for="adminLoginPass" class="form-label">パスワード</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="adminLoginPass" name="adminLoginPass" required>
				              </div>
				            </div>

				            <div class="row">
				              <div class="col-12 mt-5 d-flex justify-content-center">
				                <input type="submit" value="ログイン" class="btn border" style="background-color:#9933FF; color: white; padding: 12px 80px;">
				              </div>
				            </div>
				        </form>
				</div>
			</div>
    	</div>
  </main>
</body>
</html>
