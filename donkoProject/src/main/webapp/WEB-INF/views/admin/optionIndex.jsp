<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="javax.management.monitor.CounterMonitor"%>
<%@page import="java.util.ArrayList, bean.OptionCategoryBean, java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
			crossorigin="anonymous">
		<link rel="stylesheet"  href="./css/optionIndex.css">
	</head>
	<body>
		<main>
			<div class="container">
				<div class="row" style="height:100vh;">
					<div class="border m-auto p-5" style="border-radius:10px; box-shadow:10px 10px 10px lightgray;">
						<div class="row d-flex justify-content-center mb-3">
							<!-- 戻るボタン -->
							<a href="adminTopPage" class="arrow my-3 link" style="display: inline-block; color:navy;">
								<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-arrow-left-square" viewBox="0 0 16 16">
								  <path fill-rule="evenodd" d="M15 2a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2zM0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm11.5 5.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
								</svg>
							</a>
							
							<h5 class="mb-0 mx-3"><strong>カテゴリ編集</strong></h5>
						    <small class="mx-3" style="vertical-align: middle;">
						  		登録中の商品に紐づくカテゴリは削除できません
						    </small>
						    
						    <!-- カテゴリ追加 -->
							<!-- モーダルボタン -->
							<button type="button" class="btn px-3 m-3" 
									data-bs-toggle="modal" data-bs-target="#modalOptionAdd" 
									style="background-color: #E5CCFF; color: black; border-radius: 2rem;">
								オプションを追加
							</button>
						</div>
						
						<!-- メッセージ -->
						<%
						String message = (String)request.getAttribute("message");
						if (message != null){
						%>
							<div class="alert alert-warning" role="alert"> <%= message %></div>
						<% 
						} 
						%>
					
						<div class="row">
							<div class="col-lg-6">
							<%
							String optionCategoryName = (String) request.getAttribute("optionCategoryName");
							ArrayList<OptionCategoryBean> optionList = (ArrayList<OptionCategoryBean>)request.getAttribute("optionList");
							if(optionList != null || optionList.size() > 0){
							%>
								<div class="overflow-auto" style="height: 55vh;">
									<table class="table table-borderless st-tbl1" id="itemTable" style="padding: 10px">
									<thead>
										<tr>
											<th scope="col">ID</th>
											<th scope="col">オプション</th>
											<th scope="col">削除</th>
										</tr>
									</thead>
									<tbody>
											<%
											int counter = 1;
											for (OptionCategoryBean option : optionList) {
											%>
										    <tr style="cursor: pointer;" onclick="location.href='optionIndex?optionCategoryName=<%= option.getOptionCategoryName() %>'">
										        <!-- ID -->
										        <td><%= counter  %></td>
										        <td><!-- オプション名 -->
										            <p><%= option.getOptionCategoryName() %></p>
										        </td>
										        <td><!-- 削除ボタン -->
										            <p>
										                <button type="button" class="btn btn-outline-danger"
										                        onclick="event.stopPropagation(); location.href='editOption?optionCategoryName=<%= option.getOptionCategoryName() %>'">
										                    削除
										                </button>
										            </p>
										        </td>
										    </tr>
										<%
										counter++;
										}
										%>
									</tbody>
								</table>
								</div>
							</div>
							<% } %>
							<div class="col-lg-5">
							<%
							ArrayList<OptionCategoryBean> optionValueList = (ArrayList<OptionCategoryBean>)request.getAttribute("optionValueList");
							if(optionCategoryName == null){
							%>
								<div class="" style="display:block;">オプションをクリックすると詳細が表示されます</div>
							<%}else if(optionValueList.size() == 0){ %>
								<div class="" style="display:block;">必ず一つ以上のオプション詳細を登録してください</div>
								<div class="d-grid gap-2 col-6 mx-auto">
									    <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#modalOptionAdd2" >
									    	<svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-plus-circle-dotted" viewBox="0 0 16 16">
									            <path d="M8 0c-.176 0-.35.006-.523.017l.064.998a7.117 7.117 0 0 1 .918 0l.064-.998A8.113 8.113 0 0 0 8 0zM6.44.152c-.346.069-.684.16-1.012.27l.321.948c.287-.098.582-.177.884-.237L6.44.153zm4.132.271a7.946 7.946 0 0 0-1.011-.27l-.194.98c.302.06.597.14.884.237l.321-.947zm1.873.925a8 8 0 0 0-.906-.524l-.443.896c.275.136.54.29.793.459l.556-.831zM4.46.824c-.314.155-.616.33-.905.524l.556.83a7.07 7.07 0 0 1 .793-.458L4.46.824zM2.725 1.985c-.262.23-.51.478-.74.74l.752.66c.202-.23.418-.446.648-.648l-.66-.752zm11.29.74a8.058 8.058 0 0 0-.74-.74l-.66.752c.23.202.447.418.648.648l.752-.66zm1.161 1.735a7.98 7.98 0 0 0-.524-.905l-.83.556c.169.253.322.518.458.793l.896-.443zM1.348 3.555c-.194.289-.37.591-.524.906l.896.443c.136-.275.29-.54.459-.793l-.831-.556zM.423 5.428a7.945 7.945 0 0 0-.27 1.011l.98.194c.06-.302.14-.597.237-.884l-.947-.321zM15.848 6.44a7.943 7.943 0 0 0-.27-1.012l-.948.321c.098.287.177.582.237.884l.98-.194zM.017 7.477a8.113 8.113 0 0 0 0 1.046l.998-.064a7.117 7.117 0 0 1 0-.918l-.998-.064zM16 8a8.1 8.1 0 0 0-.017-.523l-.998.064a7.11 7.11 0 0 1 0 .918l.998.064A8.1 8.1 0 0 0 16 8zM.152 9.56c.069.346.16.684.27 1.012l.948-.321a6.944 6.944 0 0 1-.237-.884l-.98.194zm15.425 1.012c.112-.328.202-.666.27-1.011l-.98-.194c-.06.302-.14.597-.237.884l.947.321zM.824 11.54a8 8 0 0 0 .524.905l.83-.556a6.999 6.999 0 0 1-.458-.793l-.896.443zm13.828.905c.194-.289.37-.591.524-.906l-.896-.443c-.136.275-.29.54-.459.793l.831.556zm-12.667.83c.23.262.478.51.74.74l.66-.752a7.047 7.047 0 0 1-.648-.648l-.752.66zm11.29.74c.262-.23.51-.478.74-.74l-.752-.66c-.201.23-.418.447-.648.648l.66.752zm-1.735 1.161c.314-.155.616-.33.905-.524l-.556-.83a7.07 7.07 0 0 1-.793.458l.443.896zm-7.985-.524c.289.194.591.37.906.524l.443-.896a6.998 6.998 0 0 1-.793-.459l-.556.831zm1.873.925c.328.112.666.202 1.011.27l.194-.98a6.953 6.953 0 0 1-.884-.237l-.321.947zm4.132.271a7.944 7.944 0 0 0 1.012-.27l-.321-.948a6.954 6.954 0 0 1-.884.237l.194.98zm-2.083.135a8.1 8.1 0 0 0 1.046 0l-.064-.998a7.11 7.11 0 0 1-.918 0l-.064.998zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
									        </svg>
									    	オプション詳細を追加
										</button>
									</div>
							<%}else{ %>
								<div class="overflow-auto" style="height: calc(80vh - 200px);">
								<table class="table table-borderless st-tbl1" id="itemTable" style="padding: 10px">
									<thead>
									    <tr>
									      <th scope="col">
									      	#
									      </th>
									      <th scope="col">
									      	オプション詳細：<%= optionCategoryName %>
									      </th>
									      <th scope="col">
									      	削除
									      </th>
									    </tr>
									</thead>
									<tbody>
										<%
										int counter2 = 1;
											for(OptionCategoryBean optionValue : optionValueList){
										%>
										<tr>
											<td>
													<%= counter2  %>
											<td>
													<%= optionValue.getOptionCategoryValue() %>
											</td>
											<td>
												<form action="editOptionDeteal" method="get">
									                <input type="hidden" name="optionCategoryName" value="<%= optionCategoryName %>">
									                <input type="hidden" name="optionCategoryValue" value="<%= optionValue.getOptionCategoryValue() %>">
									                <button type="submit" class="btn btn-outline-danger">削除</button>
									            </form>
											</td>
										</tr>
										<%
										counter2++;
										}
										%>
									</tbody>
								</table>
									<div class="d-grid gap-2 col-6 mx-auto">
									    <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#modalOptionAdd2" >
									    	<svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-plus-circle-dotted" viewBox="0 0 16 16">
									            <path d="M8 0c-.176 0-.35.006-.523.017l.064.998a7.117 7.117 0 0 1 .918 0l.064-.998A8.113 8.113 0 0 0 8 0zM6.44.152c-.346.069-.684.16-1.012.27l.321.948c.287-.098.582-.177.884-.237L6.44.153zm4.132.271a7.946 7.946 0 0 0-1.011-.27l-.194.98c.302.06.597.14.884.237l.321-.947zm1.873.925a8 8 0 0 0-.906-.524l-.443.896c.275.136.54.29.793.459l.556-.831zM4.46.824c-.314.155-.616.33-.905.524l.556.83a7.07 7.07 0 0 1 .793-.458L4.46.824zM2.725 1.985c-.262.23-.51.478-.74.74l.752.66c.202-.23.418-.446.648-.648l-.66-.752zm11.29.74a8.058 8.058 0 0 0-.74-.74l-.66.752c.23.202.447.418.648.648l.752-.66zm1.161 1.735a7.98 7.98 0 0 0-.524-.905l-.83.556c.169.253.322.518.458.793l.896-.443zM1.348 3.555c-.194.289-.37.591-.524.906l.896.443c.136-.275.29-.54.459-.793l-.831-.556zM.423 5.428a7.945 7.945 0 0 0-.27 1.011l.98.194c.06-.302.14-.597.237-.884l-.947-.321zM15.848 6.44a7.943 7.943 0 0 0-.27-1.012l-.948.321c.098.287.177.582.237.884l.98-.194zM.017 7.477a8.113 8.113 0 0 0 0 1.046l.998-.064a7.117 7.117 0 0 1 0-.918l-.998-.064zM16 8a8.1 8.1 0 0 0-.017-.523l-.998.064a7.11 7.11 0 0 1 0 .918l.998.064A8.1 8.1 0 0 0 16 8zM.152 9.56c.069.346.16.684.27 1.012l.948-.321a6.944 6.944 0 0 1-.237-.884l-.98.194zm15.425 1.012c.112-.328.202-.666.27-1.011l-.98-.194c-.06.302-.14.597-.237.884l.947.321zM.824 11.54a8 8 0 0 0 .524.905l.83-.556a6.999 6.999 0 0 1-.458-.793l-.896.443zm13.828.905c.194-.289.37-.591.524-.906l-.896-.443c-.136.275-.29.54-.459.793l.831.556zm-12.667.83c.23.262.478.51.74.74l.66-.752a7.047 7.047 0 0 1-.648-.648l-.752.66zm11.29.74c.262-.23.51-.478.74-.74l-.752-.66c-.201.23-.418.447-.648.648l.66.752zm-1.735 1.161c.314-.155.616-.33.905-.524l-.556-.83a7.07 7.07 0 0 1-.793.458l.443.896zm-7.985-.524c.289.194.591.37.906.524l.443-.896a6.998 6.998 0 0 1-.793-.459l-.556.831zm1.873.925c.328.112.666.202 1.011.27l.194-.98a6.953 6.953 0 0 1-.884-.237l-.321.947zm4.132.271a7.944 7.944 0 0 0 1.012-.27l-.321-.948a6.954 6.954 0 0 1-.884.237l.194.98zm-2.083.135a8.1 8.1 0 0 0 1.046 0l-.064-.998a7.11 7.11 0 0 1-.918 0l-.064.998zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
									        </svg>
									    	オプション詳細を追加
										</button>
									</div>
									<% } %>
								</div>
							</div>
						</div>
					</div>

					<!-- ポップアップ１：オプションを追加 -->
					<div class="modal fade" id="modalOptionAdd" tabindex="-1"
						aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered"
							style="max-width: 25%;">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">オプションを追加</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<!-- フォーム内容 -->
									<form action="editOption" method="post">
										<label for="itemName" class="form-label"
											style="font-size: small; color: grey;">20文字以内</label> <input
											type="text" class="form-control" id="newOptionName"
											name="newOptionName" maxlength="20" required value="">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">キャンセル</button>
									<button type="submit" class="btn btn-success">追加</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- ポップアップ２：オプション詳細を追加 -->
					<div class="modal fade" id="modalOptionAdd2" tabindex="-1"
						aria-labelledby="modalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered"
							style="max-width: 25%;">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="modalLabel">オプション詳細を追加：</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<!-- フォーム内容 -->
									<form action="editOptionDeteal" method="post">
										<label for="newOptionValue" class="form-label"
											style="font-size: small; color: grey;">20文字以内</label> <input
											type="text" class="form-control" id="newOptionValue"
											name="newOptionValue" maxlength="20" required value="">
										<input type="hidden" name="currentOptionCategoryName"
											value="<%=optionCategoryName%>">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">キャンセル</button>
									<button type="submit" class="btn btn-success">追加</button>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<script src="./js/optionScript.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
	</body>
</html>