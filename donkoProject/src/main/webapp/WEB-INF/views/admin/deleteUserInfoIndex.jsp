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
			<div class="coll">
				<table class="table table-borderless  st-tbl1">
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
						<div class="overflow" style="overflow: auto;"></div>
			            <tr>
			                <td><!-- ID -->
			                    <p></p>
			                </td>
			                <td><!-- ログインID -->
			                    <p></p>
			                </td>
			                <td><!-- ユーザー名 -->
			                    <p></p>
			                </td>
			                <td><!-- ステータス -->
			                    <% if(item.isDeleted()){ %>
			                        <p style="color: #CCC">削除済み</p>
			                    <% } else { %>
			                        <p style="color: #00FF00">販売中</p>
			                    <% } %>
			                </td>
			            </tr>
					</tbody>
				</table>
			</div>
		</main>
	</body>
</html>