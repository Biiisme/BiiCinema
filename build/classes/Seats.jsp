<%@page import="Seat_Modal.Seat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chọn Chỗ Ngồi</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .screen-container {
            perspective: 1000px;
            margin: 40px 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .screen {
            background: rgb(71, 165, 209);
            height: 70px;
            width: 70%;
            margin: 15px 0;
            transform: rotateX(-45deg);
            box-shadow: 0 3px 10px rgba(19, 120, 145, 0.7);
        }
        input.largerCheckbox {
            width: 80px;
            height: 80px;
            cursor: pointer;
        }
    </style>
</head>

<body>
<jsp:include page="header.jsp"/>
<br><br><br>
<div class="container">
    <h1>Chọn Chỗ Ngồi</h1>
</div>
<div class="screen-container">
    <h2>Màn Hình</h2>
    <div class="screen"></div>
    <br><br><br>
    <%String err = "";
    err = request.getParameter("err");
    if(err !="") { %>
    <p style="color: red"><%="Vui lòng chọn ghế ngồi" %></p>
    <%} %>
    <div class="container">
        <form action="BillController" method="get">
            <table style="width:100%">
                <tr>
                    <th></th>
                    <th>1</th>
                    <th>2</th>
                    <th>3</th>
                    <th>4</th>
                    <th>5</th>
                    <th>6</th>
                    <th>7</th>
                    <th>8</th>
                </tr>

                <%-- Danh sách ghế hàng A --%>
                <tr>
                    <th>A</th>
                    <% ArrayList<Seat> ListA = (ArrayList<Seat>)request.getAttribute("listA");
                    for(Seat item : ListA) { %>
                        <th>
                            <input type="checkbox" class="largerCheckbox" name="seats" value="<%=item.getGheID()%>" 
                                <%= item.getTrangThai() == 1 ? "checked disabled" : "" %> >
                        </th>
                    <% } %>
                </tr>

                <%-- Danh sách ghế hàng B --%>
                <tr>
                    <th>B</th>
                    <% ArrayList<Seat> ListB = (ArrayList<Seat>)request.getAttribute("listB");
                    for(Seat item : ListB) { %>
                        <th>
                            <input type="checkbox" class="largerCheckbox" name="seats" value="<%=item.getGheID()%>" 
                                <%= item.getTrangThai() == 1 ? "checked disabled" : "" %> >
                        </th>
                    <% } %>
                </tr>

                <%-- Danh sách ghế hàng C --%>
                <tr>
                    <th>C</th>
                    <% ArrayList<Seat> ListC = (ArrayList<Seat>)request.getAttribute("listC");
                    for(Seat item : ListC) { %>
                        <th>
                            <input type="checkbox" class="largerCheckbox" name="seats" value="<%=item.getGheID()%>" 
                                <%= item.getTrangThai() == 1 ? "checked disabled" : "" %> >
                        </th>
                    <% } %>
                </tr>

                <%-- Danh sách ghế hàng D --%>
                <tr>
                    <th>D</th>
                    <% ArrayList<Seat> ListD = (ArrayList<Seat>)request.getAttribute("listD");
                    for(Seat item : ListD) { %>
                        <th>
                            <input type="checkbox" class="largerCheckbox" name="seats" value="<%=item.getGheID()%>" 
                                <%= item.getTrangThai() == 1 ? "checked disabled" : "" %> >
                        </th>
                    <% } %>
                </tr>

                <%-- Danh sách ghế hàng E --%>
                <tr>
                    <th>E</th>
                    <% ArrayList<Seat> ListE = (ArrayList<Seat>)request.getAttribute("listE");
                    for(Seat item : ListE) { %>
                        <th>
                            <input type="checkbox" class="largerCheckbox" name="seats" value="<%=item.getGheID()%>" 
                                <%= item.getTrangThai() == 1 ? "checked disabled" : "" %> >
                        </th>
                    <% } %>
                </tr>
            </table>
            <br><br>
            <input type="hidden" name="PhimID" value="<%=request.getAttribute("PhimID")%>">
       		 <input type="hidden" name="RoomID" value="<%=request.getAttribute("RoomID")%>">
       		 <input type="hidden" name="startDate" value="<%=request.getAttribute("startDate")%>">
       		 <input type="hidden" name="startTime" value="<%=request.getAttribute("startTime")%>">
       		 <input type="hidden" name="LichChieuID" value="<%=request.getAttribute("LichChieuID")%>">
       		
            <input type="submit" class="btn btn-outline-danger btn-block" value="Tiếp Tục">
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
