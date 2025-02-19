package AdminController;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Movie_Modal.Movie;
import Movie_Modal.Movie_Bo;

/**
 * Servlet implementation class UpdateMovieController
 */
@WebServlet("/UpdateMovieController")
public class UpdateMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMovieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
          response.setCharacterEncoding("utf-8");
          response.setContentType("text/html; charset=utf-8");
          DiskFileItemFactory factory = new DiskFileItemFactory();
          ServletFileUpload upload = new ServletFileUpload(factory);
          String uploadPath = request.getServletContext().getRealPath("") +  File.separator +  "images";
          System.out.println(uploadPath);
          List<FileItem> fileItems;
		try {
			fileItems = upload.parseRequest(request);
		
		String tenPhim = "", posterUrl = "", moTaNgan = "", moTaDai = "", daoDien = "", dienVien = "", trailerUrl = "", loai = "", ngayKhoiChieu = "", doiTuong = "";
        int thoiLuong = 0;

        for (FileItem item : fileItems) {
            if (!item.isFormField()) {
                String fileName = item.getName();
                if (!fileName.isEmpty()) {
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    
                    File file = new File(uploadPath + File.separator + fileName);
                    if (!file.exists()) {//nếu ko có thư mục thì tạo ra
                    	file.mkdir();
					}
				            String fileImg = uploadPath + File.separator + fileName;
				            posterUrl = "images/" + fileName;
				           File file1 = new File(posterUrl);//tạo file
				            try {
				            	item.write(file1);//lưu file
				              System.out.println("UPLOAD THÀNH CÔNG...!");
				              System.out.println("Đường dẫn lưu file là: "+fileImg);
				 } catch (Exception e) {
				    e.printStackTrace();
				}
                    
                }
            } else {
                switch (item.getFieldName()) {
                    case "TenPhim": tenPhim = item.getString(); break;
                    case "MoTaNgan": moTaNgan = item.getString(); break;
                    case "MoTaDai": moTaDai = item.getString(); break;
                    case "DaoDien": daoDien = item.getString(); break;
                    case "DienVien": dienVien = item.getString(); break;
                    case "trailer_url": trailerUrl = item.getString(); break;
                    case "TheLoai": loai = item.getString(); break;
                    case "NgayPhatHanh": ngayKhoiChieu = item.getString(); break;
                    case "DoiTuong": doiTuong = item.getString(); break;
                    case "ThoiLuong": thoiLuong = Integer.parseInt(item.getString()); break;
                }
            }
        }
          Movie movie;
		try {
			movie = extractMovieData(fileItems, uploadPath);
			Movie_Bo mvBo = new Movie_Bo();
			mvBo.update(movie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          

          response.sendRedirect("AdminHomeController");
	}
	private Movie extractMovieData(List<FileItem> fileItems, String uploadPath) throws Exception {
        String tenPhim = "", posterUrl = "", moTaNgan = "", moTaDai = "", daoDien = "", dienVien = "", trailerUrl = "", loai = "", ngayKhoiChieu = "", doiTuong = "";
        int thoiLuong = 0;

        for (FileItem item : fileItems) {
            if (!item.isFormField()) {
                String fileName = item.getName();
                if (!fileName.isEmpty()) {
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    
                    File file = new File(uploadPath + File.separator + fileName);
                    if (!file.exists()) {//nếu ko có thư mục thì tạo ra
                    	file.mkdir();
					}
				            String fileImg = uploadPath + File.separator + fileName;
				            posterUrl = "images/" + fileName;
				           File file1 = new File(posterUrl);//tạo file
				            try {
				            	item.write(file1);//lưu file
				              System.out.println("UPLOAD THÀNH CÔNG...!");
				              System.out.println("Đường dẫn lưu file là: "+fileImg);
				 } catch (Exception e) {
				    e.printStackTrace();
				}
                    
                }
            } else {
                switch (item.getFieldName()) {
                    case "TenPhim": tenPhim = item.getString(); break;
                    case "MoTaNgan": moTaNgan = item.getString(); break;
                    case "MoTaDai": moTaDai = item.getString(); break;
                    case "DaoDien": daoDien = item.getString(); break;
                    case "DienVien": dienVien = item.getString(); break;
                    case "trailer_url": trailerUrl = item.getString(); break;
                    case "TheLoai": loai = item.getString(); break;
                    case "NgayPhatHanh": ngayKhoiChieu = item.getString(); break;
                    case "DoiTuong": doiTuong = item.getString(); break;
                    case "ThoiLuong": thoiLuong = Integer.parseInt(item.getString()); break;
                }
            }
        }

        return new Movie(1, tenPhim, posterUrl, moTaNgan, moTaDai, daoDien, dienVien, trailerUrl, loai, doiTuong, ngayKhoiChieu, thoiLuong);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
