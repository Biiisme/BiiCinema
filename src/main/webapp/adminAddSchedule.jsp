<%@page import="Movie_Modal.Movie"%>
<%@page import="Room_Modal.Room"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Lịch Chiếu Mới - Admin</title>
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
        .time-slots {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
            gap: 10px;
            margin-top: 10px;
        }
        .time-slot {
            padding: 8px 12px;
            border: 2px solid #e9ecef;
            border-radius: 5px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .time-slot:hover {
            border-color: #007bff;
            background-color: #e7f3ff;
        }
        .time-slot.selected {
            border-color: #007bff;
            background-color: #007bff;
            color: white;
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
                    <h2 class="mb-4">Thêm Lịch Chiếu Mới</h2>

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
                        <form class="form-horizontal" action="AdminScheduleController" method="post" id="scheduleForm">
                            <div class="row">
                                <!-- Chọn Phim -->
                                <div class="col-md-6 mb-3">
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

                                <!-- Chọn Phòng -->
                                <div class="col-md-6 mb-3">
                                    <label for="phongId" class="form-label">Chọn Phòng</label>
                                    <select class="form-control" id="phongId" name="phongId" required>
                                        <option value="">-- Chọn phòng --</option>
                                        <%
                                        ArrayList<Room> roomList = (ArrayList<Room>) request.getAttribute("roomList");
                                        if (roomList != null) {
                                            for (Room room : roomList) {
                                        %>
                                            <option value="<%= room.getPhong_ID() %>"><%= room.getTenPhong() %></option>
                                        <%
                                            }
                                        }
                                        %>
                                    </select>
                                </div>
                            </div>

                            <!-- Chọn Ngày Chiếu -->
                            <div class="mb-3">
                                <label for="ngayChieu" class="form-label">Ngày Chiếu</label>
                                <input type="date" class="form-control" id="ngayChieu" name="ngayChieu"
                                       min="<%= java.time.LocalDate.now() %>" required>
                                <div class="form-text">Chọn ngày chiếu (tối thiểu từ ngày hôm nay)</div>
                            </div>

                            <!-- Chọn Giờ Chiếu -->
                            <div class="mb-3">
                                <label class="form-label">Giờ Chiếu</label>
                                <input type="time" class="form-control" id="gioChieu" name="gioChieu" required>
                                <div class="form-text">Chọn giờ chiếu</div>
                            </div>

                            <!-- Quick Time Slots (Optional) -->
                            <div class="mb-3">
                                <label class="form-label">Giờ Chiếu Phổ Biến</label>
                                <div class="time-slots">
                                    <div class="time-slot" data-time="09:00">09:00</div>
                                    <div class="time-slot" data-time="12:00">12:00</div>
                                    <div class="time-slot" data-time="15:00">15:00</div>
                                    <div class="time-slot" data-time="18:00">18:00</div>
                                    <div class="time-slot" data-time="21:00">21:00</div>
                                </div>
                            </div>

                            <div class="d-flex gap-2">
                                <button type="submit" name="addSchedule" value="add" class="btn btn-primary">
                                    <i class="bi bi-plus-lg"></i> Thêm Lịch Chiếu
                                </button>
                                <a href="AdminScheduleController?list=true" class="btn btn-secondary">
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
        // Time slot selection
        document.querySelectorAll('.time-slot').forEach(slot => {
            slot.addEventListener('click', function() {
                // Remove selected class from all slots
                document.querySelectorAll('.time-slot').forEach(s => s.classList.remove('selected'));
                // Add selected class to clicked slot
                this.classList.add('selected');
                // Set time input value
                document.getElementById('gioChieu').value = this.dataset.time;
            });
        });

        // Update time slots when manual input changes
        document.getElementById('gioChieu').addEventListener('input', function() {
            const timeValue = this.value;
            document.querySelectorAll('.time-slot').forEach(slot => {
                if (slot.dataset.time === timeValue) {
                    slot.classList.add('selected');
                } else {
                    slot.classList.remove('selected');
                }
            });
        });

        // Form validation
        document.getElementById('scheduleForm').addEventListener('submit', function(e) {
            const movieSelect = document.getElementById('movieId');
            const roomSelect = document.getElementById('phongId');
            const ngayChieu = document.getElementById('ngayChieu');
            const gioChieu = document.getElementById('gioChieu');

            if (!movieSelect.value) {
                e.preventDefault();
                alert('Vui lòng chọn phim!');
                movieSelect.focus();
                return false;
            }

            if (!roomSelect.value) {
                e.preventDefault();
                alert('Vui lòng chọn phòng!');
                roomSelect.focus();
                return false;
            }

            if (!ngayChieu.value) {
                e.preventDefault();
                alert('Vui lòng chọn ngày chiếu!');
                ngayChieu.focus();
                return false;
            }

            if (!gioChieu.value) {
                e.preventDefault();
                alert('Vui lòng chọn giờ chiếu!');
                gioChieu.focus();
                return false;
            }

            // Check if selected date is not in the past
            const selectedDate = new Date(ngayChieu.value);
            const today = new Date();
            today.setHours(0, 0, 0, 0);

            if (selectedDate < today) {
                e.preventDefault();
                alert('Ngày chiếu không được là ngày trong quá khứ!');
                ngayChieu.focus();
                return false;
            }
        });

        // Set minimum date to today
        document.addEventListener('DOMContentLoaded', function() {
            const ngayChieuInput = document.getElementById('ngayChieu');
            const today = new Date().toISOString().split('T')[0];
            ngayChieuInput.min = today;
        });
    </script>
</body>
</html>
