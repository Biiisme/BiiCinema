<%@page import="Movie_Modal.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supplier Management</title>
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
                    <% String msg = (String) session.getAttribute("msg");
                       if (msg != null) { %>
                        <div class="alert alert-success"> <%= msg %> </div>
                    <% session.removeAttribute("msg"); } %>
                    <h2>Quản lý phim</h2>
                    <!-- Search and Add Section -->
                    <div class="row mb-3">
                        <div class="col">
                        <form action="AdminHomeController" class="search-bar" method="get">
                            <div class="input-group">
                                <input type="search" name="keysearch" required class="form-control" placeholder="Nhập tên phim để tìm kiếm...">
                                <button type="submit" class="btn btn-info text-white"><i class="bi bi-search"></i></button>
                            </div>
                             
                
                   
               
            </form>
                        </div>
                        <div class="col-auto">
                            <a href="AddMovieController" class="btn btn-info text-white"><i class="bi bi-plus-lg"></i> Thêm mới</a>
                        </div>
                    </div>

                    <!-- Table -->
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Tên Phim</th>
                                    
                                    <th>Đạo diễn</th>
                                    
                                    <th>Loại</th>
                                    <th>Ngày khởi chiếu</th>
                                    
                                    <th>Thời lượng</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
							ArrayList<Movie> ds = new ArrayList<Movie>();
							ds =(ArrayList<Movie>)request.getAttribute("listSearch");
							int m = ds.size();
							for(int i = 0;i<m;i++){ 
							%>
                                <tr>
                                    <td><img style="height: 60px; width: 50px;" src="<%=ds.get(i).getPoster_url() %>"></td>
                                    <td><%=ds.get(i).getTenPhim() %></td>
                                    
                                    <td><%=ds.get(i).getDaoDien() %></td>
                                   
                                    <td><%=ds.get(i).getLoai() %> </td>
                                    <td><%=ds.get(i).getNgayKhoiChieu() %></td>
                                  
                                    <td><%=ds.get(i).getThoiLuong() %></td>
                                    <td>
                                    
                                        <a href="AddMovieController?UpdateMovie=<%=ds.get(i).getPhim_ID()%>" 
                                        class="btn btn-info btn-sm text-white"><i class="bi bi-pencil "></i>
                                        </a>
                                        <a href="DeleteMovieController?Movie_ID=<%=ds.get(i).getPhim_ID()%>" 
										   class="btn btn-danger btn-sm">
									    <i class="bi bi-trash"></i>
									</a>
                                    </td>
                                </tr>
                                <%} %>
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
