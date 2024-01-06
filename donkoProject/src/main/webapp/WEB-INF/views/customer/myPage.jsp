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
			
			<!-- ユーザー情報の確認 -->
			<!-- モーダルボタン -->
			<div style="width: 32%;" class="link">
				<button type="button" class="btn mb-4 p-3 text-nowrap text-center w-100 border" 
						data-bs-toggle="modal" data-bs-target="#staticBackdrop"
						style="color: #385A37; text-decoration: none; border-radius:5px;">
				 		ユーザー情報の確認
				</button>
			</div>
			<!-- モーダルウィンドウ -->
			<div class="modal fade" id="staticBackdrop"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered w-100">
					<div class="modal-content" id="modalWindow" style="width:100%;">
						<div class="modal-header">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16" style="color:#385A37;">
								<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
								<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
							</svg>
							<small class="ms-2">ユーザー情報</small>
							<button type="button" class="btn-close"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body p-5 d-flex flex-wrap">
							<div>
								<%
								CustomerUser users = (CustomerUser) request.getAttribute("users");
								String gender = users.getGender();
								Date birthday = users.getBirthday();
								int userId = (int) request.getAttribute("user_id");
								%>
								<div class="mb-3">
									<small>ログインID：</small><br>
									<%=users.getUserLoginId()%>
								</div>
								<div class="mb-3">
									<small>名前：</small><br>
									<%=users.getUserName()%>
								</div>
								<div class="mb-3">
									<small>性別：</small><br>
									<%
									if (gender == null) {
									%>
										未設定<br>
									<%
									} else {
									%>
										<%=users.getGender()%><br>
									<%
									}
									%>
								</div>
								<div>
									<small>性別：</small><br>
									<%
									if (birthday == null) {
									%>
										未設定<br>
									<%
									} else {
									%>
										<%=new SimpleDateFormat("yyyy/MM/dd").format(users.getBirthday())%><br>
									<%
									}
									%>
								</div>
							</div>
							<div class="ms-auto">
								<a href="userInfoEdit?=<%= userId %>" class="ms-auto mb-3 link" style="color:#385A37;">
									<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
										<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
										<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
									</svg>
								</a>
							</div>
						</div>
						<div class="modal-footer d-flex flex-column p-4">
							<a href="logout" class="ms-auto link" style="color:#385A37; text-decoration:none;"><small>ログアウト</small></a>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 配送先の登録 -->
			<!-- モーダルボタン -->
			<div style="width: 32%;" class="link">
				<button type="button" class="btn mb-4 p-3 text-nowrap text-center w-100 border" 
						data-bs-toggle="modal" data-bs-target="#staticBackdrop2"
						style="color: #385A37; text-decoration: none; border-radius:5px;">
				 		配送先の登録
				</button>
			</div>
			<!-- モーダルウィンドウ -->
			<div class="modal fade" id="staticBackdrop2"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered w-100">
					<div class="modal-content" id="modalWindow" style="width:100%;">
						<div class="modal-header">
							<small class="ms-2">配送先の登録</small>
							<button type="button" class="btn-close"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<!-- フォームを入れる -->
						<form action="createShippingAddress" method="post">
							<div class="modal-body p-5 d-flex flex-wrap">
								<div class="form-group w-100 mb-3">
									<label for="exampleInputAddresses"><small>宛名：</small></label><br>
									<input type="text" class="form-control"
										id="exampleInputAddresses" aria-describedby="addresses"
										name="addresses" value="" placeholder="伊藤 直也" autocomplete="name">
									<span id="errorMessageAddressee" style="color:red;"></span>
								</div>
								<div class="form-group w-100 mb-3">
									<label for="exampleInputPostalCode"><small>郵便番号：</small></label><br>
									<input type="text" class="form-control" id="exampleInputPostalCode"
										aria-describedby="postalCode" maxlength="7" maxlength="7" name="postalcode" value=""
										placeholder="6500001" autocomplete="postal-code">
									<span id="errorMessagePostalCode" style="color:red;"></span>
								</div>
								<div class="form-group w-100">
									<label for="exampleInputAddress"><small>住所：</small></label><br>
									<input type="text" class="form-control" id="exampleInputAddress"
										aria-describedby="address" name="address" value="" placeholder="兵庫県神戸市中央区加納町４丁目２−１" autocomplete="address-line1">
									<span id="errorMessageAddress" style="color:red;"></span>
								</div>
							</div>
							<div class="modal-footer d-flex flex-column p-4">
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
			<!-- 配送先一覧のボタン -->
			<a href="shippingAddressIndex"
				class="d-inline-block border mb-4 p-3 text-center link"
				style="width: 32%; color: #385A37; text-decoration: none; border-radius:5px;">
				配送先一覧 
			</a>
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
			<button class="button-light-purple px-3 py-1" id="hideShippedButton" name="status_sort"
				value="hidden" style="border-radius: 40px;">
				<small>発送済みを非表示</small>
			</button>
			<% 
			} 
			%>
		</div>
		<div style="overflow-x: scroll; height: 57vh; border-radius: 5px;"
			class="border mx-5 mt-3 mb-5 px-3">
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
	</main>
	<%@include file="../component/footer.jsp"%>
	<script src="./js/createShippingAddress.js"></script>
	<script src="./js/nullValidationScript.js"></script>
	<script src="./js/myPageScript.js"></script>
</body>
</html>
