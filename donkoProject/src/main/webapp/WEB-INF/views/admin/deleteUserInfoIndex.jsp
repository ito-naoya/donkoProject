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
		<%@include file= "../component/adminheader.jsp" %>
		<%@include file= "../component/adminheaderTopSpace.jsp" %>
		<main>
			<div class="container">
				<div class="row px-5">
				
					<%
					ArrayList<CustomerUser> userList = (ArrayList<CustomerUser>)request.getAttribute("userList");
					%>
					<div class="d-flex justify-content-between align-items-center mt-5 mb-3">
						<div style="display: flex; align-items: end;">
						    <h5 class="mb-0 me-3"><strong>削除ユーザー一覧</strong></h5>
						    <small class="mx-3" style="vertical-align: middle;">
						  		ダブルクリックで商品を編集
						    </small>
						</div>
						<%
						String toIndicate = (String)request.getAttribute("toIndicate");
						%>
						<form action="deleteUserInfoIndex" method="POST">
							<%
							if(toIndicate.equals("deletedUser")) {
							%>
								<input type="hidden" name="showSelect" value="notDeletedUser">
								<button type="submit" class="btn btn-sm px-4 border" style="background-color: #e5ccff; border-radius:40px;">
									全てのユーザーを表示
								</button>
							<%
							} else if(toIndicate.equals("notDeletedUser")) {
							%>
								<input type="hidden" name="showSelect" value="deletedUser">
								<button type="submit" class="btn btn-sm px-4 border" style="background-color: #e5ccff; border-radius:40px;">
									無効のユーザーを表示
								</button>
							<%
							}
							%>
						</form>
					</div>
					
					<div style=" overflow-x: scroll; overflow:scroll; height:70vh; border-radius:5px;" class="border px-4">
						<table class="table table-borderless st-tbl1 my-5 text-center" id="userTable">
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
									<tr style="cursor: pointer;" ondblclick="location.href='editUserInfo?userId=<%= user.getUserId() %>'" >
										<!-- ID -->
										<td class="p-4"><%= user.getUserId()  %></td>
										<!-- ログインID -->
										<td class="p-4"><%= user.getUserLoginId() %></td>
										<!-- ユーザー名 -->
										<td class="p-4"><%= user.getUserName() %></td>
										<!-- ステータス -->
										<td class="p-4">
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