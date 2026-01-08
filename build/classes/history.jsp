<%@page import="History_Modal.history"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh Toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        td{
            text-align:center
        }
    </style>
</head>

<body>
<!-- nav bar -->
<jsp:include page="header.jsp"/>
<!-- end of navbar -->

<br><br><br>
<div class="container-fluid">
    <h2>Lịch Sử Mua Vé </h2>
    <br>
    <div>
        <table style="width:100%">
            <tr>
                <th>Tên Phim</th>
               
                <th>Giờ Chiếu</th>
                <th>Tên Phòng</th>
                <th>Ghế</th>
                <th>Ngày Mua</th>
                <th>Mã QR</th>
            </tr>
            <%
            ArrayList<history> ds = new ArrayList<history>();
			ds =(ArrayList<history>)request.getAttribute("ds");
			int m = ds.size();
			for(int i = 0;i<m;i++){ 
            %>
                <tr>
                    <td ><%=ds.get(i).getTenPhim() %></td>
                
                    <td><%=ds.get(i).getGioChieu() %> <%=ds.get(i).getNgayChieu() %></td>
                    <td><%=ds.get(i).getTenPhong()%></td>
                    <td><%=ds.get(i).getTenGhe() %></td>
                    <td><%=ds.get(i).getNgayMua() %></td>
                    <td><img style="width: 60px ;height: 60px;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgLCEfwx8yDcF7yTxiLUwzNAWVSCAY0fQgnblUNHjYxMqVsWb8r4_zNWN0hGhcAPQGGNA&usqp=CAU"></td>
                </tr>
          <%} %>


        </table>

    </div>
    <br>
</div>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>

</html>