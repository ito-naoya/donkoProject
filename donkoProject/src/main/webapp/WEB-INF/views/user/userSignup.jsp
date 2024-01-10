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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<title>donko</title>
</head>
<body>
<%
String admin = (String) request.getAttribute("admin");
if (admin != null){
%>
	<%@include file= "../component/adminheader.jsp" %>
	<%@include file= "../component/adminheaderTopSpace.jsp" %>
<% } else {%>
	<%@include file= "../component/header.jsp" %>
	<%@include file= "../component/headerTopSpace.jsp" %>
<% } %>
	<main>
		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%>
		<div class="container">
			<div class="row" style="height:100vh;">
				<div class="m-auto">
					<%
					if (admin == null){
				    %>
					<!-- ここにdonko画像を表示 -->
					<span class="d-block d-flex justify-content-center my-3">
					<a
						href="home" class="link" style="text-decoration: none;">
						<img
							src="./images/donkoLogo2.png" style="height: 80px;">
					</a>
					</span>
					<%
					}
					%>
					<div class="col-lg-5 m-auto p-5 border" style="border-radius: 10px; box-shadow:10px 10px 10px lightgray;">
						<!-- アドミン側のみ表示 -->
						<%
						if (admin != null){
						%>
							<a href="adminTopPage" class="arrow mb-3 link" style="display: inline-block; color:navy;">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
								  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
								</svg>
							</a>
						<%
						}
						%>
						<div class="cancelButton"
							 style="display: flex; justify-content: space-between;">
							<h5 class="mt-1">
								<strong>ユーザー新規登録</strong>
							</h5>
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
								<label for="userLoginId" class="form-label d-flex justify-content-between mb-0">
									<small>ログインID：</small>
									<button type="button" class="btn link p-0 mb-1"
										data-bs-container="body" data-bs-toggle="popover"
										data-bs-placement="top"
										data-bs-content="5 文字以上 10 文字以内" data-bs-html="true"
										style="border: none;">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
												<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
												<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
											</svg>
									</button>
								</label>
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
								<label for="userLoginUser" class="form-label d-flex justify-content-between mb-0">
								<small>ユーザー名：</small>
								<button type="button" class="btn link p-0 mb-1"
									data-bs-container="body" data-bs-toggle="popover"
									data-bs-placement="top"
									data-bs-content="1 文字以上 25 文字以内" data-bs-html="true"
									style="border: none;">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
											<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
											<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
										</svg>
									</button>
								</label>
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
								<label for="userLoginPass" class="form-label d-flex justify-content-between mb-0">
									<small>パスワード：</small>
									<button type="button" class="btn link p-0 mb-1"
									data-bs-container="body" data-bs-toggle="popover"
									data-bs-placement="top"
									data-bs-content="半角英数字 8 文字以上 16 文字以内&lt;br&gt;数字を1つ以上含む" data-bs-html="true"
									style="border: none;">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle me-2" viewBox="0 0 16 16">
											<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
											<path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
										</svg>
									</button>
								</label>
								<div class="input-group">
									<input type="text" class="form-control" id="userLoginPass"
										name="userLoginPass" maxlength="16"
										value="<%= users.getPassword() != null ? users.getPassword() : "" %>"
										required>
									<span id="passShowToggleIcon" class="d-inline-block p-2  d-inline-flex align-items-center border"
										style="cursor: pointer; margin: 0; height:100%; border-radius: 0 5px 5px 0; background-color:#D5E8D4;">
										<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
											fill="currentColor" class="bi bi-eye-slash-fill"
											viewBox="0 0 16 16">
	           								<path
												d="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z" />
	           								<path
												d="M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z" />
	        							</svg>
									</span>
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
					<!-- ユーザー側のみ表示 -->
					<%if (admin == null){ %>
					<div class="row justify-content-center my-4">
						<div class="col-lg-5">
							<a href="userSignin" class="link"
								style="color: #385A37; text-decoration: none;">ログインはこちら</a>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</main>
	<script src="./js/userSignup.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
	<script>
	  // ポップオーバーを初期化
	  var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
	  var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
	    return new bootstrap.Popover(popoverTriggerEl)
	  });
	</script>
</body>
</html>