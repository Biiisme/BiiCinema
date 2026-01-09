<%@page import="Movie_Modal.Movie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Movie</title>
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

        /* Simple image preview styles */
        #imagePreview img {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
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

 		<%	Movie mv =(Movie)request.getAttribute("Update");
			if(mv!=null){
			%> 
		 <div class="pt-3">
                    <h2 class="mb-4">Cập nhật phim</h2>
                    <div class="form-container">
                        <form class="form-horizontal" action="AddMovieController" method="post" enctype= "multipart/form-data">
                            <input type="hidden" name="movie_id" value="<%=mv.getPhim_ID() %>">
                            <div class="mb-3">
                                <label for="movieTitle" class="form-label">Tên phim</label>
                                <input type="text" class="form-control" id="movieTitle" name="TenPhim" value="<%=mv.getTenPhim() != null ? mv.getTenPhim() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieDescription" class="form-label">Mô tả ngắn</label>
                                <textarea class="form-control" id="movieDescription" name="MoTaNgan" rows="2" required><%=mv.getMoTaNgan() != null ? mv.getMoTaNgan() : "" %></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="movieDescription" class="form-label">Mô tả dài</label>
                              	 <textarea class="form-control" id="movieDescription" name="MoTaDai" rows="3" required><%=mv.getMoTaDai() != null ? mv.getMoTaDai() : "" %></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Thể loại</label>
                                <input type="text" class="form-control" id="movieGenre" name="TheLoai" value="<%=mv.getLoai() != null ? mv.getLoai() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Đạo diễn</label>
                                <input type="text" class="form-control" id="movieGenre" name="DaoDien" value="<%=mv.getDaoDien() != null ? mv.getDaoDien() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Diễn viên</label>
                                <input type="text" class="form-control" id="movieGenre" name="DienVien" value="<%=mv.getDienVien() != null ? mv.getDienVien() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Đối tượng</label>
                                <input type="text" class="form-control" id="movieGenre" name="DoiTuong" value="<%=mv.getDoiTuong() != null ? mv.getDoiTuong() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieDuration" class="form-label">Thời lượng (phút)</label>
                                <input type="number" class="form-control" id="movieDuration" name="ThoiLuong" value="<%=mv.getThoiLuong() %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieReleaseDate" class="form-label">Ngày phát hành</label>
                                <input type="date" class="form-control" id="movieReleaseDate" name="NgayPhatHanh" value="<%=mv.getNgayKhoiChieu() != null ? mv.getNgayKhoiChieu() : "" %>" required>
                            </div>
                            <div class="mb-3">
                                <label for="moviePoster" class="form-label">Poster phim</label>
                                <input type="file" class="form-control" id="moviePoster" name="Poster" accept="image/*">
                                <small class="form-text text-muted">Để trống nếu không muốn thay đổi ảnh</small>

                                <!-- Image Preview -->
                                <div id="imagePreview" class="mt-2" style="display: none;">
                                    <img id="previewImg" src="" alt="Preview" style="max-width: 200px; max-height: 200px;">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="movieTrailer" class="form-label">Link trailer</label>
                                <input type="url" class="form-control" id="movieTrailer" name="LinkTrailer" value="<%=mv.getTrailer_url() != null ? mv.getTrailer_url() : "" %>" required>
                                <div class="form-text">
                                    Nhập URL YouTube (ví dụ: https://www.youtube.com/watch?v=dQw4w9WgXcQ)<br>
                                    <small class="text-muted">Hệ thống sẽ tự động chuyển đổi thành embed URL để hiển thị trailer.</small>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Cập nhật</button>
                        </form>
                    </div>
                </div>
            </div>
		
		<%}else { %>
			
		
                <div class="pt-3">
                    <h2 class="mb-4">Thêm phim mới</h2>
                    <div class="form-container">
                        <form class="form-horizontal" action="AddMovieController" method="post" enctype= "multipart/form-data">
                            <div class="mb-3">
                                <label for="movieTitle" class="form-label">Tên phim</label>
                                <input type="text" class="form-control" id="movieTitle" name="TenPhim" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieDescription" class="form-label">Mô tả ngắn</label>
                                <textarea class="form-control" id="movieDescription" name="MoTaNgan" rows="2" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="movieDescription" class="form-label">Mô tả dài</label>
                                <textarea class="form-control" id="movieDescription" name="MoTaDai" rows="3" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Thể loại</label>
                                <input type="text" class="form-control" id="movieGenre" name="TheLoai" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Đạo diễn</label>
                                <input type="text" class="form-control" id="movieGenre" name="DaoDien" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Diễn viên</label>
                                <input type="text" class="form-control" id="movieGenre" name="DienVien" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieGenre" class="form-label">Đối tượng</label>
                                <input type="text" class="form-control" id="movieGenre" name="DoiTuong" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieDuration" class="form-label">Thời lượng (phút)</label>
                                <input type="number" class="form-control" id="movieDuration" name="ThoiLuong" required>
                            </div>
                            <div class="mb-3">
                                <label for="movieReleaseDate" class="form-label">Ngày phát hành</label>
                                <input type="date" class="form-control" id="movieReleaseDate" name="NgayPhatHanh" required>
                            </div>
                            <div class="mb-3">
                                <label for="moviePoster" class="form-label">Poster phim</label>
                                <input type="file" class="form-control" id="moviePoster" name="Poster" accept="image/*" required>

                                <!-- Image Preview -->
                                <div id="imagePreview" class="mt-2" style="display: none;">
                                    <img id="previewImg" src="" alt="Preview" style="max-width: 200px; max-height: 200px;">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="movieTrailer" class="form-label">Link trailer</label>
                                <input type="url" class="form-control" id="movieTrailer" name="LinkTrailer" required>
                                <div class="form-text">
                                    Nhập URL YouTube (ví dụ: https://www.youtube.com/watch?v=dQw4w9WgXcQ)<br>
                                    <small class="text-muted">Hệ thống sẽ tự động chuyển đổi thành embed URL để hiển thị trailer.</small>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Thêm phim</button>
                        </form>
                    </div>
                </div>
            </div>
	<%} %>
	
     </div>
     
    
    <!-- Bootstrap 5 JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Simple Image Preview Script -->
    <script>
        document.getElementById('moviePoster').addEventListener('change', function(e) {
            const file = e.target.files[0];
            const preview = document.getElementById('imagePreview');
            const previewImg = document.getElementById('previewImg');

            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    previewImg.src = e.target.result;
                    preview.style.display = 'block';
                };
                reader.readAsDataURL(file);
            } else {
                preview.style.display = 'none';
            }
        });
    </script>
</body>
</html>