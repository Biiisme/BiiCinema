<%@page import="History_Modal.history"%>
<%@page import="History_Modal.GroupedHistory"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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
    <div class="row">
        <%
        ArrayList<GroupedHistory> groupedHistoryList = (ArrayList<GroupedHistory>) request.getAttribute("groupedHistoryList");
        if(groupedHistoryList != null && !groupedHistoryList.isEmpty()){
            for(GroupedHistory item : groupedHistoryList){
        %>
        <div class="col-md-6 mb-4">
            <div class="card h-100 shadow-sm">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="<%= item.getPosterPhim() != null ? item.getPosterPhim() : "images/default-movie.jpg" %>"
                             class="img-fluid rounded-start" alt="<%= item.getTenPhim() %>"
                             style="height: 200px; width: 100%; object-fit: cover;">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title fw-bold"><%= item.getTenPhim() %></h5>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-calendar-alt"></i> Ngày chiếu: <%= item.getNgayChieu() %>
                                </small>
                            </div>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-clock"></i> Giờ chiếu: <%= item.getGioChieu() %>
                                </small>
                            </div>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-chair"></i> Ghế: <%= item.getDanhSachGhe() %>
                                </small>
                            </div>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-home"></i> Phòng: <%= item.getTenPhong() %>
                                </small>
                            </div>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-shopping-cart"></i> Ngày mua: <%= item.getNgayMua() %>
                                </small>
                            </div>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="fas fa-ticket-alt"></i> Số lượng: <%= item.getSoLuongVe() %> vé
                                </small>
                            </div>
                            <div class="mb-3">
                                <small class="text-muted fw-bold">
                                    <i class="fas fa-money-bill-wave"></i> Tổng tiền: <%= String.format("%,d", item.getTongTien()) %> VNĐ
                                </small>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <small class="text-primary fw-bold">Mã QR: <%= item.getMaQR() %></small>
                                </div>
                                <div>
                                    <img src="https://api.qrserver.com/v1/create-qr-code/?size=80x80&data=<%= item.getMaQR() %>"
                                         alt="QR Code" class="img-thumbnail" style="width: 60px; height: 60px;">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="col-12">
            <div class="alert alert-info text-center" role="alert">
                <h4 class="alert-heading">Không có lịch sử mua vé</h4>
                <p>Bạn chưa mua vé nào. Hãy khám phá các bộ phim đang chiếu!</p>
                <hr>
                <a href="HomePageController" class="btn btn-primary">Xem phim đang chiếu</a>
            </div>
        </div>
        <%
        }
        %>
    </div>
    <br>
</div>
<br>
<br>
<jsp:include page="footer.jsp"/>
</body>

</html>