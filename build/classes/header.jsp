<%@page import="User_Modal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="trangChuController">BIICINEMA</a>
			</div>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">	
					<li class="nav-item"><a class="nav-link" href="HomePageController">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="HistoryController">Lịch sử mua vé</a></li>
					<li class="nav-item"><a class="nav-link" href="xacNhanController">Liên hệ</a></li>
					<li class="nav-item"><a class="nav-link" href="xacNhanController">Cá nhân</a></li>
				</ul>
				<ul class="navbar-nav ms-auto">
	
					<%
			 		User u = new User();
			 		u=(User)session.getAttribute("dn");
			 		if(u!=null){ %>
					<li class="nav-item" type="button"  data-bs-toggle="modal" data-bs-target="#myModal"><a href="#" class="nav-link"><span
							class="glyphicon glyphicon-user"></span>Xin chào <%=u.getHoten() %></a></li>
					
					<li class="nav-item"><a  href="LogoutController" class="nav-link"><span
							class="glyphicon glyphicon-log-in"></span>Đăng xuất</a></li>
					<%}else{ %>
					<li class="nav-item" type="button"  data-bs-toggle="modal" data-bs-target="#myModal"><a href="LoginController?login=login" class="nav-link"><span
							class="glyphicon glyphicon-user"></span>Đăng nhập</a></li>
					<%} %>
				</ul>
			</div>
		</div>
	</nav>
	
	
	