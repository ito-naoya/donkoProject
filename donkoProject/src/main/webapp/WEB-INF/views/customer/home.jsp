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
			<%
			ArrayList<ItemBean> itemList = (ArrayList<ItemBean>) request.getAttribute("itemList");
			%>
			<% 
			if (itemList != null) {
			%>
				<%
				for (ItemBean item : itemList) {
				%>
				<a href="itemDetail?itemId=<%= item.getItemId() %>&home=home" style="color: #385a37; display: block; text-decoration:none;" class="mx-2">
					<span class="card mb-3" style="width: 300px; height: 300px;">
						<img src="./images/<%= item.getImageFileName() %>.jpg"
							class="card-img-top" alt="<%= item.getImageFileName() %>"
							style="object-fit: cover; height: 100%;">
					</span>
				</a>
				<%
				}
				%>
			<%
			} 
			%>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>