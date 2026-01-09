<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Tài Khoản - Cinestar</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .register-container {
            max-width: 500px;
            margin: 50px auto;
        }

        .register-card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .register-header {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }

        .register-header h2 {
            margin: 0;
            font-weight: 600;
        }

        .register-body {
            padding: 40px;
        }

        .form-floating {
            margin-bottom: 20px;
        }

        .form-control {
            border: 2px solid #e9ecef;
            border-radius: 10px;
            padding: 12px 15px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: #4facfe;
            box-shadow: 0 0 0 0.2rem rgba(79, 172, 254, 0.25);
        }

        .btn-register {
            background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
            border: none;
            border-radius: 10px;
            padding: 12px 30px;
            font-size: 18px;
            font-weight: 600;
            color: white;
            width: 100%;
            transition: all 0.3s ease;
        }

        .btn-register:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(79, 172, 254, 0.4);
        }

        .login-link {
            text-align: center;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #e9ecef;
        }

        .login-link a {
            color: #4facfe;
            text-decoration: none;
            font-weight: 500;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        .alert {
            border-radius: 10px;
            border: none;
        }

        .captcha-section {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
        }

        .captcha-section h5 {
            color: #495057;
            margin-bottom: 15px;
        }

        .input-group-text {
            background: white;
            border: 2px solid #e9ecef;
            border-right: none;
        }

        .password-strength {
            font-size: 12px;
            margin-top: 5px;
        }

        .strength-weak { color: #dc3545; }
        .strength-medium { color: #ffc107; }
        .strength-strong { color: #28a745; }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="HomePageController">
                <i class="fas fa-film"></i> Cinestar
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="LoginController?login=true">
                    <i class="fas fa-sign-in-alt"></i> Đăng Nhập
                </a>
            </div>
        </div>
    </nav>

    <div class="container register-container">
        <div class="register-card">
            <div class="register-header">
                <i class="fas fa-user-plus fa-3x mb-3"></i>
                <h2>Đăng Ký Tài Khoản</h2>
                <p class="mb-0">Tham gia cùng Cinestar để trải nghiệm xem phim tuyệt vời</p>
            </div>

            <div class="register-body">
                <!-- Error/Success Messages -->
                <% String error = (String) request.getAttribute("error"); %>
                <% if (error != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <i class="fas fa-exclamation-triangle"></i>
                        <%= error %>
                    </div>
                <% } %>

                <% String success = (String) request.getAttribute("success"); %>
                <% if (success != null) { %>
                    <div class="alert alert-success" role="alert">
                        <i class="fas fa-check-circle"></i>
                        <%= success %>
                    </div>
                <% } %>

                <form action="RegisterController" method="post" id="registerForm">
                    <!-- Họ Tên -->
                    <div class="form-floating">
                        <input type="text" class="form-control" id="hoTen" name="HoTen"
                               placeholder="Nhập họ tên đầy đủ" required>
                        <label for="hoTen">
                            <i class="fas fa-user"></i> Họ Tên
                        </label>
                    </div>

                    <!-- Số Điện Thoại -->
                    <div class="form-floating">
                        <input type="tel" class="form-control" id="sdt" name="SDT"
                               placeholder="Nhập số điện thoại" required>
                        <label for="sdt">
                            <i class="fas fa-phone"></i> Số Điện Thoại
                        </label>
                    </div>

                    <!-- Email -->
                    <div class="form-floating">
                        <input type="email" class="form-control" id="email" name="Email"
                               placeholder="Nhập địa chỉ email" required>
                        <label for="email">
                            <i class="fas fa-envelope"></i> Email
                        </label>
                    </div>

                    <!-- Tên Đăng Nhập -->
                    <div class="form-floating">
                        <input type="text" class="form-control" id="tenDN" name="TenDN"
                               placeholder="Chọn tên đăng nhập" required>
                        <label for="tenDN">
                            <i class="fas fa-at"></i> Tên Đăng Nhập
                        </label>
                    </div>

                    <!-- Mật Khẩu -->
                    <div class="form-floating">
                        <input type="password" class="form-control" id="matKhau" name="MatKhau"
                               placeholder="Nhập mật khẩu" required>
                        <label for="matKhau">
                            <i class="fas fa-lock"></i> Mật Khẩu
                        </label>
                        <div id="passwordStrength" class="password-strength"></div>
                    </div>

                    <!-- Xác Nhận Mật Khẩu -->
                    <div class="form-floating">
                        <input type="password" class="form-control" id="confirmMatKhau" name="ConfirmMatKhau"
                               placeholder="Nhập lại mật khẩu" required>
                        <label for="confirmMatKhau">
                            <i class="fas fa-lock"></i> Xác Nhận Mật Khẩu
                        </label>
                    </div>

                    <!-- Captcha Section -->
                    <div class="captcha-section">
                        <h5><i class="fas fa-shield-alt"></i> Xác Minh Bảo Mật</h5>
                        <img src="simpleCaptcha.jpg" alt="CAPTCHA" class="img-fluid mb-3" style="max-width: 200px;"/>
                        <div class="form-floating">
                            <input type="text" class="form-control" id="captcha" name="answer"
                                   placeholder="Nhập mã captcha" required>
                            <label for="captcha">Nhập mã hiển thị ở trên</label>
                        </div>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" name="btRegister" value="register" class="btn btn-register">
                        <i class="fas fa-user-plus"></i> Đăng Ký Ngay
                    </button>
                </form>

                <!-- Login Link -->
                <div class="login-link">
                    <p class="mb-0">Đã có tài khoản?
                        <a href="LoginController?login=true">Đăng nhập ngay</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JavaScript -->
    <script>
        // Password strength checker
        document.getElementById('matKhau').addEventListener('input', function() {
            const password = this.value;
            const strengthIndicator = document.getElementById('passwordStrength');
            let strength = 0;
            let feedback = [];

            if (password.length >= 8) strength++;
            else feedback.push('ít nhất 8 ký tự');

            if (/[a-z]/.test(password)) strength++;
            else feedback.push('chữ thường');

            if (/[A-Z]/.test(password)) strength++;
            else feedback.push('chữ hoa');

            if (/[0-9]/.test(password)) strength++;
            else feedback.push('số');

            if (/[^A-Za-z0-9]/.test(password)) strength++;
            else feedback.push('ký tự đặc biệt');

            switch(strength) {
                case 0:
                case 1:
                    strengthIndicator.innerHTML = '<span class="strength-weak">Yếu</span>';
                    break;
                case 2:
                case 3:
                    strengthIndicator.innerHTML = '<span class="strength-medium">Trung bình</span>';
                    break;
                case 4:
                case 5:
                    strengthIndicator.innerHTML = '<span class="strength-strong">Mạnh</span>';
                    break;
            }
        });

        // Form validation
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            const matKhau = document.getElementById('matKhau').value;
            const confirmMatKhau = document.getElementById('confirmMatKhau').value;

            if (matKhau !== confirmMatKhau) {
                e.preventDefault();
                alert('Mật khẩu xác nhận không khớp!');
                return false;
            }

            if (matKhau.length < 6) {
                e.preventDefault();
                alert('Mật khẩu phải ít nhất 6 ký tự!');
                return false;
            }
        });

        // Phone number validation
        document.getElementById('sdt').addEventListener('input', function() {
            this.value = this.value.replace(/[^0-9]/g, '');
            if (this.value.length > 11) {
                this.value = this.value.slice(0, 11);
            }
        });

        // Username validation
        document.getElementById('tenDN').addEventListener('input', function() {
            this.value = this.value.replace(/[^a-zA-Z0-9_]/g, '');
        });
    </script>
</body>
</html>
