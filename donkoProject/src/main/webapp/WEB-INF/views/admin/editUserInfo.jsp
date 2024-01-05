<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="classes.user.CustomerUser"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
		<title>
			donko
		</title>
	</head>
	<body class="container">
		<main>
			<%
			CustomerUser user = (CustomerUser) request.getAttribute("user");
			%>
			<form action="editUserInfo" method="post" style="display: flex; justify-content: center; margin: 30px;">
				<input type="hidden" name="userId" value="<%= user.getUserId() %>">
				<div class="col-lg-5 m-5" style="border: 1px solid #333333; padding: 65px;">
					<div class="cancelButton" style="display: flex; justify-content: space-between; margin-bottom: 20px;">
						<h2>
							<strong>
								ユーザ情報編集
							</strong>
						</h2>
						<div>
							<a href="deleteUserInfoIndex" style="text-decoration: none; text-align: center;">
								<button type="button" class="btn-close border" aria-label="Close">
								</button>
							️</a>
						</div>
					</div>
					<div style="margin-bottom: 10px;">
						<label for="exampleInputUserId">
							ログインID
						</label>
						<br>
					</div>
					<div class="form-group　d-flex flex-wrap　justify-content-center" style="margin-bottom: 30px;">
						<input type="text" class="form-control" id="exampleInputUserId" name="user_login_id" value="<%=user.getUserLoginId()%>">
						<% String userLoginId = (String)request.getAttribute("userLoginId"); %>
						<% if (userLoginId != null) {%>
						<p style="color: red; margin: 0;">
							<%= userLoginId %>
						</p>
						<%} %>
					</div>
					<div style="margin-bottom: 10px;">
						<label for="exampleInputUserName">
							ユーザー名
							<span style="color: red;">
								（編集不可）
							</span>
						</label>
						<br>
					</div>
					<div class="form-group d-flex flex-wrap justify-content-start" style="margin-bottom: 30px;">
						<input disabled type="text" class="form-control" id="exampleInputUserName" aria-describedby="userName"  value="<%=user.getUserName()%>">
						<input type="hidden" name="user_name" value="<%=user.getUserName()%>">
						<% String userName = (String)request.getAttribute("userName"); %>
						<% if (userName != null) {%>
						<p style="color: red; margin: 0;">
							<%= userName %>
						</p>
						<%} %>
					</div>
					<div style="margin-bottom: 10px;">
						<label for="exampleInputGender">
							性別
							<span style="color: red;">
								（編集不可）
							</span>
						</label>
						<br>
					</div>
					<div class="form-group d-flex flex-wrap justify-content-center" style="margin-bottom: 30px;">
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
					<div style="margin-bottom: 10px;">
						<label for="exampleInputbirthday">
							誕生日
							<span style="color: red;">
								（編集不可）
							</span>
						</label>
						<br>
					</div>
					<div class="form-group d-flex flex-wrap justify-content-start" style="margin-bottom: 30px;">
						<input disabled type="date" class="form-control" id="exampleInputbirthday" value="<%=user.getBirthday()%>">
						<input type="hidden" value="<%=user.getBirthday()%>" name="birthday">
						<% String birthday = (String)request.getAttribute("birthday"); %>
						<% if (birthday != null) {%>
						<p style="color: red; margin: 0;">
							<%= birthday %>
						</p>
						<%} %>
					</div>
					<div style="margin-bottom: 10px;">
						<label for="exampleInputbirthday">
							ステータス
						</label>
						<br>
					</div>
					<div class="form-group d-flex flex-wrap justify-content-center" style="margin-bottom: 70px;">
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
					<div class="d-flex justify-content-center" style="margin-bottom: 20px;">
						<button type="submit" class="btn btn-lg w-100" style="border: 1px solid #000000; background: #E5CCFF; padding: 10px;">
							更新
						</button
					</div>
				</div>
			</form>
		</main>
	</body>
</html>