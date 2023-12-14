<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.OptionCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style type="text/css">
	a,label{cursor:pointer;}
	label:hover { color : #E5CCFF; }
	}
</style>
</head>
<body>
	<%@include file= "../component/header.jsp" %>
	<%@include file= "../component/headerTopSpace.jsp" %>
	<main>
		<form action="option" method="get">
			<div class="d-flex">
				<%
				String message = (String) request.getAttribute("message");
				%>
				<% if (message != null) { %>
				<span class="border ms-4 py-2 px-3 w-auto" style="display: inline-flex; vertical-align: middle;"><%= message %></span>
				<% } %>
				<div class="col-lg-6 d-flex border ms-auto me-3 p-3" style="width:auto; height: 70px; box-shadow:5px 5px 5px lightgray;">
					<% 
					ArrayList<ArrayList<OptionCategoryBean>> ONVListAll = (ArrayList<ArrayList<OptionCategoryBean>>)request.getAttribute("ONValueListALL");
					%>
					<% 
					for (ArrayList<OptionCategoryBean> ONVList : ONVListAll) {
					%>
					<div class="d-flex justify-content-center border mx-2 px-3" style="width:auto; height: 35px; background-color: #D5E8D4";">
						<% for (OptionCategoryBean ONValue : ONVList) { %>
						<small class="mx-3" style="display: flex; align-items: center;">
							<input type="checkbox" name="option"
							id="<%= ONValue.getOptionCategoryValue() %>"
							onclick="chebg('<%= ONValue.getOptionCategoryValue() %>')" 
							style="display: none;" 
							value="<%= ONValue.getOptionCategoryValue() %>"> 
							<label for="<%= ONValue.getOptionCategoryValue() %>">
								<%= ONValue.getOptionCategoryValue() %>
							</label>
						</small>
						<% } %>
					</div>
					<%
					} 
					%>
					<input type="hidden" name="categoryName" value="<%= (String)request.getAttribute("categoryName") %>">
					<button type="submit" class="btn text-nowrap ms-3" style="background-color: #E5CCFF";">こだわり検索</button>
				</div>
			</div>
		</form>
		
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
	<script src="./js/categoryIndexScript.js"></script>
</body>
</html>