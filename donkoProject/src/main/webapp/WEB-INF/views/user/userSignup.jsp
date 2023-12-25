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
								<strong>ユーザー新規登録</strong>
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
						<br>
						<!-- ここからフォーム -->
					    <form action="userSignup" method="post">
				            <div class="row my-3">
				              <label for="userLoginId" class="form-label">ユーザーID</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginId" name="userLoginId"  maxlength="10" value="<%=users.getUserLoginId()%>" required>
				              </div>
				              <% String userLoginId = (String)request.getAttribute("userLoginId");
									 if (userLoginId != null) {
							  %>
				              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
									<%= userLoginId %>
							  </div>
							  <% } %>
							</div>
	
				            <div class="row mb-3">
				              <label for="userLoginUser" class="form-label">ユーザー名</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginName" name="userLoginName"  maxlength="25" value="<%=users.getUserName()%>" required>
				              </div>
				              <% String userName = (String)request.getAttribute("userName");
									 if (userName != null) {
							  %>
				              <div class="d-flex flex-wrap" style="display: flex; justify-content: start; margin-bottom: 30px; color: #FF0000;">
									<%= userName %>
							  </div>
							  <% } %>
				            </div>
	
				            <div class="row">
				              <label for="userLoginPass" class="form-label">パスワード</label>
				              <div class="col-12">
				                <input type="text" class="form-control" id="userLoginPass" name="userLoginPass"  maxlength="16"  value="<%=users.getPassword()%>" required>
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
				                <input type="submit" value="作成する" class="btn border" style="background-color:#9933FF; color: white; width:50%;">
				              </div>
				            </div>
				        </form>
				</div>
			</div>
			<div class="row justify-content-center my-3">
        		<div class="col-5">
					<!-- ユーザー側のみログイン画面にいくボタン表示 -->
					<%if (admin == null){ %>
						<div class="cancelButton">
							<a href="userSignin" class="link" style="color:#385A37; text-decoration: none;">ログインはこちら</a>
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
