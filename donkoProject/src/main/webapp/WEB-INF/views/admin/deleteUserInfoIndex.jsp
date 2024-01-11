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
						  		ダブルクリックでユーザー情報を編集
						    </small>
						</div>
						<%
						String toIndicate = (String)request.getAttribute("toIndicate");
						%>
						<form action="deleteUserInfoIndex" method="GET">
							<%
							if(toIndicate.equals("delete")) {
							%>
								<input type="hidden" name="status" value="notDelete">
								<button type="submit" class="button-light-purple px-3" style="border-radius:40px;">
									<small>全てのユーザーを表示</small>
								</button>
							<%
							} else if(toIndicate.equals("notDelete")) {
							%>
								<input type="hidden" name="status" value="delete">
								<button type="submit" class="button-light-purple px-3" style="border-radius:40px;">
									<small>無効のユーザーを表示</small>
								</button>
							<%
							}
							%>
						</form>
					</div>
					
					<div style=" overflow-x: scroll; overflow:scroll; height:67vh; border-radius:5px;" class="border">
						<table class="table table-borderless st-tbl1 text-center" id="userTable">
							<thead>
							    <tr>
							      <th scope="col" class="py-4 ps-4"><small>ID</small></th>
							      <th scope="col" class="py-4"><small>ログインID</small></th>
							      <th scope="col" class="py-4"><small>ユーザー名</small></th>
							      <th scope="col" class="py-4 pe-4"><small>ステータス</small></th>
							    </tr>
							</thead>
							<tbody>
								<%
								for(CustomerUser user : userList){
								%>
									<tr style="cursor: pointer;" ondblclick="location.href='editUserInfo?userId=<%= user.getUserId() %>'" >
										<!-- ID -->
										<td class="ps-4 py-4"><%= user.getUserId()  %></td>
										<!-- ログインID -->
										<td class="p-4"><%= user.getUserLoginId() %></td>
										<!-- ユーザー名 -->
										<td class="p-4"><%= user.getUserName() %></td>
										<!-- ステータス -->
										<td class="pe-4 py-4">
											<% if(user.isDeleted()){ %>
												<p class="mb-0" style="color: #CCC">無効</p>
											<% } else { %>
												<p class="mb-0" style="color: royalblue">有効</p>
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
		<%@include file= "../component/adminfooter.jsp" %>
	</body>
</html>