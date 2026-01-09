<%@page import="Movie_Modal.Banner"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Banner - Admin</title>
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
        .banner-image {
            width: 100px;
            height: 60px;
            object-fit: cover;
            border-radius: 5px;
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
                    <h2>Quản lý Banner</h2>

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

                    <!-- Add Banner Button -->
                    <div class="row mb-3">
                        <div class="col">
                            <h4>Danh sách Banner</h4>
                        </div>
                        <div class="col-auto">
                            <a href="BannerController?add=true" class="btn btn-info text-white">
                                <i class="bi bi-plus-lg"></i> Thêm Banner Mới
                            </a>
                        </div>
                    </div>

                    <!-- Banner Table -->
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Hình Banner</th>
                                    <th>Tên Phim</th>
                                    <th>URL Banner</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            ArrayList<Banner> bannerList = (ArrayList<Banner>) request.getAttribute("bannerList");
                            if (bannerList != null && !bannerList.isEmpty()) {
                                for (Banner banner : bannerList) {
                            %>
                                <tr>
                                    <td><%= banner.getId() %></td>
                                    <td>
                                        <% if (banner.getBannerUrl() != null && !banner.getBannerUrl().isEmpty()) { %>
                                            <img src="<%= banner.getBannerUrl() %>"
                                                 alt="Banner" class="banner-image">
                                        <% } else { %>
                                            <span class="text-muted">No Image</span>
                                        <% } %>
                                    </td>
                                    <td><%= banner.getTenPhim() != null ? banner.getTenPhim() : "N/A" %></td>
                                    <td>
                                        <small class="text-break">
                                            <%= banner.getBannerUrl() != null ? banner.getBannerUrl() : "N/A" %>
                                        </small>
                                    </td>
                                    <td>
                                        <a href="BannerController?edit=true&id=<%= banner.getId() %>"
                                           class="btn btn-warning btn-sm text-white me-1">
                                            <i class="bi bi-pencil"></i> Sửa
                                        </a>
                                        <a href="BannerController?delete=true&id=<%= banner.getId() %>"
                                           class="btn btn-danger btn-sm"
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa banner này?')">
                                            <i class="bi bi-trash"></i> Xóa
                                        </a>
                                    </td>
                                </tr>
                            <%
                                }
                            } else {
                            %>
                                <tr>
                                    <td colspan="5" class="text-center text-muted py-4">
                                        <i class="fas fa-image fa-2x mb-2"></i>
                                        <br>Chưa có banner nào được tạo.
                                        <br><a href="BannerController?add=true" class="btn btn-primary btn-sm mt-2">Tạo banner đầu tiên</a>
                                    </td>
                                </tr>
                            <%
                            }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
