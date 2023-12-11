<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main class="m-5">
		<div>
			<form action="purchaseConfirm" method="get" class="d-flex justify-content-end mb-3">
				<%-- <input type="hidden" value="<%= item.getItemId() %>" name="itemId"> --%>
				<button type=submit class="btn px-5 py-3" style="background-color: #9933ff; color: white; width: 210px;">
					レジに進む
				</button>
			</form>
		</div>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-11">
					<table class="table table-borderless">
						<tr>
							<td>
								<a href="#" class="me-3"
								style="text-decoration: none; display: inline-block;">
									<div style="height: 150px; width: 150px;">
										<img class="object-fit-cover w-100 h-100"
											src="./images/Galaxy1.jpg">
									</div>
								</a>
							</td>
							<td style="vertical-align: middle;"><strong>商品タイトル</strong></td>
							<td style="vertical-align: middle;">¥ 30,009</td>
							<td style="vertical-align: middle;">
							<p class="border p-2" style="margin: 0">
									数量: 
									<select name="quantity" style="border: none;">
										<option value="1" selected>1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
									</select>
								</p>
							</td>
							<td style="vertical-align: middle;">
								<button type="submit" class="btn p-2 ms-3"
									style="background-color: #e5ccff;">更新</button>
							</td>
							<td style="vertical-align: middle;">
								<button type="submit" class="btn p-2 ms-3"
									style="background-color: red; color: white;">削除</button>
							</td>
						<tr>
					</table>
				</div>
				<div class="d-flex justify-content-center">
					<form action="" class="d-flex justify-content-end mt-3">
						<button type=submit
							class="btn px-5 py-3 rounded-pill border border-danger"
							style="background-color: white; color: red;">
							カートの中身を全て削除する</button>
					</form>
				</div>
			</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>