package AdminController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Movie_Modal.Banner;
import Movie_Modal.BannerBo;
import Movie_Modal.Movie;
import User_Modal.User;

/**
 * Servlet implementation class BannerController
 */
@WebServlet("/BannerController")
public class BannerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BannerController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("dn");

        // Kiểm tra quyền admin
        if (admin == null || !"admin".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        BannerBo bannerBo = new BannerBo();

        // Hiển thị danh sách banner
        if (request.getParameter("list") != null || request.getParameter("list") == null) {
            try {
                ArrayList<Banner> bannerList = bannerBo.getAllBanners();
                request.setAttribute("bannerList", bannerList);
                RequestDispatcher rd = request.getRequestDispatcher("adminBanner.jsp");
                rd.forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi khi tải danh sách banner!");
                RequestDispatcher rd = request.getRequestDispatcher("adminBanner.jsp");
                rd.forward(request, response);
            }
            return;
        }

        // Hiển thị form thêm banner
        if (request.getParameter("add") != null) {
            try {
                ArrayList<Movie> movieList = bannerBo.getAvailableMovies();
                request.setAttribute("movieList", movieList);
                RequestDispatcher rd = request.getRequestDispatcher("adminAddBanner.jsp");
                rd.forward(request, response);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Lỗi khi tải danh sách phim!");
                RequestDispatcher rd = request.getRequestDispatcher("adminBanner.jsp");
                rd.forward(request, response);
            }
            return;
        }

        // Hiển thị form sửa banner
        if (request.getParameter("edit") != null) {
            String bannerIdStr = request.getParameter("id");
            if (bannerIdStr != null) {
                try {
                    int bannerId = Integer.parseInt(bannerIdStr);
                    Banner banner = bannerBo.getBannerById(bannerId);
                    ArrayList<Movie> movieList = bannerBo.getAvailableMovies();

                    if (banner != null) {
                        request.setAttribute("banner", banner);
                        request.setAttribute("movieList", movieList);
                        RequestDispatcher rd = request.getRequestDispatcher("adminEditBanner.jsp");
                        rd.forward(request, response);
                    } else {
                        request.setAttribute("error", "Không tìm thấy banner!");
                        response.sendRedirect("BannerController?list=true");
                    }
                } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Lỗi khi tải banner!");
                    response.sendRedirect("BannerController?list=true");
                }
            }
            return;
        }

        // Xóa banner
        if (request.getParameter("delete") != null) {
            String bannerIdStr = request.getParameter("id");
            if (bannerIdStr != null) {
                try {
                    int bannerId = Integer.parseInt(bannerIdStr);
                    if (bannerBo.deleteBanner(bannerId)) {
                        request.setAttribute("success", "Xóa banner thành công!");
                    } else {
                        request.setAttribute("error", "Xóa banner thất bại!");
                    }
                } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Lỗi khi xóa banner!");
                }
            }
            response.sendRedirect("BannerController?list=true");
            return;
        }

        // Mặc định hiển thị danh sách
        response.sendRedirect("BannerController?list=true");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("dn");

        // Kiểm tra quyền admin
        if (admin == null || !"admin".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        BannerBo bannerBo = new BannerBo();

        // Xử lý thêm banner
        if (request.getParameter("addBanner") != null) {
            String movieIdStr = request.getParameter("movieId");
            String bannerUrl = request.getParameter("bannerUrl");

            try {
                int movieId = Integer.parseInt(movieIdStr);
                if (bannerBo.addBanner(movieId, bannerUrl)) {
                    request.setAttribute("success", "Thêm banner thành công!");
                } else {
                    request.setAttribute("error", "Thêm banner thất bại!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID phim không hợp lệ!");
            } catch (SQLException e) {
                request.setAttribute("error", e.getMessage());
            } catch (ClassNotFoundException e) {
                request.setAttribute("error", "Lỗi kết nối database!");
                e.printStackTrace();
            }

            // Reload lại form với danh sách phim
            try {
                ArrayList<Movie> movieList = bannerBo.getAvailableMovies();
                request.setAttribute("movieList", movieList);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("adminAddBanner.jsp");
            rd.forward(request, response);
            return;
        }

        // Xử lý cập nhật banner
        if (request.getParameter("updateBanner") != null) {
            String bannerIdStr = request.getParameter("bannerId");
            String movieIdStr = request.getParameter("movieId");
            String bannerUrl = request.getParameter("bannerUrl");

            try {
                int bannerId = Integer.parseInt(bannerIdStr);
                int movieId = Integer.parseInt(movieIdStr);
                if (bannerBo.updateBanner(bannerId, movieId, bannerUrl)) {
                    request.setAttribute("success", "Cập nhật banner thành công!");
                } else {
                    request.setAttribute("error", "Cập nhật banner thất bại!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID không hợp lệ!");
            } catch (SQLException e) {
                request.setAttribute("error", e.getMessage());
            } catch (ClassNotFoundException e) {
                request.setAttribute("error", "Lỗi kết nối database!");
                e.printStackTrace();
            }

            response.sendRedirect("BannerController?list=true");
            return;
        }

        // Nếu không có action nào, redirect về danh sách
        response.sendRedirect("BannerController?list=true");
    }
}
