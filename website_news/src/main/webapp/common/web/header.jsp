<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Trang chủ
                <span class="sr-only">(current)</span>
              </a>
            </li>
   <!--Tức là khi login thì UserModel sẽ đc set cho session cùng request 1 UserModel có mk, tk do user truyền vào-->
   <!--TH1:Nếu UserModel rỗng  vào(tức là chưa đăng nhập) chỉ có thẻ <a> Đăng ký, Đăng nhập-->     
   <!--TH2:Nếu UserModel đc truyền  vào(tức là đã đăng nhập) thì sẽ xoá thẻ <a> Đăng ký, Đăng nhập và chỉ có thẻ Thoát -->
            <c:if test= "${empty sessionScope.fullName}"> 
            	<li class="nav-item">
       <!-- khi click vào thẻ a này thì c:url sẽ gửi request đến servlet tên "/dang-nhap" cùng vs tham số
         và servlet sẽ chuyển hướng request đến 1 trang jsp khác.  -->
                	<a class="nav-link" href='<c:url value ="/dang-nhap?action=login"/>'>Đăng nhập</a>
             	 </li>
             </c:if>
            <c:if test= "${not empty sessionScope.fullName}"> <!--USERMODEL từ session-->
           		 <li class="nav-item">
                	<a class="nav-link" href='#'>Welcome ${sessionScope.fullName}</a>
             	 </li> 
            	<li class="nav-item">           		
                	<a class="nav-link" href='<c:url value ="/thoat?action=logout" />'>Thoát</a>
             	 </li>
             </c:if>             
          </ul>
        </div>
      </div>
</nav>