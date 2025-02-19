<%@page import="User_Modal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="top-bar">
 		<%
 		User u = new User();
 		u=(User)session.getAttribute("dn");
 		if(u!=null){ %>
 		<button class="login-btn">Xin chào <%=u.getHoten() %></button>
 		<form action="LogoutController" method="get">
 		<button class="logout-btn">Logout</button>
 		</form>
 		<%}else{ %>
        <a href="LogoutController" class="login-btn">Login</a>
        <%} %>
 </div>