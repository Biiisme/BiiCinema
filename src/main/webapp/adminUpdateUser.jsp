<%@page import="User_Modal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật User</title>
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
                User user = (User) request.getAttribute("user");
                %>

                <div class="pt-3">
                    <h2 class="mb-4">Cập nhật user</h2>
                    <div class="form-container">
                        <form class="form-horizontal" action="UpdateUserController" method="post" >
                            <input type="hidden" name="userID" value="<%= user.getMaUser() %>">
                            <div class="mb-3">
                                <label for="hoten" class="form-label">Họ tên</label>
                                <input type="text" class="form-control" id="hoten" name="HoTen" value="<%= user.getHoten() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="sdt" class="form-label">SĐT</label>
                                <input type="text" class="form-control" id="sdt" name="SDT" value="<%= user.getSDT() %>" required>
                            </div>
                              <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="Email" value="<%= user.getEmail() %>" required>
                            </div>
                             <div class="mb-3">
                                <label for="tendn" class="form-label">Tên đăng nhập</label>
                                <input type="text" class="form-control" id="tendn" name="TenDN" value="<%= user.getTenDN() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="matkhau" class="form-label">Mật khẩu</label>
                                <input type="password" class="form-control" id="matkhau" name="MatKhau" value="<%= user.getMatKhau() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="role" class="form-label">Quyền</label>
                                <select class="form-control" id="role" name="Role" required>
                                    <option value="customer" <%= user.getRole().equals("customer") ? "selected" : "" %>>Customer</option>
                                    <option value="admin" <%= user.getRole().equals("admin") ? "selected" : "" %>>Admin</option>
                                </select>
                            </div>

                            <button type="submit" name="btUpdate" value="update" class="btn btn-primary">Cập nhật user</button>
                            <a href="UserHomeController" class="btn btn-secondary">Hủy</a>
                        </form>
                    </div>
                </div>
            </div>


     </div>


    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
