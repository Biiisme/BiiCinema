package AdminController;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Movie_Modal.Movie;
import Room_Modal.Room;
import Schedule_Modal.Schedule;
import Schedule_Modal.Schedule_Bo;
import User_Modal.User;

/**
 * Servlet implementation class AdminScheduleController
 */
@WebServlet("/AdminScheduleController")
public class AdminScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminScheduleController() {
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

		Schedule_Bo scheduleBo = new Schedule_Bo();

		// Hiển thị danh sách lịch chiếu
		if (request.getParameter("list") != null || request.getParameter("list") == null) {
			try {
				ArrayList<Schedule> scheduleList = scheduleBo.getAllSchedules();
				request.setAttribute("scheduleList", scheduleList);
				RequestDispatcher rd = request.getRequestDispatcher("adminSchedule.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Lỗi khi tải danh sách lịch chiếu!");
				RequestDispatcher rd = request.getRequestDispatcher("adminSchedule.jsp");
				rd.forward(request, response);
			}
			return;
		}

		// Hiển thị form thêm lịch chiếu
		if (request.getParameter("add") != null) {
			try {
				ArrayList<Room> roomList = scheduleBo.getAllRooms();
				ArrayList<Movie> movieList = scheduleBo.getAllMovies();
				request.setAttribute("roomList", roomList);
				request.setAttribute("movieList", movieList);
				RequestDispatcher rd = request.getRequestDispatcher("adminAddSchedule.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("error", "Lỗi khi tải danh sách phòng và phim!");
				RequestDispatcher rd = request.getRequestDispatcher("adminSchedule.jsp");
				rd.forward(request, response);
			}
			return;
		}

		// Hiển thị form sửa lịch chiếu
		if (request.getParameter("edit") != null) {
			String scheduleIdStr = request.getParameter("id");
			if (scheduleIdStr != null) {
				try {
					int scheduleId = Integer.parseInt(scheduleIdStr);
					Schedule schedule = scheduleBo.getScheduleById(scheduleId);
					ArrayList<Room> roomList = scheduleBo.getAllRooms();
					ArrayList<Movie> movieList = scheduleBo.getAllMovies();

					if (schedule != null) {
						request.setAttribute("schedule", schedule);
						request.setAttribute("roomList", roomList);
						request.setAttribute("movieList", movieList);
						RequestDispatcher rd = request.getRequestDispatcher("adminEditSchedule.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("error", "Không tìm thấy lịch chiếu!");
						response.sendRedirect("AdminScheduleController?list=true");
					}
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					request.setAttribute("error", "Lỗi khi tải lịch chiếu!");
					response.sendRedirect("AdminScheduleController?list=true");
				}
			}
			return;
		}

		// Xóa lịch chiếu
		if (request.getParameter("delete") != null) {
			String scheduleIdStr = request.getParameter("id");
			if (scheduleIdStr != null) {
				try {
					int scheduleId = Integer.parseInt(scheduleIdStr);
					if (scheduleBo.deleteSchedule(scheduleId)) {
						request.setAttribute("success", "Xóa lịch chiếu thành công!");
					} else {
						request.setAttribute("error", "Xóa lịch chiếu thất bại!");
					}
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					request.setAttribute("error", "Lỗi khi xóa lịch chiếu!");
				}
			}
			response.sendRedirect("AdminScheduleController?list=true");
			return;
		}

		// Mặc định hiển thị danh sách
		response.sendRedirect("AdminScheduleController?list=true");
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

		Schedule_Bo scheduleBo = new Schedule_Bo();

		// Xử lý thêm lịch chiếu
		if (request.getParameter("addSchedule") != null) {
			String phongIdStr = request.getParameter("phongId");
			String movieIdStr = request.getParameter("movieId");
			String ngayChieuStr = request.getParameter("ngayChieu");
			String gioChieu = request.getParameter("gioChieu");

			try {
				int phongId = Integer.parseInt(phongIdStr);
				int movieId = Integer.parseInt(movieIdStr);
				Date ngayChieu = parseDate(ngayChieuStr);

				if (scheduleBo.addSchedule(phongId, movieId, ngayChieu, gioChieu)) {
					request.setAttribute("success", "Thêm lịch chiếu thành công!");
				} else {
					request.setAttribute("error", "Thêm lịch chiếu thất bại!");
				}
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID phòng hoặc phim không hợp lệ!");
			} catch (ParseException e) {
				request.setAttribute("error", "Định dạng ngày không hợp lệ!");
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
			} catch (ClassNotFoundException e) {
				request.setAttribute("error", "Lỗi kết nối database!");
				e.printStackTrace();
			}

			// Reload lại form với danh sách
			try {
				ArrayList<Room> roomList = scheduleBo.getAllRooms();
				ArrayList<Movie> movieList = scheduleBo.getAllMovies();
				request.setAttribute("roomList", roomList);
				request.setAttribute("movieList", movieList);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("adminAddSchedule.jsp");
			rd.forward(request, response);
			return;
		}

		// Xử lý cập nhật lịch chiếu
		if (request.getParameter("updateSchedule") != null) {
			String scheduleIdStr = request.getParameter("scheduleId");
			String phongIdStr = request.getParameter("phongId");
			String movieIdStr = request.getParameter("movieId");
			String ngayChieuStr = request.getParameter("ngayChieu");
			String gioChieu = request.getParameter("gioChieu");

			try {
				int scheduleId = Integer.parseInt(scheduleIdStr);
				int phongId = Integer.parseInt(phongIdStr);
				int movieId = Integer.parseInt(movieIdStr);
				Date ngayChieu = parseDate(ngayChieuStr);

				if (scheduleBo.updateSchedule(scheduleId, phongId, movieId, ngayChieu, gioChieu)) {
					request.setAttribute("success", "Cập nhật lịch chiếu thành công!");
				} else {
					request.setAttribute("error", "Cập nhật lịch chiếu thất bại!");
				}
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID không hợp lệ!");
			} catch (ParseException e) {
				request.setAttribute("error", "Định dạng ngày không hợp lệ!");
			} catch (SQLException e) {
				request.setAttribute("error", e.getMessage());
			} catch (ClassNotFoundException e) {
				request.setAttribute("error", "Lỗi kết nối database!");
				e.printStackTrace();
			}

			response.sendRedirect("AdminScheduleController?list=true");
			return;
		}

		// Nếu không có action nào, redirect về danh sách
		response.sendRedirect("AdminScheduleController?list=true");
	}

	private Date parseDate(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.trim().isEmpty()) {
			throw new ParseException("Ngày không được để trống", 0);
		}
		try {
			// Parse từ format yyyy-MM-dd (HTML date input)
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			return new Date(utilDate.getTime());
		} catch (ParseException e) {
			throw new ParseException("Định dạng ngày không hợp lệ (định dạng: YYYY-MM-DD)", 0);
		}
	}
}
