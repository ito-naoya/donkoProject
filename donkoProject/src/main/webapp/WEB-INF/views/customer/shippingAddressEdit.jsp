<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
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
<title>doko</title>
</head>
<body class="container">
	<main>
<%-- 		<%
		CustomerUser users = (CustomerUser) request.getAttribute("users");
		%> --%>
		<form action="editShippingAddress" method="post"
			style="display: flex; justify-content: center; margin: 30px;">
			<div class="col-lg-5 m-5"
				style="border: 1px solid #333333; padding: 65px;">
				<div class="cancelButton"
					style="display: flex; justify-content: space-between; margin-bottom: 20px;">
					<h2>
						<strong>配送先編集</strong>
					</h2>
					<div>
						<a href="myPage"
							style="text-decoration: none; text-align: center;"><button
								type="button" class="btn-close border" aria-label="Close"></button>️</a>
					</div>
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputUserId">宛名</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputAddresses"
						aria-describedby="addresses" name="addresses" value="">
<%-- 					<input type="text" class="form-control" id="exampleInputUserId"
						aria-describedby="userId" value="<%=users.getUserLoginId()%>"> --%>
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputUserName">郵便番号</label><br>
				</div>
				<div class="form-group"
					style="display: flex; justify-content: center; margin-bottom: 30px;">
					<input type="text" class="form-control" id="exampleInputPostCode"
						aria-describedby="postCode" maxlength="8" name="postcode" value="">
<%-- 					<input type="text" class="form-control" id="exampleInputPostCode"
						aria-describedby="postCode" maxlength="8" value="<%=shippingAddress.getpostCode()%>"> --%>
				</div>
				<div style="margin-bottom: 10px;">
					<label for="exampleInputGender">住所</label><br>
				</div>
        <div class="form-group"
          style="display: flex; justify-content: center; margin-bottom: 30px;">
          <input type="text" class="form-control" id="exampleInputAddress"
            aria-describedby="address" name="address" value="">
<%--           <input type="text" class="form-control" id="exampleInputPostCode"
            aria-describedby="postCode" maxlength="8" value="<%=shippingAddress.getpostCode()%>"> --%>
        </div>
				<div class="cancelButton"
					style="display: flex; justify-content: center; margin-bottom: 20px;">
					<button type="submit" class="btn btn-lg w-100"
						style="border: 1px solid #000000; background: #9933FF; color:#FFFFFF; padding: 10px;">更新</button
				</div>
		</form>
		</div>
	</main>
</body>
</html>