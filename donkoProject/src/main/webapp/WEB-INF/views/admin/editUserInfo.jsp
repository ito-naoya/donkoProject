<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="classes.user.CustomerUser"%>
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
		<title>
			donko
		</title>
	</head>
	<body>
		<main>
			<div class="container">
				<div class="row" style="height:100vh;">
					<%
					CustomerUser user = (CustomerUser) request.getAttribute("user");
					%>
					<div class="m-auto">
						<form action="editUserInfo" method="post" style="display: flex; justify-content: center;">
							<input type="hidden" name="userId" value="<%= user.getUserId() %>">
							<div class="col-lg-5 border p-5" style="border-radius:10px; box-shadow:10px 10px 10px lightgray;">
								<div class="cancelButton" style="display: flex; justify-content: space-between; margin-bottom: 20px;">
									<h5>
										<strong>
											ユーザ情報編集
										</strong>
									</h5>
									<div>
										<a href="deleteUserInfoIndex" style="text-decoration: none; text-align: center;">
											<button type="button" class="btn-close border" aria-label="Close">
											</button>
										️</a>
									</div>
								</div>
								<div class="form-group　d-flex flex-wrap　justify-content-center mb-3"
									<label for="exampleInputUserId">
										<small>ログインID：</small>
									</label>
									<input type="text" class="form-control" id="exampleInputUserId" name="user_login_id" value="<%=user.getUserLoginId()%>">
									<%
									String userLoginId = (String)request.getAttribute("userLoginId");
									%>
									<%
									if (userLoginId != null) {
									%>
										<p style="color: red; margin: 0;">
											<%= userLoginId %>
										</p>
									<%
									}
									%>
								</div>
								<div class="form-group d-flex flex-wrap justify-content-start mb-3">
									<label for="exampleInputUserName">
										<small>ユーザー名：</small>
										<small style="color: red;">
											（編集不可）
										</small>
									</label>
									<input disabled type="text" class="form-control" id="exampleInputUserName" aria-describedby="userName"  value="<%=user.getUserName()%>">
									<input type="hidden" name="user_name" value="<%=user.getUserName()%>">
								</div>
								<div class="form-group d-flex flex-wrap justify-content-start mb-3">
									<label for="exampleInputGender">
										<small>性別：</small>
										<small style="color: red;">
											（編集不可）
										</small>
									</label>
									<input type="hidden" name="gender" value="<%= user.getGender() %>">
									<select disabled class="form-control" id="exampleInputGender">
										<%
										String selected = user.getGender();
										String men = (selected != null && selected.equals("男性") ? "selected" : "");
										String woman = (selected != null && selected.equals("女性") ? "selected" : "");
										%>
										<option hidden>
											選択してください
										</option>
										<option value="男性" <%=men%>>
											男性
										</option>
										<option value="女性" <%=woman%>>
											女性
										</option>
									</select>
								</div>
								<div class="form-group d-flex flex-wrap justify-content-start mb-3">
									<label for="exampleInputbirthday">
										<small>誕生日：</small>
										<small style="color: red;">
											（編集不可）
										</small>
									</label>
									<input disabled type="date" class="form-control" id="exampleInputbirthday" value="<%=user.getBirthday()%>">
									<input type="hidden" value="<%=user.getBirthday()%>" name="birthday">
								</div>
								<div class="form-group d-flex flex-wrap justify-content-start">
									<label for="exampleInputbirthday">
										<small>ステータス：</small>
									</label>
									<select class="form-control" name="status" required>
									<%
									String isDeleted = user.isDeleted() ? "selected" : "";
									String isNotDeleted = user.isDeleted() ? "" : "selected";
									%>
										<option value="delete" <%= isDeleted %>>
											無効
										</option>
										<option value="notDelete" <%= isNotDeleted %>>
											有効
										</option>
									</select>
								</div>
								<div class="d-flex justify-content-center">
									<button type="submit" class="button-purple px-2 py-1 w-50" style="border-radius:5px; margin-top:70px;">
										更新
									</button
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>