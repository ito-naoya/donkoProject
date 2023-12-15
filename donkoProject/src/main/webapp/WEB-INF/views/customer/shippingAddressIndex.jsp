<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ page import="java.util.ArrayList, bean.ShippingAddressBean" %>
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
    <a href="myPage" class="mb-3" style="display: inline-block">
      <div class="border text-center" style="width: 50px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
          fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd"
            d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"></path>
      </svg>
      </div>
    </a>
    <div class="AddressChoice"
      style="display: flex; justify-content:end;">
      <!-- TODO:サーブレットができたら変える -->
      <from action="" method="post">
      <select class="form-control"style="width:400px;"  >
        <option hidden>デフォルト住所選択 修正予定</option>
        <option value="住所A">大阪府守口市あいうえお町z-z-z</option>
        <option value="住所B">京都府京都市伏見区かきくけこ町y-y</option>
        <option value="住所C">愛知県名古屋市北区さしすせそ町x-x-x</option>
      </select>
      </from>
      <!--  -->
      <div class="update d-flex text-nowrap ms-3" >
        <button class="btn" type="submit" style="border: 1px solid #000000; background: #E5CCFF;">更新</button>
      </div>
    </div>
    <h2 style="margin-top: 30px; margin-bottom: 20px;"><strong>配送先一覧</strong></h2>
    <table class="table table-borderless">
      <tbody>
        <% ArrayList<ShippingAddressBean> shippingAddressList =
          (ArrayList<ShippingAddressBean>)request.getAttribute("shippingAddressList");%>
           <% for (ShippingAddressBean shippingAddressBean : shippingAddressList) { %>
        <tr>
          <!-- 配送先ID -->
            <td><%= shippingAddressBean.getShippingAddressId()%></a> 
          </td>
          <!-- 郵便番号 -->
            <td><%= shippingAddressBean.getPostalCode()%></td>
          <!-- 住所 -->
        <td><%= shippingAddressBean.getAddress()%></td>
          <!-- 宛名 -->
            <td><%= shippingAddressBean.getAddressee()%></td>
          <!-- 更新ボタン -->
          <td>
           <button type="submit"class="btn" style="border: 1px solid #000000; background:   #E5CCFF;">
            <a href="editShippingAddress?=<%= shippingAddressBean.getShippingAddressId() %>"
              style="color: #000000; vertical-align: middle; text-decoration: none;">編集</a> 
           </button>
          </td>
          </tr>
          <% } %>
    </table>
  </main>
  <%@include file="../component/footer.jsp"%>
</body>
</html>
