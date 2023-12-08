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
	<div class="d-flex flex-wrap">
		<% ArrayList<ItemBean> IList = (ArrayList<ItemBean>)request.getAttribute("resultList"); %>
		<% for (ItemBean item : IList) { %>
		<div class="card" style="width: 200px; height: 200px;">
			<img src="..." class="card-img-top" alt="...">
			<p><%= item.getImageFileName() %></p>
			<p><%= item.getItemCategoryName() %></p>
		</div>
		<% } %>
	</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>