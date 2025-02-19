<%@page import="java.util.ArrayList"%>
<%@page import="Movie_Modal.Movie"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        th {
            padding: 0px 20px 5px 0px;
        }
    </style>
</head>

<body>
<!-- nav bar -->
<jsp:include page="header.jsp"/>
<!-- end of navbar -->

<br><br><br>
<div class="container">
	<%
	int PhimID = Integer.parseInt(request.getParameter("PhimID"));
	int PhongID = Integer.parseInt(request.getParameter("RoomID"));
	String startDate =  request.getParameter("startDate");
	String startTime =  request.getParameter("startTime");
	String TenPhong = (String) request.getAttribute("TenPhong");
	String LichChieuID = "2";
	 LichChieuID = request.getParameter("LichChieuID");
	String[] seatsParam = request.getParameterValues("seats");
	
	Movie Phim = (Movie) request.getAttribute("Movie");
	
	List<Integer> seats = new ArrayList<>();
    for (String seat : seatsParam) {
        seats.add(Integer.parseInt(seat)); // Convert String thành Integer
    }
	%>
        <h2>Thanh toán hóa đơn</h2>
        <br>
        <div style="display:flex">
        <div style="margin-right:50px; height: 300px;">
                <img style="height: 100%;" src="<%=Phim.getPoster_url() %>" alt="">
            </div>
            <table>
                <tr>
                    <th><b>Tên Phim: </b></th>
                    <th><%=Phim.getTenPhim() %></th>
                </tr>
                
                <tr>
                    <th><b>Giờ Chiếu:</b></th>
                    <th><%=startTime%></th>
                </tr>
                <tr>
                    <th><b>Ngày Chiếu:</b></th>
                    <th><%=startDate%></th>
                </tr>
                <tr>
                    <th><b>Tên Phòng:</b></th>
                    <th><%=TenPhong%></th>
                </tr>
                <tr>
                    <th><b>Số Vé:</b></th>
                    <th><%=seatsParam.length %></th>
                </tr>
                <tr>
                    <th><b>Tiền Vé Đơn:</b></th>
                    <th>45.000đ</th>
                </tr>
                <tr>
                    <th><b>Tổng:</b></th>
                    <th><%=seatsParam.length*45000 %>đ</th>
                </tr>
            </table>       
            
        </div>
        <br>
        <form action="BankingController" method="get">
         	<input type="hidden" name="PhimID" value="<%=PhimID%>">
       		 <input type="hidden" name="RoomID" value="<%=PhongID%>">       	
       		 <input type="hidden" name="LichChieuID" value="<%=LichChieuID%>">
       		 <input type="hidden" name="seats" value="<%=seats%>">
       		 
       		 <button type="submit" class="btn btn-outline-danger btn-block">Thanh Toán</button>
        </form>
        
</div>
<br>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>

</html>