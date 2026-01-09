<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 100px auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-container">
            <div class="card shadow">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4">Login</h2>
                    <form action="LoginController" method="get">
                        <div class="mb-3">
                            <label for="email" class="form-label">Tên đăng nhập</label>
                            <input type="text" class="form-control" id="email" name="TenDN" placeholder="Enter email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Mật khẩu</label>
                            <input type="password" class="form-control" id="password" name="MatKhau" placeholder="Password" required>
                        </div>
                       
                        <div class="d-grid">
                            <button type="submit" name="btLogin" value="login" class="btn btn-primary">Login</button>
                        </div>
                        <% 
								if(session.getAttribute("dem")!=null){
								int dem=(int)session.getAttribute("dem");
								if(dem>=3){
								%>		
								
								<h3>Vd CAPTCHA </h3>
								<img src="simpleCaptcha.jpg" />
								<input type="text" name="answer" /><br>
								
						<%}} %>
                    </form>
                    <div class="text-center mt-3">
                        <a href="#" class="text-decoration-none">Forgot password?</a>
                    </div>
                    <div class="text-center mt-2">
                        <span class="text-muted">Chưa có tài khoản? </span>
                        <a href="RegisterController?register=true" class="text-primary fw-bold">Đăng ký ngay</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS (optional, only needed if you want to use Bootstrap's JavaScript components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>