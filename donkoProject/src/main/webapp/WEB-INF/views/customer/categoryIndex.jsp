<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
	<%@include file= "../component/header.jsp" %>
	<%@include file= "../component/headerTopSpace.jsp" %>
	<main>
		<div class="d-flex flex-wrap justify-content-center mx-5 my-4">
			<% ArrayList<ItemBean> IList = (ArrayList<ItemBean>)request.getAttribute("itemList"); %>
			<% for (ItemBean item : IList) { %>
			<div>
				<a href="itemDetail?itemId=<%= item.getItemId() %>" style="color: #385a37; display: block; text-decoration:none;">
					<span class="card mx-2" style="width: 200px; height: 200px;">
					<img src="./images/<%= item.getImageFileName() %>.jpg"
						class="card-img-top" alt="<%= item.getImageFileName() %>"
						style="object-fit: cover; height: 100%; display: block;">
					</span>
				</a>
				<div class="mb-3 mx-2">
					<a href="itemDetail?itemId=<%= item.getItemId() %>" style="color: #385a37;">
						<%= item.getItemName() %>
					</a>
				</div>
			</div>
			<% } %>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>