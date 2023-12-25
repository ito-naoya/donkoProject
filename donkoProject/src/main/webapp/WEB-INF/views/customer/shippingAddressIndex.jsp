<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, bean.ShippingAddressBean, java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.arrow:hover{opacity: 0.7;}
</style>
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
		<div class="row">
			<div class="col-lg-8 mt-5 px-4" style="margin-bottom:80px;">
				<div class="d-flex justify-content-between align-items-center">
					<a href="myPage" class="arrow" style="display: inline-block; color:#385A37;">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
						</svg>
					</a>
				</div>
				<h4 class="mt-4"><strong>配送先一覧</strong></h4>
				<div class="d-flex flex-wrap">
					<a href="myPage" class="me-2 mb-2" style="text-decoration:none; width: calc(50% - 8px);">
						<div class="card" style="width:100%; height:100%; border: 1px dashed lightgray !important;">
							<div class="card-body text-center p-4 w-100">
								<h4 class="mb-0 mt-4" style="color:lightgray;">
								<svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-plus-circle-dotted" viewBox="0 0 16 16">
								  <path d="M8 0c-.176 0-.35.006-.523.017l.064.998a7.117 7.117 0 0 1 .918 0l.064-.998A8.113 8.113 0 0 0 8 0zM6.44.152c-.346.069-.684.16-1.012.27l.321.948c.287-.098.582-.177.884-.237L6.44.153zm4.132.271a7.946 7.946 0 0 0-1.011-.27l-.194.98c.302.06.597.14.884.237l.321-.947zm1.873.925a8 8 0 0 0-.906-.524l-.443.896c.275.136.54.29.793.459l.556-.831zM4.46.824c-.314.155-.616.33-.905.524l.556.83a7.07 7.07 0 0 1 .793-.458L4.46.824zM2.725 1.985c-.262.23-.51.478-.74.74l.752.66c.202-.23.418-.446.648-.648l-.66-.752zm11.29.74a8.058 8.058 0 0 0-.74-.74l-.66.752c.23.202.447.418.648.648l.752-.66zm1.161 1.735a7.98 7.98 0 0 0-.524-.905l-.83.556c.169.253.322.518.458.793l.896-.443zM1.348 3.555c-.194.289-.37.591-.524.906l.896.443c.136-.275.29-.54.459-.793l-.831-.556zM.423 5.428a7.945 7.945 0 0 0-.27 1.011l.98.194c.06-.302.14-.597.237-.884l-.947-.321zM15.848 6.44a7.943 7.943 0 0 0-.27-1.012l-.948.321c.098.287.177.582.237.884l.98-.194zM.017 7.477a8.113 8.113 0 0 0 0 1.046l.998-.064a7.117 7.117 0 0 1 0-.918l-.998-.064zM16 8a8.1 8.1 0 0 0-.017-.523l-.998.064a7.11 7.11 0 0 1 0 .918l.998.064A8.1 8.1 0 0 0 16 8zM.152 9.56c.069.346.16.684.27 1.012l.948-.321a6.944 6.944 0 0 1-.237-.884l-.98.194zm15.425 1.012c.112-.328.202-.666.27-1.011l-.98-.194c-.06.302-.14.597-.237.884l.947.321zM.824 11.54a8 8 0 0 0 .524.905l.83-.556a6.999 6.999 0 0 1-.458-.793l-.896.443zm13.828.905c.194-.289.37-.591.524-.906l-.896-.443c-.136.275-.29.54-.459.793l.831.556zm-12.667.83c.23.262.478.51.74.74l.66-.752a7.047 7.047 0 0 1-.648-.648l-.752.66zm11.29.74c.262-.23.51-.478.74-.74l-.752-.66c-.201.23-.418.447-.648.648l.66.752zm-1.735 1.161c.314-.155.616-.33.905-.524l-.556-.83a7.07 7.07 0 0 1-.793.458l.443.896zm-7.985-.524c.289.194.591.37.906.524l.443-.896a6.998 6.998 0 0 1-.793-.459l-.556.831zm1.873.925c.328.112.666.202 1.011.27l.194-.98a6.953 6.953 0 0 1-.884-.237l-.321.947zm4.132.271a7.944 7.944 0 0 0 1.012-.27l-.321-.948a6.954 6.954 0 0 1-.884.237l.194.98zm-2.083.135a8.1 8.1 0 0 0 1.046 0l-.064-.998a7.11 7.11 0 0 1-.918 0l-.064.998zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
								</svg>
								</h4>
								<small style="color:lightgray;">新しく配送先を追加する</small>
							</div>
						</div>
					</a>
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
          <%
          String postalCode = (String) shippingAddressBean.getPostalCode();
          %>
          <%
          String head = postalCode.substring(0, 3);
          String end = postalCode.substring(3);
          %>
					<div class="card me-2 mb-2" style="width: calc(50% - 8px);">
						<div class="card-body">
							<%= numbers %>
							<h5 class="card-title"><%= shippingAddressBean.getAddressee() %></h5>
							<h6 class="card-subtitle mb-2 text-body-secondary">
							〒 <%= head %>-<%= end %><br>
							<%= shippingAddressBean.getAddress() %>
							</h6>
							<button type="submit" class="btn" style="border: 1px solid #000000; background: #E5CCFF;">
								<a href='editShippingAddress?shipping_address_id=<%= shippingAddressBean.getShippingAddressId() %>'
									style="color: #000000; vertical-align: middle; text-decoration: none;">
									編集
								</a>
							</button>
							<%
							int main_address = shippingAddressBean.getMainShippingAddress();
							if (main_address != 1) {
							%>
							<button type="submit" class="btn"
								style="border: 1px solid #FF0000; background: #FFFFFF;">
								<a href="deleteShippingAddress?shipping_address_id=<%= shippingAddressBean.getShippingAddressId() %>"
									style="color: #FF0000; vertical-align: middle; text-decoration: none;">
									削除
								</a>
							</button>
							<% 
							} 
							%>
						</div>
					</div>
					<%
					numbers++;
					}
					%>
				</div>
			</div>
			<div class="col-lg-4 px-4">
				<div style="position: sticky; top:130px;">
					<label for="main_address_select"><strong>メイン発送先</strong></label>
					<div class="AddressChoice d-flex"
						style="display: flex; justify-content: end;">
						<form action="shippingAddressIndex" method="post" class="d-flex w-100">
							<select class="form-control d-flex"
								name="update_shipping_address"
								id="main_address_select">
								<%
                for (ShippingAddressBean updateMainShippingAddress : mainShippingAddressList) {
                %>
                <%String postalCode = (String)updateMainShippingAddress.getPostalCode();%>
                <% String head = postalCode.substring(0, 3);
                   String end = postalCode.substring(3);
                     %>
                <option
                  value="<%=updateMainShippingAddress.getShippingAddressId()%>">
                  〒 <%= head %>-<%= end %>
                  <%=updateMainShippingAddress.getAddress()%>
                  <%=updateMainShippingAddress.getAddressee()%></option>
                <%
                }
                %>
							</select>
							<button type="submit" class="btn text-nowrap"
								style="border: 1px solid #000000; background: #E5CCFF; margin-left: 15px;">更新</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>
