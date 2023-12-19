<%@page import="classes.ShippingAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@page import="java.sql.Date"%>
<%@ page import="java.util.ArrayList, bean.ShippingAddressBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
  crossorigin="anonymous">
<title>doko</title>
</head>
<body class="container">
  <main>
 <%
    ShippingAddressBean shippingAddressEdit = (ShippingAddressBean) request.getAttribute("shippingAddressEdit");
    %>
    <form action="editShippingAddress" method="post"
      style="display: flex; justify-content: center; margin: 30px;">
      <div class="col-lg-5 m-5"
        style="border: 1px solid #333333; padding: 65px;">
        <div class="cancelButton"
          style="display: flex; justify-content: space-between; margin-bottom: 20px;">
          <h2>
            <strong>配送先編集</strong>
          </h2>
          <div>
            <a href="shippingAddressIndex"
              style="text-decoration: none; text-align: center;"><button
                type="button" class="btn-close border" aria-label="Close"></button>️</a>
          </div>
        </div>
        <div style="margin-bottom: 10px;">
          <label for="exampleInputAddresses">宛名</label><br>
        </div>
        <div class="form-group"
          style="display: flex; justify-content: center; margin-bottom: 30px;">
          <input type="text" class="form-control" id="exampleInputAddresses"
            aria-describedby="addresses" name="addressee" value="<%=shippingAddressEdit.getAddressee() %>">
        </div>
        <div style="margin-bottom: 10px;">
          <label for="exampleInputPostalCode">郵便番号</label><br>
        </div>
        <div class="form-group"
          style="display: flex; justify-content: center; margin-bottom: 30px;">
          <input type="text" class="form-control" id="exampleInputPostalCode"
            aria-describedby="postalcode" maxlength="8" name="postalcode" value="<%=shippingAddressEdit.getPostalCode() %>">
        </div>
        <div style="margin-bottom: 10px;">
          <label for="exampleInputAddress">住所</label><br>
        </div>
        <div class="form-group"
          style="display: flex; justify-content: center; margin-bottom: 30px;">
          <input type="text" class="form-control" id="exampleInputAddress"
            aria-describedby="address" name="address" value="<%=shippingAddressEdit.getAddress() %>">
        </div>
        <div class="cancelButton"
          style="display: flex; justify-content: center; margin-bottom: 20px;">
          <button type="submit" class="btn btn-lg w-100"
            style="border: 1px solid #000000; background: #9933FF; color:#FFFFFF; padding: 10px;">更新</button
        </div>
    </form>
    </div>
  </main>
</body>
</html>