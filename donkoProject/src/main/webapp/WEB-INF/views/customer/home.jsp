<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
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
	<% ArrayList<ItemBean> IList = (ArrayList<ItemBean>)request.getAttribute("IList"); %>
	<% for(ItemBean item : IList) { %>
		<div class="card" style="width: 200px; height: 200px;">
			<img src="..." class="card-img-top" alt="...">
			<%= item.getItemId() %>
		</div>
		<% } %>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>