<%@page import="Movie_Modal.Banner"%>
<%@page import="Movie_Modal.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Banner - Admin</title>
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
            display: block;
        }
        .current-banner {
            border: 2px solid #007bff;
            padding: 10px;
            background-color: #e7f3ff;
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

                <%
                Banner banner = (Banner) request.getAttribute("banner");
                %>

                <div class="pt-3">
                    <h2 class="mb-4">Sửa Banner</h2>

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

                    <% if (banner != null) { %>
                    <div class="row">
                        <!-- Current Banner Info -->
                        <div class="col-md-4">
                            <div class="current-banner">
                                <h5>Hình Banner Hiện Tại</h5>
                                <% if (banner.getBannerUrl() != null && !banner.getBannerUrl().isEmpty()) { %>
                                    <img src="<%= banner.getBannerUrl() %>"
                                         alt="Current Banner" class="preview-image img-fluid">
                                <% } else { %>
                                    <div class="text-center text-muted">
                                        <i class="fas fa-image fa-3x"></i>
                                        <p>Không có hình</p>
                                    </div>
                                <% } %>
                                <p class="mt-2 mb-1"><strong>Phim:</strong> <%= banner.getTenPhim() %></p>
                                <p class="mb-0"><strong>ID:</strong> <%= banner.getId() %></p>
                            </div>
                        </div>

                        <!-- Edit Form -->
                        <div class="col-md-8">
                            <div class="form-container">
                                <form class="form-horizontal" action="BannerController" method="post">
                                    <input type="hidden" name="bannerId" value="<%= banner.getId() %>">

                                    <div class="mb-3">
                                        <label for="movieId" class="form-label">Chọn Phim</label>
                                        <select class="form-control" id="movieId" name="movieId" required>
                                            <option value="">-- Chọn phim --</option>
                                            <%
                                            ArrayList<Movie> movieList = (ArrayList<Movie>) request.getAttribute("movieList");
                                            if (movieList != null) {
                                                for (Movie movie : movieList) {
                                                    boolean isSelected = (movie.getMovie_id() == banner.getMovieId());
                                            %>
                                                <option value="<%= movie.getMovie_id() %>" <%= isSelected ? "selected" : "" %>>
                                                    <%= movie.getTenPhim() %>
                                                </option>
                                            <%
                                                }
                                            }
                                            %>
                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label for="bannerUrl" class="form-label">URL Hình Banner</label>
                                        <input type="url" class="form-control" id="bannerUrl" name="bannerUrl"
                                               placeholder="Nhập URL hình banner"
                                               value="<%= banner.getBannerUrl() != null ? banner.getBannerUrl() : "" %>"
                                               required>
                                        <div class="form-text">Nhập URL đầy đủ của hình banner</div>
                                    </div>

                                    <!-- Image Preview -->
                                    <div class="mb-3">
                                        <label class="form-label">Xem trước hình banner:</label>
                                        <div>
                                            <img id="previewImage" class="preview-image img-fluid" alt="Preview">
                                        </div>
                                    </div>

                                    <div class="d-flex gap-2">
                                        <button type="submit" name="updateBanner" value="update" class="btn btn-primary">
                                            <i class="bi bi-pencil"></i> Cập Nhật Banner
                                        </button>
                                        <a href="BannerController?list=true" class="btn btn-secondary">
                                            <i class="bi bi-arrow-left"></i> Quay lại
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <% } else { %>
                    <div class="alert alert-warning" role="alert">
                        <i class="fas fa-exclamation-triangle"></i>
                        Không tìm thấy thông tin banner để chỉnh sửa.
                        <a href="BannerController?list=true" class="alert-link">Quay lại danh sách</a>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JavaScript -->
    <script>
        // Set initial preview
        document.addEventListener('DOMContentLoaded', function() {
            const bannerUrlInput = document.getElementById('bannerUrl');
            const previewImage = document.getElementById('previewImage');

            if (bannerUrlInput.value) {
                previewImage.src = bannerUrlInput.value;
                previewImage.style.display = 'block';
            }
        });

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
