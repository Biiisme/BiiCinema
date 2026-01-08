
<%@page import="java.util.List"%>
<%@page import="Movie_Modal.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
   	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="styles/style1.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Trang chủ</title>
</head>

<body>
<%--    navbar--%>
    <jsp:include page="header.jsp"/>
<!-- content -->
<div class="container" >
    <%
	List<String> dsm = new ArrayList<String>();
	dsm =(List<String>)request.getAttribute("dsphimmoi");
	int n = dsm.size();
	%>
    <!-- carousel -->
    <br>
    <br>
    <br>
    <h1>Phim Sắp Chiếu</h1>
    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- Indicators -->
        <ul class="carousel-indicators">
		    <% int index = 0; %>
		    <% for(String temp : dsm) { %>
		        <li data-target="#demo" data-slide-to="<%= index %>" class="<%= index == 0 ? "active" : "" %>"></li>
		        <% index++; %>
		    <% } %>
		</ul>

        <!-- The slideshow -->
        <div class="carousel-inner">
			    <% 
			        boolean isActive = true;
			        for(String temp : dsm){ 
			    %>
			        <div class="carousel-item <%= isActive ? "active" : "" %>">
			            <img src="<%= temp %>" alt="" width="1100" height="500">
			        </div>
			    <% 
			        isActive = false; 
			        } 
			    %>
			</div>

        <!-- Left and right controls -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
    <!-- end of carousel -->

    <!-- movie selections-->
    <br>
    <br>

	<%
	ArrayList<Movie> ds = new ArrayList<Movie>();
	ds =(ArrayList<Movie>)request.getAttribute("listSearch");
	int m = ds.size();
	%>
        <div class = 'search-box d-flex justify-content-between'>
            <h2>Chọn Phim</h2>
            <form action="HomePageController" class="search-bar" method="get">
                <input type="search" name="keysearch" required>
                <button class="search-btn" type="submit">
                    <span>Tìm</span>
                </button>
            </form>
        </div>
    <div class="d-flex justify-content-between flex-wrap">
        <%for(int i = 0;i<m;i++){ %>
            <div class="card movie-item" style="width:300px">
                <img class="card-img-top img-movie"
                     src="<%=ds.get(i).getPoster_url() %>"
                     alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title"><%=ds.get(i).getTenPhim() %></h4>
                    <p class="card-text" style="height: 155px"><%=ds.get(i).getMoTaNgan() %></p>
                    <a href="HomePageController?movieId=<%=ds.get(i).getPhim_ID() %>" class="btn btn-outline-warning"
                       style="margin-right:70px">Chi tiết</a>

                        <%--Nếu chưa đăng nhập mà đã click vào nút mua vé thì trả về trang có nút có class btn-buy-ticket-not-signed-in để
                        toggle cái form đăng nhập--%>
                    
                        
                            <a href="/branches?movieId=<%=ds.get(i).getPhim_ID() %>" class="btn btn-outline-danger">Mua vé</a>
                       

                </div>
            </div>
        <%} %>


    </div>
    <!-- end of movie selections -->
</div>



<br>
<br>
<jsp:include page="footer.jsp"/>
</body>

</html>