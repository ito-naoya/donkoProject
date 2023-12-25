<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, bean.ItemBean, bean.OptionCategoryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style type="text/css">
	a,label{ cursor:pointer; }
	label:hover { color : #E5CCFF; }
	}
</style>
</head>
<body>
	<%@include file= "../component/header.jsp" %>
	<%@include file= "../component/headerTopSpace.jsp" %>
	<main>
		<%
		ArrayList<ArrayList<OptionCategoryBean>> optionCategoryValueListAll = (ArrayList<ArrayList<OptionCategoryBean>>)request.getAttribute("optionCategoryValueListAll");
		ArrayList<ItemBean> itemList = (ArrayList<ItemBean>)request.getAttribute("itemList");
		%>
		<% 
		if (optionCategoryValueListAll != null || optionCategoryValueListAll.size() > 0) {
		%>
			<form action="option" method="get" class="mt-3">
				<div class="d-flex">
					<div class="col-lg-6 d-flex border ms-auto me-3 p-3" style="width:auto; height: 70px; box-shadow:5px 5px 5px lightgray;">
						<% 
						for (ArrayList<OptionCategoryBean> optionCategoryValueList : optionCategoryValueListAll) {
						%>
						<div class="d-flex justify-content-center border mx-2 px-3" style="width:auto; height: 35px; background-color: #D5E8D4";">
							<% 
							for (OptionCategoryBean optionCategoryValue : optionCategoryValueList) { 
							%>
							<small class="mx-3" style="display: flex; align-items: center;">
								<input type="checkbox" name="option"
								id="<%= optionCategoryValue.getOptionCategoryValue() %>"
								onclick="chebg('<%= optionCategoryValue.getOptionCategoryValue() %>')" 
								style="display: none;" 
								value="<%= optionCategoryValue.getOptionCategoryValue() %>"> 
								<label for="<%= optionCategoryValue.getOptionCategoryValue() %>">
									<%= optionCategoryValue.getOptionCategoryValue() %>
								</label>
							</small>
							<% 
							} 
							%>
						</div>
						<%
						} 
						%>
						<input type="hidden" name="categoryName" value="<%= (String)request.getAttribute("categoryName") %>">
						<button type="submit" class="btn text-nowrap ms-3" style="background-color: #E5CCFF;" onclick="return testCheck()">こだわり検索</button>
					</div>
				</div>
			</form>
			
			<div class="row my-4 mx-auto">
				<div class="d-flex flex-wrap">
					<% 
					for (ItemBean item : itemList) { 
					%>
					<div class="px-2" style="width:20%;">
						<a href="itemDetail?itemId=<%= item.getItemId() %>&source=category&categoryName=<%= (String)request.getAttribute("categoryName") %>" style="color: #385a37; display: block; text-decoration:none;">
							<span class="card">
							<img src="./images/<%= item.getImageFileName() %>.jpg"
								class="card-img-top" alt="<%= item.getImageFileName() %>"
								style="object-fit: cover;  width: 100%; aspect-ratio: 1 / 1; display: block;">
							</span>
						</a>
						<div class="mb-3">
							<a href="itemDetail?itemId=<%= item.getItemId() %>" style="color: #385a37;">
								<%= item.getItemName() %>
							</a>
						</div>
					</div>
					<% 
					} 
					%>
				</div>
			</div>
		<% 
		} else {
		%>
			<div class="d-flex justify-content-center align-items-center" style="height: 600px;">
				<p class="mb-0">アイテムが存在しません</p>
			</div>
		<% 
		}
		%>
	</main>
	<%@include file= "../component/footer.jsp" %>
	<script src="./js/categoryIndexScript.js"></script>
</body>
</html>