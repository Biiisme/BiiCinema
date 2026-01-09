<%@page import="Movie_Modal.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Banner Mới - Admin</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .form-container {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-label {
            font-weight: bold;
        }

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
        .preview-image {
            max-width: 200px;
            max-height: 150px;
            border-radius: 5px;
            margin-top: 10px;
            display: none;
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
                    <h2 class="mb-4">Thêm Banner Mới</h2>

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

                    <div class="form-container">
                        <form class="form-horizontal" action="BannerController" method="post">
                            <div class="mb-3">
                                <label for="movieId" class="form-label">Chọn Phim</label>
                                <select class="form-control" id="movieId" name="movieId" required>
                                    <option value="">-- Chọn phim --</option>
                                    <%
                                    ArrayList<Movie> movieList = (ArrayList<Movie>) request.getAttribute("movieList");
                                    if (movieList != null) {
                                        for (Movie movie : movieList) {
                                    %>
                                        <option value="<%= movie.getMovie_id() %>"><%= movie.getTenPhim() %></option>
                                    <%
                                        }
                                    }
                                    %>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="bannerUrl" class="form-label">URL Hình Banner</label>
                                <input type="url" class="form-control" id="bannerUrl" name="bannerUrl"
                                       placeholder="Nhập URL hình banner" required>
                                <div class="form-text">Nhập URL đầy đủ của hình banner (ví dụ: https://example.com/banner.jpg)</div>
                            </div>

                            <!-- Image Preview -->
                            <div class="mb-3">
                                <label class="form-label">Xem trước hình banner:</label>
                                <div>
                                    <img id="previewImage" class="preview-image" alt="Preview">
                                </div>
                            </div>

                            <div class="d-flex gap-2">
                                <button type="submit" name="addBanner" value="add" class="btn btn-primary">
                                    <i class="bi bi-plus-lg"></i> Thêm Banner
                                </button>
                                <a href="BannerController?list=true" class="btn btn-secondary">
                                    <i class="bi bi-arrow-left"></i> Quay lại
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JavaScript -->
    <script>
        // Image preview functionality
        document.getElementById('bannerUrl').addEventListener('input', function() {
            const url = this.value;
            const previewImage = document.getElementById('previewImage');

            if (url && isValidUrl(url)) {
                previewImage.src = url;
                previewImage.style.display = 'block';
                previewImage.onerror = function() {
                    previewImage.style.display = 'none';
                    showError('Không thể tải hình ảnh từ URL này!');
                };
            } else {
                previewImage.style.display = 'none';
            }
        });

        function isValidUrl(string) {
            try {
                new URL(string);
                return true;
            } catch (_) {
                return false;
            }
        }

        function showError(message) {
            // You can implement a toast notification here
            alert(message);
        }

        // Form validation
        document.querySelector('form').addEventListener('submit', function(e) {
            const movieSelect = document.getElementById('movieId');
            const bannerUrl = document.getElementById('bannerUrl');

            if (!movieSelect.value) {
                e.preventDefault();
                alert('Vui lòng chọn phim!');
                movieSelect.focus();
                return false;
            }

            if (!bannerUrl.value) {
                e.preventDefault();
                alert('Vui lòng nhập URL banner!');
                bannerUrl.focus();
                return false;
            }

            if (!isValidUrl(bannerUrl.value)) {
                e.preventDefault();
                alert('URL không hợp lệ!');
                bannerUrl.focus();
                return false;
            }
        });
    </script>
</body>
</html>
