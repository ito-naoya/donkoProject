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
.card:hover {background-color: #00000013;}
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
		ArrayList<ShippingAddressBean> shippingAddressList = (ArrayList<ShippingAddressBean>) request
				.getAttribute("shippingAddressList");
		%>
		<div class="row">
			<% 
			if (shippingAddressList.size() > 0) { 
			%>
			<div class="col-lg-4 mt-5 px-4">
				<div class="d-flex justify-content-between align-items-center">
				<%
				String url = (String)request.getAttribute("url");
				%>
					<a href="<%= url %>" class="arrow" style="display: inline-block; color:#385A37;">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
						</svg>
					</a>
				</div>
				<div class="mt-4" style="position: sticky; top:130px;">
					<label for="main_address_select"><strong>メイン発送先</strong></label>
					<div class="AddressChoice d-flex"
						style="display: flex; justify-content: end;">
						<form action="shippingAddressIndex" method="post" class="d-flex w-100">
							<select class="form-control d-flex"
								name="update_shipping_address"
								id="main_address_select"
								style="cursor: pointer;">
								<%
								for (ShippingAddressBean updateMainShippingAddress : mainShippingAddressList) {
								%>
								<%
								String postalCode = (String) updateMainShippingAddress.getPostalCode();
								%>
								<%
								String head = postalCode.substring(0, 3);
								String end = postalCode.substring(3);
								%>
								<option
									value="<%=updateMainShippingAddress.getShippingAddressId()%>">
									〒
									<%=head%>-<%=end%>
									<%=updateMainShippingAddress.getAddress()%>
									<%=updateMainShippingAddress.getAddressee()%></option>
								<%
								}
								%>
							</select>
							<button type="submit" class="px-2 text-nowrap button-light-purple"
								style="margin-left: 15px; border-radius:5px;">更新</button>
								<input type="hidden" value="<%= url %>" name="source">
						</form>
					</div>
				</div>
			</div>
			<% 
			} 
			%>
			<div class="col-lg-8 mt-5 px-4" style="margin-bottom:80px;">
			<div class="d-flex justify-content-between">
				<h5><strong>配送先一覧</strong></h5>
				<small class="mx-3" style="vertical-align: middle;">
					ダブルクリックで配送先を編集 </small>
				</div>
				<div class="d-flex flex-wrap">
					<a href="myPage" class="me-2 mb-2" style="text-decoration:none; width: calc(50% - 8px);">
						<div class="card" style="width:100%; height:100%; border: 1px dashed lightgray !important;">
							<div class="card-body d-flex justify-content-center align-items-center text-center p-4 w-100">
								<div>
								<h4 class="mb-0" style="color:lightgray;">
								<svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-plus-circle-dotted" viewBox="0 0 16 16">
								  <path d="M8 0c-.176 0-.35.006-.523.017l.064.998a7.117 7.117 0 0 1 .918 0l.064-.998A8.113 8.113 0 0 0 8 0zM6.44.152c-.346.069-.684.16-1.012.27l.321.948c.287-.098.582-.177.884-.237L6.44.153zm4.132.271a7.946 7.946 0 0 0-1.011-.27l-.194.98c.302.06.597.14.884.237l.321-.947zm1.873.925a8 8 0 0 0-.906-.524l-.443.896c.275.136.54.29.793.459l.556-.831zM4.46.824c-.314.155-.616.33-.905.524l.556.83a7.07 7.07 0 0 1 .793-.458L4.46.824zM2.725 1.985c-.262.23-.51.478-.74.74l.752.66c.202-.23.418-.446.648-.648l-.66-.752zm11.29.74a8.058 8.058 0 0 0-.74-.74l-.66.752c.23.202.447.418.648.648l.752-.66zm1.161 1.735a7.98 7.98 0 0 0-.524-.905l-.83.556c.169.253.322.518.458.793l.896-.443zM1.348 3.555c-.194.289-.37.591-.524.906l.896.443c.136-.275.29-.54.459-.793l-.831-.556zM.423 5.428a7.945 7.945 0 0 0-.27 1.011l.98.194c.06-.302.14-.597.237-.884l-.947-.321zM15.848 6.44a7.943 7.943 0 0 0-.27-1.012l-.948.321c.098.287.177.582.237.884l.98-.194zM.017 7.477a8.113 8.113 0 0 0 0 1.046l.998-.064a7.117 7.117 0 0 1 0-.918l-.998-.064zM16 8a8.1 8.1 0 0 0-.017-.523l-.998.064a7.11 7.11 0 0 1 0 .918l.998.064A8.1 8.1 0 0 0 16 8zM.152 9.56c.069.346.16.684.27 1.012l.948-.321a6.944 6.944 0 0 1-.237-.884l-.98.194zm15.425 1.012c.112-.328.202-.666.27-1.011l-.98-.194c-.06.302-.14.597-.237.884l.947.321zM.824 11.54a8 8 0 0 0 .524.905l.83-.556a6.999 6.999 0 0 1-.458-.793l-.896.443zm13.828.905c.194-.289.37-.591.524-.906l-.896-.443c-.136.275-.29.54-.459.793l.831.556zm-12.667.83c.23.262.478.51.74.74l.66-.752a7.047 7.047 0 0 1-.648-.648l-.752.66zm11.29.74c.262-.23.51-.478.74-.74l-.752-.66c-.201.23-.418.447-.648.648l.66.752zm-1.735 1.161c.314-.155.616-.33.905-.524l-.556-.83a7.07 7.07 0 0 1-.793.458l.443.896zm-7.985-.524c.289.194.591.37.906.524l.443-.896a6.998 6.998 0 0 1-.793-.459l-.556.831zm1.873.925c.328.112.666.202 1.011.27l.194-.98a6.953 6.953 0 0 1-.884-.237l-.321.947zm4.132.271a7.944 7.944 0 0 0 1.012-.27l-.321-.948a6.954 6.954 0 0 1-.884.237l.194.98zm-2.083.135a8.1 8.1 0 0 0 1.046 0l-.064-.998a7.11 7.11 0 0 1-.918 0l-.064.998zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
								</svg>
								</h4>
								<small style="color:lightgray;">新しく配送先を追加する</small>
								</div>
							</div>
						</div>
					</a>
					<%
					// カウンタ変数の初期化
					int numbers = 1;
					// Iteratorを使用してShippingAddressBeanのリストをイテレート
					Iterator<ShippingAddressBean> iterator = shippingAddressList.iterator();
					while (iterator.hasNext()) {
						ShippingAddressBean shippingAddressBean = iterator.next();
					%>
					<%
					String postalCode = (String) shippingAddressBean.getPostalCode();
					String head = postalCode.substring(0, 3);
					String end = postalCode.substring(3);
					%>
					<div class="card me-2 mb-2" style="width: calc(50% - 8px); cursor: pointer;" ondblclick="location.href='editShippingAddress?shipping_address_id=<%= shippingAddressBean.getShippingAddressId() %>'">
						<div class="card-body">
						<div class="card-head d-flex justify-content-between">
							<%= numbers %>
							<% int mainStatus = shippingAddressBean.getMainShippingAddress();
									if (mainStatus == 1) {
							%>
								<small class="mb-0 px-3 py-1" style="color: #63A162; background-color:white; border: 0.5px #63A162 solid; border-radius:40px;">
								メイン配送先
								</small>
							<% } %>
							</div>
							<h5 class="card-title"><%= shippingAddressBean.getAddressee() %></h5>
							<h6 class="card-subtitle mb-2 text-body-secondary">
							〒 <%= head %>-<%= end %><br>
							<%= shippingAddressBean.getAddress() %>
							</h6>
							<%
							int main_address = shippingAddressBean.getMainShippingAddress();
							if (main_address != 1) {
							%>
							<div class="d-flex justify-content-end">
								<button type="submit" class="btn p-0" data-bs-toggle="modal"
									data-bs-target="#staticBackdrop" style="color: red;">
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
  <path fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
</svg>
								</button>
							</div>
							<!-- 配送先削除確認ボタン -->
							<div class="modal fade" id="staticBackdrop"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered w-100">
								<div class="modal-dialog" style="display: flex; width:100%;">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="staticBackdropLabel">
												<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-shield-exclamation" viewBox="0 0 16 16">
													<path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z"/>
													<path d="M7.001 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.553.553 0 0 1-1.1 0L7.1 4.995z"/>
												</svg>
											</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body p-5">
											この配送先を削除しますか？<br><br>
											<small>一度削除した配送先は復元できません。</small>
										</div>
										<div class="modal-footer">
											<a href="deleteShippingAddress?shipping_address_id=<%=shippingAddressBean.getShippingAddressId()%>"
												style="text-decoration: none;">
												<button type="submit" class="button-red px-2 py-1" style="border-radius:5px;">削除</button>
											</a>
										</div>
									</div>
								</div>
							</div>
							</div>
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
		</div>
	</main>
	<%@include file="../component/footer.jsp"%>
</body>
</html>
