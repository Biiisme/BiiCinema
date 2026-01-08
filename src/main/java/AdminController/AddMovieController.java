package AdminController;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Movie_Modal.Movie;
import Movie_Modal.Movie_Bo;





/**
 * Servlet implementation class AddMovieController
 */
@WebServlet("/AddMovieController")
public class AddMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            String updateMovieId = request.getParameter("UpdateMovie");
            if (updateMovieId != null) {
                handleUpdateRequest(request, response, updateMovieId);
                return;
            }

            if (request.getContentLength() <= 0) {
                forwardToPage(request, response, "adminAdd_movie.jsp");
                return;
            }

            processMovieUpload(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin và đảm bảo dữ liệu hợp lệ.");
            forwardToPage(request, response, "adminAdd_movie.jsp");
        }
    }

    private void handleUpdateRequest(HttpServletRequest request, HttpServletResponse response, String updateMovieId) throws ServletException, IOException {
        try {
            Movie_Bo mvBo = new Movie_Bo();
            Movie movie = mvBo.ChiTietphim(Integer.parseInt(updateMovieId));
            request.setAttribute("Update", movie);
            forwardToPage(request, response, "adminAdd_movie.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy thông tin phim");
        }
    }

    private void processMovieUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "images";
            System.out.println("Upload path: " + uploadPath);
            
            List<FileItem> fileItems = upload.parseRequest(request);
            Movie movie = extractMovieData(fileItems, uploadPath);
            
            Movie_Bo mvBo = new Movie_Bo();
            
            // Kiểm tra nếu có movie_id thì là update, không có thì là add
            if (movie.getPhim_ID() > -1) {
                // Update phim - nếu không có poster mới thì giữ nguyên poster cũ
                if (movie.getPoster_url() == null || movie.getPoster_url().isEmpty()) {
                    Movie existingMovie = mvBo.ChiTietphim(movie.getPhim_ID());
                    if (existingMovie != null) {
                        movie.setPoster_url(existingMovie.getPoster_url());
                    }
                }
                
                boolean success = mvBo.update(movie);
                if (success) {
                    session.setAttribute("msg", "Cập nhật phim thành công!");
                } else {
                    session.setAttribute("msg", "Cập nhật phim thất bại!");
                }
            } else {
                // Add phim mới - phải có poster
                if (movie.getPoster_url() == null || movie.getPoster_url().isEmpty()) {
                    throw new Exception("Vui lòng chọn poster cho phim!");
                }
                
                boolean success = mvBo.addMovie(movie);
                if (success) {
                    session.setAttribute("msg", "Thêm phim thành công!");
                } else {
                    session.setAttribute("msg", "Thêm phim thất bại!");
                }
            }

            response.sendRedirect("AdminHomeController");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi xử lý dữ liệu phim: " + e.getMessage());
            forwardToPage(request, response, "adminAdd_movie.jsp");
        }
    }

    private Movie extractMovieData(List<FileItem> fileItems, String uploadPath) throws Exception {
        String tenPhim = "", posterUrl = "", moTaNgan = "", moTaDai = "", daoDien = "", dienVien = "", trailerUrl = "", loai = "", ngayKhoiChieu = "", doiTuong = "";
        int thoiLuong = 0;
        String movieIdStr = "";

        for (FileItem item : fileItems) {
            if (!item.isFormField()) {
                // Xử lý file upload
                String fileName = item.getName();
                if (fileName != null && !fileName.isEmpty()) {
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    
                    // Tạo file với tên unique để tránh trùng
                    String fileExtension = "";
                    int lastDot = fileName.lastIndexOf('.');
                    if (lastDot > 0) {
                        fileExtension = fileName.substring(lastDot);
                    }
                    String uniqueFileName = System.currentTimeMillis() + fileExtension;
                    File uploadedFile = new File(uploadPath + File.separator + uniqueFileName);
                    
                    try {
                        item.write(uploadedFile);
                        posterUrl = "images/" + uniqueFileName;
                        System.out.println("UPLOAD THÀNH CÔNG: " + uploadedFile.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception("Lỗi khi upload file: " + e.getMessage());
                    }
                }
            } else {
                // Xử lý các field form
                String fieldName = item.getFieldName();
                String fieldValue = item.getString("UTF-8");
                
                switch (fieldName) {
                    case "TenPhim": tenPhim = fieldValue; break;
                    case "MoTaNgan": moTaNgan = fieldValue; break;
                    case "MoTaDai": moTaDai = fieldValue; break;
                    case "DaoDien": daoDien = fieldValue; break;
                    case "DienVien": dienVien = fieldValue; break;
                    case "LinkTrailer": 
                    case "trailer_url": trailerUrl = fieldValue; break;
                    case "TheLoai": loai = fieldValue; break;
                    case "NgayPhatHanh": ngayKhoiChieu = fieldValue; break;
                    case "DoiTuong": doiTuong = fieldValue; break;
                    case "ThoiLuong": 
                        if (fieldValue != null && !fieldValue.isEmpty()) {
                            thoiLuong = Integer.parseInt(fieldValue);
                        }
                        break;
                    case "movie_id":
                    case "Phim_ID": movieIdStr = fieldValue; break;
                }
            }
        }

        int movieId = 0;
        if (movieIdStr != null && !movieIdStr.isEmpty()) {
            try {
                movieId = Integer.parseInt(movieIdStr);
            } catch (NumberFormatException e) {
                movieId = 0;
            }
        }

        return new Movie(movieId, tenPhim, posterUrl, moTaNgan, moTaDai, daoDien, dienVien, trailerUrl, loai, doiTuong, ngayKhoiChieu, thoiLuong);
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
