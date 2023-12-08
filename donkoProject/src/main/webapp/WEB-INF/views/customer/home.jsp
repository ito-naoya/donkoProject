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
		<% ArrayList<ItemBean> IList = (ArrayList<ItemBean>)request.getAttribute("resultList"); %>
		<% for (ItemBean item : IList) { %>
		<div class="card mb-3 mx-2" style="width: 300px; height: 300px;">
			<img src="./images/<%= item.getImageFileName() %>.jpg" class="card-img-top" alt="...">
			<p><%= item.getImageFileName() %></p>
			<p><%= item.getItemCategoryName() %></p>
		</div>
		<% } %>
	</div>
	</main>
	<%-- <%@include file= "../component/footer.jsp" %> --%>
</body>
</html>