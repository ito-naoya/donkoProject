<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="container">
			<div class="row my-5">
			
				<div class="col-lg-8 d-flex justify-content-center m-auto">
					<div class="d-flex flex-column">
						<div class="mb-5 mx-auto" style="width:70%;">
							<h5><strong>アカウントの解約について</strong></h5>
							<p>以下のサービスが受けられなくなります。</p>
							<div class="d-flex flex-wrap justify-content-center">
								<div class="border m-4 p-4 d-flex flex-column align-items-center justify-content-center text-center" style="width:calc(50% - 48px); aspect-ratio: 1 / 1; border-radius:5px;">
									<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
										fill="currentColor" class="bi bi-basket" viewBox="0 0 16 16" style="color:#385A37;">
									  <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z" />
									</svg>
									<small class="mt-3" style="color:gray;">商品購入</small>
								</div>
								<div class="border m-4 p-4 d-flex flex-column align-items-center justify-content-center text-center" style="width:calc(50% - 48px); aspect-ratio: 1 / 1; border-radius:5px;">
									<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16" style="color:#385A37;">
										<path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0l3-3z"/>
										<path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
									</svg>
									<small class="mt-3" style="color:gray;">購入履歴の閲覧</small>
								</div>
								<div class="border m-4 p-4 d-flex flex-column align-items-center justify-content-center text-center" style="width:calc(50% - 48px); aspect-ratio: 1 / 1; border-radius:5px;">
									<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16" style="color:#385A37;">
										<path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
									</svg>
									<small class="mt-3" style="color:gray;">配送先登録・編集</small>
								</div>
								<div class="border m-4 p-4 d-flex flex-column align-items-center justify-content-center text-center" style="width:calc(50% - 48px); aspect-ratio: 1 / 1; border-radius:5px;">
									<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-journal-text" viewBox="0 0 16 16" style="color:#385A37;">
										<path d="M5 10.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
										<path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
										<path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
									</svg>
									<small class="mt-3" style="color:gray;">配送先の閲覧</small>
								</div>
							</div>
						</div>
						<!-- モーダルボタン -->
						<div class="mx-auto" style="width: 50%;" class="link">
							<button type="button" class="btn text-nowrap w-100" 
									data-bs-toggle="modal" data-bs-target="#staticBackdrop"
									style="border: 1px solid #FF0000; color: #FF0000; border-radius:40px;">
							 		アカウントの解約
							</button>
						</div>
						<!-- モーダルウィンドウ -->
						<div class="modal fade" id="staticBackdrop"
							data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
							aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered w-100">
								<div class="modal-content" id="modalWindow" style="width:100%;">
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
										本当に解約してよろしいですか？<br><br>
										<small>
											アカウント解約後は現在のアカウントでログインできません。<br>
											一度解約されたアカウントの復元は不可能です。
										</small>
									</div>
									<div class="modal-footer">
										<form action="deleteUser" method="post">
											<button type="submit" class="btn link" style="border: 1px solid #FF0000; color: #FF0000; border-radius:40px;">解約する</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</main>
	<%@include file= "../component/footer.jsp" %>
</body>
</html>