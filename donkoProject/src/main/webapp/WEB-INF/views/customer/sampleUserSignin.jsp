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


					<!-- ここにdonko画像を表示 -->
					
			          <h2>
			            <strong>ログイン</strong>
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
					    <form action="userSignin" method="post">
				            <div class="row mb-3 mt-3">
				              <label for="userLoginId" class="form-label">ユーザーID</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginId" name="userLoginId" required>
				              </div>
				            </div>

				            <div class="row mb-3">
				              <label for="userLoginPass" class="form-label">パスワード</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginPass" name="userLoginPass" required>
				              </div>
				            </div>

				            <div class="row mb-3">
				              <div class="col-12 mt-5 d-flex justify-content-center">
				                <input type="submit" value="ログイン" class="btn border" style="background-color:#9933FF; color: white; padding: 12px 80px;">
				              </div>
				            </div>
				        </form>
				        
				        <form action="updateUserPassword" method="GET">
					        <div class="row">
					              <div class="col-12 mt-5 d-flex justify-content-center">
					                <input type="submit" value="パスワード変更" class="btn border" style="background-color:#e5ccff; color: black; padding: 12px 80px;">
					              </div>
					         </div>
				        </form>
				</div>
			</div>
    	</div>
  </main>
</body>
</html>
