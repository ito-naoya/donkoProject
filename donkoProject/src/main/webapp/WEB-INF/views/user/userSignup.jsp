<%@page import="controller.user.UserSigninServlet"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, classes.user.CustomerUser"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
        crossorigin="anonymous">
    <title>ユーザー新規登録画面</title>
</head>
<body class="container">
    <main>
        <%
        UserSigninServlet userSignin = (UserSigninServlet) request.getAttribute("userSignin");
        %>
        <div style="text-align: center; margin-bottom: 20px;">
            <h1>DONKO</h1>
        </div>
        <form action="UserSigninServlet" method="post"
            style="display: flex; justify-content: center; margin: 30px;">
            <div class="col-lg-5 m-5"
                style="border: 1px solid #333333; padding: 65px;">
                <div class="cancelButton"
                    style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                    <h2>
                        <strong>新規登録画面</strong>
                    </h2>
                </div>
                
                //ユーザーID入力欄
                <div style="margin-bottom: 10px;">
                    <label for="exampleInputUserId">ユーザーID</label><br>
                </div>
                <div class="form-group"
                    style="display: flex; justify-content: center; margin-bottom: 30px;">
                    <input type="text" class="form-control" id="exampleInputUserId"
                        aria-describedby="userId" value="<%=users.getUserLoginId()%>">
                </div>

                //ユーザー名入力欄
                <div style="margin-bottom: 10px;">
                    <label for="exampleInputUserName">ユーザー名</label><br>
                </div>
                <div class="form-group"
                    style="display: flex; justify-content: center; margin-bottom: 30px;">
                    <input type="text" class="form-control" id="exampleInputUserName"
                        aria-describedby="userName" value="">
                </div>
                
                パスワード入力欄
                <div style="margin-bottom: 10px;">
                    <label for="exampleInputPassword">パスワード</label><br>
                </div>
                <div class="form-group"
                    style="display: flex; justify-content: center; margin-bottom: 30px;">
                    <input type="password" class="form-control" id="exampleInputPassword"
                        maxlength="8" value="">
                </div>
                <div class="cancelButton"
                    style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                    <div>
                       
                        //ログイン画面へのボタン
                        <a href="login"
                            style="text-decoration: none; text-align: center;"><button
                                type="button" class="btn btn-primary" aria-label="Login">ログイン画面へ</button></a>
                    </div>
                    <a href="registration"
                        style="text-decoration: none; text-align: center;"><button
                            type="button" class="btn btn-primary" aria-label="Back">新規登録画面へ戻る</button></a>
                </div>
            </div>
        </form>
    </main>
</body>
</html>
