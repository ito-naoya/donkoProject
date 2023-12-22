<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="classes.user.CustomerUser" %>
	<%@ page import="java.util.*" %>
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
		<link rel="stylesheet" href="./css/deleteUserInfoIndex.css">
	</head>
	<body>
		<main>
			<div class="container">
				<%
				ArrayList<CustomerUser> userList = (ArrayList<CustomerUser>)request.getAttribute("userList");
				%>
				<div class="d-flex justify-content-evenly align-items-center mt-5 mb-3">
					<a href="adminTopPage" style="display: inline-block;">
						<div class="border text-center" style="width: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
  								<path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
							</svg>
						</div>
					</a>
					<%
					String toIndicate = (String)request.getAttribute("toIndicate");
					%>
					<form action="deleteUserInfoIndex" method="POST">
						<%
						if(toIndicate.equals("deletedUser")) {
						%>
							<input type="hidden" name="showSelect" value="notDeletedUser">
							<button type="submit" class="btn border p-2 ms-3" style="background-color: #e5ccff;">
								全てのユーザーを表示
							</button>
						<%
						} else if(toIndicate.equals("notDeletedUser")) {
						%>
							<input type="hidden" name="showSelect" value="deletedUser">
							<button type="submit" class="btn border p-2 ms-3" style="background-color: #e5ccff;">
								無効のユーザーを表示
							</button>
						<%
						}
						%>
					</form>
				</div>
				<div class="row">
					<div class="col">
						<table class="table table-borderless st-tbl1" id="userTable" style="padding: 10px">
							<thead>
							    <tr>
							      <th scope="col">
							      	ID
							      </th>
							      <th scope="col">
							      	ログインID
							      </th>
							      <th scope="col">
							      	ユーザー名
							      </th>
							      <th scope="col">
							      	ステータス
							      </th>
							    </tr>
							</thead>
							<tbody>
								<%
								for(CustomerUser user : userList){
								%>
									<tr style="cursor: pointer;" onclick="location.href='editUserInfo?userId=<%= user.getUserId() %>'" >
										<td><!-- ID -->
											<p>
												<%= user.getUserId()  %>
											</p>
										</td>
										<td><!-- ログインID -->
											<p>
												<%= user.getUserLoginId() %>
											</p>
										</td>
										<td><!-- ユーザー名 -->
											<p>
												<%= user.getUserName() %>
											</p>
										</td>
										<td><!-- ステータス -->
											<% if(user.isDeleted()){ %>
												<p style="color: #CCC">無効</p>
											<% } else { %>
												<p style="color: #00FF00">有効</p>
											<% } %>
										</td>
									</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>