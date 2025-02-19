<%@page import="Room_Modal.Room"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<body>
	<!-- nav bar -->
	<jsp:include page="header.jsp"/>
	<!-- end of navbar -->
	
	<!-- movie selections-->
	
	
	<br>
	<%	
		ArrayList<Room> dsr ;
	    dsr= (ArrayList<Room>)request.getAttribute("dsRoom");
		
	%>
	<h2 class="container">Chọn Phòng</h2>
	
	<div class="d-flex justify-content-between flex-wrap container">
	    <%for(Room r : dsr) {%>
	    <div class="card branch-item" style="width:500px; margin-bottom:50px">
	        <img class="card-img-top img-branch"
	             src="images/Room.jpg"
	             alt="Card image" style="width:100%">
	        <div class="card-body">
	            <h4 class="card-title"><%=r.getTenphong() %> </h4>
	            <p class="card-text">Sức chứa: 40 người </p>
	            <a href="SchedulePageController?movieId=<%=request.getAttribute("PhimID")%>&roomID=<%=r.getRoom_id()%>"
	               class="btn btn-outline-danger btn-block">Chọn</a>
	        </div>
	    </div>
	    <%} %>
	
	</div>
	</div>
	</div>
	<!-- end of movie selections -->

<jsp:include page="footer.jsp"/>
</body>
</html>