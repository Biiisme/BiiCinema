package AdminController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User_Modal.User;
import User_Modal.UserBo;

/**
 * Servlet implementation class UpdateUserController
 */
@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserController() {
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

		// Hiển thị form cập nhật user
		if(request.getParameter("userID") != null && request.getParameter("btUpdate") == null) {
			String userID = request.getParameter("userID");
			UserBo userBo = new UserBo();

			try {
				// Lấy thông tin user để hiển thị trong form
				User user = null;
				for(User u : userBo.getUser()) {
					if(u.getMaUser() == Integer.parseInt(userID)) {
						user = u;
						break;
					}
				}

				if(user != null) {
					request.setAttribute("user", user);
					RequestDispatcher rd = request.getRequestDispatcher("adminUpdateUser.jsp");
			        rd.forward(request, response);
				} else {
					response.sendRedirect("UserHomeController");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect("UserHomeController");
			}
		}

		// Xử lý cập nhật user
		if(request.getParameter("btUpdate") != null) {
			String userID = request.getParameter("userID");
			String hoten = request.getParameter("HoTen");
			String sdt = request.getParameter("SDT");
			String email = request.getParameter("Email");
			String TenDN = request.getParameter("TenDN");
			String matkhau = request.getParameter("MatKhau");
			String role = request.getParameter("Role");

			UserBo u = new UserBo();
			User user = new User(Integer.parseInt(userID), hoten, sdt, email, TenDN, role, matkhau);

			try {
				u.updateUser(user);
				response.sendRedirect("UserHomeController");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect("UserHomeController");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
