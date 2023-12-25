<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.ShippingAddressBean"%>
<%-- <%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<style>
.link:hover{opacity: 0.7;}
.link2:hover{opacity: 0.8;}
</style>
<title>donko</title>
</head>
<body>
	<%
	Object userId = session.getAttribute("user_id");
	ShippingAddressBean mainShippingAddress = (ShippingAddressBean)request.getAttribute("mainShippingAddress");
	Object cartItemNum = request.getAttribute("cartItemNum");
    String postalCode = (String) mainShippingAddress.getPostalCode();
    String head = postalCode.substring(0, 3);
    String end = postalCode.substring(3);
    %>
	<div id="top"></div>
	<header class="fixed-top" style="background-color:white;">
    	<div style="height:54px; background-color:#385A37; position:relative; top: 0; left: 0; z-index:2; display:flex; align-items:center;">
    		<ul class="d-flex mb-0 p-0 w-100">
    			<li class="mx-3" style="list-style:none; display: flex; align-items: center;">
	    			<a href="home" class="link" style="text-decoration:none;">
	    				<img src="./images/donkoLogo.png" style="height:50px;">
	    			</a>
    			</li>
    			<% if (mainShippingAddress != null) { %>
    			<li class="mx-3" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<small>〒 <%= head %>-<%= end %><br><%= mainShippingAddress.getAddress() %></small>
    			</li>
    			<% 
    			} 
    			%>
    			<% 
    			if (userId == null) {
    			%>
    			<li class="ms-auto me-3" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<a href="userSignup" class="link2 btn btn-sm px-4" style="color:white; background-color:#9933FF; border-radius:40px;">
    					新規登録
    				</a>
    			</li>
    			<li class="ms-3 me-4" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<a href="userSignin" class="link" style="color:white; text-decoration:none;">
    					<small>ログイン</small>
    				</a>
    			</li>
    			<% 
    			} else {
    			%>
    			<li class="ms-auto me-3" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
    				<a href="myPage" class="link" style="color:white; text-decoration:none;">
    					<small>購入履歴</small>
    				</a>
    			</li>
				<li class="ms-3 me-4" style="list-style:none; color:white; display: flex; align-items: center; vertical-align: middle;">
	    			<a href="cart" class="link" style="color:white; text-decoration:none;" class="position-relative">
						  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart3 me-1" viewBox="0 0 16 16">
							<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
						  </svg>
						  <small>カート</small>
						  <span class="badge ms-1" style="background-color:#333;"><%= cartItemNum %></span>
    				</a>
    			</li>
    			<% 
    			}
    			%>
    		</ul>
    	</div>
	    <nav style="height:40px; background-color:#D5E8D4; display:flex; align-items:center;">
	    	<ul class="d-flex mb-0 p-0 w-100">
	    		<li class="mx-3" style="list-style:none; display: flex; align-items: center;">
	    			<button class="link btn" style="border: none; outline: none; background: transparent;" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">
   						<strong>
   						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
						</svg>
   						<small>カテゴリーで探す</small>
   						</strong>
   					</button>
   					<div class="offcanvas offcanvas-start" data-bs-scroll="true"
					data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling"
					aria-labelledby="offcanvasScrollingLabel"
					style="background-color:#63A162; opacity: 0.96;">
						<div class="offcanvas-header" style="border-bottom:1px white solid;">
							<h5 class="offcanvas-title ms-3 mt-3 text-light" id="offcanvasScrollingLabel">
								<strong>カテゴリー一覧</strong>
							</h5>
							<button type="button" class="btn-close me-2"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<div class="offcanvas-body">
							<ul class="mb-0 p-0 mx-auto">
								<li class="link2 m-3" style="list-style: none; z-index: 3;"><a
									href="category?categoryName=衣類" style=" text-decoration:none; color:white;">衣類</a></li>
								<li class="link2 m-3" style="list-style: none; z-index: 3;"><a
									href="category?categoryName=靴" style="text-decoration:none; color:white;">靴</a></li>
								<li class="link2 m-3" style="list-style: none; z-index: 3;"><a
									href="category?categoryName=携帯" style=" text-decoration:none; color:white;">携帯</a></li>
								<li class="link2 m-3" style="list-style: none; z-index: 3;"><a
									href="category?categoryName=本" style="text-decoration:none; color:white;">本</a></li>
								<li class="link2 m-3" style="list-style: none; z-index: 3;"><a
									href="category?categoryName=食品" style="text-decoration:none; color:white;">食品</a></li>
							</ul>
						</div>
					</div>
	    		</li>
	    		<li class="ms-auto me-3" style="list-style:none; display: flex; align-items: center;">
	    			<small class="link ms-1">お知らせ</small>
	    		</li>
	    		<li class="link mx-3" style="list-style:none; display: flex; align-items: center;">
	    			<small>利用ガイド</small>
	    		</li>
	    	</ul>
	    </nav>
    </header>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>