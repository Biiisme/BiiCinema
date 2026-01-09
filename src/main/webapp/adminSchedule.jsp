<%@page import="Schedule_Modal.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Lịch Chiếu - Admin</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .sidebar {
            background-color: #2d3741;
            min-height: 100vh;
            color: #fff;
        }
        .sidebar .nav-link {
            color: #a7b1bc;
            padding: 0.5rem 1rem;
        }
        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            color: #fff;
            background-color: rgba(255,255,255,0.1);
        }
        .top-bar {
            background-color: #ffffff;
            padding: 10px;
            color: white;
            display: flex;
            justify-content: flex-end;
        }
        .login-btn {
            background-color: #2d3741;
            color: #fff;
            border: none;
            padding: 5px 15px;
            border-radius: 5px;
        }
        .schedule-card {
            transition: transform 0.2s;
        }
        .schedule-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <jsp:include page="layout/sidebarAdmin.jsp"/>

            <!-- Main Content -->
            <div class="col-md-12 col-lg-10 px-4">
                <!-- Header with Login Button -->
                <jsp:include page="layout/header.jsp"/>

                <div class="pt-3">
                    <h2>Quản lý Lịch Chiếu</h2>

                    <!-- Success/Error Messages -->
                    <% String success = (String) request.getAttribute("success"); %>
                    <% if (success != null) { %>
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="fas fa-check-circle"></i> <%= success %>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    <% } %>

                    <% String error = (String) request.getAttribute("error"); %>
                    <% if (error != null) { %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="fas fa-exclamation-triangle"></i> <%= error %>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    <% } %>

                    <!-- Add Schedule Button -->
                    <div class="row mb-3">
                        <div class="col">
                            <h4>Danh sách Lịch Chiếu</h4>
                        </div>
                        <div class="col-auto">
                            <a href="AdminScheduleController?add=true" class="btn btn-info text-white">
                                <i class="bi bi-plus-lg"></i> Thêm Lịch Chiếu Mới
                            </a>
                        </div>
                    </div>

                    <!-- Schedule Cards -->
                    <div class="row">
                    <%
                    ArrayList<Schedule> scheduleList = (ArrayList<Schedule>) request.getAttribute("scheduleList");
                    if (scheduleList != null && !scheduleList.isEmpty()) {
                        for (Schedule schedule : scheduleList) {
                    %>
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card schedule-card h-100">
                                <div class="card-header bg-primary text-white">
                                    <h6 class="card-title mb-0">
                                        <i class="fas fa-calendar-alt"></i> Lịch Chiếu #<%= schedule.getLichChieuID() %>
                                    </h6>
                                </div>
                                <div class="card-body">
                                    <div class="mb-2">
                                        <strong><i class="fas fa-film"></i> Phim:</strong><br>
                                        <%= schedule.getTenPhim() != null ? schedule.getTenPhim() : "N/A" %>
                                    </div>
                                    <div class="mb-2">
                                        <strong><i class="fas fa-home"></i> Phòng:</strong><br>
                                        <%= schedule.getTenPhong() != null ? schedule.getTenPhong() : "N/A" %>
                                    </div>
                                    <div class="mb-2">
                                        <strong><i class="fas fa-calendar"></i> Ngày Chiếu:</strong><br>
                                        <%= schedule.getNgayChieu() %>
                                    </div>
                                    <div class="mb-2">
                                        <strong><i class="fas fa-clock"></i> Giờ Chiếu:</strong><br>
                                        <%= schedule.getGioChieu() %>
                                    </div>
                                </div>
                                <div class="card-footer bg-light">
                                    <div class="d-flex gap-1">
                                        <a href="AdminScheduleController?edit=true&id=<%= schedule.getLichChieuID() %>"
                                           class="btn btn-warning btn-sm flex-fill">
                                            <i class="bi bi-pencil"></i> Sửa
                                        </a>
                                        <a href="AdminScheduleController?delete=true&id=<%= schedule.getLichChieuID() %>"
                                           class="btn btn-danger btn-sm flex-fill"
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa lịch chiếu này?')">
                                            <i class="bi bi-trash"></i> Xóa
                                        </a>
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
                                <h4 class="alert-heading">Không có lịch chiếu nào</h4>
                                <p>Chưa có lịch chiếu được tạo. Hãy tạo lịch chiếu đầu tiên!</p>
                                <hr>
                                <a href="AdminScheduleController?add=true" class="btn btn-primary">Tạo lịch chiếu đầu tiên</a>
                            </div>
                        </div>
                    <%
                    }
                    %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
