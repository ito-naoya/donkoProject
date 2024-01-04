<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, bean.PurchaseBean, classes.user.CustomerUser, java.text.SimpleDateFormat, java.sql.Timestamp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/popUpWindow.css" type="text/css">
<link href="./css/style.css" rel="stylesheet">
<title>donko</title>
<style>
.border::-webkit-scrollbar {display: none;}
.th {position: sticky; top: 0;}
td {vertical-align: middle;}
.link:hover{opacity: 0.7;}
.link2:hover{opacity: 0.8;}
</style>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main class="container">
		<!--  メニューボタンの表示 -->
		<div class="d-flex justify-content-between m-5 mb-2">
			<label class="open mx-1" for="pop-up" style="width: 32%;">
				<div
					class="d-inline-block border mb-4 p-3 w-100 text-center text-nowrap"
					style="color: #385A37; text-decoration: none;">
					<p class="mb-0">ユーザー情報を確認</p>
				</div>
			</label> <input type="checkbox" id="pop-up">
			<div class="overlay">
				<div class="window" id="userInfoWindow">
					<label class="close m-3" for="pop-up"> <svg
							xmlns="http://www.w3.org/2000/svg" width="22" height="22"
							fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
						  <path
								d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
						  <path
								d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
						</svg>
					</label>
					<%
					CustomerUser users = (CustomerUser) request.getAttribute("users");
					%>
					<div class="row d-flex justify-content-centert"
						style="height: 100%;">
						<div class="my-auto p-5">
							<div class="cancelButton"
								style="display: flex; justify-content: space-between; margin-bottom: 20px;">
								<h5>
									<strong>ユーザー情報</strong>
								</h5>
							</div>
							<table class="table table-borderless">
								<tbody>
									<tr>
										<td>ユーザーID</td>
										<td><%=users.getUserLoginId()%></td>
									</tr>
									<tr>
										<td>ユーザー名</td>
										<td><%=users.getUserName()%></td>
									</tr>
									<tr>
										<td>性別</td>
										<%
										String gender = users.getGender();
										if (gender == null) {
										%>
										<td>未設定</td>
										<%
										} else {
										%>
										<td><%=users.getGender()%></td>
										<%
										}
										%>
									</tr>
									<tr>
									</tr>
									<tr>
										<td>誕生日</td>
										<%
										Date birthday = users.getBirthday();
											if (birthday == null) {
										%>
										<td>未設定</td>
										<%
										} else {
										%>
										<td><%=new SimpleDateFormat("yyyy/MM/dd").format(users.getBirthday())%></td>
										<%
										}
										%>
									</tr>
								</tbody>
							</table>
							<div class="d-flex mt-5">
								<%
								int userId = (int) request.getAttribute("user_id");
								%>
								<a href="userInfoEdit?=<%=userId%>"
									class="link"
									style="color: #000000; vertical-align: middle; text-decoration: none; width: 50%; margin-right: 30px;">
									<button class="btn p-2 w-100"
										style="border: 1px solid gray; background: #E5CCFF;">編集</button>
								</a>
								<div style="width: 50%;" class="link">
									<button class="deleteUserButton btn p-2 text-nowrap w-100"
										data-bs-toggle="modal" 
										data-bs-target="#staticBackdrop" 
										id="deleteUserButton"
										style="border: 1px solid #FF0000; color: #FF0000;">退会</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 退会確認ボタン -->
				<div class="modal fade" id="staticBackdrop"
					data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered w-100">
						<div class="modal-content" id="modalWindow" style="width:100%;">
							<div class="modal-header">
								<p class="modal-title fs-5" id="staticBackdropLabel">
									退会手続き
								</p>
								<button class="btn ms-auto p-0" data-bs-dismiss="modal" id="cancelButton">
									<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
									<path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
									  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
									</svg>
								</button>
							</div>
							<div class="modal-body">
								退会処理を行うとアカウントのデータが
								<strong>全て</strong>消えます。<br>
								本当に退会してよろしいですか？<br><br>
								<small>退会後は現在のアカウントでログインできません。</small>
							</div>
							<div class="modal-footer">
								<form action="deleteUser" method="post">
									<button type="submit" class="btn link" style="color:white; background-color:#FF0000;">退会</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 配送先の登録ボタン -->
			<label class="open mx-1" for="pop-up2" style="width: 32%;">
				<div
					class="d-inline-block border mb-4 p-3 w-100 text-center text-nowrap"
					style="color: #385A37; text-decoration: none;">
					<p class="mb-0">配送先の登録</p>
				</div>
			</label> 
			<input type="checkbox" id="pop-up2">
			<div class="overlay">
				<div class="window">
					<label class="close m-3" for="pop-up2"> 
						<svg
							xmlns="http://www.w3.org/2000/svg" width="22" height="22"
							fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
						  <path
								d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z" />
						  <path
								d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
						</svg>
					</label>
					<div class="row d-flex justify-content-centert"
						style="height: 100%;">
						<div class="my-auto p-5">
							<div class="cancelButton"
								style="display: flex; justify-content: space-between; margin-bottom: 20px;">
								<h5>
									<strong>配送先の登録</strong>
								</h5>
							</div>
							<!-- フォームを入れる -->
							<form action="createShippingAddress" method="post">
								<div class="form-group">
									<label for="exampleInputAddresses">宛名</label><br>
									<input type="text" class="form-control"
										id="exampleInputAddresses" aria-describedby="addresses"
										name="addresses" value="" placeholder="伊藤 直也" autocomplete="name">
								</div>
								<p id="errorMessageAddressee" style="color:red;"></p>
								<div class="form-group">
									<label for="exampleInputPostalCode">郵便番号</label><br>
									<input type="text" class="form-control" id="exampleInputPostalCode"
										aria-describedby="postalCode" maxlength="7" maxlength="7" name="postalcode" value=""
										placeholder="6500001" autocomplete="postal-code">
								</div>
								<p id="errorMessagePostalCode" style="color:red;"></p>
								<div class="form-group">
									<label for="exampleInputAddress">住所</label><br>
									<input type="text" class="form-control" id="exampleInputAddress"
										aria-describedby="address" name="address" value="" placeholder="兵庫県神戸市中央区加納町４丁目２−１" autocomplete="address-line1">
								</div>
								<p id="errorMessageAddress" style="color:red;"></p>
								<div class="cancelButton mt-5 d-flex justify-content-center">
									<button 
										type="submit" class="btn" id="createButton"
										style="border: 1px solid #000000; background: #9933FF; color: #FFFFFF; width:50%;">
										登録
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- 配送先一覧のボタン -->
			<a href="shippingAddressIndex"
				class="d-inline-block border mb-4 p-3 text-center"
				style="width: 32%; color: #385A37; text-decoration: none;">
				配送先一覧 </a>
		</div>
		<%
		ArrayList<PurchaseBean> purchaseList = (ArrayList<PurchaseBean>) request.getAttribute("purchaseList");
		%>
		<div class="logout d-flex justify-content-between mx-5">
			<h4 style="margin-bottom: 0;">
				<strong>購入履歴</strong>
			</h4>
			<%
			if (purchaseList != null && purchaseList.size() > 0) {
			%>
			<button class="btn btn-sm" id="hideShippedButton" name="status_sort"
				value="hidden"
				style="border: 1px solid gray; background-color: #E5CCFF; border-radius: 40px;">発送済みを非表示</button>
			<% 
			} 
			%>
		</div>
		<div style="overflow-x: scroll; height: 30vh; border-radius: 5px;"
			class="border mx-5 my-3 px-3">
			<%
			if (purchaseList != null && purchaseList.size() > 0) {
			%>
			<table class="table purchaseDetailTable table-hover table-borderless my-4">
				<thead align="center">
					<tr>
						<th class="th"><strong style="white-space: nowrap;">注文ID</strong></th>
						<th class="th"><strong style="white-space: nowrap;">合計金額</strong></th>
						<th class="th"><strong>購入日</strong></th>
						<th class="th"><strong>配送先</strong></th>
						<th class="th"><strong style="white-space: nowrap;">配送ステータス</strong></th>
					<tr>
				</thead>
				<tbody>
					<!-- PurchaseBeanから順次値を取得して表示 -->
					<%
					for (PurchaseBean purchaseBean : purchaseList) {
					%>
					<tr onclick="location.href='orderDetail?purchase_id=<%=purchaseBean.getPurchaseId()%>'"
						style="cursor: pointer;">
						<!-- 注文番号 -->
						<td align="middle">#<%=purchaseBean.getPurchaseId()%></td>
						<!-- 単価 -->
						<td align="middle" style="white-space: nowrap;">¥ <%=String.format("%,d", purchaseBean.getTotalAmount())%></td>
						<!-- 購入日 -->
						<td align="middle" style="white-space: nowrap;"><%=new SimpleDateFormat("yyyy / MM / dd　hh:mm").format(purchaseBean.getPurchaseDate())%></td>
						<!-- 配送先 -->
						<td align="middle" style="white-space: nowrap;">
						<% String postalCode = (String) purchaseBean.getPostalCode();%>
						<% String head = postalCode.substring(0, 3);
						String end = postalCode.substring(3);
						%>
						〒 <%= head %>-<%= end %><br>
						<%=purchaseBean.getAddress()%><br>
						<%=purchaseBean.getAddressee()%></td>
						<!-- 配送ステータス -->
						<%
						if ((purchaseBean.getShippingStatus()).equals("処理中")) {
						%>
							<td align="middle" style="color: #CCC;"><%=purchaseBean.getShippingStatus()%></td>
						<%
						} else {
						%>
							<td align="middle" style="color: #63A162;"><%=purchaseBean.getShippingStatus()%></td>
						<%
						}
						%>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			} else {
			%>
			<div class="d-flex justify-content-center align-items-center" style="height:100%; color:lightgray;">
				<p class="mb-0">購入履歴はありません</p>
			</div>
			<%
			}
			%>
		</div>
		<div class="logout d-flex justify-content-end my-4">
			<a href="logout" class="btn px-4"
				style="color: white; background-color: #385A37; border-radius: 40px; margin-right: 45px;">ログアウト</a>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
	<script src="./js/createShippingAddress.js"></script>
	<script src="./js/nullValidationScript.js"></script>
	<script src="./js/myPageScript.js"></script>
</body>
</html>
