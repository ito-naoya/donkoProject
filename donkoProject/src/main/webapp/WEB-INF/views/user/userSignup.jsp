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
    <title>donko</title>
</head>
<body>
  <main>
  		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%>
		<div class="container　ml-5 mr-5">
			<div class="row justify-content-center">
				<div class="col-5 mt-5 p-5" style="border: 1px solid #333333;">
					<%
						String admin = (String) request.getAttribute("admin");
						if (admin == null){
					%>
					<!-- ここにdonko画像を表示 -->
					<%
						}
					%>
			            <div class="cancelButton"
							style="display: flex; justify-content: space-between; margin-bottom: 20px;">
							<h2>
								<strong>ユーザー新規登録</strong>
							</h2>
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

						<!-- ここからフォーム -->
					    <form action="userSignup" method="post">
				            <div class="row mb-5 mt-3">
				              <label for="userLoginId" class="form-label">ユーザーID</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginId" name="userLoginId"  maxlength="10" value="<%= users.getUserLoginId() != null ? users.getUserLoginId() : "" %>" required>
				              </div>
				              <% String userLoginId = (String)request.getAttribute("userLoginId");
									 if (userLoginId != null) {
							  %>
				              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
									<%= userLoginId %>
							  </div>
							  <% } %>
							</div>

				            <div class="row mb-5 mt-3">
				              <label for="userLoginUser" class="form-label">ユーザー名</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginName" name="userLoginName"  maxlength="25" value="<%= users.getUserName() != null ? users.getUserName() : "" %>" required>
				              </div>
				              <% String userName = (String)request.getAttribute("userName");
									 if (userName != null) {
							  %>
				              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
									<%= userName %>
							  </div>
							  <% } %>
				            </div>

				            <div class="row mb-3">
				              <label for="userLoginPass" class="form-label">パスワード</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginPass" name="userLoginPass"  maxlength="16"  value="<%= users.getPassword() != null ? users.getPassword() : "" %>" required>
				              </div>
				              <% String password = (String)request.getAttribute("password");
									 if (password != null) {
							  %>
				              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
									<%= password %>
							  </div>
							  <% } %>
				            </div>

				            <div class="row">
				              <div class="col-12 mt-5 d-flex justify-content-center">
				                <input type="submit" value="作成する" class="btn border" style="background-color:#9933FF; color: white; padding: 12px 80px;">
				              </div>
				            </div>
				        </form>
				</div>
			</div>
			<div class="row justify-content-center">
        		<div class="col-5 mb-5 p-5">
					<!-- ユーザー側のみログイン画面にいくボタン表示 -->
					<%if (admin == null){ %>
						<div class="cancelButton"style="display: flex; justify-content: center; margin-bottom: 20px;">
							<button type="submit" class="btn btn-lg w-100" style="border: 1px solid #000000; background: #E5CCFF; padding: 10px;">
							<a href="userSignin" style="color: black; text-decoration: none;">ログイン画面に移動</a>
							</button>
						</div>
					<%
						}
					%>
				</div>
			</div>
    	</div>
  </main>
</body>
</html>
