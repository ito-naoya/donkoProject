<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
	</head>
	<body>
		<main>
			<div class="container">
				<div class="row" style="margin: 250px 0;">
					<div class="col-lg-5 d-flex align-items-center flex-column border mx-auto p-5">
						<%
						String errorMessage = (String)request.getAttribute("errorMessage");
						String url = (String)request.getAttribute("url");
						%>
						<h1></h1>
						<h3><%= errorMessage %></h3>
						<a href="<%= url %>" style="margin-top: 30px"><%= returnPage %>戻る</a>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>