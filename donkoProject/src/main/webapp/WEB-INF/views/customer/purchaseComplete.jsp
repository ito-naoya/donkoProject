<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			donko
		</title>
	</head>
	<body>
		<%@include file="../component/header.jsp"%>
		<%@include file="../component/headerTopSpace.jsp"%>
		<main>
			<div class="mx-auto mt-5 w-50 d-flex flex-column align-items-start justify-content-center border border-2" style="height:auto; padding: 100px">
				<h3 style="color:#63A162;">
					<strong>
						<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
						  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
						  <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
						</svg>
						注文が完了しました！
					</strong>
				</h3>
				<small>ご購入ありがとうございます。<br>商品の発送までしばらくお待ちください。</small>
				<a  href="myPage" style="color: #385a37">
					<p class="mt-5" >
						購入履歴を確認する
					</p>
				</a>
				<a  href="home" style="color: #385a37">
					<p class="mt-2" >
						ホームへ戻る
					</p>
				</a>
			</div>
		</main>
		<%@include file="../component/footer.jsp"%>
	</body>
</html>