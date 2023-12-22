<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, bean.ShippingAddressBean, java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
</head>
<body>
	<%@include file="../component/header.jsp"%>
	<%@include file="../component/headerTopSpace.jsp"%>
	<main class="container">
		<%
		ArrayList<ShippingAddressBean> mainShippingAddressList = (ArrayList<ShippingAddressBean>) request
				.getAttribute("mainShippingAddressList");
		%>
		<div class="d-flex justify-content-between align-items-center my-3">
			<a href="myPage" style="display: inline-block">
				<div class="border text-center" style="width: 50px;">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
	        			<path fill-rule="evenodd"
							d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"></path>
	      			</svg>
				</div>
			</a>
			<div>
				<label for="main_address_select"><strong>メイン発送先変更</strong></label>
				<div class="AddressChoice d-flex"
					style="display: flex; justify-content: end;">
					<form action="shippingAddressIndex" method="post" class="d-flex">
						<select class="form-control d-flex" name="update_shipping_address"
							style="width: 500px;" id="main_address_select">
							<%
							for (ShippingAddressBean updateMainShippingAddress : mainShippingAddressList) {
							%>
							<option
								value="<%=updateMainShippingAddress.getShippingAddressId()%>">〒
								<%=updateMainShippingAddress.getPostalCode()%>
								<%=updateMainShippingAddress.getAddress()%>
								<%=updateMainShippingAddress.getAddressee()%></option>
							<%
							}
							%>
						</select>
						<button type="submit" class="btn"
							style="border: 1px solid #000000; background: #E5CCFF; margin-left: 15px;">更新</button>
					</form>
				</div>
			</div>
		</div>
		
		<h4 style="margin-top: 50px; margin-bottom: 20px;">
			<strong>配送先一覧</strong>
		</h4>
		<table class="table table-borderless">
			<tbody>
				<%
				// カウンタ変数の初期化
				int numbers = 1;
				ArrayList<ShippingAddressBean> shippingAddressList = (ArrayList<ShippingAddressBean>) request
						.getAttribute("shippingAddressList");
				// Iteratorを使用してShippingAddressBeanのリストをイテレート
				Iterator<ShippingAddressBean> iterator = shippingAddressList.iterator();
				while (iterator.hasNext()) {
					ShippingAddressBean shippingAddressBean = iterator.next();
				%>
				<tr>
					<!-- No. -->
					<td><%=numbers%></td>
					<!-- 郵便番号 -->
					<td>〒 <%=shippingAddressBean.getPostalCode()%></td>
					<!-- 住所 -->
					<td><%=shippingAddressBean.getAddress()%></td>
					<!-- 宛名 -->
					<td><%=shippingAddressBean.getAddressee()%></td>
					<!-- 更新ボタン -->
					<td style="width:10%;">
						<button type="submit" class="btn"
							style="border: 1px solid #000000; background: #E5CCFF;">
							<a
								href='editShippingAddress?shipping_address_id=<%=shippingAddressBean.getShippingAddressId()%>'
								style="color: #000000; vertical-align: middle; text-decoration: none;">編集</a>
						</button>
					</td>
					<td style="width:10%;">
						<%
						int main_address = shippingAddressBean.getMainShippingAddress();
						if (main_address != 1) {
						%>
						<button type="submit" class="btn"
							style="border: 1px solid #FF0000; background: #FFFFFF;">
							<a href="deleteShippingAddress?shipping_address_id=<%=shippingAddressBean.getShippingAddressId()%>"
								style="color: #FF0000; vertical-align: middle; text-decoration: none;">
								削除
							</a>
						</button>
					</td>
				</tr>
				<%
				}
				numbers++;
				}
				%>
			
		</table>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>
