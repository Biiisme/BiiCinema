<%@page import="Movie_Modal.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
    <h1>Chi Tiết Phim</h1>
    <br>
    <%Movie temp = new Movie();
    	temp =(Movie)request.getAttribute("ChiTietPhim");
    %>
    <div style="display:flex">
        <div style="margin-right:50px">
            <img src="<%=temp.getPoster_url() %>" alt="">
                       
            <a href="RoomPageController?movieId=<%=temp.getPhim_ID()%>" class="btn btn-danger btn-block d-grid">Mua Vé</a>    
       
        </div>
        <table>
            <tr>
                <th><b>Tên Phim: </b></th>
                <th><%=temp.getTenPhim() %></th>
            </tr>
            <tr>
                <th><b>Đạo Diễn:</b></th>
                <th><%=temp.getDaoDien() %></th>
            </tr>
            <tr>
                <th><b>Diễn Viên:</b></th>
                <th><%=temp.getDienVien() %></th>
            </tr>
            <tr>
                <th><b>Ngày Khởi Chiếu:</b></th>
                <th>12/06/2022</th>
            </tr>
            <tr>
                <th><b>Thể Loại:</b></th>
                <th><%=temp.getLoai() %></th>
            </tr>
            <tr>
                <th><b>Thời Lượng:</b></th>
                <th><%=temp.getThoiLuong() %> phút</th>
            </tr>
            <tr>
                <th><b>Đối Tượng:</b></th>
                <th><%=temp.getDoiTuong() %></th>
            </tr>

        </table>


    </div>
    <br>
    <br>
    <div style="display:flex;">
        <div style="margin-right: 50px">
            <h1>Trailer:</h1>
            <iframe width="560" height="315" src="<%= temp.getTrailer_url() %>"
                    title="YouTube video player" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; gyroscope; picture-in-picture"
                    allowfullscreen>
            </iframe>
        </div>
        <div>
            <h1>Giới Thiệu:</h1>
            <p><%=temp.getMoTaDai() %></p>
        </div>
    </div>
    <br>

    <br>
    <br>
</div>
<br>
<br>
</body>
</html>