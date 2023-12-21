<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, classes.user.CustomerUser, java.text.SimpleDateFormat"%>
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
<body class="container">
	<main>
		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%>
		<div class="row d-flex justify-content-centert" style="height:100vh;">
		<div class="col-lg-5 col-md-8 m-auto "
			style="border: 1px solid #333333; padding: 65px; item-">
			<div class="cancelButton"
				style="display: flex; justify-content: space-between; margin-bottom: 20px;">
				<h2>
					<strong>ユーザー情報</strong>
				</h2>
				<div>
					<a href="myPage" style="text-decoration: none; text-align: center;"><button
							type="button" class="btn-close border" aria-label="Close"></button>️</a>
				</div>
			</div>
			<table class="table table-borderless">
				<tbody>
					<tr>
						<td>ユーザーID</td>
						<td><strong><%=users.getUserLoginId()%></strong></td>
					</tr>
					<tr>
						<td>ユーザー名</td>
						<td><strong><%=users.getUserName()%><strong></td>
					</tr>
					<tr>
						<td>性別</td>
						<td ><strong><%=users.getGender()%></strong></td>
					</tr>
					<tr>
						<td>誕生日</td>
						<td><strong><%=new SimpleDateFormat("yyyy/MM/dd").format(users.getBirthday())%><strong></td>
					</tr>
				</tbody>
			</table>
			<div class="d-flex">
			<% int userId = (int) request.getAttribute("user_id"); %>
			<a href="userInfoEdit?=<%=userId%>"
          style="color: #000000; vertical-align: middle; text-decoration: none; width:50%; margin-right:30px;">
				<button class="btn w-100"
					style="border: 1px solid #000000; background: #E5CCFF; padding:10px;">編集</button></a>
				<form action="userInfoPage" method="post" style="width:50%;">
					<button type="submit" class="btn text-nowrap w-100"
						style="border: 1px solid #FF0000; background: #FFFFFF; color: #FF0000; padding: 10px;">退会</button>
				</form>
			</div>
		</div>
		</div>
	</main>
</body>
</html>