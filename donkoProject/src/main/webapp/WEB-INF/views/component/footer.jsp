<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donko</title>
<style type="text/css">
.wrapper{
    min-height: 100vh;
    position: relative;
    padding-bottom: 120px;/*footerの高さ*/
    box-sizing: border-box;
}
</style>
</head>
<body class="wrapper">
	<footer style="position: absolute; bottom: 0; width:100%;">
		<span id="back">
		    <a href="#top"  style="text-decoration:none;">
		      <div class="d-flex justify-content-center border mt-5" 
		           style="width:100%; height:50px; line-height:50px; background-color:#D5E8D4;">
					<small class="mb-0 text-dark ">ページのトップへ戻る
						<svg
							xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-arrow-up-circle"
							viewBox="0 0 16 16">
 							 <path fill-rule="evenodd"
								d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z" />
						</svg>
					</small>
				</div>
		    </a>
		</span>
		<div class="text-center" style="height:70px; background-color:#63A162; width:100%; line-height:70px;">
			<p class="mb-0" style="color:white;">© donko All Rights Reserved.</p>
		</div>
	</footer>
</body>
</html>